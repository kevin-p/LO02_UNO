package com.lo02.UNO.core;

import java.awt.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
/**
 * 
 * IA n'ayant pas de directive de jeu particulière
 * 
 * @author Kevin
 *
 */
public class BotClassic extends Bot{
	
	public boolean isPiocher(Joueur joueur) {
		
		Carte carteVisible = Talon.getInstanceTalon().getLast();
		
		for(Carte c : joueur.getMainJoueur()) {
			if (c.isPosableSur(carteVisible));
				return false;
		}
		return true;
	}

	public int choisirIndexCarte(Joueur joueur) {
		
		Carte carteVisible = Talon.getInstanceTalon().getLast();
//		joueur.AfficherMain();
		for(Carte c : joueur.getMainJoueur()) {
			if (c.isPosableSur(carteVisible))
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
		int nbPointBleu=0;
		int nbPointRouge=0;
		int nbPointJaune=0;
		int nbPointVert=0;
		for(Carte c : joueur.getMainJoueur()) {
			switch (c.getCouleur()){
			case BLEU : nbPointBleu+=c.getLabel().valeur(); break;
			case JAUNE : nbPointJaune+=c.getLabel().valeur(); break;
			case VERT : nbPointVert+=c.getLabel().valeur(); break;
			case ROUGE : nbPointRouge+=c.getLabel().valeur(); break;
			default : ;break;
			}
		}
			
			int max;
			max = Math.max(nbPointBleu, Math.max(nbPointRouge, Math.max(nbPointJaune, nbPointVert)));
			
			if (nbPointBleu==max)
				return Couleur.BLEU;
			if(nbPointRouge==max) 
				return Couleur.ROUGE; 
			if(nbPointJaune==max)
				return Couleur.JAUNE; 			
			
			return Couleur.VERT; 
					
	}
	
	public boolean contesterPlus4(Joueur joueur) {
		return false;
	}
}
