package com.lo02.UNO.core;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Joker;
import com.lo02.UNO.core.cartes.Label;
import com.lo02.UNO.core.cartes.Plus4;
import com.lo02.UNOTest.Test;

import java.util.*;
/**
 * 
 * Repr�sente et stocke toutes les informations concernant un {@link Joueur}
 * 
 * @author Kevin personnic
 *
 */
public class Joueur {
	
	/**
	 * Le nom du {@link Joueur}
	 * 
	 * @see #getNom()
	 * @see #setNom(String)
	 */
	private String nom;
	
	/**
	 * Compatabilise le nombre de points d'un {@link Joueur}
	 * 
	 * @see #getPoint()
	 * @see #setPoint(int point)
	 */
	private int point = 0;
	
	/**
	 * Repr�ssente la main du {@link Joueur} sous le forme d'un objet {@link MainJoueur} qui sotcke les cartes.
	 * 
	 * @see #getNbCarte()
	 * @see #detruireMain()
	 */
	private MainJoueur mainJoueur = new MainJoueur();

	/**
	 * Constructeur par default de {@link Joueur}
	 */
	public Joueur () {}
	
	/**
	 * Constructeur de {@link Joueur}
	 * 
	 * @param nom le nom du {@link Joueur}
	 */
	public Joueur (String nom) {
		this.nom = nom;
	}
	
	/**
	 * constructeur par recopie de {@link Joueur}
	 * 
	 * @param joueur {@link Joueur}
	 */
	public Joueur (Joueur joueur) {
		this.nom = joueur.nom;
		this.point = joueur.point;
		this.mainJoueur.addAll(joueur.mainJoueur);
	}
	
	/**
	 * 
	 * Detruit la {@link #mainJoueur} du {@link Joueur} en replacant sa main acutel par un nouvel objet {@link MainJoueur}
	 * Est utilis� entre 2 {@link Manche} pour "r�cup�rer" les {@link Carte} non jou�es
	 * 
	 * @see Manche#reset()
	 */
	public void detruireMain () {
		mainJoueur = new MainJoueur();
	}
	
	/**
	 * 
	 * Est appel� par la {@link Manche} afin de faire jouer un {@link Joueur}, fonctionne en mode cli
	 * Le {@link Joueur} va pouvoir choisir une carte � {@link #poser(int)} dans sa main ou de {@link #piocher(int)}
	 * 
	 * @see Manche#lancerManche()
	 */
	public void jouer() {
		int numCarte = 0;
		
		ConsoleUI.afficherTalon();	
		numCarte = ConsoleUI.selectionnerCarte(this);
		
		if(numCarte >= 1 && numCarte <= mainJoueur.size())
			poser(numCarte - 1);
		else {
			piocher(1);
			
			numCarte = ConsoleUI.selectionnerCarte(this);
			
			if(numCarte >= 1 && numCarte <= mainJoueur.size())
				poser(numCarte - 1);
		}
	}
	
	/**
	 * 
	 * Fait piocher le {@link Joueur} dans la {@link Pioche}.
	 * 
	 * @param nbCarte
	 * @see #jouer()
	 * @see Manche#distribuerCarte()
	 * @see Manche#poserCarte(Carte, Joueur)
	 */
	public void piocher(int nbCarte) {
		Pioche pioche = Pioche.getInstancePioche();
		mainJoueur.addAll(pioche.piocher(nbCarte));
		Collections.sort(mainJoueur, null);
	}
	
	/**
	 * 
	 * Pose la {@link Carte} � l'Index sp�cifi� de la {@link #mainJoueur} sur le {@link Talon}
	 * 
	 * @param IndexCarte {@link Integer} l'index de la carte � poser sur le {@link Talon}
	 * @see #jouer()
	 */
	public void poser(int IndexCarte) {
		Manche manche = Manche.getInstanceManche();
		if(manche.poserCarte(mainJoueur.get(IndexCarte), this))
			mainJoueur.remove(IndexCarte);
	}
	
	/***
	 * 
	 * Ajoute une {@link Carte} � la {@link #mainJoueur} arbitrairement.
	 * Utilis� dans le cas o� un {@link Plus4} � �t� jou� et contest� � raison pour le {@link Joueur} suivant pour 
	 * remettre le {@link Plus4} rejet� dans la {@link #mainJoueur}.
	 * Peut �tre utilis� � des fin de test ou de triche pour ajouter des {@link Carte} sp�cifique � un {@link Joueur}.
	 * 
	 * @param carte {@link Carte}
	 */
	public void addCarte(Carte carte){
		mainJoueur.add(carte);
	}

	/**
	 * 
	 * Affiche la {@link #mainJoueur} en mode cli
	 * 
	 * @see #jouer()
	 * @see Plus4#action(Manche, Joueur)
	 * @see Test#TestDistribution()
	 */
	public void AfficherMain() {
		for(Carte carte : mainJoueur) {
			System.out.print("|" + (int)(mainJoueur.indexOf(carte) + 1));
			carte.afficher();
		}
		System.out.println();
	}
	
	/**
	 * 
	 * Demande au {@link Joueur} de selectionner une {@link Couleur} dans le cas o� il a pos� un {@link Plus4} ou 
	 * un {@link Joker}, en mode cli.
	 * 
	 * @return {@link Couleur} choisi par le joueur
	 * @see Plus4#action(Manche, Joueur)
	 * @see Joker#action(Manche, Joueur)
	 */
	public Couleur choisirCouleur() {
		return ConsoleUI.selectionnerCouleur(this);
	}
	
	/**
	 * 
	 * Demande au {@link Joueur} si il veut contester un {@link Plus4} jou� contre lui, mode cli
	 * 
	 * @return {@link Boolean} le choix du {@link Joueur}, ture pour oui, false sinon
	 * @see Plus4#action(Manche, Joueur)
	 */
	public boolean isContestPlus4() {
		return ConsoleUI.demanderContestPlus4(this);
	}
	
	/**
	 * 
	 * Verifi si le {@link Joueur} avait le droit de poser un {@link Plus4} sur le {@link Talon}
	 * 
	 * @return {@link Boolean} true si le plus 4 �tait l�gitime, false sinon
	 */
	public boolean isLegitimePlus4(){
		boolean legitime=true;
		Talon talon = Talon.getInstanceTalon();
		for(Carte carte : mainJoueur) {
			if(carte.getLabel()!= Label.PLUS4 && carte.isPosableSur(talon.get(talon.size()-2))){ // compare toutes les cartes sauf les +4
				legitime=false;
			}
		}
		return legitime;
		
	}
	
	/**
	 * 
	 * Compte les points de la main d'un joueur et les ajoute aux points du joueur pour la partie courante
	 * 
	 * @see Manche#CompterPoint(Joueur)
	 *//*
	public void compterPoint(){
		for(Carte carte : mainJoueur) {
			point+=carte.getLabel().valeur();
		}
	}*/
	
	public void addPoint(int points){
		this.point += points;
	}
	
	/**
	 * Compte les points de la main d'un joueur et les ajoute aux points du joueur gagnant de la manche
	 * 
	 * @see Manche#CompterPoint(Joueur)
	 * @param jouerGagnant
	 * 
	 *//*
	public void CompterPoint(Joueur jouerGagnant){
		int pointManche=0;
		for(Carte carte : mainJoueur) {
			pointManche+=carte.getLabel().valeur();
		}
		jouerGagnant.setPoint(pointManche+jouerGagnant.getPoint());
	
	}*/
	
	/**
	 * 
	 * Compte le nombre de cartes du joueur
	 * 
	 * @return int le nombre de carte du joueur
	 */
	public int getNbCarte() {
		return mainJoueur.size();
	}
	
	/**
	 * 
	 * Renvoi le {@link #nom} du {@link Joueur}
	 * 
	 * @return {@link String} le {@link #nom}
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * 
	 * Change le nom du {@link Joueur} par le nom pass� en param�tre
	 * 
	 * @param nom {@link String} le nouveau {@link #nom} du {@link Joueur}
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * 
	 * Renvoi le nombre de {@link #point} d'un {@link Joueur}
	 * 
	 * @return {@link #point} {@link Integer}
	 */
	public int getPoint() {
		return point;
	}
	
	/**
	 * 
	 * Modifi le nombre de {@link #point} d'un {@link Joueur}
	 * 
	 * @param point {@link Integer} le nombre de {@link #point} du {@link Joueur}
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	
	/**
	 * 
	 * Renvoi la main du joueur
	 * 
	 * @return mainJoueur {@link MainJoueur}
	 */
	public MainJoueur getMainJoueur() {
		return mainJoueur;
	}
}
