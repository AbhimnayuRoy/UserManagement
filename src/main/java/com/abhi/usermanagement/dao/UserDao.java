package com.abhi.usermanagement.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.abhi.usermanagement.bean.User;

import java.io.*;
public class UserDao {
	private String url = Authentication.getUrl();
	private String username = Authentication.getUsername();
	private String password = Authentication.getPassword();
	
	
	//All the required sql query
	private static final String INSERT_USERS_SQL = "INSERT INTO users VALUES (?,?,?,?);";
	private static final String SELECT_USERS_BY_ID = "SELECT * FROM users where id = ?;";
	private static final String SELECT_ALL_USERS = "SELECT * FROM users;";
	private static final String DELETE_USERS_BY_ID = "DELETE FROM users where id = ?;";
	private static final String UPDATE_USERS_SQL = "UPDATE users SET full_name = ? , email_add = ?, country = ? where id = ?;";

	//Get connection
	public Connection connectDatabase() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,username,password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	//insert user 
	public int insertUser(User user) {
		int rowEffected = 0;
		try(Connection con = connectDatabase(); PreparedStatement preSt = con.prepareStatement(INSERT_USERS_SQL)){
			preSt.setInt(1, user.getId());
			preSt.setString(2, user.getName());
			preSt.setString(3, user.getEmail());
			preSt.setString(4, user.getCountry());
			System.out.println(preSt);
			rowEffected = preSt.executeUpdate();
			System.out.println("Insert Ok, " + rowEffected + " row affected");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rowEffected;
	}
	
	//select user by id
	public User selectUser(int id) {
		User user = null;
		try(Connection con = connectDatabase(); PreparedStatement preSt = con.prepareStatement(SELECT_USERS_BY_ID)){
			preSt.setInt(1, id);
			ResultSet rs = preSt.executeQuery();
			
			while(rs.next()) {
				String fullName = rs.getString("full_name");
				String emailAdd = rs.getString("email_add");
				String country = rs.getString("country");
				user = new User(id,fullName,emailAdd,country);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return user;
	}

	//select all user
	public List<User> selectAllUser() {
		List<User> users = new ArrayList<>();
		try(Connection con = connectDatabase(); PreparedStatement preSt = con.prepareStatement(SELECT_ALL_USERS)){
			ResultSet rs = preSt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String fullName = rs.getString("full_name");
				String emailAdd = rs.getString("email_add");
				String country = rs.getString("country");
				users.add(new User(id,fullName,emailAdd,country));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		 
		return users;
	}
	
	//delete user by id, return 1 if deleted else return 0
	public int deleteUser(int id) {
		int rowAffected = 0;
		try(Connection con = connectDatabase(); PreparedStatement preSt = con.prepareStatement(DELETE_USERS_BY_ID)){
			preSt.setInt(1, id);
			rowAffected = preSt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}
	
	//update user, return 1 if updated else return 0
	public int updateUser(User user) {
		int rowAffected = 0;
		try(Connection con = connectDatabase(); PreparedStatement preSt = con.prepareStatement(UPDATE_USERS_SQL)){
			preSt.setString(1, user.getName());
			preSt.setString(2, user.getEmail());
			preSt.setString(3, user.getCountry());
			preSt.setInt(4, user.getId());
			
			rowAffected = preSt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return rowAffected;
	}
}
