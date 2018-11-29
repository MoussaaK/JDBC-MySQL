package org.konate.bdd.serie1.exo1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

	public static void main(String[] args) throws SQLException {
		String uri = "jdbc:mysql://localhost:3306/sys";
		String username = "root";
		String pwd = "Massare@";
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		Statement statement = connection.createStatement();
		
		String query = "SELECT * from database";
		statement.executeQuery(query );
		
	}

}
