package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.Card;
import service.DaoService;
import utils.BeanUtils;
import utils.ConstValue;
import utils.JsonUtils;

/**
 * Servlet implementation class PublicCardServlet
 */
public class PublicCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		Card card = new Card() ;
		try {
			card = BeanUtils.requestToBean(request, Card.class);
		} catch (InstantiationException e) {
			result  = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "封装参数到bean出现错误", null);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
		} catch (IllegalAccessException e) {
			result  = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "程序出现未知错误", null);
			response.setCharacterEncoding("utf-8");
			response.getWriter().write(result);
		}
		if(service.insertCard(card)) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "发表成功", null);
		}else {
			result =  JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "发表失败", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
