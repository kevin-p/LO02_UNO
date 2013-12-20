package com.lo02.UNO.core;

import java.util.ArrayList;
import com.lo02.UNO.core.cartes.Carte;

/**
 * 
 * Représente la main d'un joueur.
 * Extends {@link ArrayList} de {@link Carte}
 * 
 * Stocke aussi des méthode lié à l'organisation de la main d'un joueur
 * 
 * @author Kevin personnic
 *
 */
public class MainJoueur extends ArrayList<Carte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7331662739769106381L;

	/**
	 * 
	 * Constructeur par defaut
	 * @see Joueur#Joueur()
	 * @see Joueur#detruireMain()
	 * 
	 */
	public MainJoueur () {}
	
	public int getValeurTotal () {
		int point = 0;
		
		for(Carte carte : this) {
			point+=carte.getLabel().valeur();
		}
		
		return point;
	}
}
