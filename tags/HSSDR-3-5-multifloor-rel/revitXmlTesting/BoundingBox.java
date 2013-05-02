package revitXmlTesting;

public class BoundingBox {
	
	int minX, minY,minZ,maxX,maxY,maxZ;
	public BoundingBox(){};
	
	public BoundingBox(int minX,int minY,int minZ,int maxX, int maxY, int maxZ){
		this.minX =minX;
		this.minY =minY ;
		this.minZ =minZ ;
		this.maxX =maxX;
		this.maxY =maxY;
		this.maxZ =maxZ ;
	}
	
	public String toString(){
		return "Bbox: "+minX+","+minY +","+minZ+"->"+maxX+","+maxY+","+maxZ ;
		
	}

}
