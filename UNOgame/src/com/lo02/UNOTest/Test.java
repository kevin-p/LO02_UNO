package com.lo02.UNOTest;

import com.lo02.UNO.core.cartes.Couleur;
import com.lo02.UNO.core.cartes.Label;

public class Test {

	public static void TestLabel () {
		for (Label l : Label.values())
			System.out.println("carte : " + l.label() + ", point : " + l.valeur());
		
	}
	
	public static void TestCouleur () {
		for (Couleur c : Couleur.values())
			System.out.println(c);
	}
	
	public static void main(String[] args) {
		TestLabel();
		TestCouleur();

	}

}
