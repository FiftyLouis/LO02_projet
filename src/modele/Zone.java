/**
 * 
 */
package modele;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author louis
 *
 */
public class Zone {
	private LinkedList<Etudiant> J1;
	private LinkedList<Etudiant> J2;
	private nomZone zone;
	
	
	enum nomZone{
		Bibliotheque,BureauEtudiant,QuartierAdministratif,HallesIndustrielles,HalleSportive
	}
	
	public Zone(nomZone z) {
		this.zone = z;
		this.J1 = new LinkedList<Etudiant>();
		this.J2 = new LinkedList<Etudiant>();
	}
	
	
	
}
