package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;

import java.util.ArrayList;
import java.util.Collection;
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
					
					Carte carte = new Carte(CarteFactory.getCarte(l, c));
					this.add(carte);
					
					if(l != Label.ZERO && l != Label.PLUS4 && l != Label.JOKER) {
						this.add(carte);
					}
				}
			}
		}
		
		Collections.shuffle(this);
	}
	
	public static Pioche getInstancePioche() {
		
		if (SinglePioche == null)
			SinglePioche = new Pioche();
		
		return SinglePioche;
	}
}


