package com.jsp.service;

import java.time.LocalDate;
import java.util.List;

import com.jsp.dao.BookDao;
import com.jsp.dao.LibrarianDao;
import com.jsp.dao.StudentDao;
import com.jsp.dto.Book;
import com.jsp.dto.Librarian;

import com.jsp.dto.Student;

public class LibrarianService {
	LibrarianDao librarianDao=new LibrarianDao();
	BookDao bookDao=new BookDao();
	StudentDao studentDao=new StudentDao();
	public Librarian saveLibrarian(Librarian librarian) {
		if (librarian.getStatus().equalsIgnoreCase("authorized")) {
			librarian.setStatus("unauthorized");
			}
		return librarianDao.saveLibrarian(librarian);
	}
	
	public Librarian deleteLibrarian(int id) {
		return librarianDao.deleteLibrarian(id);
	}
	
	public Librarian updateLibrarian(int id,String password) {
		return  librarianDao.updateLibrarian(id, password);
		
	}
	public Librarian getLibrarianById(int id) {
		return librarianDao.getLibrarianById(id);
	}
	
	public List<Librarian> getAllres(){
		return librarianDao.getAllres();
	
	}
	
	public boolean loginLibrarian(String user,String pass) {
		List<Librarian> librarians=librarianDao.getAllres();
		for (Librarian lib : librarians) {
			if (lib.getStatus().equalsIgnoreCase("authorized") && lib.getUsername().equals(user) && lib.getPassword().equals(pass)) {
				return true;
				}
		}
		return false;
	}
//	public boolean issueBookById(int bookid, int libid, int studid) {
//		Book book = bookDao.getBookById(bookid);
//		Student student = studentDao.getStudentById(studid);
//		Librarian librarian = librarianDao.getLibrarianById(libid);
//		if (librarian != null && book != null && student != null) {
//			if (book.getStatus().equals("available")) {
//				book.setStatus("Issued");
//				book.setStudent(student);
//				book.setLibrarian(librarian);
//				return bookDao.issue(book);
//			}else {
//				return false;
//			}
//		}
//		return false;
//	}
	

    public Book issueBookById(int bookid, int libid, int studid) {
        Book book = bookDao.getBookById(bookid);
        Student student = studentDao.getStudentById(studid);
        Librarian librarian = librarianDao.getLibrarianById(libid);

        if (librarian != null && book != null && student != null) {
            if (book.getStatus().equals("Issued")) {
                System.out.println("The book is already issued");
            } else {
                book.setStatus("Issued");
                book.setStudent(student);
                book.setIssueDate(LocalDate.now());
                book.setLibrarian(librarian);

                System.out.println("Book issued successfully!");
               return bookDao.issue(book);
            }
        } else {
            System.out.println("Book not issued.");
        }
        return book;
    }

//	public boolean returnBookById(int bookid) {
//		Book book = bookDao.getBookById(bookid);
//		if (book != null && book.getStatus().equals("Issued")) {
//			Student student = book.getStudent();
//			Librarian librarian = book.getLibrarian();
//
//			if (student != null && librarian != null) {
//				book.setStatus("Available");
//				book.setStudent(null);
//				book.setLibrarian(null);
//				return bookDao.returnBook(book);
//			}
//		}
//		return false;
//	}


}
