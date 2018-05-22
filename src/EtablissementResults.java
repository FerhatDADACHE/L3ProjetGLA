import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class EtablissementResults extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel etPanel = new JPanel();
	private Font font = new Font("Verdana", Font.BOLD, 15);
	private Color color = new Color(51, 0, 26);

	private JLabel nameEtablissement = new JLabel(Geolocalisation.etablissementInfo[0] + " :");
	private JLabel addrEtablissement = new JLabel(Geolocalisation.etablissementInfo[1]);
	private JLabel lienEtablissement = new JLabel(Geolocalisation.etablissementInfo[2]);

	// Boutons pour l'URL Google Maps, accepter ou refuser un bar
	private JButton url = new JButton("Plus d'informations ");
	private JButton ok = new JButton("Terminez");
	private JButton no = new JButton("Un autre établissement");

	public EtablissementResults() {
		// On nomme la fenetre
		this.setTitle("Votre activite");
		// On definit sa taille en largeur et en hauteur
		this.setSize(300, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Background et Layout
		etPanel.setBackground(color);
		etPanel.setLayout(new BoxLayout(etPanel, BoxLayout.PAGE_AXIS));

		// Ecrire les informations de la activite
		JPanel panelEtab = new JPanel();
		panelEtab.setBackground(color);
		panelEtab.add(nameEtablissement);
		nameEtablissement.setFont(font);
		addrEtablissement.setForeground(Color.WHITE);
		nameEtablissement.setForeground(Color.WHITE);
		panelEtab.add(addrEtablissement);
		addrEtablissement.setFont(font);

		ImageIcon imageEtablissement = new ImageIcon("Image/Etablissement_Image.jpg");
		Image img = imageEtablissement.getImage();
		Image newImg = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon immg = new ImageIcon(newImg); // transform it back

		panelEtab.add(new JLabel(immg));
		etPanel.add(panelEtab);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);
		panelURL.add(url);
		url.setForeground(new Color(69, 0, 6));
		url.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		url.addActionListener(new BoutonListener5());
		etPanel.add(panelURL);

		// Boutons de validations
		JPanel panelButtonBoite = new JPanel();
		panelButtonBoite.setBackground(color);
		
		ok.setForeground(new Color(69, 0, 6));
		ok.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		ok.addActionListener(new BoutonListener5());
		panelButtonBoite.add(ok);
		
		JPanel panelNo = new JPanel();
		panelNo.setBackground(color);
		
		no.setForeground(new Color(69, 0, 6));
		no.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		no.addActionListener(new BoutonListener5());
		panelNo.add(no);
		
		etPanel.add(panelNo);
		etPanel.add(panelButtonBoite);

		
		this.setContentPane(etPanel);
		this.setVisible(true);
	}

	public class BoutonListener5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			//  on accepte pas la activite propos�e
			if (e.getSource() == no) {
				try {
					Geolocalisation.recherchetablissement(Geolocalisation.centerLat, Geolocalisation.centerLng);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				etPanel.setVisible(false);
				// Rappel de l'interface pour la activite
				new EtablissementResults();
			}

			if (e.getSource() == url) {
				String plainText = lienEtablissement.getText();
				try {
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}

			if (e.getSource() == ok) {
				// On quitte l'application
				System.exit(0);
			}

		}
	}

}
