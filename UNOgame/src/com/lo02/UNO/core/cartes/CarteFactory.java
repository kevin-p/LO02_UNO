package com.lo02.UNO.core.cartes;

/**
 * 
 * Foctory de {@link Carte}, permet de construire des objets {@link Carte} ou d�rivant de {@link Carte}, comme des 
 * cartes sp�ciale facilement.
 * Les d�riv� de {@link Carte} sont construit en fonction du {@link Label} de carte demand�, suivant le type de 
 * {@link Carte} cr�� la {@link Couleur} est ignor�e.
 * 
 * Elle ne poss�de qu'une m�thode static {@link #getCarte(Label, Couleur)} qui renvoi un objet de type {@link Carte}
 * 
 * @author Kevin Personnic
 *
 */
public class CarteFactory {
	
	/**
	 * 
	 * Prend en param�tre un {@link Label} et une {@link Couleur} et renvoi un objet {@link Carte} de UNO adapt�.
	 * Si jamais la {@link Carte} ne poss�de pas de {@link Couleur} alors quel que soit la {@link Couleur} demand� 
	 * elle est ignor�e.
	 * 
	 * @param label {@link Label} le label de la carte � construire
	 * @param couleur {@link Couleur} la couleur de la carte � construire
	 * @return carte {@link Carte}
	 */
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
		case SINT:
			carte = new SensInterdit(couleur);
			break;
		case CHSENS:
			carte = new ChangerSens(couleur);
			break;
		default:
			carte = new Carte(label, couleur);
			break;
		}
		
		return carte;
	}
}
