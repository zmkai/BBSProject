package service;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import mapper.CardMapper;
import mapper.CommentMapper;
import mapper.TypicMapper;
import mapper.UserMapper;
import pojo.Card;
import pojo.CardContent;
import pojo.CardExpand;
import pojo.Comment;
import pojo.Inform;
import pojo.Typic;
import pojo.User;

public class DaoService {
	private SqlSessionFactory sqlSessionFactory;
	public DaoService() {
		try {
			InputStream inputStream = Resources.getResourceAsStream("config/SqlSessionConfig.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//插入一个用户信息
	/*
	 * flog为true，表示操作成功，否则失败
	 */
	@SuppressWarnings("finally")
	public boolean insertUserService(User user) {
		boolean flog = true;
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
		try {
			userMapper.insertUser(user);
			//sqlSession.commit();
		}catch (Exception e) {
			flog = false;
		}finally {
			sqlSession.close();
			return flog;
		}
	}
	//用户登录
	/*
	 * 返回查询到的数据
	 */
	public User userLogin(Map<String, Object> params) {
		SqlSession session = sqlSessionFactory.openSession(true);
		UserMapper userMapper =session.getMapper(UserMapper.class);
		User user = new User();
		try {
			user = userMapper.userLogin(params);
		} catch (Exception e) {
			return null;
		}
		session.close();
		return user;
	}
	//主题表的查询
	/*
	 * 返回查询到的主题表的数据
	 */
	public List<Typic> findTypic(){
		SqlSession session = sqlSessionFactory.openSession(true);
		TypicMapper topicMapper = session.getMapper(TypicMapper.class);
		List<Typic> topics = topicMapper.findTypic();
		session.close();
		return topics;
	}
	
	@SuppressWarnings("finally")
	/*
	 * 返回boolean值，为true表明查找成功，否则失败
	 */
	public boolean insertCard(Card card) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession();
		CardMapper cardMapper = session.getMapper(CardMapper.class);
		try {
			cardMapper.insertCard(card);
			session.commit();
		}catch (Exception e) {
			e.printStackTrace();
			flog = false;
		}finally {
			session.close();
			return flog;
		}
	}
	
	//判断帖子是否重复
	/*
	 * 返回帖子数量，如果大于0说明已经存在了
	 */
	@SuppressWarnings("finally")
	public int findCardRepeat(Map<String, Object> params){
		int count = 0;
		try {
			SqlSession session = sqlSessionFactory.openSession();
			CardMapper cardMapper = session.getMapper(CardMapper.class);
			count = cardMapper.findCardRepeat(params);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			return count;
		}
	}
	//查找用户下被审核通过的帖子
	public List<CardExpand> queryCheckList(String author){
		SqlSession session = sqlSessionFactory.openSession();
		CardMapper cardMapper = session.getMapper(CardMapper.class);
		try {
			List<CardExpand> cardList = cardMapper.queryCheckList(author);
			return cardList;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//删除帖子
	@SuppressWarnings("finally")
	public boolean delCardById(int cardId) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession(true);
		try {
			CardMapper cardMapper = session.getMapper(CardMapper.class);
			cardMapper.delCardById(cardId);
		}catch (Exception e) {
			//e.printStackTrace();
			flog = false;
		}finally {
			return flog;
		}
	}
	//查找帖子内容
	public CardContent queryCardInfo(int cardId) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		CardContent cardContent = new CardContent();
		try {
			cardContent = userMapper.queryCardInfo(cardId);
			return cardContent;
		} catch (Exception e) {
			return null;
		}
	}
	//插入帖子评论
	public boolean insertComment(Map<String, Object> params) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession(true);
		CardMapper cardMapper = session.getMapper(CardMapper.class);
		try {
			cardMapper.insertComment(params);
		}catch (Exception e) {
			flog = false;
			System.out.println("程序出现错误");
		}
		return flog;
	}
	
	//查找评论的内容
	public List<Comment> queryCommentByCardId(int cardId) {
		SqlSession session = sqlSessionFactory.openSession(true);
		CommentMapper commentMapper = session.getMapper(CommentMapper.class);
		try {
			List<Comment> comments = commentMapper.queryCommentByCardId(cardId);
			return comments;
		}catch (Exception e) {
			return null;
		}
	}
	
	public boolean stopUser(String account) {
		boolean flog = true;
		SqlSession session = sqlSessionFactory.openSession(true);
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			userMapper.stopUser(account);
		}catch (Exception e) {
			flog = false;
			return flog;
		}
		return flog;
	}
	
	//查看通知
	public List<Inform> queryInfom(String account) {
		SqlSession session = sqlSessionFactory.openSession();
		UserMapper userMapper = session.getMapper(UserMapper.class);
		try {
			List<Inform> informs = userMapper.queryInfom(account);
			return informs;
		} catch (Exception e) {
			return null;
		}
	}
	
}
