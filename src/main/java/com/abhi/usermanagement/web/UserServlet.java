package com.abhi.usermanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.abhi.usermanagement.bean.User;
import com.abhi.usermanagement.dao.UserDao;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	public void init(ServletConfig config) throws ServletException {
		userDao = new UserDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/new":
			shownewForm(request, response);
			break;

		case "/insert":
			try {
				insertUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/delete":
			try {
				deleteUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/edit":
			try {
				showEditForm(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		case "/update":
			try {
				updateUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;

		default:
			try {
				listUser(request, response);
			} catch (SQLException | IOException e) {
				e.printStackTrace();
			}
			break;
		}
			
	}
	
	//show new form method
	private void shownewForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}
	
	//insert user method
	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id")); //------look if some problem
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String country = request.getParameter("country");
		User newUser = new User(id,name, email, country);
//		userDao.insertUser(new User(name,email,country));
		int rowAff = userDao.insertUser(newUser);
		System.out.println("Ok deleted " + rowAff + " affected");
		response.sendRedirect("list");
		
	}
	
	//delete user method
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		int rowAff = userDao.deleteUser(id);
		System.out.println("Ok deleted " + rowAff + " affected");
		response.sendRedirect("list");
	}
	
	//edit form
	private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		
		User existingUser;
		existingUser = userDao.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	//create an update 
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
	    String country = request.getParameter("country");
	    User user = new User(id,name,email,country);
	    userDao.updateUser(user);
	    response.sendRedirect("list");
	}
			
	//default method list
	private void listUser(HttpServletRequest request, HttpServletResponse response) throws SQLException,IOException{
		try {
			List<User> listUser = userDao.selectAllUser();
			request.setAttribute("listUser", listUser);
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
