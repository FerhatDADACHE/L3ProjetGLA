import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import org.json.*;

public class Geolocalisation {
	public static Profil pUser = new Profil();
	public static String[] barInfo = new String[3];
	public static String[] restoInfo = new String[3];
	public static String[] clubInfo = new String[3];
	public static String[] etablissementInfo = new String[3];

	public static double[] coordLat = new double[5];
	public static double[] coordLng = new double[5];
	public static double[] coordBar = new double[2];
	public static double[] coordResto = new double[2];
	public static double centerLat = 48.85340329999999;
	public static double centerLng = 2.3487835999999334;
	JSONObject bars;
	JSONObject restos;
	JSONObject boites;
	JSONObject etablissement;
	static int cmptr = 0;
	private static String GooglePlacesKey = "AIzaSyDmP5wsuUMRGxcbZwLwq8uyTj7BwTcEauE";

	public Geolocalisation() {
	}

	public static String parseAddr(String adresses) {
		return adresses.replace(' ', '+');
	}

	public static void getCoord(String addresse, int numAddresse) throws Exception {
		String lien = "http://maps.google.com/maps/api/geocode/json?" + "sensor=false&address=";
		String parseAdresse = parseAddr(addresse);
		lien += URLEncoder.encode(parseAdresse, "UTF-8");
		URL url = new URL(lien);
	
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		// fabrique l'objet JSON, 
		JSONObject obj = new JSONObject(str);
		if (!obj.getString("status").equals("OK")) {
			return;
		}
		JSONObject res = obj.getJSONArray("results").getJSONObject(0);
		JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");

		coordLat[numAddresse - 1] = loc.getDouble("lat");
		coordLng[numAddresse - 1] = loc.getDouble("lng");

	}

	public static void getCenterCoord() {
		double sommeLat = 0;
		double sommeLng = 0;
		double countLat = 0;
		double countLng = 0;

		for (int i = 0; i < 5; i++) {
			if (coordLat[i] != 0 || coordLng[i] != 0) {
				sommeLat += coordLat[i];
				countLat++;
				sommeLng += coordLng[i];
				countLng++;
			}
		}
		if (countLat != 0 && countLng != 0) {
			centerLat = sommeLat / countLat;
			centerLng = sommeLng / countLng;
		}

	}

	// retourne le barycentre de plusieurs adresses
	public static void getBaryCentre() {
		int numAddr = 1;
		for (String addr : pUser.adresses) {
			try {
				Geolocalisation.getCoord(addr, numAddr);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			numAddr++;
		}
//		for (int i = 0; i < Geolocalisation.coordLat.length; i++) {
//			System.out.println("Lat : " + coordLat[i]);
//			System.out.println("Lng : " + coordLng[i]);
//		}
		getCenterCoord();
	}

	// trouve le bar le plus proche des coordonn�es fournies en param�tre
	public static void rechercheBar(double Lat, double Lng) throws Exception {
		
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + pUser.perimetre + "&rankbydistance&type=point_of_interest&keyword=bar&key="
				+ GooglePlacesKey;
		URL url = new URL(s);
		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		//Objet JSON avec les InfoBar 
		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			barInfo[0] = "Pas de bar";
			barInfo[1] = "  ";
			barInfo[2] = "  ";
			coordBar[0] = centerLat;
			coordBar[1] = centerLng;
			return;
		} else if (!obj.getString("status").equals("OK")) {
			barInfo[0] = "Erreur";
			barInfo[1] = "  ";
			barInfo[2] = "  ";
			coordBar[0] = centerLat;
			coordBar[1] = centerLng;
			return;
		}
		
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(cmptr % obj.length());
		barInfo[0] = lieu.getString("name");
		System.out.println(barInfo[0]);
		barInfo[1] = lieu.getString("vicinity");
		System.out.println(barInfo[1]);
		coordBar[0] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		coordBar[1] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		barInfo[2] = "https://www.google.com/maps/?q=" + barInfo[0];
		cmptr++;
	}

	public static void rechercheResto(double Lat, double Lng) throws Exception {
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + pUser.perimetre + "&rankbydistance&type=restaurant&name=" + pUser.choix + "&key=" + GooglePlacesKey;
		URL url = new URL(s);
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			restoInfo[0] = "Pas de restaurant";
			restoInfo[1] = "  ";
			restoInfo[2] = "  ";
			coordResto[0] = coordBar[0];
			coordResto[1] = coordBar[1];
			return;
		} else if (!obj.getString("status").equals("OK")) {
			restoInfo[0] = "Erreur";
			restoInfo[1] = "  ";
			restoInfo[2] = "  ";
			coordResto[0] = coordBar[0];
			coordResto[1] = coordBar[1];
			return;
		}
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(cmptr % obj.length());
		restoInfo[0] = lieu.getString("name");
		restoInfo[1] = lieu.getString("vicinity");
		restoInfo[2] = "https://www.google.com/maps/?q=" + restoInfo[0];
		System.out.println(restoInfo[0]);
		coordResto[0] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lat");
		System.out.println(restoInfo[1]);
		coordResto[1] = lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lng");
		cmptr++;
	}

	// cherche la boite de nuit le plus proche
	public static void rechercheBoite(double Lat, double Lng) throws Exception {
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + pUser.perimetre + "&language=fr&keyword=night_club&key=" + GooglePlacesKey;
		
		URL url = new URL(s);
		// lit l'URL
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();

		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			clubInfo[0] = "Pas de boite de nuit";
			clubInfo[1] = "  ";
			clubInfo[2] = "  ";
			return;
		} else if (!obj.getString("status").equals("OK")) {
			clubInfo[0] = "Erreur";
			clubInfo[1] = "  ";
			clubInfo[2] = "  ";
			return;
		}
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(cmptr % obj.length());
		clubInfo[0] = lieu.getString("name");
		System.out.println(clubInfo[0]);
		clubInfo[1] = lieu.getString("vicinity");
		System.out.println(clubInfo[1]);
		clubInfo[2] = "https://www.google.com/maps/?q=" + clubInfo[0];
		cmptr++;
	}

	public static void recherchetablissement(double Lat, double Lng) throws Exception {
		String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + Lat + "," + Lng
				+ "+&radius=" + pUser.perimetre + "&type=point_of_interest&keyword=" + pUser.activite + "&key="
				+ GooglePlacesKey;
		URL url = new URL(s);
		
		Scanner scan = new Scanner(url.openStream());
		String str = new String();
		while (scan.hasNext())
			str += scan.nextLine();
		scan.close();
	//Objet JSON avec les etablissements 
		JSONObject obj = new JSONObject(str);
		if (obj.getString("status").equals("ZERO_RESULTS")) {
			etablissementInfo[0] = "Aucun etablissement";
			etablissementInfo[1] = "  ";
			etablissementInfo[2] = "  ";
			return;
		} else if (!obj.getString("status").equals("OK")) {
			etablissementInfo[0] = "Erreur";
			etablissementInfo[1] = "  ";
			etablissementInfo[2] = "  ";
			return;
		}
		// donne au tableau tripletBoite[] le nom de la boite, son adresse courte et
		// le lien google maps
		JSONObject lieu = (obj.getJSONArray("results")).getJSONObject(cmptr % obj.length());
		etablissementInfo[0] = lieu.getString("name");
		String tripletNomEtablissement = parseAddr(etablissementInfo[0]);

		System.out.println(etablissementInfo[0]);
		etablissementInfo[1] = lieu.getString("vicinity");
//		System.out.println(tripletEtablissement[1]);
		etablissementInfo[2] = "https://www.google.com/maps/?q=" + tripletNomEtablissement;
		cmptr++;
	}

	public static void main(String[] args) throws Exception {
		InfoUsers fenetre = new InfoUsers();

		// Proxy du PUIO
		System.setProperty("https.proxyHost", "cache.u-psud.fr");
		System.setProperty("https.proxyPort", "8080");

	}

}

// 5 rue des martyrs, 75009
// 13 rue du commandant ren� mouchotte, 75014
