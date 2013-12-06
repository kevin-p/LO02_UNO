package com.lo02.UNO.core;

import java.util.ArrayList;

import com.lo02.UNO.core.cartes.Carte;

public class Manche {

	private int sens = 1;
	private int joueurCourrant;
	
	private Pioche pioche;
	private Talon talon;
	private ArrayList<Joueur> joueurs;
	
	public Manche (ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
		
		creerTalon();
		creerPioche();
		distribuerCarte();
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
		for (Joueur j : joueurs) {
			j.piocher(7);
		}
	}
	
	public boolean poserCarte(Carte carte, Joueur joueur) {
		if (!carte.isPosableSur(pioche.getLast())) {
			joueur.piocher(2);
			return false;
		}
		else {
			talon.add(carte);
			return true;
		}
	}
	
	public void recupererCartes () {
		//recupérer les cartes des joueurs
	}

	public Joueur getJoueurCourant() {
		return joueurs.get(joueurCourrant);
	}
	
	public Joueur getJoueurPorchain() {
		return joueurs.get((joueurs.size() + joueurCourrant + sens) % joueurs.size());
	}
	
	public void AvancerJoueur() {
		joueurCourrant = (joueurs.size() + joueurCourrant + sens) % joueurs.size();
	}
}
