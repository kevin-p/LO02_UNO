package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;
import java.util.ArrayList;

public class Pioche extends ArrayList<Carte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4808339253578125526L;
	private static Pioche SinglePioche = null;

	private Pioche() {
		
	}
	
	public static Pioche getInstancePioche() {
		
		if (SinglePioche == null)
			SinglePioche = new Pioche();
		
		return SinglePioche;
	}
}


