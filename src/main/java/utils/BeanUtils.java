package utils;

import java.lang.reflect.Field;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import pojo.User;

/**
 * @description bean相关的工具类
 * @author 周梦凯
 * @date 2018/4/2
 *
 */
public class BeanUtils {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		requestToBean(null, User.class);
	}
	//将请求参数封装到一个bean中
	public static <T> T  requestToBean(HttpServletRequest request,Class<T> clazz) throws InstantiationException, IllegalAccessException{
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("account", "123456");
//		map.put("password", "1234566");
//		map.put("nick", "tina");
//		map.put("role", '0');
//		map.put("sex", '1');
//		map.put("uemail", "1937915896@qq.com");
//		map.put("signature", "this is my world");
//		map.put("tel", "15940593802");
		Map<String, Object> reqMap = HttpUtils.RequestToMap(request);
		T object =  clazz.newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//确保能够访问到类中的私有属性
			field.setAccessible(true);
			if(reqMap.get(field.getName())!=null) {
				field.set(object, reqMap.get(field.getName()));
			}
		}
		if(object==null) {
			return null;
		}else {
			return object;
		}
	}
}
