import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Profil {

	public ArrayList<String> adresses = new ArrayList<String>();
	public String choix;
	public String transport;
	public String activite;
	public String hDebut;
	public SimpleDateFormat date;

	double perimetre = 3000; // en m

	public Profil() {
	}

	public void ajoutDateEtHoraire(SimpleDateFormat d, String h) {
		this.date = d;
		this.hDebut = h;
	}

	public void ajoutAdresse(String a) {
		this.adresses.add(a);
	}

	public void supprimerAdresse(String a) {
		this.adresses.remove(a);
	}

	public void ajoutTransport(String t) {
		this.transport = t;
	}

	public void ajoutPreference(String p) {
		this.choix = p;
	}

	public void ajoutActivite(String a) {
		this.activite = a;
	}

	// Méthode qui définit un périmètre
	public double definitionPerimetreMax(String t) {
		if (t == "Marche") {
			this.perimetre = 1800;
		} else if (t == "Vélo") {
			this.perimetre = 4500;
		} else if (t == "Voiture") {
			this.perimetre = 5000;
		} else if (t == "Transports en commun") {
			this.perimetre = 5000;
		}
		return this.perimetre;
	}

	// Affiche date et horaire
	public void afficheDateEtHoraire() {
		System.out.println("Date : " + this.date.format(new Date()));
		System.out.println("Horaire début : " + this.hDebut + " heures");
		System.out.println("\n");
	}

	// Affiche transport
	public void afficheTransport() {
		System.out.println("Transport : " + this.transport);
	}

	// Affiche préférences
	public void affichePreference() {
		System.out.println("Préférence alimentaire : " + this.choix);
	}

	// Affiche adresses
	public void afficheAdresses(ArrayList<String> a) {
		System.out.println("Adresses :");
		for (String line : a) {
			System.out.println(line);
		}
		System.out.println("\n");
	}

	public void afficheActivite() {
		System.out.println("Activité selectionné : " + this.activite);
	}
}