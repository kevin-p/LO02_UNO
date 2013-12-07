package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;

public class Joker extends Carte{
	
	public Joker() {
		this.setCouleur(Couleur.NOIR);
		this.setLabel(Label.JOKER);
	}
	
	public void action (Manche manche, Joueur joueur) {
		
	}
}