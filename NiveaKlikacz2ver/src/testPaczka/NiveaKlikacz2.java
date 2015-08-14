package testPaczka;

import java.awt.AWTException;
//import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
//import java.awt.Toolkit;

/**
 * @author Kamil
 *
 */
public class NiveaKlikacz2 {
	static Robot robot;
	static String captchy;

	private Point pozycjaGLOSUJ;
	private Point pozycjaAdresEMAIL;
	//private Point pozycjaWYSLIJ;
	private Point pozycjaCAPTCHY;
	//private Point pozycjaOK1;
	private Point pozycjaOK2;
	private Point pozycjaOSWIADCZENIE = null;
	private String adresEMAIL;
	private boolean wlaczKlikanieMysza = true;
	private float predkosc = 1.0f;
	private boolean czyPobracCaptcha = false;
	
	//konstruktor
	public NiveaKlikacz2(Point pozycjaGLOSUJ,
						Point pozycjaAdresEMAIL,
						//Point pozycjaWYSLIJ,
						Point pozycjaCAPTCHY,
						//Point pozycjaOK1,
						Point pozycjaOK2,
						String adresEMAIL
						//String captchy
						){
		this.pozycjaGLOSUJ = pozycjaGLOSUJ;
		this.pozycjaAdresEMAIL = pozycjaAdresEMAIL;
		//this.pozycjaWYSLIJ = pozycjaWYSLIJ;
		this.pozycjaCAPTCHY = pozycjaCAPTCHY;
		//this.pozycjaOK1 = pozycjaOK1;
		this.pozycjaOK2 = pozycjaOK2;
		this.adresEMAIL = adresEMAIL;
		//this.captchy = captchy;
		
		try{
			robot = new Robot();
		}catch (AWTException e){
			e.printStackTrace();
		}
	}
	public NiveaKlikacz2(Point pozycjaGLOSUJ,
						Point pozycjaAdresEMAIL,
						//Point pozycjaWYSLIJ,
						Point pozycjaCAPTCHY,
						//Point pozycjaOK1,
						Point pozycjaOK2,
						Point pozycjaOSWIADCZENIE,
						String adresEMAIL,
						//String captchy,
						boolean wlaczKlikanieMysza,
						String predkoscNapis){
		this (pozycjaGLOSUJ,
				pozycjaAdresEMAIL,
				//pozycjaWYSLIJ,
				pozycjaCAPTCHY,
				//pozycjaOK1,
				pozycjaOK2,
				adresEMAIL
				//captchy
				);
		this.pozycjaOSWIADCZENIE = pozycjaOSWIADCZENIE;
		this.wlaczKlikanieMysza = wlaczKlikanieMysza;
		if (predkoscNapis.equals("normal")) predkosc = 1.0f;
		else if (predkoscNapis.equals("slow")) predkosc = 1.3f;
		else if (predkoscNapis.equals("fast")) predkosc = 0.5f;
	}
	
	public void ustawPobranieCaptcha(){
		this.czyPobracCaptcha = true;
	}
	
	public String pobierzCaptcha(){
		String pobraneCaptcha = JOptionPane.showInputDialog("Pobierz CAPTCHA");
		//System.out.print("|"+pobraneCaptcha+"|");
		if (pobraneCaptcha != null && pobraneCaptcha.length()>0){
			NiveaKlikacz2.captchy = pobraneCaptcha;
			return pobraneCaptcha;
		}else{
			return null;
		}
	}
	
	//pobranie pozycji kursora
	private Point pobierzPozycjeKursora(){
		Point punkt = new Point();
		punkt = MouseInfo.getPointerInfo().getLocation();
		//System.out.println("KURSOR: wys: "+punkt.x+" szer: "+punkt.y);
		return punkt;
	}
	
	//ustawienie nowej pozycji
	private void poruszKursorem(int x, int y){
		robot.mouseMove(x,y);
	}
	private void poruszKursorem(Point point){
		robot.mouseMove(point.x,point.y);
	}
	
	//oczekiwanie
	public void oczekiwanie(int a){
		robot.delay(a);
	}
	
	private void kliknijPPM(){
		if (wlaczKlikanieMysza){
			robot.mousePress(InputEvent.BUTTON1_MASK);
		}
		oczekiwanie((int)(50*predkosc));
	}
	private void zwolnijPPM(){
		if (wlaczKlikanieMysza){
			robot.mouseRelease(InputEvent.BUTTON1_MASK);
		}
		oczekiwanie((int)(50*predkosc));
	}
	private void kliknijIZwolnijPPM(){
		if (wlaczKlikanieMysza){
			kliknijPPM();
			zwolnijPPM();
		}
	}
	
	private void wprowadzNapis(String napis){
		napis.toLowerCase();
		for (int i=0; i<napis.length(); i++){
			//System.out.println (new Mapper().pobierzKod(adresEMAIL.charAt(i)));
			try{
				if (napis.charAt(i) == '@'){
					robot.keyPress(KeyEvent.VK_SHIFT);
			        robot.keyPress(KeyEvent.VK_2);
			        robot.keyRelease(KeyEvent.VK_SHIFT);
		        }else {
		        	robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(napis.charAt(i)));
				}
			}catch(Exception e){
				System.out.println("Blad przy klawiszu: "+napis.charAt(i)+", kod: "+new Mapper().pobierzKod(napis.charAt(i)));
			}
			oczekiwanie((int)(30*predkosc));
		}
	}
	
	public int glosujTeraz(){
		//przenies na przycisk GLOSUJ
		poruszKursorem(pozycjaGLOSUJ);
		kliknijIZwolnijPPM();
		oczekiwanie((int)(700*predkosc));
		
		//przenies na pole adres email
		if (!pobierzPozycjeKursora().equals(pozycjaGLOSUJ)) return -999;
		poruszKursorem(pozycjaAdresEMAIL);
		kliknijIZwolnijPPM();
		
		wprowadzNapis(adresEMAIL);
		
		//przenies na przycisk WYSLIJ
		if (!pobierzPozycjeKursora().equals(pozycjaAdresEMAIL)) return -999;
		//poruszKursorem(pozycjaWYSLIJ);
		//kliknijIZwolnijPPM();
		robot.keyPress(KeyEvent.VK_ENTER);
		oczekiwanie((int)(700*predkosc));
		
		//przenies na pole captchy
		//if (!pobierzPozycjeKursora().equals(pozycjaWYSLIJ)) return -999;
		if (!pobierzPozycjeKursora().equals(pozycjaAdresEMAIL)) return -999;
		poruszKursorem(pozycjaCAPTCHY.x-100,pozycjaCAPTCHY.y);
		kliknijIZwolnijPPM();
		oczekiwanie(1);
		if (czyPobracCaptcha) pobierzCaptcha();
		poruszKursorem(pozycjaCAPTCHY);
		oczekiwanie((int)(150));///
		kliknijIZwolnijPPM();
		//oczekiwanie((int)(150));///
		oczekiwanie((int)(50*predkosc));
		wprowadzNapis(captchy);
		
		if (!pobierzPozycjeKursora().equals(pozycjaCAPTCHY)) return -999;
		//poruszKursorem(pozycjaOK1);
		//kliknijIZwolnijPPM();
		robot.keyPress(KeyEvent.VK_ENTER);
		//oczekiwanie((int)(50*predkosc));
		oczekiwanie((int)(1000*predkosc));
		oczekiwanie((int)(800));///
		
		if (pozycjaOSWIADCZENIE != null){
			//if (!pobierzPozycjeKursora().equals(pozycjaOK1)) return -999;
			if (!pobierzPozycjeKursora().equals(pozycjaCAPTCHY)) return -999;
			poruszKursorem(pozycjaOSWIADCZENIE);
			kliknijIZwolnijPPM();
			oczekiwanie((int)(1000*predkosc));
			oczekiwanie((int)(400));///
		}

		//if (!pobierzPozycjeKursora().equals(pozycjaOK1)
				//&& !pobierzPozycjeKursora().equals(pozycjaOSWIADCZENIE)) return -999;
		if (!pobierzPozycjeKursora().equals(pozycjaCAPTCHY)
				&& !pobierzPozycjeKursora().equals(pozycjaOSWIADCZENIE)) return -999;
		poruszKursorem(pozycjaOK2);
		kliknijIZwolnijPPM();
		oczekiwanie((int)(1000*predkosc));
		
		return 0;
	}
}