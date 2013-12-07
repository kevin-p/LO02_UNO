package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.*;;

public class SensInterdit extends Carte{
	
	public SensInterdit (Couleur couleur) {
		this.setCouleur(couleur);
		this.setLabel(Label.SINT);
	}
	
	public void action (Manche manche, Joueur joueur) {
		manche.AvancerJoueur();
		System.out.println("avancer joueur");
	}
}
