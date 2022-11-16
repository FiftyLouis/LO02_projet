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
	
	
	public Etudiant() {
		this.credit = 30;
		this.dexterite = 0;
		this.force = 0;
		this.resistance = 0;
		this.constitution = 0;
		this.initiative = 0;
		this.strategie = null;
	}
	
	public Etudiant(int d, int f, int r, int c, int i) {
		this.credit = 30;
		this.dexterite = d;
		this.force = f;
		this.resistance = r;
		this.constitution = c;
		this.initiative = i;
		this.strategie= null;
	}
	
	public void SetStrategie(Strategie s) {
		this.strategie = s;
	}
	

	@Override
	public String toString() {
		return "Etudiant [credit=" + credit + ", strategie=" + strategie + ", dexterite=" + dexterite + ", force="
				+ force + ", resistance=" + resistance + ", constitution=" + constitution + ", initiative=" + initiative
				+ "]";
	}

	/**
	 * @return the dexterite
	 */
	public int getDexterite() {
		return dexterite;
	}

	/**
	 * @param dexterite the dexterite to set
	 */
	public void setDexterite(int dexterite) {
		this.dexterite = dexterite;
	}

	/**
	 * @return the force
	 */
	public int getForce() {
		return force;
	}

	/**
	 * @param force the force to set
	 */
	public void setForce(int force) {
		this.force = force;
	}

	/**
	 * @return the resistance
	 */
	public int getResistance() {
		return resistance;
	}

	/**
	 * @param resistance the resistance to set
	 */
	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	/**
	 * @return the constitution
	 */
	public int getConstitution() {
		return constitution;
	}

	/**
	 * @param constitution the constitution to set
	 */
	public void setConstitution(int constitution) {
		this.constitution = constitution;
	}

	/**
	 * @return the initiative
	 */
	public int getInitiative() {
		return initiative;
	}

	/**
	 * @param initiative the initiative to set
	 */
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}
	
	
	
	
}