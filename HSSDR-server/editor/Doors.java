package editor;

public class Doors {
	// wspolrzedne dwoch sasiadujacych pnktow pomiedzy ktorymi znajduja sie drzwi
	public int x1,y1,x2,y2;
	
	//numer sciany w obiekcie Path do ktorego naleza drzwi, na ktorej one leza
	public int wallNr=-1;
	public Doors (int x1, int y1, int x2, int y2){
		this.x1=x1;
		this.y1=y1;
		this.x2=x2;
		this.y2=y2;
	}
	
	public boolean areAtPoint(int x1, int y1, int x2, int y2){
		return ( (this.x1==x1) && (this.y1==y1)&& (this.x2==x2)&& (this.y2==y2) );
	}

}
