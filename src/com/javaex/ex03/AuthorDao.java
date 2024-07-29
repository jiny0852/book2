package com.javaex.ex03;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
	
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
	//작가 등록
	public int insertAuthor(String name, String desc) {
		
		int count = -1; 
		
		// DB연결 메소드 호출
		this.getConnection();
				
		try {
			// 3. SQL문 준비 / 바인딩 / 실행
			
			// - sql문 준비
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) ";
			
			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			
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
	//작가 등록
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//작가 삭제
	public int deleteAuthor(int authorId) {
		
		System.out.println("작가 삭제");

		int count = -1; //최소값을 일부러 -1(정상값이 0일 수도 있어서)로 넣는다 return 값이 결정되지 않았음(try에서 빠질수도)으로 

		// 0. import java.sql.*;
		
		// DB연결 메소드 호출
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = "";
			query += " delete from author ";
			query += " where author_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);

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
	//작가 삭제
	//////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////
	//작가 수정
	public int updateAuthor(int authorId, String name, String desc) {

		int count = -1; 
		
		// 0. import java.sql.*;
		
		// DB연결 메소드 호출
				this.getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = "";
			query += " update author ";
			query += " set author_name = ? , ";
			query += " author_desc = ? ";
			query += " where author_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, desc);
			pstmt.setInt(3, authorId); 

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
	//작가 수정
	//////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////
	//작가 전체 조회
	public List<AuthorVo> selectAllAuthor() {

		//리스트 만들기
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// DB연결 메소드 호출
		this.getConnection();
		
		int count = -1; 

		// 0. import java.sql.*;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			query += " order by author_id asc ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);

			// - 실행
			rs = pstmt.executeQuery();



			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int authorId = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(authorId, name, desc);
				authorList.add(authorVo);
				
				count++;
				
			}
			
			System.out.println(count + "건 조회 되었습니다.");

		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return authorList; //리스트의 주소를 리턴한다
	}
	//작가 전체 조회
	//////////////////////////////////////////////////
	
	
	
	//////////////////////////////////////////////////
	//작가 이름만 조회
	public List<AuthorVo> selectNameAuthor() {

		//리스트 만들기
		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		// DB연결 메소드 호출
		this.getConnection();
		
		int count = -1; //최소값을 일부러 -1로 넣는다 

		// 0. import java.sql.*;

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			query += " order by author_id asc ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);

			// - 실행
			rs = pstmt.executeQuery();



			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				int authorId = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(authorId, name, desc);
				authorList.add(authorVo);
				
				System.out.println("author_name : " + name);
				
				count++;
				
			}
			
			System.out.println( (count+1) + "건 조회 되었습니다.");

		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();


		return authorList;
	}
	//작가 이름 조회
	//////////////////////////////////////////////////
	

	
	//////////////////////////////////////////////////
	//작가 하나 조회
	public List<AuthorVo> selectOneAuthor(int authorId) {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();
		
		int count = -1; //최소값을 일부러 -1로 넣는다 
		
		// 0. import java.sql.*;
		// DB연결 메소드 호출
		this.getConnection();

		try {
			// 3. SQL문 준비 / 바인딩 / 실행

			// - sql문 준비
			String query = ""; 
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			author_desc ";
			query += " from author ";
			query += " where author_id = ? ";

			// - 바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, authorId);
			

			// - 실행
			rs = pstmt.executeQuery();
			

			// 4.결과처리
			//리스트로 만들기
						
			while (rs.next()) {
			
				authorId = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("author_desc");
				
				AuthorVo authorVo = new AuthorVo(authorId, name, desc);
				authorList.add(authorVo);
				
				System.out.println("author_id : " + authorId);
				System.out.println("author_name : " + name);
				System.out.println("author_desc : " + desc);
				
				
			}
			
			
			System.out.println( (count+2) + "건 조회 되었습니다.");


		}  catch (SQLException e) {
			System.out.println("error:" + e);
		}

		this.close();

		return authorList; //리스트의 주소를 리턴한다
	}
	//작가 하나 조회
	//////////////////////////////////////////////////
	
	

}
