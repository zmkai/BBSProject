package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.User;
import service.DaoService;
import utils.BeanUtils;
import utils.ConstValue;
import utils.JsonUtils;

/**
 * Servlet implementation class regedit
 */
public class RegeditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//用户注册接口实现
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		User user = new User();
		try {
			//将request中的请求参数
			user = BeanUtils.requestToBean(request, User.class);
		} catch (InstantiationException e) {
			System.out.println("创建对象错误");
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println("不合法的访问");
			e.printStackTrace();
		}
		DaoService daoService = new DaoService();
		if(daoService.insertUserService(user)) {
			result=JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "注册成功", null);
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "注册失败", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		
	}

}
