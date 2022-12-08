/**
 * 
 */
package modele;

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
		System.out.println("this is strat def");		
	}

}
