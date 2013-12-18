package com.lo02.UNO.core;

import com.lo02.UNO.core.cartes.Couleur;

public class JoueurVirtuel extends Joueur{
	
	private static int compteur = 0;
	private Bot bot;
	
	public JoueurVirtuel() {
		super("Bot" + Integer.toString(compteur++));
		this.bot = new BotClassic();
	}
	
	public void choisirNiveau(String niveau) {
		
		/*if(niveau.equals("Agressif") || niveau.equals("A") || niveau.equals("a"))
			bot = new BotClassic();
		if(niveau.equals("Passif") || niveau.equals("P") || niveau.equals("p"))
			bot = new BotClassic();
		else*/
			bot = new BotClassic();
	}
	
	public void jouer() {
		boolean aPioche = false;
		
		if(bot.isPiocher(this)) {
			piocher(1);
			aPioche = true;
		}
		
		int IndexCarte = bot.choisirIndexCarte(this);
		
		if(IndexCarte < getMainJoueur().size()) {
			poser(IndexCarte);
		}
		else if (!aPioche) {
			piocher(1);
		}
		
		if(bot.annncerUNO(this)) {
			//annoncer UNO, non géré dans la CLI
		}
	}
	
	public Couleur choisirCouleur() {
		return bot.choisirCouleur(this);
	}
	
	public boolean isContestPlus4() {
		return bot.contesterPlus4(this);
	}	
}
