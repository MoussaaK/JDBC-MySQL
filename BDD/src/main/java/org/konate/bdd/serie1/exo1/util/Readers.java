package org.konate.bdd.serie1.exo1.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.konate.bdd.serie1.exo1.model.Eleve;
import org.konate.bdd.serie1.exo1.model.ElevesInstruments;
import org.konate.bdd.serie1.exo1.model.Instruments;
import org.konate.bdd.serie1.exo1.model.Professeur;
import org.konate.bdd.serie1.exo1.model.ProfesseursInstruments;

public class Readers {
	private static Function<String, Eleve> lineToEleve = string -> {
		return new Eleve(string.split(";")[0], Integer.parseInt(string.split(";")[1]));
	};
	private static Predicate<String> isNotEmpty = string -> !string.isEmpty();

	public static List<Eleve> readEleve(String fileName) throws IOException {
		File file = new File(fileName);
		List<Eleve> eleves = new ArrayList<>();

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);) {

			eleves = br.lines()
					.skip(1)
					.filter(s -> isNotEmpty.test(s))
					.map(s->s.replaceFirst("	", ";").replace("	", ""))
					.map(lineToEleve)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return eleves;
	}

	static Function<String, Professeur> lineToProfesseur = string -> {
		return new Professeur(string.split(";")[0], Integer.parseInt(string.split(";")[1]));
	};

	public static List<Professeur> readProfesseurs(String fileName) throws IOException {
		File file = new File(fileName);
		List<Professeur> professeurs = new ArrayList<>();

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);) {

			professeurs = br.lines()
					.skip(1)
					.filter(s -> isNotEmpty.test(s))
					.map(s->s.replaceFirst("	", ";").replace("	", ""))
					.map(lineToProfesseur)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return professeurs;
	}
	
	static Function<String, ProfesseursInstruments> lineToProfesseursInstruments = string -> {
		return new ProfesseursInstruments(string.split(";")[0], string.split(";")[1]);
	};
	
	public static List<ProfesseursInstruments> readProfesseursInstruments(String fileName) throws IOException {
		File file = new File(fileName);
		List<ProfesseursInstruments> profInstruments= new ArrayList<>();

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);) {

			profInstruments = br.lines()
					.skip(1)
					.filter(s -> isNotEmpty.test(s))
					.map(s->s.replaceFirst("	", ";").replace("	", ""))
					.map(lineToProfesseursInstruments)
					.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return profInstruments;
	}

	private static Pattern splitter = Pattern.compile("[\\s]+");
	private static Function<String, List<String>> lineToInstruments =
			line -> splitter.splitAsStream(line)
			.collect(Collectors.toList());
	
	static Predicate<String> isNotComment = string -> !string.startsWith("#");

	public static List<Instruments> readInstruments(String fileName) throws IOException {
		File file = new File(fileName);
		List<List<String>> items = new ArrayList<>();
		List<Instruments> instruments = new ArrayList<>();

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);) {

			items = br.lines()
					.filter(s -> isNotEmpty.and(isNotComment).test(s))
					.skip(1)
					.map(lineToInstruments)
					.collect(Collectors.toList());
	
			for (List<String> item  : items) {
				if(item.size()==4)
					instruments.add(new Instruments(item.get(0), Integer.parseInt(item.get(1)), Integer.parseInt(item.get(2)), item.get(3)));
				else
					instruments.add(new Instruments(item.get(0), Integer.parseInt(item.get(1))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return instruments;
	}
	
	private static Function<String, List<String>> lineToElevesInstruments =
			line -> splitter.splitAsStream(line)
			.collect(Collectors.toList());
	
	public static List<ElevesInstruments> readElevesInstruments(String fileName) throws IOException {
		File file = new File(fileName);
		List<List<String>> items = new ArrayList<>();
		List<ElevesInstruments> elevesInstruments = new ArrayList<>();

		try (FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);) {

			items = br.lines()
					.filter(s -> isNotEmpty.and(isNotComment).test(s))
					.skip(1)
					.map(lineToElevesInstruments)
					.collect(Collectors.toList());
	
			for (List<String> item  : items) {
				for (int i = 1; i < item.size(); i++) {
					elevesInstruments.add(new ElevesInstruments(item.get(0), item.get(i)));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return elevesInstruments;
	}
	
	public static void readAndExecuteSQLQueries(Connection conn, InputStream in) throws SQLException {
		
	    try (Scanner s = new Scanner(in);){
	    	s.useDelimiter("(;(\r)?\n)|(--\n)");
		    Statement statement = conn.createStatement();
	        while (s.hasNext())
	        {
	            String line = s.next();
	            if (line.trim().length() > 0) {
	            	statement.execute(line);
	            }
	        }
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
