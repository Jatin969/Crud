package com.jatin.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jatin.model.User;

public class UserDao {
	private String jdbcUrl = "jdbc:mysql://localhost:3306/crud?useSSLfalse";
	private String jdbcUserName = "root";
	private String jdbcPassword = "root";
	
	private static final  String INSERT_USERS_SQL = "Insert Into crud (name,email,country) values " + "(?,?,?);";
	private static final  String SELECT_USER_BY_ID = "SELECT * FROM crud where id=?;";
	private static final  String SELECT_ALL_USERS = "SELECT * FROM crud;";
	private static final  String DELETE_USERS_SQL = "DELETE FROM crud WHERE id = ?;";
	private static final  String UPDATE_USERS_SQL = "UPDATE crud SET name = ? ,email = ? , country = ? where id = ? ;";
	private static final  String SELECT_USER_BY_EMAIL = "SELECT * FROM crud WHERE email = ?;";

	//connection
	protected Connection getConnection() {
		Connection connection = null;
		 try {  
             Class.forName("com.mysql.cj.jdbc.Driver");  
             connection = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);  
             }  
         catch (Exception e) { 
        	 System.out.println("Exception in :::::: getConnection");
             e.printStackTrace();  
         } 
		 return connection;
	}
	
	//Create User or Insert
	public void insertUser(User user) {
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.executeUpdate();
			System.out.println("User added");
		} catch (Exception e) {
			System.out.println("Exception in :::::: insertUser");
			e.printStackTrace();   
		}
	}
	
	//Update User
	public boolean updateUser(User user) {
		boolean rowUpdate = false;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)){
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.setString(4, user.getEmail());
			rowUpdate = preparedStatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.println("Exception in :::::: updateUser");
			e.printStackTrace(); 
		}
		
		return rowUpdate;
	}
	
	//select user by email
	public User selectUser(String email) {
		User user = null;
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
			preparedStatement.setString(1, email);
			ResultSet rSet = preparedStatement.executeQuery();			
			while(rSet.next()) {
				int identity = rSet.getInt("id");
				String name = rSet.getString("name");
				String email1 = rSet.getString("email");
				String country = rSet.getString("country");				
				user = new User(identity, name, email1, country);
			}
		} catch (Exception e) {
			System.out.println("Exception in :::::: selectUser");
			e.printStackTrace();   
		}
		
		return user;
	}
	
	//Select user by id	
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)){
			preparedStatement.setInt(1, id);
			ResultSet rSet = preparedStatement.executeQuery();
			
			while(rSet.next()) {
				int identity = rSet.getInt("id");
				String name = rSet.getString("name");
				String email1 = rSet.getString("email");
				String country = rSet.getString("country");				
				user = new User(identity, name, email1, country);
			}
		} catch (Exception e) {
			System.out.println("Exception in :::::: selectUser");
			e.printStackTrace();   
		}
		
		return user;
	}
	
	//Select All users
	
	public List<User> selectAllUser() {
		List<User> list = new ArrayList<>();
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)){
			ResultSet rSet = preparedStatement.executeQuery();
			
			while(rSet.next()) {
				int id = rSet.getInt("id");
				String name = rSet.getString("name");
				String email = rSet.getString("email");
				String country = rSet.getString("country");
				
				list.add(new User(id, name, email, country)) ;
			}
		} catch (Exception e) {
			System.out.println("Exception in :::::: SelectAllUser");
			e.printStackTrace();  
		}
		
		return list;
	}
	
	
	//delete User Method
	public boolean deleteUser(int id) {
		boolean rowDeleted = false;
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL)){
			preparedStatement.setInt(1, id);
			rowDeleted = preparedStatement.executeUpdate() >0;
		} catch (Exception e) {
			System.out.println("Exception in :::::: deleteUser");
			e.printStackTrace();   
		}
		
		return rowDeleted;
	}
	
	private boolean isEmailPresent(String email) {
		boolean resultFound = false;
		try(Connection connection = getConnection(); 
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)){
			preparedStatement.setString(1,email);
			resultFound = preparedStatement.executeQuery().getFetchSize() > 0;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultFound;
	}
}
