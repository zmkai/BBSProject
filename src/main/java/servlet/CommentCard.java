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
 * Servlet implementation class CommentCard
 */
public class CommentCard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		DaoService service = new DaoService();
		char stopmark = (Character) reqparams.get("stopmark");
		if (stopmark=='1') {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "用户您已被禁言", null);
		}else {
			if(service.insertComment(reqparams)) {
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "评论成功", null);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "评论失败", null);
			}
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
