package com.lo02.UNOTest;

import java.util.ArrayList;

import com.lo02.UNO.core.cartes.*;
import com.lo02.UNO.core.*;

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
	
	public static void main(String[] args) {
//		TestLabel();
//		TestCouleur();
//		TestPioche();
//		TestDistribution();
//		TestSpecialCarte();
		TestManche();
		
	}

}