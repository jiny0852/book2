package com.javaex.ex02;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		//int count = authorDao.insertAuthor("황일영", "강사님");
		
		//authorDao.deleteAuthor(14);
		
		//update 는 과제 각자
		//authorDao.updateAuthor(16, "강사님", "황일영");
		
		//selectAll
		//List<AuthorVo> authorList = authorDao.selectAllAuthor(); //뉴리스트 만들어서 주소만 전달한다
		//System.out.println(authorList);
		
		//List<AuthorVo> authorList2 = authorDao.selectNameAuthor();
		//System.out.println(authorList2);
		
		
		//select 각자
		List<AuthorVo> authorList3 = authorDao.selectOneAuthor(16);
		System.out.println(authorList3);
		
		

	}

}
