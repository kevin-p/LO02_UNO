package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;
import com.lo02.UNO.core.Talon;

public class Plus4 extends Carte{
	
	public Plus4() {
		this.setCouleur(Couleur.NOIR);
		this.setLabel(Label.PLUS4);
	}
	
	private boolean posable=true;
	
	public void action (Manche manche, Joueur joueur) {
		if(manche.getJoueurSuivant().isContestPlus4()){
			joueur.AfficherMain();
			
			if(joueur.isLegitimePlus4()){
				 posable=true;
				System.out.println("Le plus 4 est légitime le joueur "+manche.getJoueurSuivant().getNom()+" recoit 6 cartes");
				manche.getJoueurSuivant().piocher(6);
				this.setCouleur(joueur.choisirCouleur());
				manche.AvancerJoueur();
				}
			
			else{
				Talon talon = Talon.getInstanceTalon();
				talon.remove(talon.size()-1);
				joueur.piocher(2);
				posable=false;
			}
		}
		else{
			 posable=true;
			manche.getJoueurSuivant().piocher(4);
			this.setCouleur(joueur.choisirCouleur());
			manche.AvancerJoueur();
		}
	}
	public boolean getPosable(){
		return posable;
	}
	public void reset(){
		this.setCouleur(Couleur.NOIR);
	}
}