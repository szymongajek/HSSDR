package hyperGraphs;
 

public class  MovableComponent {
	
	 
	private	 int middleX, middleY;
	private int sizeX,sizeY;
	
//	boolean selected=false;
	
 
	public void moveTo(int x, int y ){
		this.middleX=x;
		this.middleY=y;
	}
	public void translateBy(int x, int y ){
		this.middleX+=x;
		this.middleY+=y;
	}
//	public void selectMe(){
//		selected=true;
//	}
//	public void releaseMe(){
//		selected=false;
//	}
//	public boolean isSelected() {
//		return selected;
//	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	 
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public int getMiddleX() {
		return middleX;
	}
	public void setMiddleX(int middleX) {
		this.middleX = middleX;
	}
	public int getMiddleY() {
		return middleY;
	}
	public void setMiddleY(int middleY) {
		this.middleY = middleY;
	}
	
		
}
