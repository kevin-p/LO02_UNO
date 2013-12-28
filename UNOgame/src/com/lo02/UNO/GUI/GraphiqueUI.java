package com.lo02.UNO.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lo02.UNO.controle.Controleur;


public class GraphiqueUI extends JFrame implements IObservable {
	
	private Controleur controleur; 
	private JPanel pJeu;
	

	public GraphiqueUI(String titre,Controleur controleur){
		super(titre);
		this.controleur=controleur;
		
		
		
		
		
		//pJeu= new JPanel();
		//pJeu.add(new OptionPanel().getPanel(),BorderLayout.NORTH);
		//this.add(pJeu);
		
		//place();
		}

	

		public int getNbHumain() {
			try {
				int nbHumain = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre total d'humain (en vous comptant) qui vont jouer.")) ;				
				if(nbHumain > 7) {
					JOptionPane.showMessageDialog(null, "Vous avez rentrer trop d'humains. Nombre max de joueur : 7");
					throw new NumberFormatException();
				}
				return nbHumain;
			} catch(NumberFormatException e) {					
				this.getNbHumain();
			}
			return 0;
		}
	


	private void place() {

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dimEcran = tk.getScreenSize();
		int hauteurEcran, hauteurFenetre;
		int largeurEcran, largeurFenetre;
		int origineX, origineY;

		
		hauteurEcran = (int) (dimEcran.getHeight());
		largeurEcran = (int) (dimEcran.getWidth());
		hauteurFenetre = (6 * hauteurEcran) / 7;
		largeurFenetre = (4 * largeurEcran) / 5;

		
		origineX = largeurEcran / 10;
		origineY = hauteurEcran / 10;

		this.setLocation(origineX, origineY);
		this.setSize(largeurFenetre, hauteurFenetre);

		
		

		this.setVisible(true);

	}


	@Override
	public String choixNom(int i) {
		try {
			return JOptionPane.showInputDialog(null, "Entrez le nom du joueur numero "+i) ;
			
		} catch(NumberFormatException e) {
			this.choixNom(i);
		}
		return "";
	}



	@Override
	public int getNbBot() {
		try {
			int nbBot = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre total de Bot")) ;				
			if(nbBot > 7) {
				JOptionPane.showMessageDialog(null, "Vous avez rentrer trop de Bot. Nombre max de Bot : 7");
				throw new NumberFormatException();
			}
			return nbBot;
		} catch(NumberFormatException e) {					
			this.getNbBot();
		}
		return 0;
	}


	
	
}
