package com.lo02.UNO.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.lo02.UNO.controle.Controleur;
import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.cartes.Carte;
/**
 * 
 * @author 
 *
 */

public class GraphiqueUI extends JFrame implements IObservable {
	
	private Controleur controleur; 
	private JPanel pJeu;
	private JPanel pMainJoueur;
	private JButton bPioche;
	private JButton bPasser;
	private JPanel pTalon;
	private JPanel pScore;
	

	public GraphiqueUI(String titre,Controleur controleur){
		super(titre);
		this.controleur=controleur;
		controleur.getPartie().addObserver(this);
		
		//pJeu= new JPanel();
		//pJeu.add(new OptionPanel().getPanel(),BorderLayout.NORTH);
		//this.add(pJeu);
		
		place();
		}
	private void creerPanelScore()
	{
		pScore = new JPanel();

		pScore.setBorder(BorderFactory.createTitledBorder(" Scores"));
		this.add(pScore,BorderLayout.NORTH);
	}
	private void creerPanelTalon()
	{
		pTalon = new JPanel();

		pTalon.setBorder(BorderFactory.createTitledBorder(" Talon"));
		this.add(pTalon,BorderLayout.CENTER);
	}
	
	private void  creerPanelMainJoueur()
	{
		pMainJoueur=new JPanel();
		pMainJoueur.setBorder(BorderFactory.createTitledBorder(" Votre jeu"));
		
		for (Joueur j : controleur.getPartie().getJoueurs()){
				j.addObserver(this);
			}
		this.add(pMainJoueur,BorderLayout.SOUTH);
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

	public void afficherScore(){
		// ArrayList<Joueur> joueurs = arg1;
		int nbJoueur =controleur.getPartie().getJoueurs().size();
		String [][] donnees=new String[nbJoueur][2];
			
		for (int i=0;i<nbJoueur;i++){
			donnees[i][0]=controleur.getPartie().getJoueurs().get(i).getNom();
			donnees[i][1]=Integer.toString(controleur.getPartie().getJoueurs().get(i).getPoint());
			System.out.println(donnees[i][0]+"  "+donnees[i][1]);
		}
		
		String[] cols = {"Nom", "Score"};
		JTable tab = new JTable(donnees, cols);
		 
		JScrollPane sp = new JScrollPane(tab);
		sp.setMaximumSize(pScore.getSize());
		pScore.removeAll();
		pScore.add(sp);
		pScore.validate();
		pScore.repaint();
		this.validate();
	}
	@Override
	
	

	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		if(o instanceof Joueur){
			if(arg1 instanceof Carte){
				pTalon.removeAll();
				Carte c =(Carte) arg1;
				JButton bCarte = new JButton();
				bCarte.setLabel(c.getLabel().name());
				pTalon.add(bCarte);
				pTalon.getParent().repaint();
				this.validate();
				
			}
			else{
				Joueur j = (Joueur) o;
				pMainJoueur.removeAll();
				
				for (Carte c : j.getMainJoueur()){
					JButton bCarte = new JButton();
					bCarte.setLabel(c.getLabel().name());
					pMainJoueur.add(bCarte);
				}
			
				pMainJoueur.getParent().repaint();
				this.validate();
			}
			
		}else {
		
			afficherScore();
		}
	}

	@Override
	public void InitialiserVu() {
		// TODO Auto-generated method stub
		creerPanelMainJoueur();
		creerPanelTalon();
		creerPanelScore();
	}
	
}
