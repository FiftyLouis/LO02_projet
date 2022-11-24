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
	
	private Map<String, List<Etudiant>> map = new HashMap<>();

	private nomZone zone;
	
	
	public enum nomZone{
		Bibliotheque,BureauEtudiant,QuartierAdministratif,HallesIndustrielles,HalleSportive
	}
	
	public Zone(nomZone z) {
		this.zone = z;
		this.map = new HashMap<>();
	}
	
	/**
	 * @return the map
	 */
	public Map<String, List<Etudiant>> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, List<Etudiant>> map) {
		this.map = map;
	}
	
	@Override
	public String toString() {
		return "Zone [map=" + map.toString() + ", zone=" + zone + "]";
	}

	public void testMultiMap() {
		/*MultiMap<String, String> map = new MultiValueMap<>();
		map.put("key1", "value1");
		map.put("key1", "value2");
		assertThat((Collection<String>) map.get("key1"))
		  .contains("value1", "value2");*/
	}
	
	
	
}
