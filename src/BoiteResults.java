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


public class BoiteResults extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Initialisation des caract�ristiques de la fen�tre
	private JPanel container = new JPanel();
	private Font font = new Font("Verdana", Font.BOLD, 14);
	private Color color = new Color(51, 0, 26);

	// Label pour les infos de la boite
	private JLabel nameBoite = new JLabel(Geolocalisation.clubInfo[0] + " :");
	private JLabel addrBoite = new JLabel(Geolocalisation.clubInfo[1]);
	private JLabel internetBoite = new JLabel(Geolocalisation.clubInfo[2]);

	// Boutons pour l'URL Google Maps, accepter ou refuser un bar
	private JButton url = new JButton("Plus d'informations");
	private JButton okBoite = new JButton("    Terminez   ");
	private JButton nonBoite = new JButton("Un autre night-Club ");

	// Bouton de fin

	public BoiteResults() {
		// On nomme la fenetre
		this.setTitle("Votre boite");
		// On definit sa taille en largeur et en hauteur
		this.setSize(300, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Background et Layout
		container.setBackground(color);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
		nameBoite.setForeground(Color.WHITE);
		addrBoite.setForeground(Color.WHITE);

		// Ecrire les informations de la boite
		JPanel panelBoite = new JPanel();
		panelBoite.setBackground(color);
		panelBoite.add(nameBoite);
		nameBoite.setFont(font);
		panelBoite.add(addrBoite);
		addrBoite.setFont(font);

		ImageIcon imgResto = new ImageIcon("Image/Boite_Image.jpg");
		Image img = imgResto.getImage();
		Image newImg = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageResto = new ImageIcon(newImg); // transform it back

		panelBoite.add(new JLabel(imageResto), BorderLayout.BEFORE_FIRST_LINE);
		container.add(panelBoite);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);

		url.setForeground(new Color(69, 0, 6));
		url.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		panelURL.add(url);
		url.addActionListener(new BoutonListener4());
		container.add(panelURL);

		JPanel panelButtonBoite = new JPanel();
		panelButtonBoite.setBackground(color);

		okBoite.setForeground(new Color(69, 0, 6));
		okBoite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		okBoite.addActionListener(new BoutonListener4());
		panelButtonBoite.add(okBoite);

		nonBoite.setForeground(new Color(69, 0, 6));
		nonBoite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		JPanel panelNonBoite = new JPanel();
		panelNonBoite.setBackground(color);
		nonBoite.setForeground(new Color(69, 0, 6));
		nonBoite.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		panelNonBoite.add(nonBoite);
		
		nonBoite.addActionListener(new BoutonListener4());
		container.add(panelButtonBoite);

		container.add(panelNonBoite);

		this.setContentPane(container);
		this.setVisible(true);
	}

	public class BoutonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Quand on accepte pas la boite propos�e
			if (e.getSource() == nonBoite) {
				try {
					Geolocalisation.rechercheBoite(Geolocalisation.coordBar[0], Geolocalisation.coordBar[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				container.setVisible(false);
				 new BoiteResults();

			}

			// Quand on clic sur "Lien Google Maps"
			if (e.getSource() == url) {
				String plainText = internetBoite.getText();
				try {
					// Ouverture de la page Google Maps sur internet
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (e.getSource() == okBoite) {
				// On quitte l'application
				System.exit(0);
			}

		}
	}

}
