package modele;

public class Partie {
	
	private String etat;
	private Zone zones;
	
	public Partie(Zone z) {
		this.etat = "dev";
		this.zones = z;
	}
	
	public void lancement() {
		
	}
	
	public void gagner() {
		
	}
	
	
}

class Multithreading implements Runnable {
    public void run() {
        try {
            // afficher le thread en cours d'ex�cution
            System.out.println("Thread " + Thread.currentThread().getId() + " est en cours d'ex�cution");
 
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }
}
