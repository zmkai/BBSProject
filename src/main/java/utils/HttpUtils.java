package utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HttpUtils {
	public static Map<String,Object> RequestToMap(HttpServletRequest request){
		System.out.println("request的编码："+request.getContentType());
		Map<String, Object> reqParams = new HashMap<String, Object>();
		if("GET".equals(request.getMethod())){
			//获取get请求参数数据
			String paramString = request.getQueryString().trim();
			if(paramString==null) {
				return null;
			}
			//解析数据
			String params[] = paramString.split("&");
			for (String string : params) {
				String key = string.split("=")[0];
				String value = string.split("=")[1];
				reqParams.put(key, value);
			}
		}else if("POST".equals(request.getMethod())){
			//获取post请求参数
			Enumeration<String> keys = request.getParameterNames();
			//解析post请求参数
			while(keys.hasMoreElements()) {
				String key = keys.nextElement();
				//System.out.println(key);
				String value = request.getParameter(key);
//				System.out.println(value);
				reqParams.put(key, value);
			}
		}
		return reqParams;
	}
}
