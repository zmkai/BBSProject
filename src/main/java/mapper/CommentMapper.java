package mapper;

import java.util.List;

import pojo.Comment;

public interface CommentMapper {
	//通过cardId查找评论功能
	public List<Comment> queryCommentByCardId(int cardId) throws Exception;
}
