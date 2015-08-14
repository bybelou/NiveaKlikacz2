/**
 * 
 */
package testPaczka;

import java.awt.AWTException;
//import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
//import java.awt.Toolkit;

/**
 * @author Kamil
 *
 */
public class aa {

	static Robot robot;
	//private Point rozdzielczoscEkranu = new Point();
	
	private static int czasOczekiwana;
	private static int przesuniecieKursora;

	public Point nowaPozycja = new Point();
	public Point staraPozycja = new Point();
	
	public boolean czyRobSzejkera = false;
	public boolean czyRobKwadrat = false;
	public boolean czyRobWirtualke = false;
	
	
	//konstruktor
	public aa(){
		try{
			robot = new Robot();
		}catch (AWTException e){
			e.printStackTrace();
		}
		
		//rozdzielczoscEkranu = pobierzRozdzielczosc();

		ustawCzasOczekiwania(1000);
		ustawPrzesuniecie(3);
	}
	
	//pobranie wysokosci i szerokosci ekranu
	/*private Dimension pobierzRozdzielczoscDimension(){
		Dimension rozdzielczosc = Toolkit.getDefaultToolkit().getScreenSize();
		return rozdzielczosc;
	}*/
	/*private Point pobierzRozdzielczosc(){
		Point punkt = new Point();
		punkt.x = pobierzRozdzielczoscDimension().width;
		punkt.y = pobierzRozdzielczoscDimension().height;
		System.out.println("EKRAN:");
		System.out.println("szer: "+punkt.x+" wys: "+punkt.y);
		return punkt; 
	}*/
	
	//pobranie pozycji kursora
	public Point pobierzPozycjeKursora(){
		Point punkt = new Point();
		punkt = MouseInfo.getPointerInfo().getLocation();
		//System.out.println("KURSOR: wys: "+punkt.x+" szer: "+punkt.y);
		return punkt;
	}
	
	//ustawienie nowej pozycji
	public void poruszKursorem(int x, int y){
		robot.mouseMove(x,y);
	}
	public void poruszKursorem(Point point){
		robot.mouseMove(point.x,point.y);
	}
	
	//oczekiwanie
	public void oczekiwanie(int a){
		robot.delay(a);
	}

	public void ustawStaraPozycje(){
		staraPozycja=pobierzPozycjeKursora();
	}
	public void ustawNowaPozycje(){
		nowaPozycja=pobierzPozycjeKursora();
	}
	public boolean czyTaSamaPozycja(){
		if (staraPozycja==nowaPozycja) return true;
		else return false;
	}
	
	public void ustawCzasOczekiwania(int czas){
		czasOczekiwana = czas;
	}
	public void ustawPrzesuniecie(int przesun){
		przesuniecieKursora = przesun;
	}
	public void ustawRobienieSzejka(boolean x){
		this.czyRobSzejkera = x;
		ustawCzasOczekiwania(50);
	}
	public void zrobSzejkera(){
		if (czyRobSzejkera){
			//pobranie starej pozycji kursora
			ustawStaraPozycje();
			System.out.println("KURSOR: szer: "+staraPozycja.x+" wys: "+staraPozycja.y);
			
			while(czyRobSzejkera){
				ustawNowaPozycje();
				nowaPozycja.x=nowaPozycja.x-przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				ustawNowaPozycje();
				nowaPozycja.x=nowaPozycja.x+przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				if (!nowaPozycja.equals(staraPozycja)){
					ustawRobienieSzejka(false);
					ustawRobienieKwadratu(false);
					ustawRobienieWirtualki(false);
				}
			}
		}
	}
	public void ustawRobienieKwadratu(boolean x){
		this.czyRobKwadrat = x;
		ustawCzasOczekiwania(1000);
	}
	public void zrobKwadrat(){
		if (czyRobKwadrat){
			//pobranie starej pozycji kursora
			ustawStaraPozycje();
			System.out.println("KURSOR: szer: "+staraPozycja.x+" wys: "+staraPozycja.y);
			
			while(czyRobKwadrat){
				ustawNowaPozycje();
				nowaPozycja.x=nowaPozycja.x+przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				ustawNowaPozycje();
				nowaPozycja.y=nowaPozycja.y+przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				ustawNowaPozycje();
				nowaPozycja.x=nowaPozycja.x-przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				ustawNowaPozycje();
				nowaPozycja.y=nowaPozycja.y-przesuniecieKursora;
				poruszKursorem(nowaPozycja);
				oczekiwanie(czasOczekiwana);
				
				if (!nowaPozycja.equals(staraPozycja)){
					ustawRobienieSzejka(false);
					ustawRobienieKwadratu(false);
					ustawRobienieWirtualki(false);
				}
			}
		}
	}
	public void ustawRobienieWirtualki(boolean x){
		this.czyRobWirtualke = x;
		ustawCzasOczekiwania(1000);
	}
	public void zrobWirtualke(){
		if (czyRobWirtualke){
			System.out.println("KURSOR: szer: "+pobierzPozycjeKursora().x+" wys: "+pobierzPozycjeKursora().y);
			
			while(czyRobWirtualke){
				poruszKursorem(0,0);
				oczekiwanie(czasOczekiwana);
				poruszKursorem(10,10);
				oczekiwanie(czasOczekiwana);
				ustawNowaPozycje();
				if (!nowaPozycja.equals(new Point(10,10))){
					ustawRobienieSzejka(false);
					ustawRobienieKwadratu(false);
					ustawRobienieWirtualki(false);
				}
			}
		}
	}
}