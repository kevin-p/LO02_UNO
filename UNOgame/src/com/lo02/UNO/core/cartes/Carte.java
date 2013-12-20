package com.lo02.UNO.core.cartes;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Manche;

/**
 * 
 * Représente une carte de UNO
 * 
 * @author Kevin Personnic
 *
 */
public class Carte  implements  Comparable<Carte>{//ajouter méthode reset() à implémenter dans le plus4 et le joker
	
	/**
	 * 
	 * {@link Couleur} de la carte. Peut être bleu, rouge, jaune, vert ou noir dans le cas de carte spécial tel que le
	 * {@link Plus4} ou le {@link Joker}.
	 * 
	 */
	private Couleur couleur;
	
	/**
	 * 
	 * {@link Label} de la carte
	 * 
	 * @see #Carte(Carte)
	 * @see #Carte(Label, Couleur)
	 * @see #afficher()
	 * @see #getCouleur()
	 * @see #isPosableSur(Carte)
	 * @see #setCouleur(Couleur)
	 * 
	 */
	private Label label;
	
	/**
	 * 
	 * Constructeur par defaut que {@link Carte}
	 * 
	 * @see #afficher()
	 * @see #Carte(Carte)
	 * @see #getLabel()
	 * @see #isPosableSur(Carte)
	 * @see #getLabel()
	 * 
	 */
	public Carte() {}
	
	/**
	 * 
	 * Constructeur par recopie de {@link Carte}
	 * 
	 * @param carte {@link Carte}
	 */
	public Carte(Carte carte) {
		this.couleur = carte.couleur;
		this.label = carte.label;
	}
	
	/**
	 * 
	 * Constructeur par {@link Label} et {@link Couleur} de {@link Carte}
	 * 
	 * @param label {@link Label} label de la carte à construire
	 * @param couleur {@link Couleur} couleur de la carte à construire
	 * @see CarteFactory
	 */
	public Carte(Label label, Couleur couleur) {
		this.setCouleur(couleur);
		this.setLabel(label);
	}
	
	/**
	 * 
	 * Action qu'entraine le fait de poser la {@link Carte}. L'action est vide pour les {@link Carte} classique de O à 
	 * 9 et doit être surchargé par les {@link Carte} special dérivant de {@link Carte}.
	 * 
	 * @param manche {@link Manche} la manche en cours
	 * @param joueur {@link Joueur} le joueur qui pose la carte
	 * @see Manche#poserCarte(Carte, Joueur)
	 */
	public void action (Manche manche, Joueur joueur) {
//		System.out.println("pas d'action");
	}
	
	/**
	 * 
	 * affiche la carte en mode cli sous la forme :
	 *  | {@link Label} :  {@link Couleur}
	 *  
	 * @see Joueur#AfficherMain()
	 * @see Joueur#jouer()
	 * 
	 */
	public void afficher() {
		System.out.print("|" + label+ ":" + couleur + "| ");
	}
	
	/**
	 * 
	 * Vérifie si la {@link Carte} peut être posé sur uen autre {@link Carte}
	 * 
	 * @param carte {@link Carte} sur la quelle va être posé notre objet {@link Carte}
	 * @return {@link Boolean} <code>true</code> si la carte peut être posé, <code>false</code> sinon
	 * @see Joueur#isLegitimePlus4()
	 * @see Manche#poserCarte(Carte, Joueur)
	 */
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

	/**
	 * 
	 * Renvoi le couleur de la {@link Carte}
	 * 
	 * @return {@link #couleur} {@link Couleur} de la {@link Carte}
	 * @see #isPosableSur(Carte)
	 */
	public Couleur getCouleur() {
		return couleur;
	}

	/**
	 * 
	 * Change la {@link Couleur} de la {@link Carte} par la {@link Couleur} passé en paramètre
	 * 
	 * @param couleur {@link Couleur}
	 * @see #Carte(Label, Couleur)
	 * @see Joker#action(Manche, Joueur)
	 * @see Plus4#action(Manche, Joueur)
	 */
	public void setCouleur(Couleur couleur) {
		this.couleur = couleur;
	}

	/**
	 * 
	 * Renvoi le {@link Label} de la {@link Carte}
	 * 
	 * @return {@link #label} {@link Label}
	 * @see Joueur#isLegitimePlus4()
	 */
	public Label getLabel() {
		return label;
	}

	/**
	 * 
	 * Change le {@link Label} de la {@link Carte} par le {@link Label} passé en paramètre
	 * 
	 * @param label {@link Label}
	 * @see #Carte(Label, Couleur)
	 */
	public void setLabel(Label label) {
		this.label = label;
	}
	
	@Override
	public int compareTo(Carte c) {
		if(this.couleur.equals(c.couleur)){
			if(this.label.ordinal()>c.label.ordinal())
				return 1;
			else return -1;
		}
			else if(this.couleur.ordinal()>c.couleur.ordinal())
				return 1;
			else return -1;
				
		}
	
	public void reset(){}
		
	}
	
	

