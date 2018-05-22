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

public class BarResults extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel container = new JPanel();
	private Font font = new Font("Tiffany", Font.BOLD, 14);
	private Color color = new Color(51, 0, 26);

	// Label pour les infos du bar
	private JLabel nameBar = new JLabel(Geolocalisation.barInfo[0] + " :");
	private JLabel addrBar = new JLabel(Geolocalisation.barInfo[1]);
	private JLabel internetBar = new JLabel(Geolocalisation.barInfo[2]);

	// Boutons pour l'URL Google Maps, accepter ou refuser un bar
	private JButton url = new JButton("Plus d'information");
	private JButton okBar = new JButton("Un resto?");
	private JButton nonBar = new JButton("Un autre bar ?");

	public BarResults() {
		// On nomme la fenetre
		this.setTitle("Votre bar");
		// On definit sa taille en largeur et en hauteur
		this.setSize(300, 600);
		// On place la fenetre au milieu
		this.setLocationRelativeTo(null);
		// On termine le processus lorsqu'on clique sur la croix rouge
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Background et Layout
		container.setBackground(color);
		container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

		// Ecrire les informations du bar
		JPanel panelBar = new JPanel();
		panelBar.setBackground(color);
		panelBar.add(nameBar);
		nameBar.setFont(font);
		nameBar.setForeground(Color.WHITE);
		panelBar.add(addrBar);
		addrBar.setFont(font);
		addrBar.setForeground(Color.WHITE);
		ImageIcon imgBar = new ImageIcon("Image/bar_image.jpg");
		Image img = imgBar.getImage();
		Image newImg = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageBar = new ImageIcon(newImg); // transform it back

		panelBar.add(new JLabel(imageBar), BorderLayout.BEFORE_FIRST_LINE);

		container.add(panelBar);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);
		panelURL.add(url);
		url.setForeground(new Color(69, 0, 6));
		url.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		;
		url.addActionListener(new BoutonListener2());
		container.add(panelURL);

		// Boutons de validations
		JPanel panelButtonBar = new JPanel();
		panelButtonBar.setBackground(color);
		okBar.addActionListener(new BoutonListener2());
		okBar.setForeground(new Color(69, 0, 6));
		okBar.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		panelButtonBar.add(okBar);
		JPanel panelNonBar = new JPanel();
		panelNonBar.setBackground(color);
		nonBar.addActionListener(new BoutonListener2());
		nonBar.setForeground(new Color(69, 0, 6));
		nonBar.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		panelNonBar.add(nonBar);
		container.add(panelButtonBar);
		container.add(panelNonBar);

		this.setContentPane(container);
		this.setVisible(true);
	}

	public class BoutonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// Quand on accepte pas le resto propos�
			if (e.getSource() == nonBar) {
				try {
					// Rappel de la m�thode recherche de bar
					Geolocalisation.rechercheBar(Geolocalisation.centerLat, Geolocalisation.centerLng);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				// Rappel de l'interface pour le bar
				BarResults newF = new BarResults();
			}

			// Quand on accepte le resto propos�
			if (e.getSource() == okBar) {
				// On remet le compteur de recherche � 0
				Geolocalisation.cmptr = 0;
				try {
					// Appel de la m�thode recherche de resto
					Geolocalisation.rechercheResto(Geolocalisation.coordBar[0], Geolocalisation.coordBar[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				container.setVisible(false);
				// Appel de l'interface pour le resto
				RestoResults newF = new RestoResults();
			}

			// Quand on clic sur "Lien Google Maps"
			if (e.getSource() == url) {
				String plainText = internetBar.getText();
				try {
					// Ouverture de la page Google Maps sur internet
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

}
// import java.awt.BorderLayout;
