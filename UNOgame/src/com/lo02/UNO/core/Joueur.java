package com.lo02.UNO.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.GUI.GraphiqueUI;
import com.lo02.UNO.controle.Controleur;
import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Joker;
import com.lo02.UNO.core.cartes.Label;
import com.lo02.UNO.core.cartes.Plus4;
import com.lo02.UNOTest.Test;
/**
 * 
 * Repr�sente et stocke toutes les informations concernant un {@link Joueur}
 * 
 * @author Kevin personnic
 *
 */
public class Joueur extends Observable{
	
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
	 * Liste des observateurs des joueurs
	 * @see Observer
	 */
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	/**
	 * indexe de la carte selectionner par le joueur
	 * @see #choisirIndex()
	 */
	private int index=-1;
	/**
	 * couleur choisie par le joueur mise � jour via le {@link Controleur}
	 */
	private Couleur choixCouleur=null;

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
		//int numCarte = 0;
		
		ConsoleUI.afficherTalon();
		notifyObservers("Tour du joueur " +getNom()+"\n");
		notifyObservers();
		//numCarte = ConsoleUI.selectionnerCarte(this);
		int numCarte=choisirIndexCarte();
		if(numCarte >= 1 && numCarte <= mainJoueur.size())
			poser(numCarte - 1);
		else {
			piocher(1);
			notifyObservers(getNom()+" a pioch�\n");
			notifyObservers();
			//numCarte = ConsoleUI.selectionnerCarte(this);
			numCarte=choisirIndexCarte();
			
			if(numCarte >= 1 && numCarte <= mainJoueur.size()){
				poser(numCarte - 1);
			}
			else
				notifyObservers(getNom()+" n'a pas pose de carte durant son tour\n");
		}
	}
	
	/**
	 * M�thode en attente d'une notification du controleur pour connaitre la carte choisie par l'utilisateur 
	 * @return @see index
	 */
	synchronized public int choisirIndexCarte(){
		
		while(index==-1){
            try {
                //attente passive
                wait();
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
		int numCarte=index;
		index=-1;
        return numCarte;
        
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
		
		
		if(manche.poserCarte(mainJoueur.get(IndexCarte), this)){
			System.out.println("Carte pose par "+getNom()+"  "+mainJoueur.get(IndexCarte).getLabel().label()+" | "+mainJoueur.get(IndexCarte).getCouleur());
			notifyObservers(mainJoueur.get(IndexCarte));
			mainJoueur.remove(IndexCarte);
		}
		else{
			notifyObservers(getNom()+" a pose une carte invalide : 2 cartes de p�nalit�\n");
		}
			
		
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
	synchronized public Couleur choisirCouleur() {
		
		notifyObservers("Couleur");
		while(choixCouleur==null){
            try {
                //attente passive
                wait();
            } catch(InterruptedException ie) {
                ie.printStackTrace();
            }
        }
		Couleur choix=choixCouleur;
		choixCouleur=null;
        return choix;
		
		//return ConsoleUI.selectionnerCouleur(this);
	}
	
	/**
	 * 
	 * Demande au {@link Joueur} si il veut contester un {@link Plus4} jou� contre lui, mode cli
	 * 
	 * @return {@link Boolean} le choix du {@link Joueur}, ture pour oui, false sinon
	 * @see Plus4#action(Manche, Joueur)
	 */
	public boolean isContestPlus4() {
		 int choix = JOptionPane.showConfirmDialog(null, getNom()+"  voulez vous contester le +4", "Contestation plus4", JOptionPane.YES_NO_OPTION);
		 if(choix==JOptionPane.YES_OPTION){
			 return true;
		 }
		 else
			 return false;
		//return ConsoleUI.demanderContestPlus4(this);
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
		if(!legitime)
			notifyObservers("Le +4 n'�tait pas l�gitime "+getNom()+" recoit 2 cartes de p�nalit�\n");
		else
			notifyObservers("Le +4 �tait l�gitime ");
		return legitime;
		
	}

	
	/**
	 * 
	 * Compte les points de la main d'un joueur et les ajoute aux points du joueur pour la partie courante
	 * 
	 * @see Manche#CompterPoint(Joueur)
	 */

	
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
	 * @param choixCouleur the choixCouleur to set
	 */
	public void setChoixCouleur(Couleur choixCouleur) {
		this.choixCouleur = choixCouleur;
	}

	/**
	 * @param numCarte the numCarte to set
	 */
	public void setNumCarte(int numCarte) {
		this.index = numCarte;
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
	
	/**
	 * Notifie la {@link GraphiqueUI} lors d'un changement de {@link Joueur}
	 */
	public void notifyObservers() {

		 for (Observer ob : observers) {
           ob.update(this, this);
		 }
		 	 
	}
	/**
	 * Notifie la {@link GraphiqueUI} lorsqu'une {@link Carte} est jou�e, de la l�gitim� d'un +4, du choix d'une couleur
	 * 
	 * 
	 * @see Joueur#poser(int)
	 * @see Joueur#isLegitimePlus4()
	 * @see Joueur#choixCouleur()
	 */
	public void notifyObservers(Object arg) {

		 for (Observer ob : observers) {
          ob.update(this, arg);
		 }
		 	 
	}
	
/**
 * Ajout une vue � liste des observeurs
 */
	public void addObserver(Observer observer) {
		 observers.add(observer);
		
	}
/**
 * Supprime un observeur � la liste des observeurs
 * @param observer
 */
	public void removeObserver(Observer observer) {
		 observers.remove(observer);
		
	}
}
