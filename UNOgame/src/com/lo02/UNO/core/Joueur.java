package com.lo02.UNO.core;

import java.util.ArrayList;

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
	
	public void piocher(int nbCarte) {
		Pioche pioche = Pioche.getInstancePioche();
		
		mainJoueur.addAll(pioche.piocher(nbCarte));
	}
	
	public void AfficherMain() {
		for(Carte carte : mainJoueur) {
			System.out.print(" | " + carte.getLabel()+ ":" + carte.getCouleur());
		}
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
