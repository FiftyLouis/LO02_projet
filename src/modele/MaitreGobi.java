/**
 * 
 */
package modele;

/**
 * @author louis
 *
 */
public class MaitreGobi extends Etudiant{

	public MaitreGobi(int id) {
		super(2,2,2,10,2, id);
	}

	@Override
	public String toString() {
		return "MaitreGobi : " + this.getId() ;
	}

}
