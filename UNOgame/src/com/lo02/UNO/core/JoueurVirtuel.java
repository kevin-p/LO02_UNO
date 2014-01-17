package com.lo02.UNO.core;

import com.lo02.UNO.GUI.ConsoleUI;
import com.lo02.UNO.core.cartes.Couleur;

public class JoueurVirtuel extends Joueur{
	
	private static int compteur = 0;
	private Bot bot;
	
	public JoueurVirtuel() {
		super("Bot" + Integer.toString(++compteur));
		this.bot = new BotClassic();
	}
	
	public void choisirNiveau(String niveau) {
		
			bot = new BotClassic();
	}
	
	public void jouer() {
		boolean aPioche = false;
		if (getMainJoueur().size()<4)
			this.bot=new BotAgressif();
		else
			this.bot= new BotClassic();
		if(bot.isPiocher(this)) {
			piocher(1);
			aPioche = true;
			notifyObservers(getNom()+" a pioché\n");
			System.out.println(this.getNom()+" a pioché");
		}
		
		ConsoleUI.afficherTalon();
		int IndexCarte = bot.choisirIndexCarte(this);
		
		if(IndexCarte < getMainJoueur().size()) {
			System.out.print(getNom() + " à joué : ");
			getMainJoueur().get(IndexCarte).afficher(); System.out.println();
			poser(IndexCarte);			
		}
		else if (!aPioche) {
			piocher(1);
			notifyObservers(getNom()+" a pioché\n");
			System.out.println(this.getNom()+" a pioché");
		}

	}
	
	public Couleur choisirCouleur() {
		return bot.choisirCouleur(this);
	}
	
	public boolean isContestPlus4() {
		return bot.contesterPlus4(this);
	}	
}
