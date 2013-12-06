package com.lo02.UNOTest;

import com.lo02.UNO.core.*;

public class Test {

	public static void TestLabel () {
		for (Label l : Label.values())
			System.out.println("carte : " + l.label() + ", point : " + l.valeur());
		
	}
	
	public static void main(String[] args) {
		TestLabel();

	}

}
