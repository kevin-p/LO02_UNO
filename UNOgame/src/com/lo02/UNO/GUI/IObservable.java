package com.lo02.UNO.GUI;

import java.util.Observer;

import com.lo02.UNO.controle.Controleur;
/**
 * Interface qui donne les méthodes de bases
 * que toute les vues doivent implémenter pour dialoguer avec le {@link Controleur}
 *
 */
public interface IObservable extends Observer{

	public int getNbHumain();
	public int getNbBot();
	public String choixNom(int i);
	public void afficherScore();
	public void InitialiserVu();
}
