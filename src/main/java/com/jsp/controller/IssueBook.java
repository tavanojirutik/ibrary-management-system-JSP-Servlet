package com.jsp.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.service.LibrarianService;

@WebServlet("/issue")
public class IssueBook extends HttpServlet {
	LibrarianService librarianService=new LibrarianService();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int bookid=Integer.parseInt(req.getParameter("bid"));
		int libid=Integer.parseInt(req.getParameter("lid"));
		int studentid=Integer.parseInt(req.getParameter("sid"));
		
	
	librarianService.issueBookById(bookid, libid, studentid);
	RequestDispatcher requestDispatcher=req.getRequestDispatcher("studentportal.jsp");
	requestDispatcher.forward(req, resp);
	}

}
