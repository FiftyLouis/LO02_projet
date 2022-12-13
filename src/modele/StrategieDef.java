/**
 * 
 */
package modele;

import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author louis
 *
 */
public class StrategieDef implements Strategie {

	@Override
	public String toString() {
		return "Defensive";
	}

	@Override
	public void jouer(Etudiant e, Zone z,String j1,String j2) {
		Random r = new Random();
		List<Etudiant> allie = z.getMap().get(j1);
		Optional<Etudiant> cible = allie.stream().min((Etudiant e1, Etudiant e2) -> Integer.compare(e1.getCredit(), e2.getCredit()));
		int x = 0 + r.nextInt(100 - 0);
		if(x<20+6*e.getDexterite()) {
			System.out.println("soin réussi");
			double y = Math.random() * 0.6 +0.1;
			int soin = (int) Math.ceil((y) *(10 + cible.get().getConstitution()));
			cible.get().setCredit(cible.get().getCredit()+ soin);
			System.out.println("allie : " +e.toString() + " a heal de "+ soin + "\n allie " + cible.get().toString());
		}else {
			System.out.println("soin raté");
		}
	}

}
