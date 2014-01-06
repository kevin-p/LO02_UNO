package com.lo02.UNO.controle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.lo02.UNO.GUI.IObservable;
import com.lo02.UNO.core.Manche;
import com.lo02.UNO.core.Partie;
import com.lo02.UNO.core.cartes.Couleur;

public class Controleur implements WindowListener,ActionListener {

	private Partie partie;
	private IObservable vue;
	public Controleur(Partie partie){
		this.partie=partie;

	}


	public void setVue(IObservable vue){
		this.vue=vue;
	}


	public void initialiser(){ // rajouter une exception si le nombre de joueurs est trop grand 
		int nbHumain=0;
		int nbBot=0;
		do{
			JOptionPane.showMessageDialog(null, "Bienvenue sur le jeu de Uno. Nombre max de joueur : 10");
			nbHumain = vue.getNbHumain();
			nbBot = vue.getNbBot();
		}while((nbHumain+nbBot)<1||(nbHumain+nbBot)>10);



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

	@Override


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Manche manche = Manche.getInstanceManche();
		System.out.println((e.getActionCommand().toString()));
		synchronized (manche.getJoueurCourant()) {
			if(e.getActionCommand().toString().equals("bleu")||e.getActionCommand().toString().equals("rouge")||e.getActionCommand().toString().equals("jaune")||e.getActionCommand().toString().equals("vert")){
				if (e.getActionCommand().toString().equals("bleu")){
					manche.getJoueurCourant().setChoixCouleur(Couleur.BLEU);
					manche.getJoueurCourant().notify();
				}
				if (e.getActionCommand().toString().equals("rouge")){
					manche.getJoueurCourant().setChoixCouleur(Couleur.ROUGE);
					manche.getJoueurCourant().notify();
				}
				if (e.getActionCommand().toString().equals("jaune")){
					manche.getJoueurCourant().setChoixCouleur(Couleur.JAUNE);
					manche.getJoueurCourant().notify();
				}
				if (e.getActionCommand().toString().equals("vert")){
					manche.getJoueurCourant().setChoixCouleur(Couleur.VERT);
					manche.getJoueurCourant().notify();
				}
			}
			else{

				int index = Integer.parseInt(e.getActionCommand().toString());
				System.out.println((e.getActionCommand().toString()));
				manche.getJoueurCourant().setNumCarte(index);
				manche.getJoueurCourant().notify();}

		}

	}
	@Override



	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override




	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}


	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}













}
