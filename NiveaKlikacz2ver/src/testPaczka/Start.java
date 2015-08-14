/**
 * 
 */
package testPaczka;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author Kamil
 *
 */
public class Start {
	//UWAGA!!! w tym momencie mozna nie uzywac "STATIC" - stworzylem nizej obiekt tej klasy
	private NiveaKlikacz2 obiekt;
	private boolean uruchom = true;
	private int petlaWykonanaRazy = 0;
	private final String _SEP = ";";
	//konfiguracja
		private String adresEMAIL;
		private String captchy;
		private int liczba;
		private boolean czyUzycListyAdresow;
		private boolean czyKlikac;
		//slow, normal, fast
		private String predkosc = "normal";
		
		private Point pozycjaGLOSUJ;
		private Point pozycjaAdresEMAIL;
		//private Point pozycjaWYSLIJ;
		private Point pozycjaCAPTCHY;
		//private Point pozycjaOK1;
		private Point pozycjaOK2;
		private Point pozycjaOSWIADCZENIE;
	//konfiguracja
	
	public Start(){
		/*pozycjaGLOSUJ = new Point(898,495);
		pozycjaAdresEMAIL = new Point(513,519);
		pozycjaWYSLIJ = new Point(513,580);
		pozycjaCAPTCHY = new Point(600,590);
		pozycjaOK1 = new Point(795,650);
		pozycjaOK2 = new Point(790,585);
		pozycjaOSWIADCZENIE = new Point(788,433);
		
		adresEMAIL = "k1las@dlalosic.pl";
		captchy = "mmwks2u";
		liczba = 6364;*/
		czyUzycListyAdresow = false;
		czyKlikac = true;
		
		pozycjaGLOSUJ = null;
		pozycjaAdresEMAIL = null;
		//pozycjaWYSLIJ = null;
		pozycjaCAPTCHY = null;
		//pozycjaOK1 = null;
		pozycjaOK2 = null;
		pozycjaOSWIADCZENIE = null;
		
		adresEMAIL = null;
		captchy = null;
		liczba = 0;
	}
	
	private void wczytajParametry() throws FileNotFoundException{
		File plik = new File("ustawienia.txt");
		Scanner skaner = new Scanner(plik);
		
		while(skaner.hasNextLine()){
			String temp = skaner.nextLine();
			//System.out.println(temp);
			
			String[] parsowanieLinii = temp.split(_SEP);
			
			switch(parsowanieLinii[0]){
				case "adresEMAIL":
					adresEMAIL = parsowanieLinii[1];
					break;
				case "captchy":
					captchy = parsowanieLinii[1];
					break;
				case "liczba":
					liczba = Integer.parseInt(parsowanieLinii[1]);
					break;
				case "czyKlikac":
					czyKlikac = Boolean.parseBoolean(parsowanieLinii[1]);
					break;
				case "czyUzycListyAdresow":
					czyUzycListyAdresow = Boolean.parseBoolean(parsowanieLinii[1]);
					break;
				case "predkosc":
					predkosc = parsowanieLinii[1];
					break;
				case "pozycjaGLOSUJ":
					pozycjaGLOSUJ = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					break;
				case "pozycjaAdresEMAIL":
					pozycjaAdresEMAIL = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					break;
				//case "pozycjaWYSLIJ":
					//pozycjaWYSLIJ = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					//break;
				case "pozycjaCAPTCHY":
					pozycjaCAPTCHY = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					break;
				//case "pozycjaOK1":
					//pozycjaOK1 = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					//break;
				case "pozycjaOK2":
					pozycjaOK2 = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					break;
				case "pozycjaOSWIADCZENIE":
					pozycjaOSWIADCZENIE = new Point(Integer.parseInt(parsowanieLinii[1]),Integer.parseInt(parsowanieLinii[2]));
					break;
			}
		}
		
		skaner.close();
	}
	
	private void zapiszParametry() throws FileNotFoundException{
		PrintWriter zapis = new PrintWriter("ustawienia.txt");
		zapis.println("adresEMAIL" + _SEP + adresEMAIL);
		//zapis.println("captchy" + _SEP + captchy);
		zapis.println("captchy" + _SEP + NiveaKlikacz2.captchy);
		zapis.println("liczba" + _SEP + liczba);
		zapis.println("czyKlikac" + _SEP + czyKlikac);
		zapis.println("czyUzycListyAdresow" + _SEP + czyUzycListyAdresow);
		zapis.println("predkosc" + _SEP + predkosc);
		zapis.println("pozycjaGLOSUJ" + _SEP + pozycjaGLOSUJ.x + _SEP + pozycjaGLOSUJ.y);
		zapis.println("pozycjaAdresEMAIL" + _SEP + pozycjaAdresEMAIL.x + _SEP + pozycjaAdresEMAIL.y);
		//zapis.println("pozycjaWYSLIJ" + _SEP + pozycjaWYSLIJ.x + _SEP + pozycjaWYSLIJ.y);
		zapis.println("pozycjaCAPTCHY" + _SEP + pozycjaCAPTCHY.x + _SEP + pozycjaCAPTCHY.y);
		//zapis.println("pozycjaOK1" + _SEP + pozycjaOK1.x + _SEP + pozycjaOK1.y);
		zapis.println("pozycjaOK2" + _SEP + pozycjaOK2.x + _SEP + pozycjaOK2.y);
		zapis.println("pozycjaOSWIADCZENIE" + _SEP + pozycjaOSWIADCZENIE.x + _SEP + pozycjaOSWIADCZENIE.y);
		zapis.close();
	}
	
	private void przetwarzajObiekt(String emailPrep){
		System.out.println(emailPrep);
		
		//pozycjaGLOSUJ,pozycjaAdresEMAIL,pozycjaWYSLIJ,pozycjaCAPTCHY,pozycjaOK1,pozycjaOK2,pozycjaOSWIADCZENIE,adresEMAIL
		//NiveaKlikacz.captchy = captchy;
		obiekt = new NiveaKlikacz2
				(pozycjaGLOSUJ,
				pozycjaAdresEMAIL,
				//pozycjaWYSLIJ,
				pozycjaCAPTCHY,
				//pozycjaOK1,
				pozycjaOK2,
				pozycjaOSWIADCZENIE,
				emailPrep,
				//captchy,
				czyKlikac,
				predkosc);
		
		/*if (petlaWykonanaRazy++ == 0){
			obiekt.ustawPobranieCaptcha(); 
			//if (input != null){
				//captchy = input;
			//}
			obiekt.oczekiwanie(10000);
		}*/
		//od tad
		if (petlaWykonanaRazy++ == 0) obiekt.oczekiwanie(10000);
		obiekt.ustawPobranieCaptcha();
		petlaWykonanaRazy++;
		//do tad

		if (liczba > 300000){
			uruchom = false;
		}
		
		//uruchomienie glosowania
		if (obiekt.glosujTeraz() == -999){
			uruchom = false;
		}
	}
	
	private void uruchomNiveaKlikacza() throws FileNotFoundException{
		NiveaKlikacz2.captchy = this.captchy;
		
		//System.out.println(czyUzycListyAdresow);
		
		if (czyUzycListyAdresow){
			File plik = new File("adresy.txt");
			Scanner skaner = new Scanner(plik);
			
			while(skaner.hasNextLine() && uruchom){
				String temp = skaner.nextLine();
				
				przetwarzajObiekt(temp);
			}
			
			skaner.close();
			
		}else{
			while(uruchom){
				String emailPrep = null;
			
				emailPrep = adresEMAIL;
				emailPrep = ++liczba + emailPrep;
				
				przetwarzajObiekt(emailPrep);
			}
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//uruchomienie zaczynamy od utworzenia obiektu klasy
		//new Start().uruchomNiveaKlikacza();
		//lub
		Start a = new Start();
		
		try {
			a.wczytajParametry();
			
			a.uruchomNiveaKlikacza();
			a.zapiszParametry();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//k1las@dlalosic.pl
		//elosice.pl
		//losice1.pl
		
		
		/*String a = null;
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Podaj czas oczekiwania:");
			a = in.readLine();
			minutDoRuszaniaMysza = 60*Integer.parseInt(a);
			System.out.println("Wykorzystaj szejker [s], kwadrat[k], wirtualka [w]:");
			a = null;
			a = in.readLine();
			if (a.equals("s")) szejker = true;
			else if (a.equals("k")) kwadrat = true;
			else wirtualka = true;
		}catch(IOException e){
			e.printStackTrace();
		}*/

		//String adresEMAIL = "klas@zmienlosice.pl";
		//String adresEMAIL = "kalas@dlalosic.pl";
		
		//System.out.println("KURSOR: wys: "+nowyPunkt.x+" szer: "+nowyPunkt.y);
	}
}