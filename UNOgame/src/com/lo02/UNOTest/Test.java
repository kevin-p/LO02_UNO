package com.lo02.UNOTest;

import java.util.ArrayList;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.JoueurVirtuel;
import com.lo02.UNO.core.Manche;
import com.lo02.UNO.core.Pioche;
import com.lo02.UNO.core.Talon;
import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Label;

public class Test {

	public static void TestLabel () {
		for (Label l : Label.values())
			System.out.println("carte : " + l.label() + ", point : " + l.valeur());
		
	}
	
	public static void TestCouleur () {
		for (Couleur c : Couleur.values())
			System.out.println(c);
	}
	
	public static void TestPioche () {
		Pioche pioche = Pioche.getInstancePioche();
		for (int i = 0; i < pioche.size(); ++i)
		{
			System.out.println(pioche.get(i).getCouleur() + " : " + pioche.get(i).getLabel().label());
			System.out.println(pioche.get(i));
		}
	}
	
	public static void TestDistribution () {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(new Joueur("Ano1"));
		joueurs.add(new Joueur("Ano2"));
		joueurs.add(new Joueur("Ano3"));
		
//		System.out.println("creation manche");
		Manche manche = Manche.getInstanceManche();
//		System.out.println("fin creation manche");
		manche.setJoueurs(joueurs);
		manche.distribuerCarte();
//		System.out.println("fin affectation joueurs");
		
		for (Joueur joueur : joueurs) {
			joueur.AfficherMain();
			System.out.print("\n");
		}
	}
	
	public static void TestSpecialCarte () {
		Pioche pioche = Pioche.getInstancePioche();
		Talon talon = Talon.getInstanceTalon();
		
		Carte carte = pioche.piocher(1).get(0);
		
		if(carte.isPosableSur(talon.getLast()))
			System.out.println("Special carte OK");
		else
			System.out.println("Special carte Fail");
		
	}
	
	public static void TestManche() {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		joueurs.add(new Joueur("Ano1"));
		joueurs.add(new Joueur("Ano2"));
		joueurs.add(new Joueur("Ano3"));
		
//		System.out.println("creation manche");
		Manche manche = Manche.getInstanceManche();
//		System.out.println("fin creation manche");
		manche.setJoueurs(joueurs);
		manche.distribuerCarte();
//		System.out.println("fin affectation joueurs");
		
		manche.lancerManche();
	}
	
	public static void TestDemarage(){
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		int nombreJoueur = ConsoleUI.choisirNbHumain();
		
		for (int i=0;i<nombreJoueur;i++){
			joueurs.add(new Joueur(ConsoleUI.nommerJoueur(i)));
		}
	}
	
	public static void TestBot() {
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		//joueurs.add(new Joueur("Ano1"));
		joueurs.add(new JoueurVirtuel());
		joueurs.add(new JoueurVirtuel());

		
//		System.out.println("creation manche");
		Manche manche = Manche.getInstanceManche();
//		System.out.println("fin creation manche");
		manche.setJoueurs(joueurs);
		manche.distribuerCarte();
//		System.out.println("fin affectation joueurs");
		
		manche.lancerManche();
	}
	
	public static void testFinirJeu(){
		ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
		//joueurs.add(new Joueur("Ano1"));
		joueurs.add(new JoueurVirtuel());
		joueurs.add(new JoueurVirtuel());
		joueurs.add(new JoueurVirtuel());
		joueurs.add(new JoueurVirtuel());
		joueurs.add(new JoueurVirtuel());
		boolean finPartie = false;
		System.out.println("creation manche");
		Manche manche = Manche.getInstanceManche();
//		System.out.println("fin creation manche");
		manche.setJoueurs(joueurs);
		manche.distribuerCarte();
//		System.out.println("fin affectation joueurs");
		int nbManche=0;
		do{
			nbManche++;
			manche.distribuerCarte();
			manche.lancerManche();
			manche.reset();
			for (Joueur j : joueurs) {
				
				if(j.getPoint()>500){
					System.out.println("Fin : Le joueur "+j.getNom()+" a obtenu "+j.getPoint());
					finPartie=true;
				}
			}
		
			
		}while(!finPartie);
		System.out.println("NBMANCHE :"+nbManche);

		
	}
	

	/*
	public static void main(String[] args) {
//		TestLabel();
//		TestCouleur();
//		TestPioche();
//		TestDistribution();
//		TestSpecialCarte();
//		TestManche();
//		TestDemarage();
//		TestBot();	
//		testFinirJeu();
	}
*/
}
