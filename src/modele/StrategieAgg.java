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
	public void jouer(Etudiant e, Zone z, String j1, String j2) {
		Random r = new Random();
		List<Etudiant> enemi = z.getMap().get(j2);
		Optional<Etudiant> cible = enemi.stream().min((Etudiant e1, Etudiant e2) -> Integer.compare(e1.getCredit(), e2.getCredit()));
		int x = 0 + r.nextInt(100 - 0);
		if(x < (40 + 3*e.getDexterite())) {
			System.out.println("attaque reussi");
			double y = r.nextDouble() +0.1;
			double coefDeg = Math.max(0, Math.min(100, 10*e.getForce()-5*cible.get().getResistance()));
			int deg = (int) ((y*(1+coefDeg))*10);
			cible.get().sousCredit(deg);
			System.out.println("etudiant " + e.toString() +" a infligé " + deg + " à \n" + cible.get().toString());
			if(cible.get().getCredit() <=0) {
				z.getMap().get(j2).remove(cible.get());
			}
		}else {
			System.out.println(j1 + " attaque raté");
		}
	}
}
