package com.lo02.UNO.GUI;

import java.util.Scanner;

public class ConsoleUI {
	
	public int nombreJoueur(){
		
		int nbJoueur = 0;
		
		System.out.println("Combien y a t-il de joueur dans la partie ?");
		
		do{
			try{
				Scanner sc = new Scanner(System.in);
				nbJoueur = sc.nextInt();
			}
			catch  (Exception e) {
				System.out.println("Erreur dans la saisie, entrez une valeur valide");
			}
		} while(nbJoueur<1 || nbJoueur>7);
		
		return nbJoueur;
	}
	
	public String nommerJoueur(int i){
		Scanner sc = new Scanner(System.in);
		System.out.println("Quel est le nom du joueur " + i+1 + " ?");
		return sc.next();
	}

}
