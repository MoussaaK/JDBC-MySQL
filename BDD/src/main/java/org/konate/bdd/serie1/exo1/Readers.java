package org.konate.bdd.serie1.exo1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Readers {
	static Function<String, Eleve> lineToEleve = string -> {
		return new Eleve(string.split(";")[0], Integer.parseInt(string.split(";")[1]));
	};
	static Predicate<String> isNotEmpty = string -> !string.isEmpty();
	
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
}
