/**
 * 
 */
package controleur;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Stream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import modele.Etudiant;
import modele.EtudiantElite;
import modele.Joueurs;
import modele.Partie;
import vue.Vue;

/**
 * @author louis
 *
 */
public class Controleur {

	
	private Partie env;
	private Vue vue;
	private Etudiant currentEtu;
	private int[] sommePts;
	private int nombreSurv;
	private String[] stringZone;
	
	public Controleur() {
		this.vue = new Vue(this);
		vue.frame.setVisible(true);
		this.env = new Partie();
		this.sommePts = new int[5];
		this.stringZone = new String[] {"Bibliotheque", "BureauEtudiant", "QuartierAdministratif", "HallesIndustrielles", "HalleSportive", "Default"};
		init();
	}
	
	public void init() {
		this.env.lancement();
		listeners();
	}
	
	public void listeners() {
		vue.validateConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(env.getCurrent().AffecterTroupes(env)) {
					if(StateCheck().equals("config")) {
						env.setJ1(env.getCurrent());
						env.setCurrent(env.getJ2());
						env.setState("config2");
						vue.pts.setText("400");
					}else {
						
						env.setJ2(env.getCurrent());
						env.setCurrent(env.getJ1());
						env.setState("config");
						String s = env.guerre();
						removeString(env.getZoneControle().getZone().toString());
						vue.Reaffectation.setModel(new DefaultComboBoxModel(stringZone));
						System.out.println(env.getJ1().getZoneControle());
						System.out.println(env.getJ2().getZoneControle());
						vue.switchLayer(vue.result);
						vue.affichageResult.setText(s);
					}
					vue.nomJ.setText(env.getCurrent().getPseudo());
				}else {
					System.out.println("erreur affectaton");
				}
			}
		});
		vue.validateTreve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(StateCheck().equals("config")) {
					env.setJ1(env.getCurrent());
					env.setCurrent(env.getJ2());
					env.setState("config2");
					vue.switchLayer(vue.treve);
					vue.nomTreve.setText(env.getCurrent().getPseudo());
					vue.ButtonSurv.clear();
					vue.ButtonReserv.clear();
					vue.PanelSurv.removeAll();
					vue.PanelReserv.removeAll();
					setSurv();
					setReserv();
				}else {
					vue.ButtonSurv.clear();
					vue.ButtonReserv.clear();
					vue.PanelSurv.removeAll();
					vue.PanelReserv.removeAll();
					env.setCurrent(env.getJ1());
					env.setState("config");
					String s = env.guerre();
					removeString(env.getZoneControle().getZone().toString());
					vue.Reaffectation.setModel(new DefaultComboBoxModel(stringZone));
					vue.affichageResult.setText(s);
					vue.switchLayer(vue.result);
				}
			}
		});
		vue.progBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) vue.progBox.getSelectedItem();
				if(vue.nomJ.getText().equals("Joueur 1")) {
					env.getJ1().setFiliere(Partie.Filiere.valueOf(s));
				}else {
					env.getJ2().setFiliere(Partie.Filiere.valueOf(s));
				}
			}
		});
		vue.buttonM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//for(int i=0;i<5;i++) sommePts[i]=0;
				currentEtu = getMaitre(env.getCurrent().getEtudiants());
				showAttribut();
			}
		});
		vue.configOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("test");
				//partie gestion attributs
				int somme = 0;
				somme+=currentEtu.getForce();
				somme+=currentEtu.getDexterite();
				somme+=currentEtu.getResistance();
				somme+=currentEtu.getConstitution();
				somme+=currentEtu.getInitiative();
				int nvs = 0;
				try {
					nvs = Integer.parseInt(vue.f.getText()) + Integer.parseInt(vue.dex.getText()) + Integer.parseInt(vue.res.getText()) + Integer.parseInt(vue.cons.getText()) + Integer.parseInt(vue.ini.getText());
				}catch (Exception excep){
					System.out.println("erreur type");
				}
				if(nvs-somme>env.getCurrent().getCaracDispo()) {
					System.out.println("erreur pas assez de points disponible");
					return;
				}
				try {
					currentEtu.setForce(Integer.parseInt(vue.f.getText()));
					currentEtu.setDexterite(Integer.parseInt(vue.dex.getText()));
					currentEtu.setResistance(Integer.parseInt(vue.res.getText()));
					currentEtu.setConstitution(Integer.parseInt(vue.cons.getText()));
					currentEtu.setInitiative(Integer.parseInt(vue.ini.getText()));
				}catch(Exception ex) {
					System.out.println("erreur type");
				}
				try {
					env.getCurrent().distribuerPoint(currentEtu);
				}catch(NullPointerException nullexcep) {
					System.out.println("erreur etu null");
				}
				env.getCurrent().setCaracDispo(nvs-somme);
				vue.pts.setText(Integer.toString(env.getCurrent().getCaracDispo()));
				//partie gestion affectation, strategie et reserviste
				currentEtu.setZone(Partie.nomZone.valueOf(vue.Affectation.getSelectedItem().toString()));
				currentEtu.setReserviste(vue.reserviste.isSelected());
				String strat = vue.Strategie.getSelectedItem().toString();
				currentEtu.setStrat(strat);
			}
		});
		vue.ButtonElite.forEach( button -> {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentEtu = env.getCurrent().getEtudiants().get(15+Integer.parseInt(button.getText()));
					showAttribut();
				}
			});
		});
		vue.ButtonEtu.forEach( button -> {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					currentEtu = env.getCurrent().getEtudiants().get(Integer.parseInt(button.getText()));
					showAttribut();
				}
			});
		});
		vue.ValidateResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(getEnv().gagner()) {
					vue.switchLayer(vue.fin);
					if(env.getJ1().getZoneControle() < env.getJ2().getZoneControle()) {
						vue.LabelFin.setText("Bravo à : " + env.getJ2().getPseudo());
					}else {
						vue.LabelFin.setText("Bravo à : " + env.getJ1().getPseudo());
					}
				}else {
					env.setCurrent(env.getJ1());
					env.setState("config");
					vue.switchLayer(vue.treve);
					vue.nomTreve.setText(env.getCurrent().getPseudo());
					setSurv();
					setReserv();
				}
			}
		});
		vue.reaffecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(env.getCurrent().getReservistes().contains(currentEtu)) {
					env.getCurrent().getReservistes().remove(currentEtu);
					reaffecterControl();
				}else {
					if(nombreSurv>1) {
						env.getZoneControle().getMap().get(env.getCurrent().getPseudo()).remove(currentEtu);
						reaffecterControl();
						nombreSurv--;
					}else {
						System.out.println("erreur pas assez dee survivant pour redeployer");
					}
				}
			}
		});
	}
	
	public void reaffecterControl() {
		currentEtu.setZone(Partie.nomZone.valueOf(vue.Reaffectation.getSelectedItem().toString()));
		String strat = vue.Strategie.getSelectedItem().toString();
		currentEtu.setStrat(strat);
		env.getCurrent().reaffecterTroupes(currentEtu, env);
	}
	
	public void showAttribut() {
		vue.f.setText(Integer.toString(currentEtu.getForce()));
		vue.dex.setText(Integer.toString(currentEtu.getDexterite()));
		vue.res.setText(Integer.toString(currentEtu.getResistance()));
		vue.cons.setText(Integer.toString(currentEtu.getConstitution()));
		vue.ini.setText(Integer.toString(currentEtu.getInitiative()));
		if(currentEtu.isReserviste()) {
			vue.reserviste.setSelected(true);
		}else {
			vue.reserviste.setSelected(false);
		}
		vue.Affectation.setSelectedItem(currentEtu.getZone().toString());
		if(currentEtu.getStrategie() == null) {
			vue.Strategie.setSelectedItem("Aleatoire");
		}else {
			vue.Strategie.setSelectedItem(currentEtu.getStrategie().toString());
		}
		System.out.println(currentEtu.getZone());
		System.out.println(currentEtu.isReserviste());
		vue.name.setText(currentEtu.toString());
	}
	
	public void setSurv() {
		int nbSurv;
		try {
			nbSurv = getEnv().getZoneControle().getMap().get(getEnv().getCurrent().getPseudo()).size();
		}catch(NullPointerException ex) {
			nbSurv = 0;
			System.out.println("this is a test for sruvivant");
			System.out.println(env.getZoneControle().getMap().toString());
			System.out.println(env.getCurrent());
		}
		System.out.println(env.getZoneControle().getMap().toString());
		vue.PanelSurv.setLayout(new GridLayout(2, nbSurv, 0, 0));
		vue.ButtonSurv = new ArrayList<JButton>();
		for(int i = 0 ; i < nbSurv ; i++) {
			vue.ButtonSurv.add(new JButton());
			vue.ButtonSurv.get(i).setText(Integer.toString(i));
			if(getEnv().getZoneControle().getMap().get(getEnv().getCurrent().getPseudo()).get(i) instanceof Etudiant) {
				vue.ButtonSurv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\etudiant.png"));
			}else if(getEnv().getZoneControle().getMap().get(getEnv().getCurrent().getPseudo()).get(i) instanceof EtudiantElite) {
				vue.ButtonSurv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\elite.png"));
			}else {
				vue.ButtonSurv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\maitre.png"));
			}
			vue.PanelSurv.add(vue.ButtonSurv.get(i));
		}
		this.nombreSurv = nbSurv;
		vue.treve.add(vue.PanelSurv);
		vue.ButtonSurv.forEach( button -> {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						currentEtu = env.getZoneControle().getMap().get(env.getCurrent().getPseudo()).get(Integer.parseInt(button.getText()));
						AffichageTreve();	
					}catch(IndexOutOfBoundsException excep) {
						System.out.println("out exception");
					}
				}
			});
		});
	}
	
	public void setReserv() {
		System.out.println("test "+ env.getCurrent().getReservistes());
		int nbReserv = env.getCurrent().getReservistes().size();
		vue.PanelReserv.setLayout(new GridLayout(2, nbReserv, 0, 0));
		vue.ButtonReserv = new ArrayList<JButton>();
		for(int i=0;i<nbReserv;i++) {
			vue.ButtonReserv.add(new JButton());
			vue.ButtonReserv.get(i).setText(Integer.toString(i));
			if(env.getCurrent().getReservistes().get(i) instanceof Etudiant) {
				vue.ButtonReserv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\etudiant.png"));
			}else if(env.getCurrent().getReservistes().get(i) instanceof EtudiantElite) {
				vue.ButtonReserv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\elite.png"));
			}else {
				vue.ButtonReserv.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\maitre.png"));
			}
			vue.PanelReserv.add(vue.ButtonReserv.get(i));
		}
		vue.treve.add(vue.PanelReserv);
		vue.ButtonReserv.forEach( button -> {
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						currentEtu = env.getCurrent().getReservistes().get(Integer.parseInt(button.getText()));
						AffichageTreve();	
					}catch(IndexOutOfBoundsException excep) {
						System.out.println("out exception");
					}
				}
			});
		});
	}
	
	public void removeString(String s) {
		ArrayList<String> l = new ArrayList<String>();
		for(int i=0;i<this.stringZone.length;i++) {
			l.add(stringZone[i]);
		}
		l.remove(s);
		this.stringZone = new String[l.size()];
		for(int i=0;i<l.size();i++) {
			stringZone[i]=l.get(i);
		}
	}
	
	public void AffichageTreve() {
		vue.nomSurv.setText(currentEtu.toString());
		vue.Reaffectation.setSelectedItem(currentEtu.getZone().toString());
		if(currentEtu.getStrategie() == null) {
			vue.StratCombo.setSelectedItem("Aleatoire");
		}else {
			vue.StratCombo.setSelectedItem(currentEtu.getStrategie().toString());
		}
	}
	
	public Etudiant getMaitre(ArrayList<Etudiant> etudiant) {
		for(Etudiant e : etudiant) {
			if(e.getId() == 20) {
				return e;
			}
		}return null;
	}
	
	public String StateCheck() {
		return env.getState();
	}
	
	public void ControloLayered(JPanel p) {
		this.vue.switchLayer(p);
	}

	/**
	 * @return the env
	 */
	public Partie getEnv() {
		return env;
	}

	/**
	 * @param env the env to set
	 */
	public void setEnv(Partie env) {
		this.env = env;
	}
}
