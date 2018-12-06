package org.konate.bdd.serie1.exo1.model;

public class Instruments {
	private String nom;
	private int prix, prixCours;
	private String location;
	public Instruments(String nom, int prix, int prixCours, String location) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.prixCours = prixCours;
		this.location = location;
	}
	public Instruments(String nom, int prix) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.prixCours = 0;
		this.location = "";
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getPrixCours() {
		return prixCours;
	}
	public void setPrixCours(int prixCours) {
		this.prixCours = prixCours;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Override
	public String toString() {
		return "Instruments [nom=" + nom + ", prix=" + prix + ", prixCours=" + prixCours + ", location=" + location
				+ "]";
	}
}
