package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;

public class Plus4 extends Carte{
	
	public Plus4() {
		this.setCouleur(Couleur.NOIR);
		this.setLabel(Label.PLUS4);
	}
	
	public void action (Manche manche, Joueur joueur) {
		if(manche.getJoueurSuivant().isContestPlus4()){
			joueur.AfficherMain();
			
			if(joueur.isLegitimePlus4()){
				manche.getJoueurSuivant().piocher(4);
				this.setCouleur(joueur.choisirCouleur());
				manche.AvancerJoueur();
				}
			
			else{
				joueur.piocher(2);
			}
		}
		else{
		manche.getJoueurSuivant().piocher(4);
		this.setCouleur(joueur.choisirCouleur());
		manche.AvancerJoueur();}
	}
}