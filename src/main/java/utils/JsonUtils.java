package utils;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.User;


/**
 * @discription json工具类
 * @author 周梦凯
 * @date 2018/4/2
 */
public class JsonUtils {
	public static void main(String[] args) {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("account", "123456");
		map.put("password", "1234566");
		map.put("nick", "tina");
		map.put("role", '0');
		map.put("sex", '1');
		map.put("uemail", "1937915896@qq.com");
		map.put("signature", "this is my world");
		map.put("tel", "15940593802");
		
		User user = new User();
		user.setAccount("123455");
		user.setDelete_mark('0');
		user.setNick("LISA");
		user.setPassword("11111");
		user.setRole('0');
		user.setSex('1');
		user.setSignature("I am the only man");
		user.setStopmark('0');
		user.setTel("13940497605");
		user.setUemail("1937915896@qq.com");
		
		List<Object> list = new ArrayList<Object>();
		list.add(map);
		JsonResponse(0, "successful", null);
	}
	
	//将结果转换成json格式的字符串
	public static String JsonResponse(int code,String msg,Object data) {
		JSONObject object = new JSONObject();
		object.put("code", code);
		object.put("msg", msg);
		if(data!=null) {
			//判断data是否为map类型，如果是则按照map的方式进行json封装
			if(Map.class.isInstance(data)) {
				object.put("data", data);
			//判断data是否为list类型，如果是按照list的方式进行json封装
			}else if(List.class.isInstance(data)) {
				JSONArray array = JSONArray.fromObject(data);
				object.put("data", array);
			}else {
				//其他格式，比如单个对象
				object.put("data", data);
			}
		}else {
			//如果data为null，则不存在data字段
			object.put("data", null);
		}
		//System.out.println(object);
		return object.toString();		
	}
}
