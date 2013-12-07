package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;

public class Plus2 extends Carte{
	
	public Plus2(Couleur couleur) {
		this.setCouleur(couleur);
		this.setLabel(Label.PLUS2);
	}
	
	public void action (Manche manche, Joueur joueur) {
//		System.out.println("je suis action special de plus2");
		manche.getJoueurSuivant().piocher(2);
		manche.AvancerJoueur();
	}
	
	public void action () {
		System.out.println("action plus2");
	}
}
