package org.konate.bdd.serie1.exo1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
		String uri = "jdbc:mysql://localhost:3306/school";
		String username = "root";
		String pwd = "Massare@";
		// TODO Auto-generated method stub
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		//Statement statement = connection.createStatement();
		List<Eleve> eleves = Readers.readEleve("./files/eleves.txt");
		List<Instruments> instruments = Readers.readInstruments("./files/instruments.txt");
		List<Professeur> profs = Readers.readProfesseurs("./files/professeurs.txt");
		//profs.forEach(s->System.out.println(s));
		//instruments.forEach(s->System.out.println(s));
		String query;
		String EleveCreationQuery = "CREATE TABLE IF NOT EXISTS eleves(id SMALLINT  NOT NULL AUTO_INCREMENT PRIMARY KEY, nom  VARCHAR(30), age int);";
		String createDB = "CREATE DATABASE IF NOT EXISTS school;";
		PreparedStatement preparedStatement  =  connection.prepareStatement(createDB);
		preparedStatement.execute();
		preparedStatement.executeUpdate("DROP TABLE IF EXISTS eleves");
		preparedStatement.executeUpdate(EleveCreationQuery);
		
		ResultSet resultSet = null;
		
		for (Eleve eleve : eleves) {
			query = "INSERT INTO eleves (nom, age) VALUES ('" + eleve.getNom() + "', '" + eleve.getAge() + "')";
			preparedStatement.executeUpdate(query);
		}
		//resultSet = preparedStatement.executeQuery("SELECT * from eleves");
		/*preparedStatement = connection.prepareStatement("SELECT * from eleves");
		ResultSetMetaData rsmd = preparedStatement.getMetaData();
		for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
			System.out.println(rsmd.getColumnLabel(i));
		}*/
		
		
		
	}

}
