package com.lo02.UNO.GUI;

import java.util.Scanner;

public class ConsoleUI {
	Scanner sc = new Scanner(System.in);
	
	public int nombreJoueur(){
		int nbJoueur;
		
		System.out.println("Combien y a t-il de joueur dans la partie ?");
		
		do{
			nbJoueur = sc.nextInt();
		} while(nbJoueur<1 || nbJoueur>7);
		
		return nbJoueur;
	}
	
	public String nommerJoueur(int i){
		System.out.println("Quel est le nom du joueur "+i+" ?");
		return sc.next();
	}

}
