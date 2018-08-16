package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.DaoService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class DelCard
 */
public class DelCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		int cardId = (Integer) reqparams.get("cardId");
		if(service.delCardById(cardId)) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "帖子删除成功", null);
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "帖子删除失败", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
