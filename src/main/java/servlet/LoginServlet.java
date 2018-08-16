package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import service.DaoService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class loginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//用户登录接口
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		DaoService service = new DaoService();
		User user = new User();
		if(reqparams!=null) {
			try {
				user = service.userLogin(reqparams);
				
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "用户登录成功", user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "账户或者密码错误", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
