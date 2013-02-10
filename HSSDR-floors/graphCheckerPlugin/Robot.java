package graphCheckerPlugin;

public class Robot {
	

//	 zakladamy ze robot jest prostokatem o wymiarach:, rx<=ry
	static double rx=1;
	static double ry=2.1;
	
	static double sqrt2 = Math.sqrt(2);
	
	//proste sprawdzanie czy robot moze przejechac przez pokoj
	public static String checkRect(int rectX, int rectY) {
		if (rectX<=rectY){
			if (rx>rectX || ry>rectY)
				return "Unpassable room for robot: (rect "+rectX+"x"+rectY+")";
		}else{
			if (rx>rectY || ry>rectX)
				return "Unpassable room for robot: (rect "+rectX+"x"+rectY+")";
		}
		 
		return "";
	}
	
	// sprawdzanie czy robot moze skrecic w pokoju o ksztalcie L
	/*
	 *  ______
	 *  |     |
	 *  |     |
	 * d|     |
	 *  |_____|
	 *  |  b  |____________
	 *  |		|a		 |	
	 *  |________|________|
	 *         c
	 */
	
	public static String checkLtype(int a, int b, int c, int d) {
		
		// czy moze sie zmiescic w prostok bxd 
		String err = checkRect(d, b);
		if (!err.equals("")) return err;
		
//		 czy moze sie zmiescic w prostok cxa
		err = checkRect(c, a);
		if (!err.equals("")) return err;
		
//		 czy moze skrecic
				
		if (a==b){
			double res = (ry/2 + rx)*sqrt2/2;
			if (res>a)
				return "Unpassable L-type turn for robot: (a:"+a+" b:"+b+" res:"+res+" )";
		}
		
		return "";
	}
	
//	 sprawdzanie czy robot moze skrecic do drzwi
	/*
	 *  |     |
	 *  |_____|
	 *  |  a  |
	 *  -	  |		  	
	 *b       |
     *  -     |
	 *  |     |      
	 *  |     | 
	 *  |     | 
	 *  
	 *  
	 *  a - szer korytarza przed drzwiami
	 *  b- wielkosc drzwi
	 */
	
	public static String checkDoorsTurntype(int a, int b) {
			
//		 czy moze skrecic

			if (rx*ry>a*b)
				return "Unpassable turn into doors for robot: (a:"+a+" b:"+b+" )";
		
		return "";
	}
}
