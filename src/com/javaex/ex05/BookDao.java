package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BookDao {
	
	//필드
	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private	String url = "jdbc:mysql://localhost:3306/book_db";
	private String id = "book";
	private String pw = "book";
	
	//생성자
	
	//메서드 gs
	
	//메서드 일반	
	
	// DB연결 메소드
	private void getConnection() {
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);
			
			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	// 자원정리 메소드
	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
					rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			} 
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}
	
	
	
	//////////////////////////////////////////////////
	//책 등록
	public int insertBook(String title, String pubs, String pub_date, int author_id) {
		
		System.out.println("저장 로직");
		
		int count = -1; 
		
		this.getConnection();
		
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// - sql문 준비
			String query = "";
			query += " insert into book ";
			query += " values ( null, ?, ?, ?, ? ) "; 
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_date);
			pstmt.setInt(4, author_id);
			
			// - 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();
		
		return count;
	}
	//책 등록
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//책 삭제
	public int deleteBook(int id) {

		System.out.println("삭제 로직");

		int count = -1; //최소값을 일부러 -1(정상값이 0일 수도 있어서)로 넣는다 return 값이 결정되지 않았음(try에서 빠질수도)으로 

		this.getConnection();
		
		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = "";
			query += " delete from book ";
			query += " where book_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			// - 실행
			count = pstmt.executeUpdate();



			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");

			
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return count;
	}
	//책 삭제
	//////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////
	//책 수정
	public int updateBook(int book_id, String title, String pubs, String pub_date, int author_id) {

		System.out.println("수정 로직");

		int count = -1; //최소값을 일부러 -1(정상값이 0일 수도 있어서)로 넣는다 return 값이 결정되지 않았음(try에서 빠질수도)으로 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = "";
			query += " update book ";
			query += " set title = ? , ";
			query += " pubs = ? , ";
			query += " pub_date = ? , ";
			query += " author_id = ? ";
			query += " where book_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, pubs);
			pstmt.setString(3, pub_date);
			pstmt.setInt(4, author_id); 
			pstmt.setInt(5, book_id);  

			// - 실행
			count = pstmt.executeUpdate();


			// 4.결과처리
			System.out.println(count + "건 수정 되었습니다.");

			
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return count;
	}
	//책 수정
	//////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////
	//책 전체 조회
	public List<BookVo> selectAllBook() {

		System.out.println("전체 조회 로직");

		//리스트 만들기
		List<BookVo> bookList = new ArrayList<BookVo>();

		int count = -1; //최소값을 일부러 -1로 넣는다 
		
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select 	book_id, ";
			query += " 			title, ";
			query += " 			pubs, ";
			query += " 			pub_date, ";
			query += " 			author_id ";
			query += " from book ";
			query += " order by book_id asc ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);

			// - 실행
			rs = pstmt.executeQuery();


			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId);
				bookList.add(bookVo);
				
				count++;
				
			}
			
			System.out.println(count + "건 조회 되었습니다.");

			
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return bookList;
	}
	//책 전체 조회
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//작가 이름만 조회
	public List<BookVo> selectNameBook(int id) {

		System.out.println("책 이름 조회 로직");

		//리스트 만들기
		List<BookVo> bookList = new ArrayList<BookVo>();
		
		int count = -1; //최소값을 일부러 -1로 넣는다 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select 	book_id, ";
			query += " 			title, ";
			query += " 			pubs, ";
			query += " 			pub_date, ";
			query += " 			author_id ";
			query += " from book ";
			query += " where book_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);

			// - 실행
			rs = pstmt.executeQuery();



			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId);
				bookList.add(bookVo);
				
				System.out.println("title : " + title);
				
				count++;
				
			}
			
			
			System.out.println( (count+1) + "건 조회 되었습니다.");

		
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return bookList; //리스트의 주소를 리턴한다
	}
	//작가 이름 조회
	//////////////////////////////////////////////////
	

	
	//////////////////////////////////////////////////
	//책 하나 조회
	public BookVo selectOneBook(int id) {

		System.out.println("책 하나 조회 로직");

		BookVo bookVo = new BookVo();
		
		int count = -1; //최소값을 일부러 -1로 넣는다 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select 	book_id, ";
			query += "			title, ";
			query += " 			pubs, ";
			query += " 			pub_date, ";
			query += " 			author_id ";
			query += " from book ";
			query += " where book_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			

			// - 실행
			rs = pstmt.executeQuery();
			

			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				bookVo.setBookId(bookId);
				bookVo.setTitle(title);
				bookVo.setPubs(pubs);
				bookVo.setPub_date(pubs);
				bookVo.setAuthor_id(authorId);
				
			}
			
			
			System.out.println( (count+2) + "건 조회 되었습니다.");

			
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return bookVo; //리스트의 주소를 리턴한다
	}
	//책 하나 조회
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//검색
	public List<BookVo> serch() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("검색어를 입력하세요");
		System.out.print(">>");
		String str = sc.nextLine();

		List<BookVo> bookList = new ArrayList<BookVo>();
		
		int count = -1; //최소값을 일부러 -1로 넣는다 

		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select 	book_id, ";
			query += "			title, ";
			query += " 			pubs, ";
			query += " 			pub_date, ";
			query += " 			author_id ";
			query += " from book ";
			query += " where contains(book, '?') ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, str);
			

			// - 실행
			rs = pstmt.executeQuery();



			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				BookVo bookVo = new BookVo(bookId, title, pubs, pubDate, authorId);
				bookList.add(bookVo);
				
				System.out.println("title : " + title);
				
				count++;
				
			}
			
			
			System.out.println( (count+1) + "건 조회 되었습니다.");

		
		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return bookList; 
	}
	//책 하나 조회
	//////////////////////////////////////////////////
	
	
	

}
