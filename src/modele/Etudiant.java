/**
 * 
 */
package modele;

/**
 * @author louis
 *
 */
public class Etudiant {
	
	private int credit;
	private Strategie strategie;
	
	//attirubt etudiant
	private int dexterite;
	private int force;
	private int resistance;
	private int constitution;
	private int initiative;
	
	public Etudiant(Strategie s) {
		this.credit = 30;
		this.dexterite = 0;
		this.force = 0;
		this.resistance = 0;
		this.constitution = 0;
		this.initiative = 0;
		this.strategie = s;
	}
	
	
	
}