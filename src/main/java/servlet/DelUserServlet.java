package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AdminerService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class DelUserServlet
 */
public class DelUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

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
			if (account!=null) {
				if(service.delUser(account)) {
					result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "删除成功", null);
				}else {
					result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "删除失败", null);
				}
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "对不起，你不是管理员，无法进行该操作", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
