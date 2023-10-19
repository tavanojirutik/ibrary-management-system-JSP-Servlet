package com.jsp.service;

import java.util.List;

import com.jsp.dao.BookDao;
import com.jsp.dao.StudentDao;
import com.jsp.dto.Book;
import com.jsp.dto.Librarian;
import com.jsp.dto.Student;

public class StudentService {
	StudentDao studentDao = new StudentDao();
	BookDao bookDao=new BookDao();
	Book book=new Book();

	public Student insertData(Student student) {
		return studentDao.insertData(student);
	}

	
	public Student deleteStudentById(int id) {
		Student student =studentDao.getStudentById(id);
		if (student != null) {
			return studentDao.deleteData(id);
		}
		return null;
	}

	public Student updateData(int id, int year) {
		Student student = studentDao.getStudentById(id);
		if (student != null) {
			return studentDao.updateData(id,year);
		}
		return null;
	}


	public Student getStudentById(int id) {
		return studentDao.getStudentById(id);
	}

	public List<Student> getAllRes() {
		return studentDao.getAllRes();
	}
	
	 public boolean returnBookById(int bookid) {
	        Book book = bookDao.getBookById(bookid);
	        if (book != null && book.getStatus().equals("Issued")) {
	            Student student = book.getStudent();
	            Librarian librarian = book.getLibrarian();

	            if (student != null && librarian != null) {
	                book.setStatus("Available");
	                book.setStudent(null);
	                book.setLibrarian(null);
	                boolean returned = bookDao.returnBook(book);
	                if (returned) {
	                    System.out.println("Book with ID has been returned");
	                } else {
	                    System.out.println("Failed to return book");
	                }
	                return returned;
	            }
	        }
	        System.out.println("Book cannot be returned");
	        return false;
	    }
	 public boolean loginStudent(String user,String pass) {
			List<Student> students=studentDao.getAllRes();
			for (Student s : students) {
				if (user != null && user.equals(s.getUsername()) && pass != null && pass.equals(s.getPassword())){
					return true;
					}
			}
			return false;
		}


}
