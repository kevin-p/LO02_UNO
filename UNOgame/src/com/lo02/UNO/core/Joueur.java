package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;

import java.io.*;

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
		Manche manche = Manche.getInstanceManche();
		
		if(manche.poserCarte(mainJoueur.get(IndexCarte), this))
			mainJoueur.remove(IndexCarte);
	}
	
	public void AfficherMain() {
		for(Carte carte : mainJoueur) {
			System.out.print(" | " + carte.getLabel()+ ":" + carte.getCouleur());
		}
	}
	
	public Couleur choisirCouleur() {
		//à changer pour fontionner de pair avec l'interface graphique
		for (;;) {
			System.out.println(this.nom + " choisissez une couleur (ROUGE/R/r, BLEU/B/b, VERT/V/v, JAUNE/J/j");
			InputStreamReader lecteur = new InputStreamReader(System.in);
			BufferedReader entree = new BufferedReader(lecteur);
			String choix = new String();
			try {
				choix = entree.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(choix == "ROUGE" || choix == "R" || choix == "r")
				return Couleur.ROUGE;
			if(choix == "BlEU" || choix == "B" || choix == "b")
				return Couleur.BLEU;
			if(choix == "VERT" || choix == "V" || choix == "v")
				return Couleur.VERT;
			if(choix == "JAUNE" || choix == "J" || choix == "j")
				return Couleur.JAUNE;
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
