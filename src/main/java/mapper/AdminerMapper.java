package mapper;

import java.util.List;
import java.util.Map;

import pojo.User;

public interface AdminerMapper {
	//删除用户，仅限管理员操作
	public void delUser(String account) throws Exception;
	
	//查询所有用户信息，仅限管理员操作
	public List<User> queryAllUsers() throws Exception;
	
	//查询某一个用户的信息，仅限管理员操作
	public User queryOneUser(String account) throws Exception;
	
	//存入审核通知
	public void sendInform(Map<String, Object> params) throws Exception;
	
	//更新帖子审核结果
	public void updateCheck(Map<String, Object> params) throws Exception;
	
}
