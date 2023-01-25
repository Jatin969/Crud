package com.jatin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.tomcat.jni.Address;

import com.jatin.dao.UserDao;
import com.jatin.model.User;


//@Webervlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UserDao userDao;
    public UserServlet() {
        super();
        this.userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		switch (action) {
		case "/new":
			showNewForm(request,response);
			break;
		case "/insert":
			insertUser(request,response);
			break;
		case "/delete":
			deleteUser(request,response);
			break;
		case "/edit":
			showEditForm(request,response);
			break;
		case "/update":
			updateUser(request,response);
			break;
		default:
			listUser(request,response);
			break;
		}
	}
	
	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		List<User> listUsers = userDao.selectAllUser();
		request.setAttribute("listUser", listUsers);
		RequestDispatcher rd = request.getRequestDispatcher("user-list.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("Error : in ::::::: listUser");
			e.printStackTrace();
		} 	
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response) {
		RequestDispatcher rd = request.getRequestDispatcher("user-from.jsp");
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("Error : in ::::::: showNewForm");
			e.printStackTrace();
		}
		
	}
	private void insertUser(HttpServletRequest request, HttpServletResponse response)  {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User user = new User(name,email,country);
		userDao.insertUser(user);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error : in ::::::: insertUser");
			e.printStackTrace();
		}
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		userDao.deleteUser(id);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			System.out.println("Error : in ::::::: deleteUser");
			e.printStackTrace();
		}
	}
	
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
		String email =request.getParameter("email") ;
		User existingUser = userDao.selectUser(email);
		RequestDispatcher rd = request.getRequestDispatcher("user-from.jsp");
		request.setAttribute("user", existingUser);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("Error : in ::::::: showEditForm");
			e.printStackTrace();
		}
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response){
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User user = new User(name,email,country);
		userDao.updateUser(user);
		try {
			response.sendRedirect("list");
		} catch (IOException e) {
			System.out.println("Error : in ::::::: updateUser");
			e.printStackTrace();
		}
	}
}
