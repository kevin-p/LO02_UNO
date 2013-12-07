package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Carte;

public class Joueur {
	
	private String nom;
	private int point = 0;
	private MainJoueur mainJoueur = new MainJoueur();
	
	public Joueur () {}
	
	public Joueur (String nom) {
		this.nom = nom;
	}
	
	public Joueur (Joueur joueur) {
		this.nom = joueur.nom;
		this.point = joueur.point;
		this.mainJoueur.addAll(joueur.mainJoueur);
	}
	
	public void detruireMain () {
		mainJoueur = new MainJoueur();
	}
	
	public void piocher(int nbCarte) {
//		System.out.println("recup instance");
		Pioche pioche = Pioche.getInstancePioche();
//		System.out.println("joueur pioche");
		mainJoueur.addAll(pioche.piocher(nbCarte));
	}
	
	public void poser(int IndexCarte) {
		
	}
	
	public void AfficherMain() {
		for(Carte carte : mainJoueur) {
			System.out.print(" | " + carte.getLabel()+ ":" + carte.getCouleur());
		}
	}
	
	public void choisirCouleur() {
		
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
