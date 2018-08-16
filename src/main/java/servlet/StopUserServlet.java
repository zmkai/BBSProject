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
 * Servlet implementation class StopUserServlet
 */
public class StopUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = "";
		DaoService service = new DaoService();
		Map<String, Object> reqParams = HttpUtils.RequestToMap(request);
		if(reqParams!=null) {
			if(reqParams.get("account")!=null) {
				String account = (String)reqParams.get("account");
				if(service.stopUser(account)) {
					result = JsonUtils.JsonResponse(ConstValue.CODE_SUCCESS, "用户禁言成功", null);
				}else {
					result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "用户禁言失败", null);
				}
			}else {
				result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "缺少account参数", null);
			}
		}else {
			result = JsonUtils.JsonResponse(ConstValue.CODE_FAIL, "数据传输错误", null);
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
