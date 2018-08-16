package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Typic;
import service.DaoService;
import utils.ConstValue;
import utils.JsonUtils;

/**
 * Servlet implementation class TypicServlet
 */
public class TypicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		try {
			List<Typic> topics = service.findTypic();
			if(topics!=null&&topics.size()>0) {
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "返回数据", topics);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "当前无数据", null);
			}
		}catch (Exception e) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "出现未知错误", null);
			e.printStackTrace();
		}finally {
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
