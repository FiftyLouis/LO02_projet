/**
 * 
 */
package modele;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author louis
 *
 */
public class Joueurs{
	
	private String pseudo;
	private int caracDispo;
	private Filiere filiere;
	private ArrayList<Etudiant> etudiants;
	private ArrayList<Etudiant> reservistes;
	
	public enum Filiere {
		ISI,MTE,GI,GM,RT
	}
	
	public Joueurs(String p,Filiere f) {
		this.pseudo = p;
		this.caracDispo = 400;
		this.filiere = f;
		this.etudiants = new ArrayList<Etudiant>() {{
			for(int i = 0 ; i<15;i++) {
				add(new Etudiant());
			}
			for(int i = 0 ; i<4; i++) {
				add(new EtudiantElite());
			}
			add(new MaitreGobi());
		}};
		this.reservistes = new ArrayList<Etudiant>();
	}
	
	public void distribuerPoint() {
		while(this.caracDispo>0) {
			int cpt = 0;
			for(Etudiant e : this.etudiants) {
				cpt++;
				if(this.caracDispo<=0) break;
				String rep = "test";
				Scanner sc = new Scanner(System.in);
				do {
					System.out.println("points disponible : " + this.caracDispo + "\n Etudiant n°"+cpt +" : " + e.toString());
					System.out.println("veuillez saisir l'attribut à améliorer suivi de sa valuer. Pour selectionner sa strategie, taper 'strategie 1' pour agressive ou 'strategie 2' pour defensive"
					+ "\n Pour passer à un autre etudiant saisissez 'suivant'.");
					rep = sc.nextLine();
					System.out.println(this.TraduireResult(rep, e));
				}while(!rep.equals("suivant"));
				
				e.SetStrategie(new StrategieAgg());
			}
		}
	}
	
	private String TraduireResult(String rep, Etudiant e) {
		if(rep.equals("suivant") || !rep.contains(" ")) return "Joueur suivant ou saisi incorrect";
		String carac = rep.split(" ")[0];
		String nbr = rep.split(" ")[1];
		int val = 0;
		try {
			 val = Integer.parseInt(nbr);
			 if(val<0 || val>this.caracDispo) return "erreur vous n'avez pas assez de points ou vous avez saisi une valuer inferieur à 0";
		}catch(java.lang.NumberFormatException ie) {
			return "erreur vous n'avez pas saisi un int";
		}
		if(carac.equals("strategie")) {
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
					return "ce que vous avez saisi n'est pas une caractéristique";
				}
			}
		}
		return "points attribuer";
	}
	
	private void setStrat(Etudiant e, int val) {
		switch(val) {
			case 1 -> e.SetStrategie(new StrategieAgg());
			case 2 -> e.SetStrategie(new StrategieDef());
			case 3 -> System.out.println("erreur sur le choix de la strategie");
		}
	}
		
	public void choisirReserviste() {
		System.out.println("vous ne pouvez ajouter que 5 etudiant à la reserve");
		Scanner sc = new Scanner(System.in);
		while(this.reservistes.size() < 5) {
			int cpt = 0;
			for(Etudiant e : this.etudiants) {
				cpt++;
				System.out.println("nombre d'etudiant dans la reserve: " + this.reservistes.size());
				System.out.println("souhaitez vous ajouter cette etudiant n°" + cpt + " à la reserve" + e.toString()+ "y or n");
				String rep = sc.nextLine();
				if(rep.equals("y")) {
					if(this.reservistes.size()==4) {
						this.reservistes.add(e);
						System.out.println("reserve complete 5/5 etudiant");
						return;
					}
					this.reservistes.add(e);
				}
			};
		}
	}
	
	public void AffecterTroupes(Zone zone) {
		Scanner sc = new Scanner(System.in);
		int cpt = 0;
		for(Etudiant e : this.etudiants) {
			cpt++;
			System.out.println("voulez vous affecter etudiant n° "+ cpt +" : " +e.toString() + " y or n");
			String rep = sc.nextLine();
			if(rep.equals("y")) {
				zone.getMap().computeIfAbsent(this.pseudo, k -> new ArrayList<>()).add(e);
				this.etudiants.remove(e);
				System.out.println("etudiant affecter " + zone.toString());
			}
		}
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
		this.caracDispo = caracDispo;
	}
	
	
	
	
}
