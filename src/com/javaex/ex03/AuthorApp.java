package com.javaex.ex03;

import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {
		
		AuthorDao authorDao = new AuthorDao();
		
		//int count = authorDao.insertAuthor("황일영", "강사님");
		
		authorDao.deleteAuthor(20);
		
		//update 는 과제 각자
		//authorDao.updateAuthor(20, "강사님", "황일영");
		
		//selectAll
		//List<AuthorVo> authorList = authorDao.selectAllAuthor(); //뉴리스트 만들어서 주소만 전달한다
		//System.out.println(authorList);
		
		//List<AuthorVo> authorList2 = authorDao.selectNameAuthor();
		//System.out.println(authorList2);
		
		
		//select 각자
		//List<AuthorVo> authorList3 = authorDao.selectOneAuthor(5);
		//System.out.println(authorList3);
		
		

	}

}
