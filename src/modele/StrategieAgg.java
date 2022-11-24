/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author louis
 *
 */
public class StrategieAgg implements Strategie{
	
	@Override
	public String toString() {
		return "Agressive";
	}

	@Override
	public void jouer(Etudiant e, Zone z, String p) {
		Random r = new Random();
		List<Etudiant> enemi = z.getMap().get(p);
		Optional<Etudiant> cible = enemi.stream().min((Etudiant e1, Etudiant e2) -> Integer.compare(e1.getCredit(), e2.getCredit()));
		int x = 0 + r.nextInt(100 - 0);
		if(x < (40 + 3*e.getDexterite())) {
			System.out.println("attaque reussi");
			double y = r.nextDouble();
		}else {
			System.out.println("attaque raté");
		}
	}
}
