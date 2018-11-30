package org.konate.bdd.serie1.exo1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
		
		String useDB = "USE school;";
		PreparedStatement preparedStatement = connection.prepareStatement(useDB);
		preparedStatement.execute();
		
		//Read SQL Database and tables creation queries
		String SQLFile = "./files/musique-creation.sql";
		FileInputStream fis = new FileInputStream(SQLFile);
		Readers.readAndExecuteSQLQueries(connection, fis);
		
		String query;
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
		
		/*String joinEleveProfs = "";
		preparedStatement  =  connection.prepareStatement(joinEleveProfs);
		preparedStatement.execute();*/
		
		/*ResultSet resultSet = null;
		resultSet = preparedStatement.executeQuery("SELECT * from eleves");
		ResultSetMetaData rsmd = resultSet.getMetaData();;
		for (int i = 1; i < rsmd.getColumnCount()+1; i++) {
			System.out.println(rsmd);
		}*/
		
	}

}
