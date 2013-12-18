package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;

public class BotClassic extends Bot{
	
	public boolean isPiocher(Joueur joueur) {
		
		Carte carteVisible = Talon.getInstanceTalon().getLast();
		
		for(Carte c : joueur.getMainJoueur()) {
			if (c.isPosableSur(carteVisible));
				return true;
		}
		return false;
	}

	public int choisirIndexCarte(Joueur joueur) {
		
		Carte carteVisible = Talon.getInstanceTalon().getLast();
		
		for(Carte c : joueur.getMainJoueur()) {
			if (c.isPosableSur(carteVisible));
				return joueur.getMainJoueur().indexOf(c);
		}
		return joueur.getMainJoueur().size();
	}

	public boolean annncerUNO(Joueur joueur) {
		
		if(joueur.getNbCarte() == 1)
			return true;
		else
			return false;
	}

	public boolean annoncerContreUnO(Joueur joueur) {
		return false;
	}

	public Couleur choisirCouleur(Joueur joueur) {
		
		return Couleur.BLEU;
	}
	
	public boolean contesterPlus4(Joueur joueur) {
		return false;
	}
}
