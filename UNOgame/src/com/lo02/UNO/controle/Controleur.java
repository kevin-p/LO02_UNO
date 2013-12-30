package com.lo02.UNO.controle;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import com.lo02.UNO.GUI.IObservable;
import com.lo02.UNO.core.Partie;

public class Controleur {

	private Partie partie;
	private IObservable vue;
	public Controleur(Partie partie){
		this.partie=partie;
		
	}
	
	
	public void setVue(IObservable vue){
		this.vue=vue;
	}
	
	
	public void initialiser(){ // rajouter une exception si le nombre de joueurs est trop grand 
		int nbHumain = vue.getNbHumain();
		int nbBot = vue.getNbBot();
		String [] nom = new String[7] ;
		for (int i=0;i<nbHumain;i++){
			nom[i]=vue.choixNom(i+1);
		}
		partie.initialiserPartie(nbHumain, nbBot, nom);
		vue.InitialiserVu();
		partie.lancerPartie();
		//vue.afficherScore();
	}
	
	public Partie getPartie(){
		return partie;
	}
	
	
	
	
}
