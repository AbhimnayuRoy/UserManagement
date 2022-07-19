package com.abhi.usermanagement.dao;

public final class Authentication {
	private static final String URL = "jdbc:mysql://localhost:3306/user_details";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "Abhimanyu@97";
	public static String getUrl() {
		return URL;
	}
	public static String getUsername() {
		return USERNAME;
	}
	public static String getPassword() {
		return PASSWORD;
	}
	
}
