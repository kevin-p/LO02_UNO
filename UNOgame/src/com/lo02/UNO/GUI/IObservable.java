package com.lo02.UNO.GUI;

import java.util.Observer;

import com.lo02.UNO.controle.Controleur;
/**
 * Interface qui donne les m�thodes de bases
 * que toute les vues doivent impl�menter pour dialoguer avec le {@link Controleur}
 *
 */
public interface IObservable extends Observer{

	public int getNbHumain();
	public int getNbBot();
	public String choixNom(int i);
	public void afficherScore();
	public void InitialiserVu();
}
