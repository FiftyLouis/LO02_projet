package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	
	private ArrayList<Zone> zones;
	private Zone zoneControle;
	private Joueurs j1;
	private Joueurs j2;
	private Joueurs current;
	private int nbZoneWin;
	private String state;
	
	public enum Filiere {
		ISI,MTE,GI,GM,RT,A2I,MM
	}
	
	public enum nomZone{
		Bibliotheque,BureauEtudiant,QuartierAdministratif,HallesIndustrielles,HalleSportive,Default
	}
	
	public Partie() {
		this.zones = new ArrayList<Zone>();
		this.zoneControle = null;
		this.j1 =  null;
		this.j2 = null;
		this.current = null;
		this.nbZoneWin = 3;
		this.state = "config";
		
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the j1
	 */
	public Joueurs getJ1() {
		return j1;
	}

	/**
	 * @param j1 the j1 to set
	 */
	public void setJ1(Joueurs j1) {
		this.j1 = j1;
	}

	/**
	 * @return the j2
	 */
	public Joueurs getJ2() {
		return j2;
	}

	/**
	 * @param j2 the j2 to set
	 */
	public void setJ2(Joueurs j2) {
		this.j2 = j2;
	}

	public void lancement() {
		//Scanner sc = new Scanner(System.in);
		this.zones.add(new Zone(nomZone.Bibliotheque));
		this.zones.add(new Zone(nomZone.BureauEtudiant));
		this.zones.add(new Zone(nomZone.HallesIndustrielles));
		this.zones.add(new Zone(nomZone.HalleSportive));
		this.zones.add(new Zone(nomZone.QuartierAdministratif));
		this.j1 = new Joueurs("Joueur 1", Filiere.ISI);
		this.j2 = new Joueurs("Joueur 2", Filiere.ISI);
		this.current = j1;
		/*System.out.println("set up partie");
		System.out.println("tour joueur :" + j1.getPseudo());
		j1.distribuerPoint();
		System.out.println("tour joueur :" + j2.getPseudo());
		j2.distribuerPoint();
		System.out.println("fin distribution \n debut choix reserviste");
		j1.choisirReserviste();
		j2.choisirReserviste();
		System.out.println("debut affectation");
		this.zones.forEach( z-> j1.AffecterTroupes((Zone) z));
		this.zones.forEach( z -> j2.AffecterTroupes((Zone) z));
		while(!this.gagner()) {
			System.out.println("debut bataille");
			Zone z = this.guerre();
			this.zones.remove(z);
			if(this.gagner()) break;
			System.out.println("phase de trève");
			this.reaffecter(z);
			this.reservistes();
		}
		System.out.println("partie fini");
		System.out.println("score joueur 1: " + j1.getZoneControle());
		System.out.println("score joueur 2: " + j2.getZoneControle());
		sc.close();*/
	}
	
	/**
	 * @return the current
	 */
	public Joueurs getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(Joueurs current) {
		this.current = current;
	}

	public String guerre() {
		for(Zone z : this.zones) {
			z.Bataille(j1.getPseudo(), j2.getPseudo());
			if(z.mapIsEmpty(j2.getPseudo())) {
				this.zones.remove(z);
				j1.setZoneControle(1);
				this.setZoneControle(z);
				return "victoire de : " + j1.getPseudo() + " sur la zone " + z.toString(); 
			}
			z.Bataille(j2.getPseudo(), j1.getPseudo());
			if(z.mapIsEmpty(j1.getPseudo())) {
				this.zones.remove(z);
				j2.setZoneControle(1);
				this.setZoneControle(z);
				return "victoire de : " + j2.getPseudo() + " sur la zone " + z.toString(); 
			}
		}
		return this.guerre();
		/*ArrayList<Zone> zoneVide = new ArrayList<Zone>();
		for( Zone z  : this.zones) {
			System.out.println("zone actuelle : " + z.getZone());
			if(z.mapIsEmpty(j1.getPseudo()) && z.mapIsEmpty(j2.getPseudo())) {
				zoneVide.add(z);
			}else if(z.mapIsEmpty(j1.getPseudo())) {
				System.out.println("gagnant de la map : "+ j2.getPseudo() + " : " + z.getZone());
				this.zoneControle.add(z);
				z.setOwner(j2);
				j2.setZoneControle(j2.getZoneControle()+1);
				return z;
			}else if(z.mapIsEmpty(j2.getPseudo())) {
				System.out.println("gagnant de la map : "+ j1.getPseudo() + " : " + z.getZone());
				this.zoneControle.add(z);
				z.setOwner(j1);
				j1.setZoneControle(j1.getZoneControle()+1);
				return z;
			}
			else {
				//int sum = 0;
				//z.getMap().get(j1).forEach( (e,sum) -> sum += e.getCredit());
				//System.out.println("credit de j1 dans zone courante : " + sum);
				z.Bataille(j1.getPseudo(), j2.getPseudo());
				if(z.mapIsEmpty(j2.getPseudo())) {
					System.out.println("gagnant de la map : "+ j1.getPseudo() + " : " + z.getZone());
					this.zoneControle.add(z);
					z.setOwner(j1);
					j1.setZoneControle(j1.getZoneControle()+1);
					return z;
				}
				z.Bataille(j2.getPseudo(), j1.getPseudo());
				if(z.mapIsEmpty(j1.getPseudo())) {
					System.out.println("gagnant de la map : "+ j2.getPseudo() + " : " + z.getZone());
					this.zoneControle.add(z);
					z.setOwner(j2);
					j2.setZoneControle(j2.getZoneControle()+1);
					return z;
				}
			}	
		}
		if(zoneVide.size() > 0) {
			this.zones.removeAll(zoneVide);
			this.nbZoneWin -= zoneVide.size();
		}
		return this.guerre();
		*/
	}
	
	/*public void reaffecter(Zone z) {
		System.out.println("réaffectation etudiant :");
		z.getOwner().reaffecterTroupes(z);
		//this.zones.forEach( zone -> z.getOwner().AffecterTroupes(zone));
	}*/
	
	/**
	 * @return the zones
	 */
	public ArrayList<Zone> getZones() {
		return zones;
	}

	/**
	 * @param zones the zones to set
	 */
	public void setZones(ArrayList<Zone> zones) {
		this.zones = zones;
	}

	/**
	 * @return the zoneControle
	 */
	public Zone getZoneControle() {
		return zoneControle;
	}

	/**
	 * @param zoneControle the zoneControle to set
	 */
	public void setZoneControle(Zone zoneControle) {
		this.zoneControle = zoneControle;
	}

	public void reservistes() {
		System.out.println("affectation réservistes :");
		System.out.println("joueur : " +j1.getPseudo());
		this.zones.forEach( z-> j1.AffecterReservistes(z));
		System.out.println("joueur : " +j2.getPseudo());
		this.zones.forEach( z-> j2.AffecterReservistes(z));
	}
	
	public boolean gagner() {
		return j1.getZoneControle()>=this.nbZoneWin || j2.getZoneControle()>=this.nbZoneWin;
	}
	
	public static void main(String args[]) {
		Partie p = new Partie();
		p.lancement();
	}
}


