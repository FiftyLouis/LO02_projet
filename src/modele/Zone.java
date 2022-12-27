/**
 * 
 */
package modele;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Random;


/**
 * @author louis
 *
 */
public class Zone {
	
	private Map<String, LinkedList<Etudiant>> map = new HashMap<>();

	private Partie.nomZone zone;
	private Joueurs owner;
	
	
	
	public Zone(Partie.nomZone z) {
		this.zone = z;
		this.map = new HashMap<>();
		this.owner = null;
	}
	
	/**
	 * @return the owner
	 */
	public Joueurs getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Joueurs owner) {
		this.owner = owner;
	}

	/**
	 * @return the map
	 */
	public Map<String, LinkedList<Etudiant>> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, LinkedList<Etudiant>> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "Zone [map=" + map.toString() + ", zone=" + zone + "]";
	}
	
	public void Bataille(String j1, String j2) {
		try {
			Etudiant e = this.map.get(j1).removeFirst();
			if(e.getStrategie() == null) {
					Random r = new Random();
					if(r.nextBoolean()) {
						e.setStrategie(new StrategieAgg());
					}else {
						e.setStrategie(new StrategieDef());
					}
					e.getStrategie().jouer(e, this, j1, j2);
					e.setStrategie(null);
				}else {
					e.getStrategie().jouer(e, this, j1, j2);				
				}
				this.map.get(j1).addLast(e);
		}catch(NoSuchElementException e) {
			System.out.println("Soin impossible, seule joueur allie sur la zone");
			return;
		}
			
	}
	
	/**
	 * @return the zone
	 */
	public Partie.nomZone getZone() {
		return zone;
	}

	/**
	 * @param zone the zone to set
	 */
	public void setZone(Partie.nomZone zone) {
		this.zone = zone;
	}

	
	public boolean mapIsEmpty(String j) {
		try {
			return this.map.get(j).isEmpty();
		}catch(NullPointerException e) {
			return true;
		}
	}
}
	
