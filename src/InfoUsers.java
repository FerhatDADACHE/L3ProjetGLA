import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.print.Pageable;
import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class InfoUsers extends JFrame {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	JPanel infoUsersPanel = new JPanel();

	String[] tabJour = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
			"18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	// String[] tabMois = new String [13];
	// String[] tabMois = { "Javier", "Fevrier", "Marse", "Avril", "Mai", "Juin",
	// "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Decembre" };
	String[] tabMois = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
	String[] tabHoraire = { "11:00", "12h:00 ", " 13h:00", "14h:00", "15:00", "16:00", "17h:00", "18:00", "19:00",
			"20:00", "21:00", "22:00", "23:00", "00:00", "1:00", "2:00" };
	String[] tabTransport = { "Voiture", "Transports", "Vélo", "Marche" };
	String[] tabTypeResto = { "Vietnamien", "Macdo", "Français", "Africain", "Italien", "Japonais", "Chinois", "Hallal",
			"libanais", "Indien", "Marocain", "Kabyle", "V�g�tarien" };
	String[] tabActivites = { "Musée", "Theatre", "Hotel", "karaoke", "Spectacle", "Parc", "Cinéma" };

	private ButtonGroup GroupePreference = new ButtonGroup();

	// private String[] jour() {
	// for (int i = 1; i < 32; i++) {
	// tabJour[i] = i+"";
	// }
	// return tabJour;
	// }
	// public String[] mois() {
	// for (int i = 1; i < 13; i++) {
	// tabMois[i] = i+"";
	// }
	// return tabMois;
	// }
	// public String[] horaire() {
	// for (int i = 18; i < 23; i++) {
	// tabHoraire[i]= i+ ":00";
	// }
	// return tabHoraire;
	// }

	private JComboBox comboResto = new JComboBox(tabTypeResto);
	private JComboBox comboJour = new JComboBox(tabJour);
	private JComboBox comboMois = new JComboBox(tabMois);
	private JComboBox comboHoraire = new JComboBox(tabHoraire);
	private JComboBox comboTransport = new JComboBox(tabTransport);
	private JComboBox<String> comboActivite = new JComboBox<>(tabActivites);

	private JButton bouton = new JButton("Organiza mi salida");
	private JButton boutonEttablissement = new JButton(" vâng ");

	private JTextField addr1 = new JTextField("");
	private JTextField addr2 = new JTextField("");
	private JTextField addr3 = new JTextField("");
	private JTextField addr4 = new JTextField("");
	private JTextField addr5 = new JTextField("");

	private JLabel jour = new JLabel("Jour");
	private JLabel mois = new JLabel("Mois");
	private JLabel horaire = new JLabel("Horaires");
	private JLabel heures = new JLabel("Heures");
	// private JLabel transport = new JLabel("Moyen de d�placement");
	// private JLabel restos = new JLabel("Type de restaurant :");

	private JTextField jourMois = new JTextField("J/M/ANNE");
	private JButton JM = new JButton("date");

	private JButton ok1 = new JButton("ok");
	private JButton ok2 = new JButton("ok");
	private JButton ok3 = new JButton("ok");
	private JButton ok4 = new JButton("ok");
	private JButton ok5 = new JButton("ok");

	private Color color = new Color(51, 0, 26);

	public InfoUsers() throws IOException {

		// On nomme la fenetre
		this.setTitle("Salsalida");
		// On definit sa taille en largeur et en hauteur
		this.setSize(350, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("Image/logo-salsalida-sans-nom.png").getImage());
		// JPanel pane = new JPanel();
		// JLabel image = new JLabel( new ImageIcon( "logo-salsalida-sans-nom.jpg"));
		// pane.setLayout(new BorderLayout());
		// pane.add(image , BorderLayout.NORTH);
		// container.add(pane);
		// Initialisation des comboBox
		infoUsersPanel.setBackground(color);
		infoUsersPanel.setLayout(new BoxLayout(infoUsersPanel, BoxLayout.Y_AXIS));

		comboJour.setPreferredSize(new Dimension(50, 20));
		comboMois.setPreferredSize(new Dimension(50, 20));
		comboHoraire.setPreferredSize(new Dimension(50, 20));
		comboTransport.setPreferredSize(new Dimension(120, 20));
		comboResto.setPreferredSize(new Dimension(120, 20));
		comboActivite.setPreferredSize(new Dimension(120, 20));

		JPanel preferencesPanel = new JPanel();
		JPanel restoPanel = new JPanel();
		restoPanel.setBackground(color);

		restoPanel.setBorder(BorderFactory.createLineBorder(color));
		TitledBorder titreResto = new TitledBorder(BorderFactory.createLineBorder(color),
				"Choisissez votre restaurant");
		titreResto.setTitleColor(Color.WHITE);
		restoPanel.setBorder(titreResto);

		preferencesPanel.setBackground(color);

		preferencesPanel.setLayout(new BoxLayout(preferencesPanel, BoxLayout.Y_AXIS));
		JLabel l1 = new JLabel("          ");
		JLabel l2 = new JLabel("                                                            ");

		restoPanel.add(l1);
		restoPanel.add(comboResto);
		restoPanel.add(l2);

		comboResto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Geolocalisation.pUser.ajoutPreference((String) comboResto.getSelectedItem());
				Geolocalisation.pUser.affichePreference();

			}
		});
		infoUsersPanel.add(preferencesPanel);

		comboResto.setPreferredSize(new Dimension(100, 20));

		// TextBox pour les adresses
		JPanel adressesPanel = new JPanel();
		adressesPanel.setBackground(color);
		Font police = new Font("Arial", Font.BOLD, 12);
		addr1.setFont(police);
		addr1.setPreferredSize(new Dimension(200, 25));
		addr1.setForeground(Color.BLACK);
		addr2.setFont(police);
		addr2.setPreferredSize(new Dimension(200, 25));
		addr2.setForeground(Color.BLACK);
		addr3.setFont(police);
		addr3.setPreferredSize(new Dimension(200, 25));
		addr3.setForeground(Color.BLACK);
		addr4.setFont(police);
		addr4.setPreferredSize(new Dimension(200, 25));
		addr4.setForeground(Color.BLACK);
		addr5.setFont(police);
		addr5.setPreferredSize(new Dimension(200, 25));
		addr5.setForeground(Color.BLACK);

		heures.setForeground(Color.WHITE);
		jour.setForeground(Color.WHITE);
		mois.setForeground(Color.WHITE);
		horaire.setForeground(Color.WHITE);
		// transport.setForeground(Color.WHITE);
		// restos.setForeground(Color.WHITE);

		// adressesPanel.add(adresses);
		// ok1.addActionListener(new BoutonListener());
		ok2.addActionListener(new BoutonListener());
		ok3.addActionListener(new BoutonListener());
		ok4.addActionListener(new BoutonListener());
		ok5.addActionListener(new BoutonListener());
		// sup1.addActionListener(new BoutonListener());
		// sup2.addActionListener(new BoutonListener());
		// sup3.addActionListener(new BoutonListener());
		// sup4.addActionListener(new BoutonListener());
		// sup5.addActionListener(new BoutonListener());
		// adressesPanel.add(sup1);
		adressesPanel.add(addr2);
		adressesPanel.add(ok2);
		// adressesPanel.add(sup2);
		adressesPanel.add(addr3);
		adressesPanel.add(ok3);
		// adressesPanel.add(sup3);
		adressesPanel.add(addr4);
		adressesPanel.add(ok4);
		// adressesPanel.add(sup4);
		adressesPanel.add(addr5);
		adressesPanel.add(ok5);
		// adressesPanel.add(sup5);
		//
		// adressesPanel.setBorder(new TitledBorder(null, "adresses"));
		adressesPanel.setBorder(BorderFactory.createLineBorder(color));
		TitledBorder titreadress = new TitledBorder(BorderFactory.createLineBorder(color), "Adresses");
		titreadress.setTitleColor(Color.WHITE);
		adressesPanel.setBorder(titreadress);

		adressesPanel.revalidate();
		adressesPanel.repaint();
		preferencesPanel.add(adressesPanel);

		// preferencesPanel.add(restoPanel , BorderLayout.SOUTH);
		infoUsersPanel.add(preferencesPanel);

		//

		// JPanel panelResto = new JPanel();
		// panelResto.add(comboResto);
		// comboResto.addItemListener(new ItemState());
		// container.add(panelResto);

		this.setContentPane(infoUsersPanel);
		this.setVisible(true);

		/***** DATE ET HEURE ****/
		JPanel dateEtHeurePanel = new JPanel();

		dateEtHeurePanel.setBackground(color);
		dateEtHeurePanel.add(jour);
		dateEtHeurePanel.add(comboJour);
		dateEtHeurePanel.add(mois);
		dateEtHeurePanel.add(comboMois);
		JPanel HeurPanel = new JPanel();
		HeurPanel.add(heures);
		HeurPanel.add(comboHoraire);
		HeurPanel.setBackground(color);
		dateEtHeurePanel.add(HeurPanel);
		TitledBorder titreDateEtHeure = new TitledBorder(BorderFactory.createLineBorder(color), "Horaire de la soiree");
		titreDateEtHeure.setBorder(BorderFactory.createLineBorder(color));
		titreDateEtHeure.setTitleColor(Color.WHITE);
		titreDateEtHeure.setTitleJustification(TitledBorder.LEFT);
		titreDateEtHeure.setTitlePosition(TitledBorder.TOP);
		dateEtHeurePanel.setBorder(titreDateEtHeure);

		infoUsersPanel.add(dateEtHeurePanel);

		// Ajout du listener
		comboJour.addItemListener(new ItemState());
		comboMois.addItemListener(new ItemState());
		comboHoraire.addItemListener(new ItemState());
		comboActivite.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				String activite = (String) comboActivite.getSelectedItem();
				Geolocalisation.pUser.ajoutActivite(activite);
				Geolocalisation.pUser.afficheActivite();
			}
		});

		// ***TRANSPORT************//
		JPanel PanelBas = new JPanel();
		JPanel transportPanel = new JPanel();
		PanelBas.setBackground(color);
		transportPanel.setBackground(color);

		PanelBas.setLayout(new BoxLayout(PanelBas, BoxLayout.PAGE_AXIS));
		// transportPanel.add(transport);
		transportPanel.add(comboTransport);
		transportPanel.setSize(100, 50);
		TitledBorder titreTransport = new TitledBorder(BorderFactory.createLineBorder(color), "Moyen de transport");
		titreTransport.setBorder(BorderFactory.createLineBorder(color));
		titreTransport.setTitleColor(Color.WHITE);
		titreTransport.setTitleJustification(TitledBorder.LEFT);
		transportPanel.setBorder(titreTransport);
		PanelBas.add(transportPanel);

		// PanelBas.setSize(new Dimension(100,50));

		// container.add(PanelBas , BorderLayout.CENTER);
		// Ajout du listener
		comboTransport.addItemListener(new ItemState());

		/**** VALIDATION *********/
		// JPanel validerPanel = new JPanel();
		// validerPanel.setBackground(color);
		JLabel l = new JLabel("                                          ");
		restoPanel.add(l);
		restoPanel.add(bouton);
		bouton.setForeground(new Color(69, 0, 6));
		bouton.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		;
		bouton.addActionListener(new BoutonListener());
		// validerPanel.add(bouton);
		PanelBas.add(restoPanel);
		JPanel activitePanel = new JPanel();

		activitePanel.setBackground(color);
		activitePanel.add(new JLabel("       "));
		activitePanel.add(comboActivite);
		activitePanel.setBorder(BorderFactory.createLineBorder(Color.darkGray));
		TitledBorder autreActivite = new TitledBorder(BorderFactory.createLineBorder(color), "Une autre activité?");
		autreActivite.setTitleColor(Color.WHITE);
		activitePanel.setBorder(autreActivite);

		activitePanel.setSize(100, 100);
		activitePanel.add(new JLabel("                                                                              "));
		boutonEttablissement.setForeground(new Color(69, 0, 6));
		boutonEttablissement.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		;
		boutonEttablissement.addActionListener(new BoutonListener());

		activitePanel.add(boutonEttablissement);
		// PanelBas.add(validerPanel);
		PanelBas.add(activitePanel);

		infoUsersPanel.add(PanelBas);

		this.setContentPane(infoUsersPanel);
		this.setVisible(true);

	}

	class ItemState implements ItemListener {
		public void itemStateChanged(ItemEvent e) {

			// Ajouter la date et l'horaire de d�but
			if (e.getSource() == comboJour || e.getSource() == comboMois || e.getSource() == comboHoraire) {
				String jour = (String) comboJour.getSelectedItem();
				String mois = (String) comboMois.getSelectedItem();
				String dateStr = jour + "/" + mois + "/2018";
				SimpleDateFormat date = new SimpleDateFormat(dateStr);
				String horaire = (String) comboHoraire.getSelectedItem();
				Geolocalisation.pUser.ajoutDateEtHoraire(date, horaire);
				Geolocalisation.pUser.afficheDateEtHoraire();
			}

			// Ajouter le transport
			else {
				String transport = (String) comboTransport.getSelectedItem();
				Geolocalisation.pUser.ajoutTransport(transport);
				Geolocalisation.pUser.afficheTransport();
				Geolocalisation.pUser.definitionPerimetreMax(transport);
				System.out.println(Geolocalisation.pUser.perimetre);
			}
		}
	}

	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Ajout des adresses
			// if (e.getSource() == ok1) {
			// GestionDonnees.u.ajoutAdresse(addr1.getText());
			// System.out.println("bouton 1");
			// GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			// }
			if (e.getSource() == ok2) {
				Geolocalisation.pUser.ajoutAdresse(addr2.getText());
				System.out.println("bouton 2");
				Geolocalisation.pUser.afficheAdresses(Geolocalisation.pUser.adresses);
			}
			if (e.getSource() == ok3) {
				Geolocalisation.pUser.ajoutAdresse(addr3.getText());
				System.out.println("bouton3");
				Geolocalisation.pUser.afficheAdresses(Geolocalisation.pUser.adresses);
			}
			if (e.getSource() == ok4) {
				Geolocalisation.pUser.ajoutAdresse(addr4.getText());
				System.out.println("bouton4");
				Geolocalisation.pUser.afficheAdresses(Geolocalisation.pUser.adresses);
			}
			// if (e.getSource() == ok5) {
			// GestionDonnees.u.ajoutAdresse(addr5.getText());
			// GestionDonnees.u.afficheAdresses(GestionDonnees.u.adresses);
			// }

			// bouton de validation
			if (e.getSource() == bouton) {

				infoUsersPanel.setEnabled(false);
				infoUsersPanel.setVisible(false);

				// Calcule le barycentre des adresses
				Geolocalisation.getBaryCentre();

				try {
					Geolocalisation.rechercheBar(Geolocalisation.centerLat, Geolocalisation.centerLng);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				BarResults rep = new BarResults();
			}

			if (e.getSource() == boutonEttablissement) {

				infoUsersPanel.setEnabled(false);
				infoUsersPanel.setVisible(false);

				// Calcule le barycentre des adresses
				Geolocalisation.getBaryCentre();

				try {
					Geolocalisation.recherchetablissement(Geolocalisation.centerLat, Geolocalisation.centerLng);
					System.out.println(Geolocalisation.etablissementInfo[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				EtablissementResults rep = new EtablissementResults();
			}

		}

	}

}

// 8 rue Eric Tabarly 91300 Massy
// 75 rue Vincent Fayo 92290
// 5 rue Rivolie Paris
//
// Rue Julian Grimau, Vitry-sur-Seine
// 75 rue des ursulines, saint denis
// 8 Place Robert Belvaux, Le Perreux-sur-Marne

//
