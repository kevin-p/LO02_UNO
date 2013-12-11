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
 * @author Kevin
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
	 * 
	 */
	public MainJoueur () {}
	
	
	/**
	 * 
	 * Range les {@link Carte} de la {@link MainJoueur} par ordre croissant
	 * 
	 */
	public void rangerCroiss() {
		
	}
	
	/**
	 * 
	 * Range les {@link Carte} de la {@link MainJoueur} par ordre décroissant
	 * 
	 */
	public void rangerDesc() {
		
	}
}
