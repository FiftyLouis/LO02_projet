package test_packages;

import java.awt.EventQueue;

import controleur.Controleur;
import vue.Vue;

public class main {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controleur controleur = new Controleur();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
		
}


