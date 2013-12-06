package com.lo02.UNO.core;

public class Joueur {
	
	private String nom;
	private int point;
	private MainJoueur mainJoueur;
	
	public void piocher(int nbCarte) {
		Pioche pioche = Pioche.getInstancePioche();
		mainJoueur.addAll(pioche.piocher(nbCarte));
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getPoint() {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}

}
