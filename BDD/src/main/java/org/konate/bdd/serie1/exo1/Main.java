package org.konate.bdd.serie1.exo1;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.konate.bdd.serie1.exo1.model.Eleve;
import org.konate.bdd.serie1.exo1.model.ElevesInstruments;
import org.konate.bdd.serie1.exo1.model.Instruments;
import org.konate.bdd.serie1.exo1.model.Professeur;
import org.konate.bdd.serie1.exo1.model.ProfesseursInstruments;
import org.konate.bdd.serie1.exo1.util.Readers;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {

		String uri = "jdbc:mysql://localhost:3306/school";
		String username = "root";
		String pwd = "Massare@";

		Connection connection = DriverManager.getConnection(uri, username, pwd);

		List<Eleve> eleves = Readers.readEleve("./files/eleves.txt");
		List<Instruments> instruments = Readers.readInstruments("./files/instruments.txt");
		List<Professeur> profs = Readers.readProfesseurs("./files/professeurs.txt");
		List<ProfesseursInstruments> profsInstruments = Readers.readProfesseursInstruments("./files/professeurs-instruments.txt");
		List<ElevesInstruments> elevesInstruments = Readers.readElevesInstruments("./files/eleves-instruments.txt");

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

		for (ProfesseursInstruments profsInstrument : profsInstruments) {
			query = "UPDATE professeurs JOIN instruments ON instruments.nom=? SET professeurs.instrument=instruments.id WHERE professeurs.nom=?;";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.setString(1, profsInstrument.getInstrument());
			preparedStatement.setString(2, profsInstrument.getNom());

			preparedStatement.executeUpdate();
		}

		for (ElevesInstruments ints : elevesInstruments) {
			query = "INSERT INTO eleves_instruments (nom, instrument) VALUES (?, ?);";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.setString(1, ints.getNom());
			preparedStatement.setString(2, ints.getinstrument());
			preparedStatement.execute();

			query = "UPDATE eleves_instruments JOIN eleves SET eleves_instruments.id = eleves.id WHERE eleves.nom=eleves_instruments.nom;";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.execute();

			/*query = "SELECT e.*, i.* FROM school.eleves e INNER JOIN school.eleves_instruments ei ON ei.id = e.id INNER JOIN school.instruments i ON i.nom = ei.instrument;";
			preparedStatement  =  connection.prepareStatement(query);
			preparedStatement.execute();*/
		}
	}
}
