package test;


import java.awt.print.Paper;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.javassist.compiler.ast.NewExpr;
import org.junit.BeforeClass;

import pojo.Card;
import pojo.CardContent;
import pojo.CardExpand;
import pojo.Comment;
import pojo.Inform;
import pojo.Typic;
import pojo.User;
import service.AdminerService;
import service.DaoService;

public class Test {
	static private DaoService service;
	static private AdminerService adminerService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = new DaoService();
		adminerService = new AdminerService();
	}
	public static void main(String[] args) {
		String aString = "ab";
		String cString = "ab";
		System.out.println(aString.equals(cString));
		System.out.println(aString==cString);

	}
	@org.junit.Test
	//测试注册用户
	public void insertUser(){
		User user = new User();
		user.setAccount("1234555");
		user.setDelete_mark('0');
		user.setNick("LISA");
		user.setPassword("11111");
		user.setRole('0');
		user.setSex('1');
		user.setSignature("I am the only man");
		user.setStopmark('0');
		user.setTel("13940497605");
		user.setUemail("1937915896@qq.com");
		service.insertUserService(user);
		System.out.println("successful");
	}
	@org.junit.Test
	//测试获取主题
	public void TopicMapper(){
		List<Typic> topics =service.findTypic();
		System.out.println(topics);
		System.out.println("successful");
	}
	
	@org.junit.Test
	//测试用户登录
	public void userLogin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", "195140040");
		map.put("password", "123456");
		User user = service.userLogin(map);
		System.out.println(user);
	}
	
	@org.junit.Test
	//测试发表帖子
	public void CardMapper() {
		Card card = new Card();
		card.setAuthor("195140040");
		card.setCardId(11);
		card.setContent("世界总是那么的美好啊");
		card.setPublicTime(new Date());
		card.setTitle("世界运动校园");
		card.setTypicId(1);
		service.insertCard(card);
		System.out.println("successfull");
	}
	
	@org.junit.Test
	public void testFinally() {
		int count = 0;
		String string = null;
		try {
			System.out.println(string.length());
			count ++;
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println(count);
		}
	}
	
	@org.junit.Test
	public void findCardRepeat() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "sport1");
		map.put("author", "195140040");
		System.out.println(service.findCardRepeat(map));
	}
	@org.junit.Test
	public void queryCheckList() {
		String author = "195140040";
		List<CardExpand> cardList = service.queryCheckList(author);
		System.out.println("successful");
	}
	
	@org.junit.Test
	public void delCardById() {
		int cardId = 9;
		service.delCardById(cardId);
		System.out.println("successful");
	}
	
	@org.junit.Test
	public void queryCardInfo() {
		int cardId = 1;
		CardContent cardContent =service.queryCardInfo(cardId);
		System.out.println("successful");
	}
	
	@org.junit.Test
	public void insertComment() {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("account", "195140040");
		params.put("cardId", 1);
		params.put("content", "天气很好啊");
		params.put("submittime", new Date());
		service.insertComment(params);
		System.out.println("successful");
		
	}
	
	@org.junit.Test
	public void queryCommentByCardId() {
		int cardId = 1;
		List<Comment> list = service.queryCommentByCardId(cardId);
		System.out.println("successful");
		
	}
	
	@org.junit.Test
	public void delUser() {
		String account = "1234556";
		boolean flog = adminerService.delUser(account);
		System.out.println(flog);
		System.out.println("successful");
	}
	
	//查询所有用户信息，仅限管理员操作
	@org.junit.Test
	public void queryAllUsers(){
		List<User> users = adminerService.queryAllUsers();
		System.out.println("successful");
	}
	@org.junit.Test
	public void queryOneUser(){
		String account = "1951400401";
		User user = adminerService.queryOneUser(account);
		System.out.println("successful");
	}
	
	@org.junit.Test
	public void sendInform(){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cardId", 1);
		params.put("inform", "你的审核通过了。。。。");
		params.put("checkmark", '0');
		params.put("checkTime", new Date());
		adminerService.sendInform(params);
		System.out.println("successful");
	}
	
	@org.junit.Test
	public void queryInfom(){
		String account = "195140040";
		List<Inform> inform = service.queryInfom(account);
		System.err.println("successful");
	}
}
