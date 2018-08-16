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
 * Servlet implementation class IsRepeat
 */
public class IsRepeatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsRepeatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		DaoService service = new DaoService();
		Map<String, Object> reqparams = HttpUtils.RequestToMap(request);
		//采用utf-8格式
		response.setCharacterEncoding("utf-8");
		if(reqparams!=null) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少参数", null);
			response.getWriter().write(result);
		}
		if(reqparams.get("author")==null) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少author参数", null);
			response.getWriter().write(result);
		}
		if(reqparams.get("title")==null) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少title参数", null);
			response.getWriter().write(result);
		}
		int count = service.findCardRepeat(reqparams);
		if(count > 0) {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "你已发表过该帖子", null);
			
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "你可以发表该帖子", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

}
