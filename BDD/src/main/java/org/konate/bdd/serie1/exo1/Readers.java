package org.konate.bdd.serie1.exo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
					instruments.add(new Instruments(item.get(0), Double.parseDouble(item.get(1)), Double.parseDouble(item.get(2)), item.get(3)));
				else
					instruments.add(new Instruments(item.get(0), Double.parseDouble(item.get(1))));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return instruments;
	}
}
