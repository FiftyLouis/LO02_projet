package vue;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Button;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import controleur.Controleur;
import modele.Etudiant;
import modele.EtudiantElite;

import javax.swing.JButton;
import java.awt.Label;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import javax.swing.JLayeredPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.beans.PropertyChangeEvent;
import javax.swing.JTextArea;

public class Vue {
	
	private Controleur control;
	public JFrame frame;
	public JLayeredPane LayeredPane;
	public JPanel config;
	public JPanel treve;
	public JPanel fin;
	public JButton validateConfig;
	public JButton validateTreve;
	public JLabel nomJ;
	public JComboBox progBox;
	public JButton buttonM;
	public JLabel pts;
	private JLabel force;
	private JLabel dexterite;
	private JLabel resistance;
	private JLabel constitution;
	private JLabel initiative;
	public JTextField f;
	public JTextField dex;
	public JTextField res;
	public JTextField cons;
	public JTextField ini;
	public JLabel name;
	private JLabel affec;
	public JComboBox Affectation;
	public JComboBox Strategie;
	public JCheckBox reserviste;
	public JButton configOk;
	public ArrayList<JButton> ButtonElite;
	public ArrayList<JButton> ButtonEtu;
	public ArrayList<JButton> ButtonSurv;
	public ArrayList<JButton> ButtonReserv;
	private JButton btnNewButton;
	private JPanel PanelEtu;
	private JLabel LabelEtu;
	public JPanel result;
	private JLabel lblResultat;
	public JButton ValidateResult;
	public JLabel affichageResult;
	public JLabel nomTreve;
	public JPanel PanelSurv;
	public JPanel PanelReserv;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JPanel panel_2;
	private JLabel lblNewLabel_3;
	public JComboBox Reaffectation;
	public JComboBox StratCombo;
	public JButton reaffecter;
	private JLabel lblNewLabel_6;
	public JLabel LabelFin;
	public JLabel nomSurv;
	


	

/*	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vue window = new Vue();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the application.
	 */
	public Vue(Controleur c) {
		this.control = c;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 926, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		LayeredPane = new JLayeredPane();
		LayeredPane.setBounds(0, 0, 910, 428);
		frame.getContentPane().add(LayeredPane);
		
		config = new JPanel();
		LayeredPane.setLayer(config, 3);
		config.setBackground(new Color(128, 255, 255));
		config.setBounds(0, 0, 910, 428);
		LayeredPane.add(config);
		config.setLayout(null);
		
		treve = new JPanel();
		LayeredPane.setLayer(treve, 1);
		treve.setBackground(new Color(0, 255, 255));
		treve.setBounds(0, 0, 910, 428);
		LayeredPane.add(treve);
		treve.setLayout(null);
		
		fin = new JPanel();
		LayeredPane.setLayer(fin, 0);
		fin.setBackground(new Color(0, 255, 0));
		fin.setBounds(0, 0, 910, 428);
		LayeredPane.add(fin);
		fin.setLayout(null);
		
		lblNewLabel_6 = new JLabel("Victoire\r\n");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel_6.setBounds(397, 102, 117, 99);
		fin.add(lblNewLabel_6);
		
		LabelFin = new JLabel("New label");
		LabelFin.setBounds(166, 209, 505, 91);
		fin.add(LabelFin);
		
		validateConfig = new JButton("Valider");
		validateConfig.setBounds(786, 383, 89, 23);
		config.add(validateConfig);
		
		nomJ = new JLabel("Joueur 1");
		nomJ.setBounds(50, 21, 76, 29);
		config.add(nomJ);
		
		progBox = new JComboBox();
		progBox.setModel(new DefaultComboBoxModel(new String[] {"ISI", "GM", "A2I", "RT", "MTE", "GI", "MM"}));
		progBox.setBounds(136, 24, 46, 23);
		config.add(progBox);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(62, 86, 66, 107);
		config.add(panel);
		panel.setLayout(null);
		
		buttonM = new JButton("");
		buttonM.setBounds(-13, 6, 93, 119);
		buttonM.setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\maitre.png"));
		panel.add(buttonM);
		
		JLabel lblNewLabel = new JLabel("Capitaine");
		lblNewLabel.setBounds(60, 61, 66, 23);
		config.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 255, 255));
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_1.setBounds(62, 237, 581, 158);
		config.add(panel_1);
		panel_1.setLayout(null);
		
		force = new JLabel("force");
		force.setBounds(57, 11, 46, 14);
		panel_1.add(force);
		
		dexterite = new JLabel("dexterite");
		dexterite.setBounds(57, 36, 46, 14);
		panel_1.add(dexterite);
		
		resistance = new JLabel("resistance");
		resistance.setBounds(57, 61, 58, 14);
		panel_1.add(resistance);
		
		constitution = new JLabel("constitution");
		constitution.setBounds(57, 94, 58, 14);
		panel_1.add(constitution);
		
		initiative = new JLabel("initiative");
		initiative.setBounds(57, 119, 46, 14);
		panel_1.add(initiative);
		
		f = new JTextField();
		f.setBounds(133, 8, 86, 20);
		panel_1.add(f);
		f.setColumns(10);
		
		dex = new JTextField();
		dex.setColumns(10);
		dex.setBounds(133, 33, 86, 20);
		panel_1.add(dex);
		
		res = new JTextField();
		res.setColumns(10);
		res.setBounds(133, 60, 86, 20);
		panel_1.add(res);
		
		cons = new JTextField();
		cons.setColumns(10);
		cons.setBounds(133, 91, 86, 20);
		panel_1.add(cons);
		
		ini = new JTextField();
		ini.setColumns(10);
		ini.setBounds(133, 116, 86, 20);
		panel_1.add(ini);
		
		affec = new JLabel("Affectation");
		affec.setBounds(298, 21, 86, 20);
		panel_1.add(affec);
		
		Affectation = new JComboBox();
		Affectation.setModel(new DefaultComboBoxModel(new String[] {"Default", "Bibliotheque", "BureauEtudiant", "QuartierAdministratif", "HallesIndustrielles", "HalleSportive"}));
		Affectation.setBounds(394, 20, 95, 22);
		panel_1.add(Affectation);
		
		JLabel strat = new JLabel("Strategie");
		strat.setBounds(298, 61, 58, 14);
		panel_1.add(strat);
		
		Strategie = new JComboBox();
		Strategie.setModel(new DefaultComboBoxModel(new String[] {"Aleatoire", "Agressive", "Defensive"}));
		Strategie.setBounds(394, 57, 95, 22);
		panel_1.add(Strategie);
		
		reserviste = new JCheckBox("Reserviste");
		reserviste.setBounds(298, 90, 97, 23);
		panel_1.add(reserviste);
		
		configOk = new JButton("ok");
		configOk.setBounds(444, 115, 89, 23);
		panel_1.add(configOk);
		
		JLabel PointsTxt = new JLabel("Points \u00E0 distribuer");
		PointsTxt.setBounds(80, 212, 95, 23);
		config.add(PointsTxt);
		
		pts = new JLabel("400");
		pts.setBounds(185, 212, 66, 23);
		config.add(pts);
		
		name = new JLabel("Aucun");
		name.setBounds(324, 212, 221, 25);
		config.add(name);
		name.setBackground(new Color(255, 255, 0));
		
		JPanel panelElite = new JPanel();
		panelElite.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelElite.setBackground(Color.CYAN);
		panelElite.setBounds(295, 86, 250, 107);
		panelElite.setLayout(new GridLayout(1, 4, 0, 0));
		ButtonElite = new ArrayList<JButton>();
		for(int i = 0 ; i<4;i++) {
			ButtonElite.add(new JButton());
			ButtonElite.get(i).setText(Integer.toString(i));
			ButtonElite.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\elite.png"));
			panelElite.add(ButtonElite.get(i));
		}
		config.add(panelElite);
		
		JLabel NomPanelElite = new JLabel("Elite");
		NomPanelElite.setBounds(298, 65, 95, 23);
		config.add(NomPanelElite);
		
		PanelEtu = new JPanel();
		PanelEtu.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		PanelEtu.setBackground(new Color(0, 255, 255));
		PanelEtu.setLayout(new GridLayout(4, 4, 2, 0));
		PanelEtu.setBounds(679, 21, 196, 335);
		
		ButtonEtu = new ArrayList<JButton>();
		for(int i = 0 ; i < 15 ; i++) {
			ButtonEtu.add(new JButton());
			ButtonEtu.get(i).setText(Integer.toString(i));
			ButtonEtu.get(i).setIcon(new ImageIcon("C:\\Users\\louis\\eclipse-workspace\\LO02_projet\\img\\etudiant.png"));
			PanelEtu.add(ButtonEtu.get(i));
		}
		config.add(PanelEtu);

		LabelEtu = new JLabel("Etudiant");
		LabelEtu.setBounds(596, 28, 73, 22);
		config.add(LabelEtu);
		
		validateTreve = new JButton("Valider");
		validateTreve.setBounds(786, 383, 89, 23);
		treve.add(validateTreve);
		
		nomTreve = new JLabel("New label");
		nomTreve.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nomTreve.setBounds(30, 11, 153, 40);
		treve.add(nomTreve);
		
		PanelSurv = new JPanel();
		PanelSurv.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		PanelSurv.setBackground(new Color(0, 255, 255));
		PanelSurv.setBounds(30, 98, 141, 271);

		
		PanelReserv = new JPanel();
		PanelReserv.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		PanelReserv.setBackground(new Color(0, 255, 255));
		PanelReserv.setBounds(554, 41, 329, 123);
		
		
		lblNewLabel_1 = new JLabel("Etudiant restant");
		lblNewLabel_1.setBounds(29, 73, 127, 14);
		treve.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Reservistes");
		lblNewLabel_2.setBounds(554, 11, 107, 14);
		treve.add(lblNewLabel_2);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel_2.setBackground(new Color(0, 255, 255));
		panel_2.setBounds(237, 275, 476, 131);
		treve.add(panel_2);
		panel_2.setLayout(null);
		
		Reaffectation = new JComboBox();
		Reaffectation.setModel(new DefaultComboBoxModel(new String[] {"Bibliotheque", "BureauEtudiant", "QuartierAdministratif", "HallesIndustrielles", "HalleSportive", "Default"}));
		Reaffectation.setBounds(156, 36, 123, 22);
		panel_2.add(Reaffectation);
		
		JLabel lblNewLabel_4 = new JLabel("Affectation");
		lblNewLabel_4.setBounds(55, 40, 91, 14);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Strategie");
		lblNewLabel_5.setBounds(55, 75, 79, 14);
		panel_2.add(lblNewLabel_5);
		
		StratCombo = new JComboBox();
		StratCombo.setModel(new DefaultComboBoxModel(new String[] {"Aleatoire", "Agressive", "Defensive"}));
		StratCombo.setBounds(160, 71, 123, 22);
		panel_2.add(StratCombo);
		
		reaffecter = new JButton("ok");
		reaffecter.setBounds(377, 97, 89, 23);
		panel_2.add(reaffecter);
		
		nomSurv = new JLabel("New label");
		nomSurv.setBounds(10, 15, 91, 14);
		panel_2.add(nomSurv);
		
		lblNewLabel_3 = new JLabel("Configuration");
		lblNewLabel_3.setBounds(240, 250, 107, 14);
		treve.add(lblNewLabel_3);
		
		result = new JPanel();
		result.setBackground(new Color(0, 255, 255));
		LayeredPane.setLayer(result, 2);
		result.setBounds(0, 0, 910, 428);
		LayeredPane.add(result);
		result.setLayout(null);
		
		lblResultat = new JLabel("Resultat");
		lblResultat.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResultat.setBounds(402, 11, 95, 61);
		result.add(lblResultat);
		
		ValidateResult = new JButton("suivant");
		ValidateResult.setBounds(780, 360, 89, 23);
		result.add(ValidateResult);
		
		affichageResult = new JLabel("New label");
		affichageResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		affichageResult.setBounds(57, 86, 780, 235);
		result.add(affichageResult);
	}
	
	public void switchLayer(JPanel p) {
		LayeredPane.removeAll();
		LayeredPane.add(p);
		LayeredPane.repaint();
		LayeredPane.revalidate();
	}
}
