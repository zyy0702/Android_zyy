package com.example.zhangyayan_text6;

public class News {
	private String content;
	private String created_at;
	private String author;
	private String username;
	private String last_up_users;
	public News() {
	}
	public News(String content, String created_at, String author,
			String username, String last_up_users) {
		super();
		this.content = content;
		this.created_at = created_at;
		this.author = author;
		this.username = username;
		this.last_up_users = last_up_users;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getLast_up_users() {
		return last_up_users;
	}
	public void setLast_up_users(String last_up_users) {
		this.last_up_users = last_up_users;
	}
	@Override
	public String toString() {
		return "News [content=" + content + ", created_at=" + created_at
				+ ", author=" + author + ", username=" + username + ", last_up_users=" + last_up_users + "]";
	}
}
