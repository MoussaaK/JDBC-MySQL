package org.konate.bdd.serie1.exo1;

public class Instruments {
	private String nom;
	private double prix, prixCours;
	private String location;
	public Instruments(String nom, double prix, double prixCours, String location) {
		super();
		this.nom = nom;
		this.prix = prix;
		this.prixCours = prixCours;
		this.location = location;
	}
	public Instruments(String nom, double prix) {
		super();
		this.nom = nom;
		this.prix = prix;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}
	public double getPrixCours() {
		return prixCours;
	}
	public void setPrixCours(double prixCours) {
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
