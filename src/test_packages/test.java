package test_packages;

import modele.Filiere;
import modele.Joueurs;

public class test {
	public static void main(String[] args) {
			
			Joueurs j = new Joueurs("test", Filiere.ISI);
			
			j.choisirReserviste();
	}
}
