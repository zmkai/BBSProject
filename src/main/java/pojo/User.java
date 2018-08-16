package pojo;

/**
 * 用户实体类
 * @author 周梦凯
 * @date 2018/4/2
 *
 */
public class User {
	private String account;
	private String password;
	private String nick;
	private char role;
	private char sex;
	private String uemail;
	private String signature;
	private String tel;
	private char stopmark;
	private char delete_mark;
	
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public char getRole() {
		return role;
	}
	public void setRole(char role) {
		this.role = role;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public char getStopmark() {
		return stopmark;
	}
	public void setStopmark(char stopmark) {
		this.stopmark = stopmark;
	}
	public char getDelete_mark() {
		return delete_mark;
	}
	public void setDelete_mark(char delete_mark) {
		this.delete_mark = delete_mark;
	}
}
