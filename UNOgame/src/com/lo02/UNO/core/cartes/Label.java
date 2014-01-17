package com.lo02.UNO.core.cartes;
/**
 * 
 * Les différent label des cartes de UNO sous forme d'énumérateur
 * 
 * @author Kevin
 *
 */
public enum Label {
	ZERO ("0", 0),
	UN ("1", 1),
	DEUX ("2", 2),
	TROIS ("3", 3),
	QUATRE ("4", 4),
	CINQ ("5", 5),
	SIX ("6", 6),
	SEPT ("7", 7),
	HUIT ("8", 8),
	NEUF ("9", 9),
	PLUS2 ("+2", 10),
	SINT ("Sens interdit", 10),
	CHSENS ("changement sens", 10),
	JOKER ("joker", 20),
	PLUS4 ("+4", 20);
	
	
	private final String label;
	private final int valeur;
	
	Label (String label, int valeur) {
		this.label = label;
		this.valeur = valeur;
	}
	
	public String label() {return label;}
	public int valeur() {return valeur;}

}
