package com.lo02.UNO.core.cartes;

public class Carte {
	
	private Couleur couleur;
	private Label label;
	
	public Carte() {}
	
	public Carte(Carte carte) {
		this.couleur = carte.couleur;
		this.label = carte.label;
	}
	
	public Carte(Label label, Couleur couleur) {
		this.setCouleur(couleur);
		this.setLabel(label);
	}
	
	public void action() {}
	
	public boolean isPosableSur(SpecialCarte carteSpecial) {
		return true;
	}
	
	public boolean isPosableSur(Carte carte) {
		if (this.couleur == carte.getCouleur())
			return true;
		else if (this.couleur == Couleur.NOIR || carte.getCouleur() == Couleur.NOIR)
			return true;
		else if (this.label == carte.getLabel())
			return true;
		else
			return false;
	}

	public Couleur getCouleur() {
		return couleur;
	}

	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}
}
