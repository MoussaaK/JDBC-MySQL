package org.konate.bdd.serie1.exo1;

public class ProfesseursInstruments {
	private String nom, instrument;
	
	public ProfesseursInstruments(String nom, String instrument) {
		super();
		this.nom = nom;
		this.instrument = instrument;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getInstrument() {
		return instrument;
	}

	public void setInstrument(String instrument) {
		this.instrument = instrument;
	}
}
