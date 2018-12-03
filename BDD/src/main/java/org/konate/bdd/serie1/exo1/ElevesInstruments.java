package org.konate.bdd.serie1.exo1;

public class ElevesInstruments {
	private String nom;
	private String instrument;
	public ElevesInstruments(String nom, String instrument) {
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
	public String getinstrument() {
		return instrument;
	}
	public void setinstrument(String instrument) {
		this.instrument = instrument;
	}
	@Override
	public String toString() {
		return "Elevesinstrument [nom=" + nom + ", instrument=" + instrument + "]";
	}
}
