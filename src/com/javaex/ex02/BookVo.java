package com.javaex.ex02;

public class BookVo {

	//필드
	private int bookId;
	private String title;
	private String pubs;
	private String pub_date;
	private int author_id;
	
	
	//생성자
	public BookVo() {
		
	}
	public BookVo(int bookId, String title, String pubs, String pub_date, int author_id) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
		this.author_id = author_id;
	}
	
	
	
	//메서드 -gs
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPubs() {
		return pubs;
	}
	public void setPubs(String pubs) {
		this.pubs = pubs;
	}
	public String getPub_date() {
		return pub_date;
	}
	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	
	
	
	
	//메서드 일반
	
	@Override
	public String toString() {
		return "BookVO [bookId=" + bookId + ", title=" + title + ", pubs=" + pubs + ", pub_date=" + pub_date + ", author_id="
				+ author_id + "]";
	}
	
	
	
	
	
}
