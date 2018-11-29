package org.konate.bdd.serie1.exo1;

public class Eleve {
	private String nom;
	private int age;
	
	public Eleve(String nom, int age) {
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
		return "Eleve [nom=" + nom + ", age=" + age + "]";
	}
	
}
