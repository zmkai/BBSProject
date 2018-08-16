package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.CardContent;
import pojo.Comment;
import service.DaoService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class ShowCardServlet
 */
public class ShowCardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		Map<String, Object> reqParams = HttpUtils.RequestToMap(request);
		if(reqParams!=null) {
			if(reqParams.get("cardId")!=null) {
				Integer cardId = (Integer)reqParams.get("cardId");
				List<Comment> list = service.queryCommentByCardId(cardId);
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "返回数据成功", list);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少cardId参数", null);
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "数据传输错误", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
