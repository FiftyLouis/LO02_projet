/**
 * 
 */
package modele;

/**
 * @author louis
 *
 */
public class EtudiantElite extends Etudiant{

	public EtudiantElite(int id) {
		super(1,1,1,5,1,id);
	}

	@Override
	public String toString() {
		return "EtudiantElite []" + super.toString();
	}

}
