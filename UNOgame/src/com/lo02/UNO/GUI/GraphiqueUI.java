package com.lo02.UNO.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.lo02.UNO.controle.Controleur;
import com.lo02.UNO.core.Joueur;
import com.lo02.UNO.core.JoueurVirtuel;
import com.lo02.UNO.core.Manche;
import com.lo02.UNO.core.cartes.Carte;
import com.lo02.UNO.core.cartes.Joker;
import com.lo02.UNO.core.cartes.Plus4;
/**
 * 
 * @author
 * Représente la vue graphique du jeu de UNO
 * Recoit les notifications du modèle et donnes les informations au controleur
 *
 */

public class GraphiqueUI extends JFrame implements IObservable {
	
	private Controleur controleur; 
	private JPanel pInfoJeu;
	private JPanel pMainJoueur;
	private JButton bPioche;
	private JPanel pTalon;
	private JPanel pScore;
	private JPanel pCentral;
	private JPanel pSud;
	private JPanel pInfoJoueur;
	private JFrame fChoixCouleur;
	private TextArea infoJeu;
	private JScrollPane spMainJoueur;
	

	public GraphiqueUI(String titre,Controleur controleur){
		super(titre);
		this.controleur=controleur;
		this.addWindowListener(controleur);
		controleur.getPartie().addObserver(this);
		place();
		}
	
	private void creerPanelInfoJeu(){
		pInfoJeu = new JPanel();
		infoJeu = new TextArea();
		
		infoJeu.append("Tableau de jeu \n");
		infoJeu.setEditable(false);
		
		pInfoJeu.add(infoJeu);
		pCentral.add(pInfoJeu,BorderLayout.EAST);
		
	}
	private void creerPanelScore(){
		pScore = new JPanel();

		pScore.setBorder(BorderFactory.createTitledBorder(" Scores"));
		pScore.setPreferredSize(new Dimension(getWidth(), 220));
		this.add(pScore,BorderLayout.NORTH);
	}
	
	private void creerPanelInfoJoueur(){
		pInfoJoueur = new JPanel();
		pCentral.add(pInfoJoueur,BorderLayout.EAST);
	}
	private void creerPanelTalon(){
		pCentral = new JPanel();
		pTalon = new JPanel();
		bPioche = new JButton("Piocher / Passer");
		ImageIcon iconImg = new ImageIcon(new ImageIcon("img/DOS.jpg")
											.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT));
		bPioche.setIcon(iconImg);
		bPioche.setActionCommand("0");
		bPioche.setForeground(Color.BLACK);
		bPioche.addActionListener(controleur);
		
		pCentral.add(bPioche,BorderLayout.EAST);
		pTalon.setBorder(BorderFactory.createTitledBorder(" Talon"));
		pCentral.add(pTalon,BorderLayout.CENTER);
		this.add(pCentral,BorderLayout.CENTER);
	}
	/**
	 * Affiche la liste des {@link Carte} d'un {@link Joueur}
	 */
	private void  creerPanelMainJoueur(){
		pMainJoueur=new JPanel();
		pMainJoueur.setSize(new Dimension(this.getWidth(),200));

		for (Joueur j : controleur.getPartie().getJoueurs()){
				j.addObserver(this);
			}
		pSud = new JPanel();
		pSud.setBorder(BorderFactory.createTitledBorder(" Votre jeu"));
		pSud.setSize(new Dimension(getWidth(),(int)(getHeight()*0.35)));
		this.add(pSud,BorderLayout.SOUTH);
	}
/**
 * Dimensionne et place la fenetre en fonction de l'écran 
 */
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


	public String choixNom(int i) {
		try {
			return JOptionPane.showInputDialog(null, "Entrez le nom du joueur numero "+i) ;
			
		} catch(NumberFormatException e) {
			this.choixNom(i);
		}
		return "";
	}

	
	/**
	 * Retourne le nombre de {@link JoueurVirtuel}
	*/
	public int getNbBot() {
		try {
			int nbBot = Integer.parseInt(JOptionPane.showInputDialog(null, "Entrez le nombre total de Bot")) ;				
			if(nbBot > 10 || nbBot <0) {
				JOptionPane.showMessageDialog(null, "Vous avez rentrer trop de Bot. Nombre max de Bot : 10");
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
				if(nbHumain > 10 || nbHumain < 0) {
					JOptionPane.showMessageDialog(null, "Vous avez rentrer trop d'humains. Nombre max de joueur : 10");
					throw new NumberFormatException();
				}
				return nbHumain;
			} catch(NumberFormatException e) {					
				return this.getNbHumain();
			}
			
		}
/**
 * Affiche la fenetre pour choisir la couleur d'un {@link Plus4} {@link Joker}
 * Choix d'une couleur en cliquant sur un des bouttons de la couleur voulue
 */
	private void creerFenetreCouleur(){
				
		fChoixCouleur = new JFrame();
		JPanel pDroite = new JPanel();
		JPanel pGauche = new JPanel();
		
		fChoixCouleur.addWindowListener(controleur);
		
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
	/**
	 * Donne le score de chaque {@link Joueur} à la fin de chaque {@link Manche}
	 * @see Manche#CompterPoint(Joueur)
	 */
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
		tab.setEnabled(false);
		 
		JScrollPane sp = new JScrollPane(tab);
		sp.setPreferredSize(new Dimension(200,200));
		pScore.removeAll();
		pScore.add(sp);
		pScore.validate();
		pScore.repaint();
		this.validate();
	}
	/**
	 * Affiche le nombre de carte de chaque {@link Joueur}
	 */
	public void afficherNombreCarte(){

		int nbJoueur =controleur.getPartie().getJoueurs().size();
		String [][] donnees=new String[nbJoueur][2];
			
		for (int i=0;i<nbJoueur;i++){
			donnees[i][0]=controleur.getPartie().getJoueurs().get(i).getNom();
			donnees[i][1]=Integer.toString(controleur.getPartie().getJoueurs().get(i).getMainJoueur().size() );
			
		}
		
		String[] cols = {"Nom", "Nombre Cartes"};
		JTable tab = new JTable(donnees, cols);
		tab.setEnabled(false);
		 
		JScrollPane sp = new JScrollPane(tab);
		sp.setPreferredSize(new Dimension(200,200));
		pInfoJoueur.removeAll();
		pInfoJoueur.add(sp);
		pInfoJoueur.validate();
		pInfoJoueur.repaint();
		this.validate();
	}
	/**
	 * Creer un boutton pour chaque carte du {@link Joueur} 
	 * @param Joueur
	 */
	private void actualiserMainJoueur(Observable o){
		Joueur j = (Joueur) o;
		pMainJoueur.removeAll();
		pSud.removeAll();
		int num=0;
		//infoJeu.append("Tour du joueur : "+j.getNom()+"\n");
		for (Carte c : j.getMainJoueur()){
			num++;
			JButton bCarte = new JButton();
			ImageIcon iconImg = new ImageIcon(new ImageIcon("img/" + c.getLabel() + "-" + c.getCouleur() + ".jpg")
												.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT));
			bCarte.setIcon(iconImg);
			bCarte.setActionCommand(Integer.toString(num));
			bCarte.addActionListener(controleur);
			pMainJoueur.add(bCarte);
			
		}
		spMainJoueur = new JScrollPane(pMainJoueur);
		
		spMainJoueur.setPreferredSize(new Dimension(this.getWidth(),150));
		pSud.add(spMainJoueur);
		pSud.repaint();
		pSud.validate();
		afficherNombreCarte();
		this.validate();
	}
	/**
	 * Affiche la dernier {@link Carte} poser sur le talon
	 * @param Joueur
	 * @param Carte
	 */
	private void actualiserTalon(Observable o,Object arg1){
		Joueur j = (Joueur) o;
		
		pTalon.removeAll();
		Carte c =(Carte) arg1;
		JButton bCarte = new JButton();
		ImageIcon iconImg = new ImageIcon(new ImageIcon("img/" + c.getLabel() + "-" + c.getCouleur() + ".jpg")
											.getImage().getScaledInstance(60, 90, Image.SCALE_DEFAULT));
		bCarte.setIcon(iconImg);
		bCarte.setVisible(true);
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
		infoJeu.append(j.getNom()+" a pose "+ c.getLabel().name()+" "+c.getCouleur()+"\n");
		afficherNombreCarte();
	}
	@Override
	
	
/**
 *  Modifie la vue en fonction des parametre recu et affiche les informations à l'écran
 *  @see actualiserTalon(Observable o,Object arg1)
 *  @see actualiserMainJoueur(Observable o)
 *  @see afficherScore()
 *  @see ajouterMessage(String msg)
 */
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		this.setEnabled(true);
		fChoixCouleur.setVisible(false);
		 
		if(arg1 instanceof String && !arg1.equals("Couleur")){
				ajouterMessage((String)arg1);
			}
		
		if(o instanceof Joueur){
			if(arg1 instanceof Carte){
				actualiserTalon(o, arg1);				
			}
			else if(arg1.equals("Couleur")){
				fChoixCouleur.setVisible(true);
				this.setEnabled(false);
				
			}
			else{
				actualiserMainJoueur(o);
				}
			
		}else {
		
			afficherScore();
		}
	}
	
	public void ajouterMessage(String msg) {
		infoJeu.append(msg);
	}

	@Override
	public void InitialiserVu() {
		// TODO Auto-generated method stub
		creerPanelMainJoueur();
		creerPanelTalon();
		creerPanelScore();
		creerFenetreCouleur();
		creerPanelInfoJeu();
		creerPanelInfoJoueur();
	}
	
}
