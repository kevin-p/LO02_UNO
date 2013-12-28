package com.lo02.UNO.core;

import java.util.ArrayList;

import com.lo02.UNO.GUI.ConsoleUI;

public class Partie {
	private ArrayList<Joueur> joueurs;

/*	public Partie () {
		joueurs = new ArrayList<Joueur>();
		int nombreJoueur = ConsoleUI.choisirNbHumain();
		for (int i = 0; i < nombreJoueur; ++i){
			joueurs.add(new Joueur(ConsoleUI.nommerJoueur(i)));
		}
		
		int nombreBot = ConsoleUI.choisirNbBot();
		
		for (int i = 0; i < nombreBot; ++i) {
			joueurs.add(new JoueurVirtuel());
		}
	}
*/
	public Partie(){
		
	}
	public void initialiserPartie(int nbHumain,int nbBot,String []nom){
		joueurs = new ArrayList<Joueur>();
		int nombreJoueurHumain = nbHumain;

		
		for (int i = 0; i < nombreJoueurHumain; ++i){
			joueurs.add(new Joueur(nom[i]));
		}
		
		int nombreBot =nbBot;
		
		for (int i = 0; i < nombreBot; ++i) {
			joueurs.add(new JoueurVirtuel());
		}
		
	}
	
	public void lancerPartie() {
		
		boolean finPartie = false;
		
		Manche manche = Manche.getInstanceManche();
		manche.setJoueurs(joueurs);
		//manche.distribuerCarte();

		int nbManche=0;
		do{
			nbManche++;
			manche.distribuerCarte();
			manche.lancerManche();
			manche.reset();
			for (Joueur j : joueurs) {
				if(j.getPoint()>1000){
					finPartie = true;
					System.out.println("La partie est finie, les scores :");
					for (Joueur joueur : joueurs) {
						if(joueur.getPoint()>1000){
							System.out.println("Le joueur "+joueur.getNom()+" a gagné avec "+joueur.getPoint());
						}
						else {
							System.out.println("Le joueur "+joueur.getNom()+" a perdu avec "+joueur.getPoint());
						}
					}
				}
			}
		
			
		}while(!finPartie);
		System.out.println("Nombre de manches pour finir a partie :"+nbManche);
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}

}
