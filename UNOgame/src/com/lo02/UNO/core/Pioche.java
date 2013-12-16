package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * Représsente la pioche du jeu de UNO
 * Extends {@link ArrayList} de {@link Carte}
 * Est un singleton
 * 
 * @author Kevin Personnic
 *
 */
public class Pioche extends ArrayList<Carte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4808339253578125526L;
	
	/**
	 * 
	 * Stocke l'instance de la {@link Pioche}
	 * 
	 */
	private static Pioche SinglePioche = null;

	/**
	 * 
	 * constructeur privé de {@link Pioche}
	 * Génère et mékange un paquet de carte de UNO
	 * 
	 */
	private Pioche() {
		
		for (Couleur c : Couleur.values()) {
			if(c != Couleur.NOIR){
				for (Label l : Label.values()) {
					
					Carte carte = CarteFactory.getCarte(l, c);
					this.add(carte);
					
					if(l != Label.ZERO && l != Label.PLUS4 && l != Label.JOKER) {
						this.add(carte);
					}
				}
			}
		}
		
		Collections.shuffle(this);
	}
	
	/**
	 * 
	 * Permet de récupérer l'instance de la {@link Pioche}
	 * 
	 * @return {@link #SinglePioche} {@link Pioche}
	 * @see Joueur#piocher(int)
	 * @see Manche#creerPioche()
	 */
	public synchronized static Pioche getInstancePioche() {
		
		if (SinglePioche == null)
			SinglePioche = new Pioche();
		
		return SinglePioche;
	}
	
	/**
	 * 
	 * Permet de forcer la reconstruction de la pioche
	 * 
	 * @return {@link #SinglePioche} {@link Pioche}
	 * @see Manche#reset()
	 */
	public synchronized static Pioche reset() {
		return SinglePioche = new Pioche();
	}
	
	/**
	 * 
	 * Renvoi la dernière {@link Carte} de la {@link Pioche}
	 * 
	 * @return lastCarte {@link Carte}
	 */
	public Carte getLast() {
		return this.get(size()-1);
	}
	
	/**
	 * 
	 * Renvoi une liste de carte pioché dans la pioche et les supprimes de la pioche
	 * 
	 * @param nbCarte {@link Integer} nombre de {@link Carte} à piocher
	 * @return {@link ArrayList} de {@link Carte}
	 */
	public ArrayList<Carte> piocher (int nbCarte) {
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		for(int i = 0; i < nbCarte; ++i) {
			cartes.add(this.get(this.size()-1));
			this.remove(this.size()-1);
		}
		if (this.size()<7){
			Manche manche = Manche.getInstanceManche();
			manche.retournerTalon();
		}
		
		return cartes;
	}
	
	public void resetCarteSpecial(){
		int i;
		for (i=0;i<this.size();i++)
		{
			this.get(i).reset();
		}
	}
}


