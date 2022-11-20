package test_packages;

import modele.Joueurs;
import modele.Joueurs.Filiere;
import modele.Zone;
import modele.Zone.nomZone;

public class test {
	public static void main(String[] args) {
			
			Joueurs j = new Joueurs("test",Filiere.ISI);
			Zone z = new Zone(nomZone.Bibliotheque);
			
			j.choisirReserviste();
	}
}
