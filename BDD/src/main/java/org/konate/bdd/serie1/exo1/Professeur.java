package org.konate.bdd.serie1.exo1;

public class Professeur {
	private String nom;
	private int age;
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
