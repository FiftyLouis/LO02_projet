package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	
	private String etat;
	private ArrayList<Zone> zones;
	private ArrayList<Zone> zoneControle;
	private Joueurs j1;
	private Joueurs j2;
	
	public enum Filiere {
		ISI,MTE,GI,GM,RT
	}
	
	public enum nomZone{
		Bibliotheque,BureauEtudiant,QuartierAdministratif,HallesIndustrielles,HalleSportive
	}
	
	public Partie() {
		this.etat = "dev";
		this.zones = new ArrayList<Zone>();
		this.zoneControle = new ArrayList<Zone>();
		this.j1 =  null;
		this.j2 = null;
		
	}
	
	public void lancement() {
		Scanner sc = new Scanner(System.in);
		this.zones.add(new Zone(nomZone.Bibliotheque));
		this.zones.add(new Zone(nomZone.BureauEtudiant));
		this.zones.add(new Zone(nomZone.HallesIndustrielles));
		this.zones.add(new Zone(nomZone.HalleSportive));
		this.zones.add(new Zone(nomZone.QuartierAdministratif));
		this.j1 = new Joueurs("test1", Filiere.ISI);
		this.j2 = new Joueurs("test2", Filiere.ISI);
		System.out.println("set up partie");
		System.out.println("tour joueur :" + j1.getPseudo());
		/*j1.distribuerPoint();
		System.out.println("tour joueur :" + j2.getPseudo());
		j2.distribuerPoint();
		System.out.println("done");*/
		j1.choisirReserviste();
		j2.choisirReserviste();
		this.zones.forEach( z-> j1.AffecterTroupes((Zone) z));
		this.zones.forEach( z -> j2.AffecterTroupes((Zone) z));
		while(this.gagner()) {
			Zone z = this.guerre();
			this.zones.remove(z);
			if(this.gagner()) break;
			this.reaffecter(z);
		}
		System.out.println("partie fini");
	}
	
	public Zone guerre() {
		for( Zone z  : this.zones) {
			if(z.mapIsEmpty(j1.getPseudo(), j2.getPseudo())) {
				this.zoneControle.add(z);
				z.setOwner(j1);
				j1.setZoneControle(j1.getZoneControle() + 1);
				return z;
			};
			z.Bataille(j2.getPseudo(), j1.getPseudo());
			if(z.mapIsEmpty(j1.getPseudo(), j2.getPseudo())) {
				z.setOwner(j2);
				j2.setZoneControle(j2.getZoneControle() + 1);
				return z;
			};
			z.Bataille(j2.getPseudo(), j1.getPseudo());
		}
		return this.guerre();
	}
	
	public void reaffecter(Zone z) {
		//z.getOwner().reaffecterTroupes(z);
		//this.zones.forEach( zone -> z.getOwner().AffecterTroupes(zone));
	}
	
	public boolean gagner() {
		return j1.getZoneControle()==3 || j2.getZoneControle()==3;
	}
	
	public static void main(String args[]) {
		Partie p = new Partie();
		p.lancement();
	}
}


