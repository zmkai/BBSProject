package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import service.AdminerService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class SendInformServlet
 */
public class SendInformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		AdminerService service = new AdminerService();
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		if(reqparams==null) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少参数", null);
			
		}else {
			for ( String key : reqparams.keySet()) {
				if(reqparams.get(key)!=null) {
					result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少"+key+"参数", null);
					response.setCharacterEncoding("utf-8");
					response.getWriter().write(result);
				}
			}
			
			if(service.sendInform(reqparams)) {
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "发布成功", null);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "发布失败", null);
			}
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	
	}

}
