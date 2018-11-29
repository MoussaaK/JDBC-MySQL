package org.konate.bdd.serie1.exo1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		String uri = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String pwd = "Massare0";
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		//Statement statement = connection.createStatement();
		List<Eleve> eleves = Readers.readEleve("./files/eleves.txt");
		
		String query;
		//String EleveCreationQuery = "DROP TABLE IF EXISTS `eleves`; CREATE TABLE `eleves` (`id` int(11) NOT NULL AUTO_INCREMENT, `nom` varchar(30) NOT NULL,`age` int(11) NOT NULL, PRIMARY KEY (`id`), UNIQUE KEY `nom` (`nom`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
		/*String EleveCreationQuery = "CREATE TABLE IF NOT EXISTS Eleve(id SMALLINT  NOT NULL AUTO_INCREMENT PRIMARY KEY"
				+ ",nom  VARCHAR(30), age int); ";*/
		String EleveCreationQuery = "CREATE DATABASE IF NOT EXISTS SCHOOL;";
		PreparedStatement preparedStatement  =  connection.prepareStatement(EleveCreationQuery);
		preparedStatement.execute();
		
		/*ResultSet resultSet = null;
		
		for (Eleve eleve : eleves) {
			query = "INSERT INTO eleves (nom, age) VALUES ('" + eleve.getNom() + "', '" + eleve.getAge() + "')";
			preparedStatement.execute(query);
		}*/
		/*resultSet = statement.executeQuery("SELECT * from `eleves`");
		while(resultSet.next()) {
			
		}*/
		
	}

}
