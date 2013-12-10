package com.lo02.UNO.core;

import java.util.ArrayList;

import com.lo02.UNO.core.cartes.Carte;

public class Manche {
	
	private static Manche SingleManche = null;

	private int sens = 1;
	private int joueurCourrant;
	
	private Pioche pioche;
	private Talon talon;
	private ArrayList<Joueur> joueurs;
	
	private Manche () {
		this.joueurs = new ArrayList<Joueur>();
		
		creerTalon();
		creerPioche();
	}
	
	public synchronized static Manche getInstanceManche() {
		if (SingleManche == null)
			SingleManche = new Manche();
		
		return SingleManche;
	}
	
	public void reset() {
		
		for (Joueur j : joueurs) {
			j.detruireMain();
		}
		
		Pioche.reset();
		Talon.reset();
		distribuerCarte();
	}
	
	public void lancerManche() {
		joueurCourrant = designerPremierJoueur(joueurs.size());
		
		boolean mancheFinie = false;
		
		do {
			joueurs.get(joueurCourrant).jouer();
			AvancerJoueur();
			
			//remplacer par exception
			for (Joueur j : joueurs) {
				if(j.getNbCarte() == 0)
					mancheFinie = true;
			}
		}while(!mancheFinie);
		
		for (Joueur j : joueurs) {
			j.compterPoint();
		}
	}
	
	
	public boolean poserCarte(Carte carte, Joueur joueur) {
//		System.out.println("haut du tas");
//		talon.getLast().afficher();
//		System.out.println(" | fin haut du tas");

		if (!carte.isPosableSur(talon.getLast())) {
			joueur.piocher(2);
			System.out.println("carte rejetee");
			return false;
		}
		else {
			talon.add(carte);
			carte.action(this, joueur);
			System.out.println("carte acceptee");
			return true;
		}
	}
	
	public void changeSens () {
		sens = sens * -1;
	}
	
	public void creerTalon () {
		talon = Talon.getInstanceTalon();
	}
	
	public void creerPioche () {
		pioche = Pioche.getInstancePioche();
	}
	
	public void distribuerCarte () {
//		System.out.println("distribution à : " + this.joueurs.size());
		for (Joueur j : this.joueurs) {
//			System.out.println(j.getNom());
			j.piocher(7);
		}
	}
	
	public int designerPremierJoueur(int nbJoueurs) {
		return (int)(Math.random() * nbJoueurs);
	}
	
	public void setJoueurs(ArrayList<Joueur> joueurs) {
//		System.out.println("ajout des joueurs");
		this.joueurs.addAll(joueurs);
//		System.out.println("nb joueurs : " + this.joueurs.size());
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	public Joueur getJoueurCourant() {
		return joueurs.get(joueurCourrant);
	}
	
	public Joueur getJoueurSuivant() {
		return joueurs.get((joueurs.size() + joueurCourrant + sens) % joueurs.size());
	}
	
	public void AvancerJoueur() {
		joueurCourrant = (joueurs.size() + joueurCourrant + sens) % joueurs.size();
	}
}
