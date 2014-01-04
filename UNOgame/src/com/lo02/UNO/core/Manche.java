package com.lo02.UNO.core;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Collections;

import javax.security.auth.callback.LanguageCallback;

import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.ChangerSens;
import com.lo02.UNO.core.cartes.Label;
import com.lo02.UNO.core.cartes.Plus2;
import com.lo02.UNO.core.cartes.Plus4;
import com.lo02.UNO.core.cartes.SensInterdit;

/**
 * 
 * Gère le déroulement d'une {@link Manche} dans la jeu de UNO.
 * Péparation de la {@link Pioche} et du {@link Talon}, distribution des {@link Carte}...
 * Heberge aussi la boucle de déroulement du jeu.
 * 
 * {@link Manche} est un singleton.
 * 
 * @author Kevin personnic
 *
 */
public class Manche {
	
	/**
	 * 
	 * Stocke la référence du singleton de l'objet {@link Manche}
	 * 
	 * @see #getInstanceManche()
	 * 
	 */
	private static Manche SingleManche = null;

	/**
	 * 
	 * Determine le {@link #sens} du jeu par 1 ou -1
	 * 
	 * @see #changeSens()
	 * 
	 */
	private int sens = 1;
	
	/**
	 * 
	 * Stocke l'index du {@link Joueur} courrant de l'array {@link #joueurs}
	 * 
	 * @see #getJoueurCourant()
	 * @see #getJoueurSuivant()
	 * @see #AvancerJoueur()
	 * 
	 */
	private int joueurCourrant;
	
	/**
	 * 
	 * Stocke la réfférence de la {@link Pioche}
	 * 
	 * @see #creerPioche()
	 * 
	 */
	private Pioche pioche;
	
	/**
	 * 
	 * Stocke la réfférence du {@link Talon}
	 * 
	 * @see #creerTalon()
	 * @see #poserCarte(Carte, Joueur)
	 * 
	 */
	private Talon talon;
	
	/**
	 * 
	 * Stocke une copie de la liste des {@link Joueur} de la partie
	 * 
	 * @see #AvancerJoueur()
	 * @see #distribuerCarte()
	 * @see #getJoueurCourant()
	 * @see #getJoueurs()
	 * @see #getJoueurSuivant()
	 * @see #lancerManche()
	 * @see #reset()
	 * @see #setJoueurs(ArrayList)
	 * 
	 */
	private ArrayList<Joueur> joueurs;
	
	/**
	 * 
	 * Constructeur de {@link Manche}.
	 * private car {@link Manche} est un singleton
	 * 
	 * @see #getInstanceManche()
	 * 
	 */
	private Manche () {
		this.joueurs = new ArrayList<Joueur>();
		
		creerTalon();
		creerPioche();
	}
	
	/**
	 * 
	 * Renvoi une réfférence sur le singleton {@link Manche}
	 * 
	 * @return {@link #SingleManche} {@link Manche}
	 * @see Joueur#poser(int)
	 */
	public synchronized static Manche getInstanceManche() {
		if (SingleManche == null)
			SingleManche = new Manche();
		
		return SingleManche;
	}
	
	/**
	 * 
	 * Reset the {@link Manche} and all objects created by its, {@link Talon} and {@link Pioche}.
	 * Also destroy all {@link MainJoueur} of {@link Joueur}.
	 * 
	 */
	public void reset() {
		System.out.println("Fin d'une manche");
		
		for (Joueur j : joueurs) {
			j.detruireMain();
		}
		
		Pioche.reset();
		Talon.reset();
		distribuerCarte();
		creerTalon();
		creerPioche();
	}
	
	/**
	 * 
	 * Lance la {@link Manche}.
	 * Designe le premier {@link Joueur} à jouer et verifi le bombres de cartes des joueurs afin de detecter la fin
	 * de {@link Manche}.
	 * 
	 */
	public void lancerManche() {
		
		joueurCourrant = designerPremierJoueur(joueurs.size());
		boolean mancheFinie = false;
		
		do{
			talon.add(pioche.remove(0));
			joueurs.get(joueurCourrant).notifyObservers(talon.getLast());
//			System.out.println("Taille du talon en début de jeu "+talon.size());
//			talon.getLast().afficher();System.out.println(" Ajouter au talon");
			
			
		}while(talon.getLast().getLabel()== Label.PLUS4);
		
//		System.out.println(	joueurs.get(joueurCourrant).getNom() + ", " +
//							joueurs.get(joueurCourrant).getNbCarte() + " cartes");
//		talon.getLast().afficher(); System.out.println(); System.out.println();
		
		if(talon.getLast().getLabel()==Label.PLUS2){
			ReculerJoueur();
			talon.getLast().action(this, joueurs.get(joueurCourrant));
			AvancerJoueur();
		}
		else				
			talon.getLast().action(this, joueurs.get(joueurCourrant));
		
		
		do {
			joueurs.get(joueurCourrant).jouer();
			AvancerJoueur();
			
			//remplacer par exception
			for (Joueur j : joueurs) {
				if(j.getNbCarte() == 0){
					mancheFinie = true;
					CompterPoint(j);
				}
					
			}
		}while(!mancheFinie);
//		System.out.println("Fin de la manche");
		
	}
	/**
	 * Compte les points de chaque {@link Joueur} en fin de manche
	 */
	public void CompterPoint(Joueur joueurGagnant) {
		for (Joueur j : joueurs) {
			joueurGagnant.addPoint(j.getMainJoueur().getValeurTotal());
		}		
	}

	/**
	 * 
	 * Utilisé par les {@link Joueur} pour poser une {@link Carte} sur le {@link #talon}.
	 * La méthode vérifie si la {@link Carte} peut être posé par le {@link Joueur} et 
	 * execute la {@link Carte#action(Manche, Joueur)}.
	 * Une pénalité est donné au {@link Joueur} si il ne pouvait pas {@link Joueur#poser(int)} la {@link Carte}.
	 * 
	 * @param carte {@link Carte} que le joueur veut poser
	 * @param joueur {@link Joueur} une réfférence sur le joueur qui joue la {@link Carte}
	 * @return {@link Boolean} true si la carte est accepté, false sinon.
	 * @see Joueur#poser(int)
	 */
	public boolean poserCarte(Carte carte, Joueur joueur) {
//		System.out.println("haut du tas");
//		talon.getLast().afficher();
//		System.out.println(" | fin haut du tas");

		if (!carte.isPosableSur(talon.getLast())) {
			joueur.piocher(2);
			System.out.println("carte rejetée, vous recevez une penalitée de 2 cartes");
			return false;
		}
		else {
			talon.add(carte);
//			System.out.println("juste avant l'action");
			carte.action(this, joueur);
//			System.out.println("juste après l'action");
//			System.out.println("carte acceptee");
			return true;
		}
	}
	
	/**
	 * 
	 * Retourne le {@link #talon} si jamais il reste moins de 6 {@link Carte} dans la {@link #pioche}.
	 * 
	 */
	public void retournerTalon() {
		//retourner le talon dès qu'il y a 6 cartes ou moins.
		//Epargner la dernière carte du talon qu'il faut remettre au début. redimensionner
		//reset les cartes
		Carte c = talon.remove(0);
		Collections.shuffle(talon);
		pioche.addAll(talon);
		talon.clear();
		talon.add(c);
		System.out.println("Le talon a été retourné");
		
	}
	
	/**
	 * 
	 * Change le sens de la manche
	 * 
	 * @see ChangerSens#action(Manche, Joueur)
	 * 
	 */
	public void changeSens () {
		sens = sens * -1;
	}
	
	/**
	 * 
	 * Crée le {@link #talon} de la {@link Manche}.
	 * 
	 * @see #Manche()
	 * @see #reset()
	 * 
	 */
	public void creerTalon () {
		talon = Talon.getInstanceTalon();
	}
	
	/**
	 * 
	 * Crée le {@link #pioche} de la {@link Manche}.
	 * 
	 * @see #Manche()
	 * @see #reset()
	 * 
	 */
	public void creerPioche () {
		pioche = Pioche.getInstancePioche();
	}
	
	/**
	 * 
	 * Distribue les {@link Carte} au {@link #joueurs}. A lancer manuellement après l'ajout des joueurs à la 
	 * {@link Manche} S'effectue automatiquement au moment du reset d'une {@link Manche}.
	 * 
	 * @see #reset()
	 * 
	 */
	public void distribuerCarte () {
//		System.out.println("distribution Ã  : " + this.joueurs.size());
		for (Joueur j : this.joueurs) {
//			System.out.println(j.getNom());
			j.piocher(7);
		}
	}
	
	/**
	 * 
	 * Choisi aléatoirement le premier {@link Joueur} à jouer.
	 * 
	 * @param nbJoueurs
	 * @return IndexJoueur {@link Integer} l'index du premier {@link Joueur} à jouer parmis les {@link #joueurs}
	 * @see #lancerManche()
	 */
	public int designerPremierJoueur(int nbJoueurs) {
		return (int)(Math.random() * nbJoueurs);
	}
	
	/**
	 * 
	 * Ajoute une liste de joueur à la manche
	 * 
	 * @param joueurs {@link ArrayList} {@link Joueur}
	 */
	public void setJoueurs(ArrayList<Joueur> joueurs) {
//		System.out.println("ajout des joueurs");
		this.joueurs.addAll(joueurs);
//		System.out.println("nb joueurs : " + this.joueurs.size());
	}
	
	/**
	 * 
	 * Renvoi la liste des {@link Joueur} de la {@link Manche}
	 * 
	 * @return {@link #joueurs} {@link ArrayList} {@link Joueur}
	 */
	public ArrayList<Joueur> getJoueurs() {
		return this.joueurs;
	}

	/**
	 * 
	 * Renvoi le {@link Joueur} en train de jouer
	 * 
	 * @return {@link #joueurCourrant} {@link Joueur}
	 */
	public Joueur getJoueurCourant() {
		return joueurs.get(joueurCourrant);
	}
	
	/**
	 * 
	 * Renvoi le {@link Joueur} qui jouera après le {@link #joueurCourrant}
	 * 
	 * @return {@link Joueur}
	 * @see Plus2#action(Manche, Joueur)
	 * @see Plus4#action(Manche, Joueur)
	 */
	public Joueur getJoueurSuivant() {
		return joueurs.get((joueurs.size() + joueurCourrant + sens) % joueurs.size());
	}
	
	/**
	 * 
	 * Fait avancer le {@link #joueurCourrant} d'un {@link Joueur}
	 * 
	 * @see #lancerManche()
	 * @see Plus4#action(Manche, Joueur)
	 * @see Plus2#action(Manche, Joueur)
	 * @see SensInterdit#action(Manche, Joueur)
	 * 
	 */
	public void AvancerJoueur() {
		joueurCourrant = (joueurs.size() + joueurCourrant + sens) % joueurs.size();
	}
	
	public void ReculerJoueur() {
		joueurCourrant = (joueurs.size() + joueurCourrant + sens*-(1)) % joueurs.size();
	}
}
