package rectangularBoard;

public class Field {
	
	public int x, y;
	
	public Field(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	public String toString(){
		return "Field: x:"+x+" y:"+y;
	}
	public float cellMiddleX(){
		return x+.5f;
	}
	
	public float cellMiddleY(){
		return y+.5f;
	}
	public Field getN(){
		return new Field (x,y-1);
	}
	public Field getE(){
		return new Field (x+1,y);
	}
	public Field getS(){
		return new Field (x,y+1);
	}
	public Field getW(){
		return new Field (x-1,y);
	}
}
