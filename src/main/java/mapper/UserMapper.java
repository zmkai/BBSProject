package mapper;

import java.util.List;
import java.util.Map;

import pojo.CardContent;
import pojo.Inform;
import pojo.User;

public interface UserMapper {
	//插入用户
	public void insertUser(User user) throws Exception;
	//用户登录
	public User userLogin(Map<String, Object> params) throws Exception;
	//查看一条帖子的信息
	public CardContent queryCardInfo(int cardId) throws Exception;
	//禁言用户
	public void stopUser(String account) throws Exception;
	//用户获取通过
	public List<Inform> queryInfom(String account) throws Exception;
	
}
