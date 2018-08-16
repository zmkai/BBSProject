package service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.AdminerMapper;
import pojo.User;

public class AdminerService {
	private SqlSessionFactory sqlSessionFactory;
	public AdminerService() {
		try {
			InputStream inputStream = Resources.getResourceAsStream("config/SqlSessionConfig.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//删除用户，仅限管理员操作
	public boolean delUser(String account) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession(true);
		AdminerMapper adminerMapper = session.getMapper(AdminerMapper.class);
		try {
			adminerMapper.delUser(account);
			return flog;
		}catch (Exception e) {
			flog = false;
			return flog;
		}
	}
	
	//查询所有用户信息，仅限管理员操作
	public List<User> queryAllUsers() {
		SqlSession session = sqlSessionFactory.openSession(true);
		AdminerMapper adminerMapper = session.getMapper(AdminerMapper.class);
		try {
			List<User> users = adminerMapper.queryAllUsers();
			return users;
		}catch (Exception e) {
			return null;
		}
	}
	
	//删除用户，仅限管理员操作
	public User queryOneUser(String account) {
		SqlSession session = sqlSessionFactory.openSession();
		AdminerMapper adminerMapper = session.getMapper(AdminerMapper.class);
		try {
			User user = adminerMapper.queryOneUser(account);
			return user;
		}catch (Exception e) {
			return null;
		}
	}
	
	//发送审核通知，仅限管理员操作
	public boolean sendInform(Map<String, Object> params) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession();
		AdminerMapper adminerMapper = session.getMapper(AdminerMapper.class);
		try {
			adminerMapper.sendInform(params);
			adminerMapper.updateCheck(params);
			session.commit();
			return flog;
		}catch (Exception e) {
			flog = false;
			return flog;
		}
	}
}
