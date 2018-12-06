package org.konate.bdd.serie1.exo1.model;

public class Professeur {
	private String nom;
	private int age, instrument;
	public Professeur(String nom, int age) {
		super();
		this.nom = nom;
		this.age = age;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getInstrument() {
		return instrument;
	}
	public void setInstrument(int instrument) {
		this.instrument = instrument;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "Professeur [nom=" + nom + ", age=" + age + "]";
	}
}
