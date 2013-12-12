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
	
	/**
	 * 
	 * Stocke le singleton de {@link Talon}
	 * 
	 */
	private static Talon SingleTalon = null;

	/**
	 * 
	 * constructeur privé de {@link Talon} car singleton
	 * 
	 * @see #getInstanceTalon()
	 * 
	 */
	private Talon() {
		this.add(new SpecialCarte());
//		System.out.println(this.size());
	}
	
	private Talon(Carte c){
		this.add(c);
	}
	/**
	 * 
	 * Renvoi une référence sur le singleton de {@link Talon}
	 * 
	 * @return {@link #SingleTalon} {@link Talon}
	 * @see Joueur#isLegitimePlus4()
	 * @see Joueur#jouer()
	 * @see Plus4#action(Manche, Joueur)
	 */
	public synchronized static Talon getInstanceTalon() {
		
		if (SingleTalon == null)
			SingleTalon = new Talon();
		
		return SingleTalon;
	}
	
	/**
	 * 
	 * permet de reconstuire un nouvel objet {@link Talon}
	 * 
	 * @return {@link #SingleTalon} {@link Talon}
	 * @see Manche#reset()
	 */
	public synchronized static Talon reset() {
		return SingleTalon = new Talon();
	}
	
	/**
	 * 
	 * renvoi la dernière {@link Carte} du {@link Talon}
	 * 
	 * @return lastCarte {@link Carte}
	 * @see Joueur#isLegitimePlus4()
	 * @see Joueur#jouer()
	 * @see Manche#poserCarte(Carte, Joueur)
	 */
	public Carte getLast() {
		return this.get(size()-1);
	}	
}


