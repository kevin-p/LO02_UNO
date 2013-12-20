package com.lo02.UNO.GUI;

import java.util.Scanner;

import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.Partie;
import com.lo02.UNO.core.Talon;
import com.lo02.UNO.core.cartes.Couleur;

public class ConsoleUI {
	
	public static int nombreJoueur(String typeJoueur){
		
		int nbJoueur = -1;
				
		do{
			System.out.println("Combien y a t-il de " + typeJoueur + " dans la partie ?");
			try{
				Scanner sc = new Scanner(System.in);
				nbJoueur = sc.nextInt();
			}
			catch  (Exception e) {
				System.out.println("Erreur dans la saisie, entrez une valeur valide");
			}
		} while(nbJoueur<0 || nbJoueur>7);
		
		return nbJoueur;
	}
	
	public static String nommerJoueur(int i){
		Scanner sc = new Scanner(System.in);
		int j = i + 1;
		System.out.println("Quel est le nom du joueur " + j + " ?");
		return sc.next();
	}
	
	public static int selectionnerCarte(Joueur joueur) {
		boolean saisieCorrecte;
		int numCarte = 0;
		
		joueur.AfficherMain();
		
		do {
			System.out.println(	joueur.getNom() + " choisissez la carte Ã  poser (nombre de 1 à  " + 
								joueur.getMainJoueur().size() + ") : ");
			try{
				saisieCorrecte = true;
				Scanner sc = new Scanner(System.in);
				numCarte = sc.nextInt();
			}catch(Exception e) {
				saisieCorrecte = false;
			}
		}while(!saisieCorrecte);
	
		return numCarte;
	}
	
	public static Couleur selectionnerCouleur (Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		
		for (;;) {
			System.out.println(joueur.getNom() + " choisissez une couleur (ROUGE/R/r, BLEU/B/b, VERT/V/v, JAUNE/J/j");
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
	
	public static void afficherTalon () {
		Talon talon = Talon.getInstanceTalon();
		System.out.println("Talon : ");
		talon.getLast().afficher();
		System.out.println();
	}
	
	public static boolean demanderContestPlus4(Joueur joueur) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println(joueur.getNom() + " contestez vous un Plus4 ? (OUI/O/o, n'importe quoi pour non) : ");
		String choix = sc.nextLine();
		System.out.println(choix);
		if(choix.equals("OUI") ||choix.equals("O") ||choix.equals("o"))
			return true;
		else
			return false;
	}
	
	public static int choisirNbHumain () {
		return nombreJoueur(new String("joueurs humain"));
	}
	
	public static int choisirNbBot () {
		return nombreJoueur(new String("bots"));
	}
	
	public static void afficherMsgDebut() {
		
		System.out.println("##################################");
		System.out.println("#                                #");
		System.out.println("#    *    *   **   *   ******    #");
		System.out.println("#    *    *   * *  *   *    *    #");
		System.out.println("#    *    *   *  * *   *    *    #");
		System.out.println("#    ******   *   **   ******    #");
		System.out.println("#                                #");
		System.out.println("##################################");
		System.out.println();
		System.out.println(	"Ce jeux de uno permet pour le moment de jouer une partie classique contre des humain et"
						  + "/ou des bots");
		System.out.println( "Il peut y avoir 0 ou plus de joueurs humain et 0 ou plus de bots. Les bots sont nommé "
						  + "automatiquement");
		System.out.println();
		System.out.println("Bon jeux !");
		System.out.println();

	}

}
