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
public class Joueurs {
	
	private String pseudo;
	private int caracDispo;
	private Filiere filiere;
	private ArrayList<Etudiant> etudiants;
	private ArrayList<Etudiant> reservistes;
	
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
		Scanner sc = new Scanner(System.in);
		while(this.caracDispo>0) {
			etudiants.forEach( (e) -> {
				System.out.println(e.toString());
				String rep = sc.nextLine();
				if(rep.equals("y")) {
					e.setDexterite(e.getDexterite() + 0);
					e.setForce(e.getForce()+ 0);
				}
				System.out.println("set strategie");
				rep = sc.nextLine();
				e.SetStrategie(new StrategieAgg());
			});
		}
	}

	
	public void choisirReserviste() {
		System.out.println("vous ne pouvez ajouter que 5 etudiant à la reserve");
		Scanner sc = new Scanner(System.in);
		while(this.reservistes.size() < 5) {
			etudiants.forEach((e) -> {
				System.out.println("souhaitez vous ajouter cette etudiant à la reserve" + e.toString()+ "y or n");
				String rep = sc.nextLine();
				if(rep.equals("y")) {
					//todo
				}
			});
		}
	}
	
	
}
