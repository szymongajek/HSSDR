package hyperGraphs;

 
import java.util.ArrayList;

public class GraphMessage {
	//tresc wiadomosci
	private String message="";
//	obiekty do zaznaczenia 
	private ArrayList <MovableComponent> objects = new ArrayList<MovableComponent>();

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<MovableComponent> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<MovableComponent> objects) {
		this.objects = objects;
	}

}
