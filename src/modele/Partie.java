package modele;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
	
	private ArrayList<Zone> zones;
	private ArrayList<Zone> zoneControle;
	private Joueurs j1;
	private Joueurs j2;
	private int nbZoneWin;
	
	public enum Filiere {
		ISI,MTE,GI,GM,RT
	}
	
	public enum nomZone{
		Bibliotheque,BureauEtudiant,QuartierAdministratif,HallesIndustrielles,HalleSportive
	}
	
	public Partie() {
		this.zones = new ArrayList<Zone>();
		this.zoneControle = new ArrayList<Zone>();
		this.j1 =  null;
		this.j2 = null;
		this.nbZoneWin = 3;
		
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
		System.out.println(j1.getZoneControle());
		System.out.println(j2.getZoneControle());
		sc.close();
	}
	
	public Zone guerre() {
		ArrayList<Zone> zoneVide = new ArrayList<Zone>();
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
				j2.setZoneControle(j1.getZoneControle()+1);
				return z;
			}
			else {
				/*int sum = 0;
				z.getMap().get(j1).forEach( (e,sum) -> sum += e.getCredit());
				System.out.println("credit de j1 dans zone courante : " + sum);*/
				z.Bataille(j1.getPseudo(), j2.getPseudo());
				if(z.mapIsEmpty(j2.getPseudo())) {
					System.out.println("gagnant de la map : "+ j1.getPseudo() + " : " + z.getZone());
					this.zoneControle.add(z);
					z.setOwner(j1);
					j1.setZoneControle(j1.getZoneControle()+1);
					return z;
				}
				z.Bataille(j2.getPseudo(), j1.getPseudo());
			}	
		}
		if(zoneVide.size() > 0) {
			this.zones.removeAll(zoneVide);
			this.nbZoneWin -= zoneVide.size();
		}
		return this.guerre();
	}
	
	public void reaffecter(Zone z) {
		System.out.println("réaffectation etudiant :");
		z.getOwner().reaffecterTroupes(z);
		this.zones.forEach( zone -> z.getOwner().AffecterTroupes(zone));
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


