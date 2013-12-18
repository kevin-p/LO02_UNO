package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Couleur;

public class Bot implements Strategie{

	@Override
	public boolean isPiocher(Joueur joueur) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int choisirIndexCarte(Joueur joueur) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean annncerUNO(Joueur joueur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean annoncerContreUnO(Joueur joueur) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Couleur choisirCouleur(Joueur joueur) {
		// TODO Auto-generated method stub
		return Couleur.BLEU;
	}

	@Override
	public boolean contesterPlus4(Joueur joueur) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
