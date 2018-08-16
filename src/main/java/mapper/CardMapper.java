package mapper;

import java.util.List;
import java.util.Map;

import pojo.Card;
import pojo.CardExpand;

public interface CardMapper {
	//发表帖子
	public void insertCard(Card card) throws Exception;
	//判断帖子是否重复
	public int findCardRepeat(Map<String, Object> params) throws Exception;
	//查找用户名下被审核通过的帖子
	public List<CardExpand> queryCheckList(String author) throws Exception;
	//通过帖子id来删除帖子
	public void delCardById(int cardId) throws Exception;
	//插入评论数据
	public void insertComment(Map<String, Object> params) throws Exception;
}
