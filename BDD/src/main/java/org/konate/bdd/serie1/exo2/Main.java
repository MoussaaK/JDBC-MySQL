package org.konate.bdd.serie1.exo2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.konate.bdd.serie1.exo2.util.Queries;

public class Main {

	public static void main(String[] args) throws SQLException {
		String uri = "jdbc:mysql://localhost:3306/school";
		String username = "root";
		String pwd = "Massare@";
		
		Connection connection = DriverManager.getConnection(uri, username, pwd);
		Statement statement = connection.createStatement();
		
		String sql = "SELECT nom, age From school.eleves WHERE eleves.age > 25 GROUP BY nom ASC;";
		ResultSet resultSet = Queries.execute(statement, sql);
		Queries.printQueryAnswer(resultSet, "nom");
		
		sql = "SELECT AVG(age) as average_age_eleves From school.eleves;";
		resultSet = Queries.execute(statement, sql);
		Queries.printQueryAnswer(resultSet, "average_age_eleves");
		
		sql = "SELECT AVG(age) as average_age_professeurs From school.professeurs;";
		resultSet = Queries.execute(statement, sql);
		Queries.printQueryAnswer(resultSet, "average_age_professeurs");
		
		sql = "SELECT e.nom, sum(prix + prix_cours*30) as depenses FROM school.eleves e INNER JOIN school.eleves_instruments ei ON ei.id = e.id INNER JOIN school.instruments i ON i.nom = ei.instrument GROUP BY e.id;";
		resultSet = Queries.execute(statement, sql);
		Queries.printQueryAnswer(resultSet, "nom");
		resultSet = Queries.execute(statement, sql);
		Queries.printQueryAnswer(resultSet, "depenses");
		
		
	}
}
