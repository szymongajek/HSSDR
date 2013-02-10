package revitXmlTesting;

import java.util.ArrayList;

public class Space {

	String id, name;
	int cadId;
	double area, volumne;
	
	ArrayList<Point> planarGeom= new ArrayList<Point>();
	
	public Space(){
		
	}
	public Space(String id, String name, int cadId, double area, double volumne) {
		super();
		this.id = id;
		this.name = name;
		this.cadId = cadId;
		this.area = area;
		this.volumne = volumne;
	}
	@Override
	public String toString() {
		String ret="space id: "+id+"\n";
		ret+="planar geometry:\n";
		
		for(Point p :planarGeom){
		ret+=p+"\n";	
		}
		
		return ret;
	}
	
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public int getCadId() {
		return cadId;
	}
	public void setCadId(int cadId) {
		this.cadId = cadId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getVolumne() {
		return volumne;
	}
	public void setVolumne(double volumne) {
		this.volumne = volumne;
	}
	public ArrayList<Point> getPlanarGeom() {
		return planarGeom;
	}
	public void setPlanarGeom(ArrayList<Point> planarGeom) {
		this.planarGeom = planarGeom;
	}
}
