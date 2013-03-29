package editor;

import folf.*;
import hyperGraphs.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import controller.HyperGraphBrowser;
 
 
public class HyperGraphEditor extends JPanel implements HyperGraphBrowser {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -794725138699406016L;

	private int sizeX=1000, sizeY=500; // pocztakowy rozmiar planszy (bez zoomowania)
	 
	public static double zoomedTo; // skala powiekszenia
	public  static double zoomSpeed=10.0/100.0; // szybkoœæ  powiekszenia
	
	//TODO: ustawic to!!!! 
 	public  static int metersToPixels=20; // ile px narysowac dla metra

 	HLH graphRoot=null;
	 
	// przesuniecie obietktow rysowanych wzgledem ich polozenia wynikajacaego z layout edit
	int pos_mod_x=0;
	int pos_mod_y=0;
 
	private int currentFloor = 0;
	
	public HyperGraphEditor(){
		  
		 sizeX=this.getWidth();
		 sizeY=this.getHeight();
		 zoomedTo=1;
		  
	}

	public void initLayout(int sizeX,int sizeY ){
		
		this.sizeX=sizeX;
		this.sizeY=sizeY;
//		this.gridSize =gridSize;
 
		this.setPreferredSize(new Dimension(sizeX,sizeY));
		this.setSize(sizeX, sizeY);
		
		repaint();
	}
	
	public void clear(int sizeX,int sizeY ){
		graphRoot=null;
		
		this.sizeX=sizeX;
		this.sizeY=sizeY;
//		this.gridSize =gridSize;
 
		this.setPreferredSize(new Dimension(sizeX,sizeY));
		this.setSize(sizeX, sizeY);
				
		repaint();
	}
	 
 
	 
	public  void paintComponent(Graphics g ){
		Graphics2D g2D = (Graphics2D)g;
//			System.out.println("scale"+this.getWidth()+  "   "+(int)(sizeX*zoomedTo) );
			g2D.setColor(Color.white);
			g2D.fillRect(0, 0, this.getWidth() , this.getHeight() );
			g2D.setColor(Color.red);
			g2D.scale(zoomedTo, zoomedTo);

			if (graphRoot.getRootEdge(currentFloor)!=null){
				
				ObjectPainter.paintGraph(graphRoot.getRootEdge(currentFloor), g2D);
	 
			}
	}

    public void updateSize(  ) 
    {   
    	 setSize( (int)(sizeX* zoomedTo), (int)(sizeY* zoomedTo) ); 
    	 setPreferredSize(new Dimension((int)(sizeX* zoomedTo), (int)(sizeY* zoomedTo) )); 
    	  
 		repaint();
    }       
    
	 /** 
     * This method increments the zoom factor with 
     * the zoom percentage, to create the zoom in effect  
     */ 
    public void zoomIn() 
    {  System.out.println("++++++++++++++++++"+this.getWidth()+  "   "+this.getHeight() );
    	zoomedTo += zoomSpeed; 
 
    	updateSize( );
    }             
     
    /** 
     * This method decrements the zoom factor with the  
     * zoom percentage, to create the zoom out effect  
     */ 
    public void zoomOut() 
    { System.out.println("-----------"+this.getWidth()+  "   "+this.getHeight() );
    	zoomedTo -= zoomSpeed; 
         
    	if(zoomedTo < zoomSpeed) 
        { 
    		zoomedTo  = zoomSpeed; 
        }
 
    	updateSize( );
    } 
     
    /** 
     * This method returns the currently 
     * zoomed percentage 
     *  
     * @return 
     */ 
    public int getZoomedPerc() 
    
    { 
        return (int)(zoomedTo * 100);  
    }

	public int getSizeX() {
		return sizeX;
	}


	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}


	public int getSizeY() {
		return sizeY;
	}


	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}

	public void setGraph(HLH graph) {
		this.graphRoot=  graph ;
		
	}

	public void clear() {
		this.clear(sizeX,sizeY);
		 
	}
	
	public int getAreaKind(int x, int y) {
		return ObjectPainter.getAreaKind(x, y,graphRoot.getRootEdgeGroundFloor());
	}

	public void dragObject(int x, int y, boolean isRightClick) {
		if (ObjectPainter.isSelected()){
			ObjectPainter.dragObject(x, y, graphRoot.getRootEdgeGroundFloor(),isRightClick);
			repaint();
		}
	}
	
	public void selectObject(int x, int y) {
		 ObjectPainter.selectObject(x, y,graphRoot.getRootEdgeGroundFloor());
		 repaint();
	}

	public void releaseObject(int x, int y) {
		 ObjectPainter.releaseObject(x, y,graphRoot.getRootEdgeGroundFloor());
		 repaint();
	}
	
	public static  int calcXY(int i){
		return   (int)(HyperGraphEditor.metersToPixels *HyperGraphEditor.zoomedTo * i) ;
		
	}

	@Override
	public void setCurrentFloor(int floorNr) {
		currentFloor=floorNr;
	}
	 
}

