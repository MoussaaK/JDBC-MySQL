package org.konate.bdd.serie1.exo1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		String uri = "jdbc:mysql://localhost:3306/sys";
		String username = "root";
		String pwd = "Massare@";
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		Statement statement = connection.createStatement();
		List<Eleve> eleves = Readers.readEleve("./files/eleves.txt");
		//System.out.println(eleves);
		
		/*String query = "";
		ResultSet resultSet = statement.executeQuery(query );
		while(resultSet.next()) {
			
		}*/
		
	}

}
