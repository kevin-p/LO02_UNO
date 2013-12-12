package com.lo02.UNO.core.cartes;

/**
 * 
 * Foctory de {@link Carte}, permet de construire des objets {@link Carte} ou dérivant de {@link Carte}, comme des 
 * cartes spéciale facilement.
 * Les dérivé de {@link Carte} sont construit en fonction du {@link Label} de carte demandé, suivant le type de 
 * {@link Carte} créé la {@link Couleur} est ignorée.
 * 
 * Elle ne possède qu'une méthode static {@link #getCarte(Label, Couleur)} qui renvoi un objet de type {@link Carte}
 * 
 * @author Kevin Personnic
 *
 */
public class CarteFactory {
	
	/**
	 * 
	 * Prend en paramètre un {@link Label} et une {@link Couleur} et renvoi un objet {@link Carte} de UNO adapté.
	 * Si jamais la {@link Carte} ne possède pas de {@link Couleur} alors quel que soit la {@link Couleur} demandé 
	 * elle est ignorée.
	 * 
	 * @param label {@link Label} le label de la carte à construire
	 * @param couleur {@link Couleur} la couleur de la carte à construire
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
