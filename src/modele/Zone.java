/**
 * 
 */
package modele;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

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
			Etudiant e = this.map.get(j1).removeFirst();
			e.getStrategie().jouer(e, this, j1, j2);
			this.map.get(j1).addLast(e);
	}
	
	public boolean mapIsEmpty(String j1, String j2) {
		try {
			return this.map.get(j1).isEmpty() || this.map.get(j2).isEmpty();
		}catch(NullPointerException e) {
			System.out.println("map vide " + this.zone);
			return true;
		}
	}
	
	
	
}
