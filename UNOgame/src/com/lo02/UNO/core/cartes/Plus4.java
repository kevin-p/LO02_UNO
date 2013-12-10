package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;
import com.lo02.UNO.core.Talon;

public class Plus4 extends Carte{
	
	public Plus4() {
		this.setCouleur(Couleur.NOIR);
		this.setLabel(Label.PLUS4);
	}
	
	public void action (Manche manche, Joueur joueur) {
		if(manche.getJoueurSuivant().isContestPlus4()){
			joueur.AfficherMain();
			
			if(joueur.isLegitimePlus4()){
				System.out.println("Le plus 4 est légitime le joueur "+manche.getJoueurSuivant().getNom()+" recoit 6 cartes");
				manche.getJoueurSuivant().piocher(6);
				this.setCouleur(joueur.choisirCouleur());
				manche.AvancerJoueur();
				}
			
			else{
				Talon talon = Talon.getInstanceTalon();
				talon.remove(talon.size()-1);
				joueur.addCarte(this);
				System.out.println("Plus 4 non légitime le joueur "+joueur.getNom()+" recoit une penalite");
				joueur.piocher(2);
			}
		}
		else{
			manche.getJoueurSuivant().piocher(4);
			this.setCouleur(joueur.choisirCouleur());
			manche.AvancerJoueur();
		}
	}
}