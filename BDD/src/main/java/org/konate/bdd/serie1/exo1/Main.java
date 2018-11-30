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
	
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		
		List<Eleve> eleves = Readers.readEleve("./files/eleves.txt");
		List<Instruments> instruments = Readers.readInstruments("./files/instruments.txt");
		List<Professeur> profs = Readers.readProfesseurs("./files/professeurs.txt");
	
		String createDB = "CREATE DATABASE IF NOT EXISTS school;";
		String query;
		String elevesCreationQuery = "USE SCHOOL;" + 
				"DROP TABLE eleves;" + 
				"CREATE TABLE eleves (id int(11) NOT NULL AUTO_INCREMENT, nom varchar(30) NOT NULL, age int(11) NOT NULL, PRIMARY KEY (id), UNIQUE KEY nom (nom));";
		
		String instrumentsCreationQuery = "DROP TABLE instruments;" + 
				"CREATE TABLE instruments (id int(11) NOT NULL AUTO_INCREMENT, nom varchar(30) NOT NULL, prix int(11) NOT NULL, prix_cours int(11) NOT NULL, location char(1) NOT NULL, PRIMARY KEY (id), UNIQUE KEY nom (nom));";
		
		String professeursCreationQuery = "DROP TABLE professeurs;\r\n" + 
				"CREATE TABLE professeurs (id int(11) NOT NULL AUTO_INCREMENT, nom varchar(30) NOT NULL, age int(11) NOT NULL, instrument int(11) NOT NULL, PRIMARY KEY (id), UNIQUE KEY nom (nom));";
		
		
		PreparedStatement preparedStatement  =  connection.prepareStatement(createDB);
		preparedStatement.execute();
		
		/*preparedStatement  =  connection.prepareStatement(elevesCreationQuery);
		preparedStatement.executeQuery();
		
		preparedStatement  =  connection.prepareStatement(professeursCreationQuery);
		preparedStatement.executeQuery();
		
		preparedStatement  =  connection.prepareStatement(instrumentsCreationQuery);
		preparedStatement.executeQuery();*/
		
		
		ResultSet resultSet = null;
		
		for (Eleve eleve : eleves) {
			
			query = "INSERT INTO eleves (nom, age) VALUES (?, ?)";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.setString(1, eleve.getNom());
			preparedStatement.setInt(2, eleve.getAge());
			
			preparedStatement.execute();
		}
		
		for (Professeur prof : profs) {

			query = "INSERT INTO professeurs (nom, age, instrument) VALUES (?, ?, ?)";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.setString(1, prof.getNom());
			preparedStatement.setInt(2, prof.getAge());
			preparedStatement.setInt(3, prof.getInstrument());
			preparedStatement.execute();
		}
		
		for ( Instruments instrument : instruments) {
				query = "INSERT INTO instruments (nom, prix, prix_cours, location) VALUES (?, ?, ?, ?)";
				preparedStatement  =  connection.prepareStatement(query);
				preparedStatement.setString(1, instrument.getNom());
				preparedStatement.setInt(2, instrument.getPrix());
				preparedStatement.setInt(3, instrument.getPrixCours());
				preparedStatement.setString(4, instrument.getLocation());
			
			
			preparedStatement.execute();
		}
		
		resultSet = preparedStatement.executeQuery("SELECT * from eleves");
		preparedStatement = connection.prepareStatement("SELECT * from eleves");
		ResultSetMetaData rsmd = preparedStatement.getMetaData();
		for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
			System.out.println(rsmd);
		}
		
	}

}
