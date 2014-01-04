package com.lo02.UNO.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	private JPanel pCentral;
	private JFrame fChoixCouleur;
	

	public GraphiqueUI(String titre,Controleur controleur){
		super(titre);
		this.controleur=controleur;
		this.addWindowListener(controleur);
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
		pScore.setMaximumSize(new Dimension(getWidth(), 700));
		this.add(pScore,BorderLayout.NORTH);
	}
	private void creerPanelTalon()
	{
		pCentral = new JPanel();
		pTalon = new JPanel();
		bPioche = new JButton("Piocher / Passer");
		bPioche.setActionCommand("0");
		bPioche.setForeground(Color.BLACK);
		bPioche.addActionListener(controleur);
		
		pCentral.add(bPioche,BorderLayout.EAST);
		pTalon.setBorder(BorderFactory.createTitledBorder(" Talon"));
		pCentral.add(pTalon,BorderLayout.CENTER);
		this.add(pCentral,BorderLayout.CENTER);
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

	private void creerFenetreCouleur(){
				
		fChoixCouleur = new JFrame();
		JPanel pDroite = new JPanel();
		JPanel pGauche = new JPanel();
		
		pDroite.setPreferredSize(new Dimension(110,110));
		pGauche.setPreferredSize(new Dimension(110,110));
		
		JButton bVert = new JButton();		
		bVert.setBackground(Color.green);
		bVert.addActionListener(controleur);
		bVert.setActionCommand("vert");
		bVert.setPreferredSize(new Dimension(100,100));
		pGauche.add(bVert,BorderLayout.SOUTH);
		
		JButton bBleu = new JButton();
		bBleu.setBackground(Color.blue);
		bBleu.addActionListener(controleur);
		bBleu.setActionCommand("bleu");
		bBleu.setPreferredSize(new Dimension(100,100));
		pGauche.add(bBleu,BorderLayout.NORTH);
		
		JButton bJaune = new JButton();
		bJaune.setBackground(Color.yellow);
		bJaune.addActionListener(controleur);
		bJaune.setActionCommand("jaune");
		bJaune.setPreferredSize(new Dimension(100,100));
		pDroite.add(bJaune,BorderLayout.SOUTH);
		
		JButton bRouge = new JButton();
		bRouge.addActionListener(controleur);
		bRouge.setActionCommand("rouge");
		bRouge.setBackground(Color.red);
		bRouge.setPreferredSize(new Dimension(100,100));
		pDroite.add(bRouge,BorderLayout.NORTH);
		
		fChoixCouleur.setSize(250, 250);
		fChoixCouleur.add(pGauche,BorderLayout.NORTH);
		fChoixCouleur.add(pDroite,BorderLayout.SOUTH);
	

	}
	public void afficherScore(){

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
		
		pScore.removeAll();
		pScore.add(sp);
		pScore.validate();
		pScore.repaint();
		this.validate();
	}
	@Override
	
	

	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		this.setEnabled(true);
		fChoixCouleur.setVisible(false);

		
		if(o instanceof Joueur){
			if(arg1 instanceof Carte){
				pTalon.removeAll();
				Carte c =(Carte) arg1;
				JButton bCarte = new JButton();
				bCarte.setLabel(c.getLabel().name());
				switch (c.getCouleur()) {
				case BLEU: bCarte.setBackground(Color.blue);break;
				case ROUGE: bCarte.setBackground(Color.red);break;
				case JAUNE: bCarte.setBackground(Color.yellow);break;
				case VERT: bCarte.setBackground(Color.green);break;

				default:
					break;
				}
				pTalon.add(bCarte);
				pTalon.getParent().repaint();
				this.validate();
				
			}
			else if(arg1.equals("Couleur")){
				fChoixCouleur.setVisible(true);
				this.setEnabled(false);
				
			}
			else{
				Joueur j = (Joueur) o;
				pMainJoueur.removeAll();
				int num=0;
				for (Carte c : j.getMainJoueur()){
					num++;
					JButton bCarte = new JButton();
					bCarte.setActionCommand(Integer.toString(num));
					bCarte.addActionListener(controleur);
					bCarte.setLabel(c.getLabel().name());
					bCarte.setForeground(Color.BLACK);
					switch (c.getCouleur()) {
					case BLEU: bCarte.setBackground(Color.blue);break;
					case ROUGE: bCarte.setBackground(Color.red);break;
					case JAUNE: bCarte.setBackground(Color.yellow);break;
					case VERT: bCarte.setBackground(Color.green);break;

					default:
						break;
					}
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
		creerFenetreCouleur();
	}
	
}
