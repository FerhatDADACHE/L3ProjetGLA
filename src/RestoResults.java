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


public class RestoResults extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel restoPanel = new JPanel();
	private Font font = new Font("Verdana", Font.BOLD, 14);
	private Color color = new Color(51, 0, 26);

	private JLabel nomResto = new JLabel(Geolocalisation.restoInfo[0] + " :");
	private JLabel addresseResto = new JLabel(Geolocalisation.restoInfo[1]);
	private JLabel lienResto = new JLabel(Geolocalisation.restoInfo[2]);

	private JButton url = new JButton("Plus d'informations");
	private JButton okResto = new JButton("Un night Club?");
	private JButton nonResto = new JButton("Un autre resto? ");

	public RestoResults() {
		this.setTitle("Votre restaurant");
		this.setSize(300, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		restoPanel.setBackground(color);
		restoPanel.setLayout(new BoxLayout(restoPanel, BoxLayout.PAGE_AXIS));

		JPanel panelResto = new JPanel();
		panelResto.setBackground(color);
		nomResto.setForeground(Color.WHITE);
		addresseResto.setForeground(Color.white);
		panelResto.add(nomResto);
		nomResto.setFont(font);
		panelResto.add(addresseResto);
		addresseResto.setFont(font);

		ImageIcon imgResto = new ImageIcon("Image/resto_image.jpg");
		Image img = imgResto.getImage();
		Image newImg = img.getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageResto = new ImageIcon(newImg); // transform it back

		panelResto.add(new JLabel(imageResto), BorderLayout.BEFORE_FIRST_LINE);

		restoPanel.add(panelResto);

		// Bouton URL
		JPanel panelURL = new JPanel();
		panelURL.setBackground(color);

		url.setForeground(new Color(69, 0, 6));
		url.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		;

		panelURL.add(url);
		url.addActionListener(new BoutonListener3());
		restoPanel.add(panelURL);

		// Boutons de validations
		JPanel panelButtonResto = new JPanel();
		panelButtonResto.setBackground(color);

		okResto.setForeground(new Color(69, 0, 6));
		okResto.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		okResto.addActionListener(new BoutonListener3());
		panelButtonResto.add(okResto);

		JPanel panelNonR = new JPanel();
		panelNonR.setBackground(color);
		
		nonResto.setForeground(new Color(69, 0, 6));
		nonResto.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));

		nonResto.addActionListener(new BoutonListener3());
		
		nonResto.setForeground(new Color(69, 0, 6));
		nonResto.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 14));
		
		panelNonR.add(nonResto);	
		
		restoPanel.add(panelButtonResto);

		restoPanel.add(panelNonR);	

		this.setContentPane(restoPanel);
		this.setVisible(true);
	}

	public class BoutonListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == nonResto) {
				try {
					Geolocalisation.rechercheResto(Geolocalisation.coordBar[0], Geolocalisation.coordBar[1]);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				restoPanel.setVisible(false);
				new RestoResults();
			}

			if (e.getSource() == okResto) {
				Geolocalisation.cmptr = 0;
				try {
					Geolocalisation.rechercheBoite(Geolocalisation.coordResto[0], Geolocalisation.coordResto[1]);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				restoPanel.setVisible(false);
				new BoiteResults();
				}

			if (e.getSource() == url) {
				String plainText = lienResto.getText();
				try {
					Desktop.getDesktop().browse(new URI(plainText));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
