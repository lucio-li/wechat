package main.entity;

public class User {
	private int id;
	private String email;
	private String username;
	private String password;
	private String head_img;
	private int status; 
	private String identify_code;
	
	public User() {
		
	}
	public User(String email, String username, String password, String head_img, int status, String identify_code) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.head_img = head_img;
		this.status = status;
		this.identify_code = identify_code;
				
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHead_img() {
		return head_img;
	}
	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getIdentify_code() {
		return identify_code;
	}
	public void setIdentify_code(String identify_code) {
		this.identify_code = identify_code;
	}
	
	
}	
