package com.javaex.ex04;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		//int count = bookDao.insertBook("황일영", "강사님");
		
		//bookDao.deleteBook(14);
		
		//update 는 과제 각자
		//bookDao.updateBook(16, "강사님", "황일영");
		
		//selectAll
		List<BookVo> bookList = bookDao.selectAllBook(); //뉴리스트 만들어서 주소만 전달한다
		System.out.println(bookList);
		
		//List<BookVo> bookList2 = bookDao.selectNameBook();
		//System.out.println(bookList2);
		
		
		
		//select 각자
		//BookVo bookVo = bookDao.selectOneBook(16);
		//System.out.println(bookVo);
		
		

	}

}
