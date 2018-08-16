package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pojo.CardExpand;
import service.DaoService;
import utils.ConstValue;
import utils.HttpUtils;
import utils.JsonUtils;

/**
 * Servlet implementation class QueryMyCards
 */
public class QueryMyCards extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	//查找用户名下被审核通过的帖子
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		List<CardExpand> cardList = new ArrayList<CardExpand>();
		Map<String,Object> reqParams = HttpUtils.RequestToMap(request);
		if(reqParams!=null) {
			String author = (String) reqParams.get("author");
			if(author!=null) {
				cardList = service.queryCheckList(author);
				result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "返回数据成功", cardList);
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少author参数", null);
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少参数", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
