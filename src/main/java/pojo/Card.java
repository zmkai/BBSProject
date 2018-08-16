package pojo;

import java.util.Date;

public class Card {
	private Integer cardId;
	private Integer typicId;
	private String author;
	private String title;
	private String content;
	private Date publicTime;
	private char delmark;
	private char checkmark;
	
	
	public Card() {
		delmark = '0';
		checkmark = '1';
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}


	public Integer getTypicId() {
		return typicId;
	}
	public void setTypicId(Integer typicId) {
		this.typicId = typicId;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(Date publicTime) {
		this.publicTime = publicTime;
	}
	public char getDelmark() {
		return delmark;
	}
	public void setDelmark(char delmark) {
		this.delmark = delmark;
	}
	public char getCheckmark() {
		return checkmark;
	}
	public void setCheckmark(char checkmark) {
		this.checkmark = checkmark;
	}
}
