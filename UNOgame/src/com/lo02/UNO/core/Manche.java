package com.lo02.UNO.core;

import java.util.ArrayList;

public class Manche {

	private int sens = 1;
	private int joueurCourrant;
	
	private Pioche pioche;
	private Talon talon;
	private ArrayList<Joueur> joueurs;
	
	public Manche (ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
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
