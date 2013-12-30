package com.lo02.UNO.GUI;

import java.util.Observer;

public interface IObservable extends Observer{

	public int getNbHumain();
	public int getNbBot();
	public String choixNom(int i);
	public void afficherScore();
	public void InitialiserVu();
}
