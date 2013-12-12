package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;

import java.util.ArrayList;

/**
 * 
 * Classe représentant le talon dans une partie de UNO.
 * Extends {@link ArrayList} de type {@link Carte}.
 * Est un singleton.
 * 
 * @author Kevin Personnic
 *
 */
public class Talon extends ArrayList<Carte> {
	
	private static final long serialVersionUID = -3455800499341853695L;
	
	
	private static Talon SingleTalon = null;
	private Couleur couleurJeu = Couleur.NOIR;

	private Talon() {
		this.add(new SpecialCarte());
//		System.out.println(this.size());
	}
	
	public synchronized static Talon getInstanceTalon() {
		
		if (SingleTalon == null)
			SingleTalon = new Talon();
		
		return SingleTalon;
	}
	
	public synchronized static Talon reset() {
		return SingleTalon = new Talon();
	}
	
	public Carte getLast() {
		return this.get(size()-1);
	}

	public Couleur getCouleurJeu() {
		return couleurJeu;
	}

	public void setCouleurJeu(Couleur couleurJeu) {
		this.couleurJeu = couleurJeu;
	}
	
}


