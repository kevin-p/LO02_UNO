package com.lo02.UNO;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.core.Partie;

public class Main {

	public static void main(String[] args) {
		
		ConsoleUI.afficherMsgDebut();
		
		Partie partie = new Partie();
		partie.lancerPartie();
	}

}
