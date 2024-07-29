package com.javaex.ex05;

import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		BookDao bookDao = new BookDao();
		
		//int count = bookDao.insertBook("초한지", "문학사", "2009/08/06", 12);
		
		
		//bookDao.deleteBook(19);
		
		
		//update 는 과제 각자
		//bookDao.updateBook(20, "초한지2", "문학학사", "2015/03/27", 11);
		
		//selectAll
		//List<BookVo> bookList = bookDao.selectAllBook(); //뉴리스트 만들어서 주소만 전달한다
		//System.out.println(bookList);
		
		//List<BookVo> bookList2 = bookDao.selectNameBook(8);
		//System.out.println(bookList2);
		
		
		
		//select 각자
		//BookVo bookVo = bookDao.selectOneBook(15);
		//System.out.println(bookVo);
		
		
		List<BookVo> bookList2 = bookDao.serch();
		

	}

}
