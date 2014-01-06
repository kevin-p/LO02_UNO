package com.lo02.UNO.core;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import com.lo02.UNO.GUI.ConsoleUI;

public class Partie extends Observable{
	private ArrayList<Joueur> joueurs;
	private ArrayList<Observer> observers = new ArrayList<Observer>();

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
		manche.distribuerCarte();
		do{
			nbManche++;
			manche.lancerManche();
			manche.reset();
			notifyObservers("Fin de la manche : "+nbManche+"\n");
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
			notifyObservers();
		
			
		}while(!finPartie);
		System.out.println("Nombre de manches pour finir a partie :"+nbManche);
		
	}
	
	public ArrayList<Joueur> getJoueurs() {
		return joueurs;
	}

	public void setJoueurs(ArrayList<Joueur> joueurs) {
		this.joueurs = joueurs;
	}
	
	public void notifyObservers() {

		 for (Observer ob : observers) {
            ob.update(this, joueurs);
     }

	}

	public void addObserver(Observer observer) {
		 observers.add(observer);
		
	}

	public void removeObserver(Observer observer) {
		 observers.remove(observer);
		
	}



}
