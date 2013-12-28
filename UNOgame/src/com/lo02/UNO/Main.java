package com.lo02.UNO;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.GUI.GraphiqueUI;
import com.lo02.UNO.GUI.IObservable;
import com.lo02.UNO.controle.Controleur;
import com.lo02.UNO.core.Partie;

public class Main {

	public static void main(String[] args) {
		
		ConsoleUI.afficherMsgDebut();
		Partie partie = new Partie();
		Controleur controleur = new Controleur(partie);
		IObservable vue = new GraphiqueUI("UNO", controleur);
		controleur.setVue(vue);
		controleur.initialiser();
		
		//Partie partie = new Partie();
		//partie.lancerPartie();
	}

}
