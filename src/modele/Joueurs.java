/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author louis
 *
 */
public class Joueurs{
	
	private String pseudo;
	private int caracDispo;
	private Partie.Filiere filiere;
	/**
	 * @return the filiere
	 */
	public Partie.Filiere getFiliere() {
		return filiere;
	}

	/**
	 * @param filiere the filiere to set
	 */
	public void setFiliere(Partie.Filiere filiere) {
		this.filiere = filiere;
	}

	private ArrayList<Etudiant> etudiants;
	private ArrayList<Etudiant> reservistes;
	private int zoneControle;
	
	
	public Joueurs(String p,Partie.Filiere f) {
		this.pseudo = p;
		this.caracDispo = 400;
		this.filiere = f;
		this.zoneControle = 0;
		this.etudiants = new ArrayList<Etudiant>() {{
			int i;
			for(i = 0; i<15;i++) {
				add(new Etudiant(i+1));
			}
			for(i = 15 ; i<19; i++) {
				add(new EtudiantElite(i+1));
			}
			add(new MaitreGobi(20));
		}};
		this.reservistes = new ArrayList<Etudiant>();
	}
	
	/**
	 * @return the zoneControle
	 */
	public int getZoneControle() {
		return zoneControle;
	}

	/**
	 * @param zoneControle the zoneControle to set
	 */
	public void setZoneControle(int zoneControle) {
		this.zoneControle += zoneControle;
	}

	/**
	 * @return the etudiants
	 */
	public ArrayList<Etudiant> getEtudiants() {
		return etudiants;
	}

	/**
	 * @param etudiants the etudiants to set
	 */
	public void setEtudiants(ArrayList<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void distribuerPoint(Etudiant etu) {
		for(Etudiant e : this.etudiants) {
			if(e.getId() == etu.getId()) {
				e = etu;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Joueurs [pseudo=" + pseudo + ", caracDispo=" + caracDispo + ", filiere=" + filiere + ", etudiants="
				+ etudiants + ", reservistes=" + reservistes + ", zoneControle=" + zoneControle + "]";
	}

	private String TraduireResult(String rep, Etudiant e) {
		if(rep.equals("suivant") || !rep.contains(" ")) return "Joueur suivant ou saisi incorrect";
		String carac = rep.split(" ")[0];
		String nbr = rep.split(" ")[1];
		int val = 0;
		try {
			 val = Integer.parseInt(nbr);
			 if(val<0 || val>this.caracDispo) return "erreur vous n'avez pas assez de points ou vous avez saisi une valuer inferieur ? 0";
		}catch(java.lang.NumberFormatException ie) {
			return "erreur vous n'avez pas saisi un int";
		}
		/*if(carac.equals("strategie")) {
			this.setStrat(e, val);
		}else {
			this.setCaracDispo(this.getCaracDispo() -val);
			switch(carac){
				case "dexterite" -> e.setDexterite(e.getDexterite() + val);
				case "force" -> e.setForce(e.getForce() + val);
				case "resistance" -> e.setResistance(e.getResistance() +val);
				case "constitution" -> e.setConstitution(e.getConstitution() +val);
				case "initiative" -> e.setInitiative(e.getInitiative() +val);
				default -> {
					this.setCaracDispo(this.getCaracDispo()+val);
					return "ce que vous avez saisi n'est pas une caract?ristique";
				}
			}
		}*/
		return "points attribuer";
	}
	
		
	public void choisirReserviste() {
		System.out.println("vous ne pouvez ajouter que 5 etudiant ? la reserve");
		Scanner sc = new Scanner(System.in);
		ArrayList<Etudiant> remove = new ArrayList<Etudiant>();
		while(this.reservistes.size() < 5) {
			for(Etudiant e : this.etudiants) {
				System.out.println("nombre d'etudiant dans la reserve: " + this.reservistes.size());
				System.out.println("souhaitez vous ajouter cette etudiant n?" + e.getId() + " ? la reserve" + e.toString()+ "y or n");
				String rep = sc.nextLine();
				if(rep.equals("y")) {
					remove.add(e);
					if(this.reservistes.size()==4) {
						this.reservistes.add(e);
						System.out.println("reserve complete 5/5 etudiant");
						this.etudiants.removeAll(remove);
						return;
					}
					this.reservistes.add(e);
				}
			}
		}
	}
	
	public void AffecterReservistes(Zone zone) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Etudiant> remove =  new ArrayList<Etudiant>();
		for(Etudiant e : this.reservistes) {
			System.out.println("voulez vous affecter etudiant n? "+ e.getId() +" : " +e.toString() + "  ? zone "+ zone.getZone() +" y or n");
			String rep = sc.nextLine();
			if(rep.equals("y")) {
				zone.getMap().computeIfAbsent(this.pseudo, k -> new LinkedList<>()).add(e);
				remove.add(e);
				System.out.println("reserviste affecter ?" + zone.getZone());
			}
		}
		this.reservistes.removeAll(remove);
	}
	
	public boolean AffecterTroupes(Partie p) {
		int cpt = 0 ;
		ArrayList<Etudiant> remove =  new ArrayList<Etudiant>();
		for(Etudiant e : this.etudiants) {
			if(e.isReserviste() && !e.getZone().equals(Partie.nomZone.Default)) {
				System.out.println("erreur saisi");
				return false;
			}else if(e.isReserviste()) {
				cpt++;
			}else if(!e.isReserviste() && e.getZone().equals(Partie.nomZone.Default)) {
				System.out.println("erreur etu pas affecter");
				return false;
			}
		}
		if(cpt!=5) {
			System.out.println("erreur nomrbe de reservistes incorrecte");
			return false;
		}
		for(Zone z : p.getZones()) {
			for(Etudiant e : this.etudiants) {
				if(!e.isReserviste() && e.getZone().equals(z.getZone())) {
					z.getMap().computeIfAbsent(this.pseudo, k -> new LinkedList<>()).add(e);
					remove.add(e);
				}else if(e.isReserviste() && e.getZone().equals(Partie.nomZone.Default)){
					if(!this.reservistes.contains(e)) {
						this.reservistes.add(e);
						remove.add(e);
					}
				}
			}
			if(z.mapIsEmpty(pseudo)) {
				System.out.println("erreur map vide");
				p.getZones().forEach(zone -> {
					if(!zone.mapIsEmpty(pseudo))zone.getMap().get(this.pseudo).clear();
				});
				this.reservistes.clear();
				return false;
			}
		}
		System.out.println("affectation reussi");
		this.etudiants.removeAll(remove);
		return true;
		/*ArrayList<Etudiant> remove =  new ArrayList<Etudiant>();
		for(Etudiant e : this.etudiants) {
			System.out.println("voulez vous affecter etudiant n? "+ e.getId() +" : " +e.toString() + "  ? zone "+ zone.getZone() +" y or n");
			Scanner sc = new Scanner(System.in);
			String rep = sc.nextLine();
			if(rep.equals("y")) {
				zone.getMap().computeIfAbsent(this.pseudo, k -> new LinkedList<>()).add(e);
				remove.add(e);
				System.out.println("etudiant affecter " + zone.getZone());
			}
		}
		this.etudiants.removeAll(remove);*/
	}
	
	/**
	 * @return the reservistes
	 */
	public ArrayList<Etudiant> getReservistes() {
		return reservistes;
	}

	/**
	 * @param reservistes the reservistes to set
	 */
	public void setReservistes(ArrayList<Etudiant> reservistes) {
		this.reservistes = reservistes;
	}

	public void reaffecterTroupes(Etudiant etu, Partie p) {
		for(Zone z : p.getZones()) {
			if(z.getZone().equals(etu.getZone())) {
				z.getMap().computeIfAbsent(this.pseudo, k -> new LinkedList<>()).add(etu);
				return;
			}
		}
		/*Scanner sc =  new Scanner(System.in);
		ArrayList<Etudiant> remove =  new ArrayList<Etudiant>();
		zone.getMap().get(this.pseudo);
		int size = zone.getMap().get(this.pseudo).size();
		System.out.println(size);
		for(Etudiant e : zone.getMap().get(this.pseudo)) {
			if(size == 1) {
				System.out.println("dernier etudiant impossible ? reaffecter");
				return;
			}
			System.out.println("voulez vous reaffecter cette etudiant : Attention il doit en restez un pour occuper la zone :" + e.toString());
			System.out.println("y or n");
			String rep = sc.nextLine();
			if(rep.equals("y")) {
				size--;
				this.etudiants.add(e);
				System.out.println("etudiant ajouter ? votre liste d'etudiants");
			}
		}
		zone.getMap().get(this.pseudo).removeAll(remove);*/
	}

	/**
	 * @return the caracDispo
	 */
	public int getCaracDispo() {
		return caracDispo;
	}

	/**
	 * @param caracDispo the caracDispo to set
	 */
	public void setCaracDispo(int caracDispo) {
		this.caracDispo -= caracDispo;
	}
	
	
	
	
}
