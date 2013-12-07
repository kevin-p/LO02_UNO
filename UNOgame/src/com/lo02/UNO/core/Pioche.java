package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;

import java.util.ArrayList;
import java.util.Collections;

public class Pioche extends ArrayList<Carte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4808339253578125526L;
	private static Pioche SinglePioche = null;

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
	
	public synchronized static Pioche getInstancePioche() {
		
		if (SinglePioche == null)
			SinglePioche = new Pioche();
		
		return SinglePioche;
	}
	
	public synchronized static Pioche reset() {
		return SinglePioche = new Pioche();
	}
	
	public Carte getLast() {
		return this.get(size()-1);
	}
	
	public ArrayList<Carte> piocher (int nbCarte) {
		ArrayList<Carte> cartes = new ArrayList<Carte>();
		for(int i = 0; i < nbCarte; ++i) {
			cartes.add(this.get(this.size()-1));
			this.remove(this.size()-1);
		}
		
		return cartes;
	}
}


