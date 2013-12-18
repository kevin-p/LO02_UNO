package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Couleur;

public interface Strategie {
	public boolean isPiocher(Joueur joueur);
	public int choisirIndexCarte(Joueur joueur);
	public boolean annncerUNO(Joueur joueur);
	public boolean annoncerContreUnO(Joueur joueur);
	public Couleur choisirCouleur(Joueur joueur);
	public boolean contesterPlus4(Joueur joueur);
}
