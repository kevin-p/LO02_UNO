package com.lo02.UNO.core.cartes;

public class PasserTour extends Carte{
	
	public PasserTour(Couleur couleur) {
		this.setCouleur(couleur);
		this.setLabel(Label.SENSINTERDIT);
	}
	
	public void action () {
		
	}
}