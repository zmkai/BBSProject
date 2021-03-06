package servlet;

import java.io.IOException;
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
 * Servlet implementation class QueryOneUserServlet
 */
public class QueryOneUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		AdminerService service = new AdminerService();
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		char role = (Character) reqparams.get("role");
		if(role!=1) {
			String account = (String) reqparams.get("account");
			if(account!=null) {
				User user = service.queryOneUser(account);
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "返回数据成功", user);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "当前无数据", null);
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "对不起，你不是管理员，无法进行该操作", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
