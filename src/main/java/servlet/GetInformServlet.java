package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.CardContent;
import pojo.Inform;
import service.DaoService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class GetInformServlet
 */
public class GetInformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		Map<String, Object> reqParams = HttpUtils.RequestToMap(request);
		if(reqParams!=null) {
			if(reqParams.get("account")!=null) {
				String account = (String) reqParams.get("account");
				List<Inform> informs = service.queryInfom(account);
				if(informs!=null&&informs.size()>0) {
					result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "返回数据成功", informs);
				}else {
					result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "当前无数据", null);
				}
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少account参数", null);
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "数据传输错误", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
