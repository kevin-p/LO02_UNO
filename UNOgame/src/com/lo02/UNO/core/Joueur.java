package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Joker;
import com.lo02.UNO.core.cartes.Label;
import com.lo02.UNO.core.cartes.Plus4;
import com.lo02.UNOTest.Test;

import java.util.*;
/**
 * 
 * Représente et stocke toutes les informations concernant un {@link Joueur}
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
	 * @see #compterPoint()
	 */
	private int point = 0;
	
	/**
	 * Représsente la main du {@link Joueur} sous le forme d'un objet {@link MainJoueur} qui sotcke les cartes.
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
	 * Est utilisé entre 2 {@link Manche} pour "récupérer" les {@link Carte} non jouées
	 * 
	 * @see Manche#reset()
	 */
	public void detruireMain () {
		mainJoueur = new MainJoueur();
	}
	
	/**
	 * 
	 * Est appelé par la {@link Manche} afin de faire jouer un {@link Joueur}, fonctionne en mode cli
	 * Le {@link Joueur} va pouvoir choisir une carte à {@link #poser(int)} dans sa main ou de {@link #piocher(int)}
	 * 
	 * @see Manche#lancerManche()
	 */
	public void jouer() {
		//a modifier au moment de l'Ã©criture de l'interface graphique
		int numCarte = 0;
		Scanner sc = new Scanner(System.in);
		
		Talon talon = Talon.getInstanceTalon();
		talon.getLast().afficher();System.out.println();
		
		AfficherMain();
		
		System.out.println(this.nom + " choisissez la carte Ã  poser (nombre de 1 à  " + mainJoueur.size() + ") : ");
		numCarte = sc.nextInt();
		System.out.println(numCarte + ":" + mainJoueur.size());
		if(numCarte >= 1 && numCarte <= mainJoueur.size()) {
//			System.out.println("juste avant poser carte");
			poser(numCarte - 1);
//			System.out.println("juste après poser cate");
		}
		else {
			piocher(1);
			
			AfficherMain();
			
			System.out.println(this.nom + " choisissez la carte Ã  poser (nombre de 1 Ã  " + mainJoueur.size() +
								") ou 0 pour passer : ");
			numCarte = sc.nextInt();
			
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
//		System.out.println("recup instance");
		Pioche pioche = Pioche.getInstancePioche();
//		System.out.println("joueur pioche");
		mainJoueur.addAll(pioche.piocher(nbCarte));
		Collections.sort(mainJoueur, null);
	}
	
	/**
	 * 
	 * Pose la {@link Carte} à l'Index spécifié de la {@link #mainJoueur} sur le {@link Talon}
	 * 
	 * @param IndexCarte {@link Integer} l'index de la carte à poser sur le {@link Talon}
	 * @see #jouer()
	 */
	public void poser(int IndexCarte) {
		Manche manche = Manche.getInstanceManche();
		if(manche.poserCarte(mainJoueur.get(IndexCarte), this))
			mainJoueur.remove(IndexCarte);
	}
	
	/***
	 * 
	 * Ajoute une {@link Carte} à la {@link #mainJoueur} arbitrairement.
	 * Utilisé dans le cas où un {@link Plus4} à été joué et contesté à raison pour le {@link Joueur} suivant pour 
	 * remettre le {@link Plus4} rejeté dans la {@link #mainJoueur}.
	 * Peut être utilisé à des fin de test ou de triche pour ajouter des {@link Carte} spécifique à un {@link Joueur}.
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
			carte.afficher();
		}
		System.out.println();
	}
	
	/**
	 * 
	 * Demande au {@link Joueur} de selectionner une {@link Couleur} dans le cas où il a posé un {@link Plus4} ou 
	 * un {@link Joker}, en mode cli.
	 * 
	 * @return {@link Couleur} choisi par le joueur
	 * @see Plus4#action(Manche, Joueur)
	 * @see Joker#action(Manche, Joueur)
	 */
	public Couleur choisirCouleur() {
		//Ã  changer pour fontionner de pair avec l'interface graphique
		Scanner sc = new Scanner(System.in);
		for (;;) {
			System.out.println(this.nom + " choisissez une couleur (ROUGE/R/r, BLEU/B/b, VERT/V/v, JAUNE/J/j");
			String choix = sc.nextLine();
			System.out.println(choix);
			if(choix.equals("ROUGE")  || choix.equals("R") || choix.equals("r"))
				return Couleur.ROUGE;
			if(choix.equals("BLEU") || choix.equals("B") || choix.equals("b"))
				return Couleur.BLEU;
			if(choix.equals("VERT") || choix.equals("V") || choix.equals("v"))
				return Couleur.VERT;
			if(choix.equals("JAUNE") || choix.equals("J") || choix.equals("j"))
				return Couleur.JAUNE;
		}
	}
	
	/**
	 * 
	 * Demande au {@link Joueur} si il veut contester un {@link Plus4} joué contre lui, mode cli
	 * 
	 * @return {@link Boolean} le choix du {@link Joueur}, ture pour oui, false sinon
	 * @see Plus4#action(Manche, Joueur)
	 */
	public boolean isContestPlus4() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(this.nom + " contestez vous un Plus4 ? (OUI/O/o : ");
		String choix = sc.nextLine();
		System.out.println(choix);
		if(choix.equals("OUI") ||choix.equals("O") ||choix.equals("o"))
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * Verifi si le {@link Joueur} avait le droit de poser un {@link Plus4} sur le {@link Talon}
	 * 
	 * @return {@link Boolean} true si le plus 4 était légitime, false sinon
	 */
	public boolean isLegitimePlus4(){
		boolean legitime=true;
		Talon talon = Talon.getInstanceTalon();
		for(Carte carte : mainJoueur) {
			if(carte.getLabel()!= Label.PLUS4 && carte.isPosableSur(talon.getLast())){ // compare toutes les cartes sauf les +4
				legitime=false;
			}
		}
		return legitime;
		
	}
	
	/**
	 * 
	 * Compte les points de la main d'un joueur et les ajoute aux points du joueur pour la partie courante
	 * 
	 * @see Manche#lancerManche()
	 */
	public void compterPoint(){
		
		for(Carte carte : mainJoueur) {
			point+=carte.getLabel().valeur();
		}
	}
	
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
	 * Change le nom du {@link Joueur} par le nom passé en paramètre
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
