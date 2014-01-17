package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Couleur;
/**
 * 
 * Classe mère pour les différentes implémentation des bots
 * 
 * @author Kevin
 *
 */
public class Bot implements Strategie{

	@Override
	public boolean isPiocher(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int choisirIndexCarte(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean annncerUNO(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean annoncerContreUnO(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couleur choisirCouleur(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return Couleur.BLEU;
	}

	@Override
	public boolean contesterPlus4(Joueur joueur) {
		System.err.println("ne devrait pas être appelé");
		// TODO Auto-generated method stub
		return false;
	}
	
}
