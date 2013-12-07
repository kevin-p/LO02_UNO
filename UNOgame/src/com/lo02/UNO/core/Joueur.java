package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Label;

import java.util.*;

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
	
	public void jouer() {
		//a modifier au moment de l'écriture de l'interface graphique
		int numCarte = 0;
		Scanner sc = new Scanner(System.in);
		
		Talon talon = Talon.getInstanceTalon();
		talon.getLast().afficher();System.out.println();
		
		AfficherMain();
		
		System.out.println(this.nom + " choisissez la carte à poser (nombre de 1 à " + mainJoueur.size() + ") : ");
		numCarte = sc.nextInt();
		System.out.println(numCarte + ":" + mainJoueur.size());
		if(numCarte >= 1 && numCarte <= mainJoueur.size()) {
			poser(numCarte - 1);
		}
		else {
			piocher(1);
			
			AfficherMain();
			
			System.out.println(this.nom + " choisissez la carte à poser (nombre de 1 à " + mainJoueur.size() +
								") ou 0 pour passer : ");
			numCarte = sc.nextInt();
			
			if(numCarte >= 1 && numCarte <= mainJoueur.size())
				poser(numCarte - 1);
		}
	}
	
	public void piocher(int nbCarte) {
//		System.out.println("recup instance");
		Pioche pioche = Pioche.getInstancePioche();
//		System.out.println("joueur pioche");
		mainJoueur.addAll(pioche.piocher(nbCarte));
	}
	
	public void poser(int IndexCarte) {
		Manche manche = Manche.getInstanceManche();
		mainJoueur.get(IndexCarte).action(manche, this);
		if(manche.poserCarte(mainJoueur.get(IndexCarte), this))
			mainJoueur.remove(IndexCarte);
	}
	
	public void AfficherMain() {
		for(Carte carte : mainJoueur) {
			carte.afficher();
		}
		System.out.println();
	}
	
	public Couleur choisirCouleur() {
		//à changer pour fontionner de pair avec l'interface graphique
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.println(this.nom + " choisissez une couleur (ROUGE/R/r, BLEU/B/b, VERT/V/v, JAUNE/J/j");
			String choix = sc.nextLine();
			
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
	
	public boolean isContestPlus4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(this.nom + " contestez vous un Plus4 ? (OUI/O/o : ");
		String choix = sc.nextLine();
		if(choix == "OUI" || choix == "O" || choix == "o")
			return true;
		else
			return false;
	}
	
	public boolean isLegitimePlus4(){
		boolean legitime=true;
		Talon talon = Talon.getInstanceTalon();
		for(Carte carte : mainJoueur) {
			if(carte.getLabel()!= Label.PLUS4 && carte.isPosableSur(talon.getLast())){ // compare toutes les cartes sauf les +4
				legitime=false;
			}
		}
		return legitime;
		
	}
	
	public void compterPoint(){
		
		for(Carte carte : mainJoueur) {
			point+=carte.getLabel().valeur();
		}
	}
	public int getNbCarte() {
		return mainJoueur.size();
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
