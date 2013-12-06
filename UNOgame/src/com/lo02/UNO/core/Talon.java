package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.*;
import java.util.ArrayList;

public class Talon extends ArrayList<Carte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3455800499341853695L;
	private static Talon SingleTalon = null;

	private Talon() {
		
	}
	
	public static Talon getInstancePioche() {
		
		if (SingleTalon == null)
			SingleTalon = new Talon();
		
		return SingleTalon;
	}
}


