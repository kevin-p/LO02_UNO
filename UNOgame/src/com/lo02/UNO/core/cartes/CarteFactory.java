package com.lo02.UNO.core.cartes;

public class CarteFactory {
	
	public static Carte getCarte (Label label, Couleur couleur) {
		Carte carte;
		
		switch (label) {
		case JOKER:
			carte = new Joker();
			break;
		case PLUS2:
			carte = new Plus2(couleur);
			break;
		case PLUS4:
			carte = new Plus4();
			break;
		case SENSINTERDIT:
			carte = new SensInterdit(couleur);
			break;
		case CHANGERSENS:
			carte = new ChangerSens(couleur);
			break;
		default:
			carte = new Carte(label, couleur);
			break;
		}
		
		return carte;
	}
}
