package editor;

import folf.*;
import hyperGraphs.*;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import util.Logger;

import controller.HyperGraphBrowser;

public class HyperGraphEditor extends JPanel implements HyperGraphBrowser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -794725138699406016L;

	private int sizeX , sizeY ; // pocztakowy rozmiar planszy  

	public static double zoomedTo; // skala powiekszenia
	public static double zoomSpeed = 10.0 / 100.0; // szybko�� powiekszenia

	// TODO: ustawic to!!!!
	public static int metersToPixels = 20; // ile px narysowac dla metra
	
	public static boolean HIDE_NON_LEAF_HE_WHILE_PAINTING; 

	HLH graphRoot = null;

	// przesuniecie obietktow rysowanych wzgledem ich polozenia wynikajacaego z
	// layout edit
	int pos_mod_x = 0;
	int pos_mod_y = 0;

	private int currentFloor = 0;
	private ObjectHE baseGraphEdgeForCurrentFloor;

	public HyperGraphEditor() {

		sizeX = this.getWidth();
		sizeY = this.getHeight();
		zoomedTo = 1;

		currentFloor = 0;
	}

	public void initLayout(int sizeX, int sizeY) {

		this.sizeX = sizeX;
		this.sizeY = sizeY;
		// this.gridSize =gridSize;

		this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setSize(sizeX, sizeY);

		repaint();
	}

	public void clear(int sizeX, int sizeY) {
		graphRoot = null;
		currentFloor=0;

		this.sizeX = sizeX;
		this.sizeY = sizeY;
		// this.gridSize =gridSize;

		this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setSize(sizeX, sizeY);

		repaint();
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		// System.out.println("scale"+this.getWidth()+
		// "   "+(int)(sizeX*zoomedTo) );
		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2D.setColor(Color.red);
		g2D.scale(zoomedTo, zoomedTo);

		// if (currentFloor == -1){
		// // rysujemy pelny graf
		// ObjectPainter.paintGraph(graphRoot.getGraphRootEdge(), g2D);
		//				
		// }else if (graphRoot.getFloorRootEdge(currentFloor)!=null){
		// // rysujemy graf pietra
		// ObjectPainter.paintGraph(graphRoot.getFloorRootEdge(currentFloor),
		// g2D);
		//	 
		// }
		if (baseGraphEdgeForCurrentFloor!=null) {
			HyperGraphPainter.paintGraph(baseGraphEdgeForCurrentFloor, g2D);

		}
		
	}

	public void updateSize() {
		setSize((int) (sizeX * zoomedTo), (int) (sizeY * zoomedTo));
		setPreferredSize(new Dimension((int) (sizeX * zoomedTo),
				(int) (sizeY * zoomedTo)));

		repaint();
	}

	/**
	 * This method increments the zoom factor with the zoom percentage, to
	 * create the zoom in effect
	 */
	public void zoomIn() {
		Logger.LOGGER.debug("zooming +" + this.getWidth() + "   "
				+ this.getHeight());
		zoomedTo += zoomSpeed;

		updateSize();
	}

	/**
	 * This method decrements the zoom factor with the zoom percentage, to
	 * create the zoom out effect
	 */
	public void zoomOut() {
		Logger.LOGGER.debug("zooming -" + this.getWidth() + "   "
				+ this.getHeight());
		zoomedTo -= zoomSpeed;

		if (zoomedTo < zoomSpeed) {
			zoomedTo = zoomSpeed;
		}

		updateSize();
	}

	/**
	 * This method returns the currently zoomed percentage
	 * 
	 * @return
	 */
	public int getZoomedPerc()

	{
		return (int) (zoomedTo * 100);
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
		this.graphRoot = graph;
		baseGraphEdgeForCurrentFloor = graphRoot.getFloorRootEdge(currentFloor);
	}

	public void clear() {
		this.clear(sizeX, sizeY);

	}

	public int getAreaKind(int x, int y) {
		return HyperGraphPainter.getAreaKind(x, y, baseGraphEdgeForCurrentFloor);
	}

	public void dragObject(int x, int y, boolean isRightClick) {
		if (HyperGraphPainter.isSelected()) {
			HyperGraphPainter.dragObject(x, y, baseGraphEdgeForCurrentFloor,
					isRightClick);
			repaint();
		}
	}

	public void selectObject(int x, int y) {
		HyperGraphPainter.selectObject(x, y, baseGraphEdgeForCurrentFloor);
		repaint();
	}

	public void releaseObject(int x, int y) {
		HyperGraphPainter.releaseObject(x, y, baseGraphEdgeForCurrentFloor);
		repaint();
	}

	public static int calcXY(int i) {
		return (int) (HyperGraphEditor.metersToPixels
				* HyperGraphEditor.zoomedTo * i);

	}

	@Override
	public void setCurrentFloor(int floorNr) {
		currentFloor = floorNr;
		if (currentFloor == -1) {
			// rysujemy pelny graf
			baseGraphEdgeForCurrentFloor = graphRoot.getGraphRootEdge();

		} else   {
			// rysujemy graf pietra
			baseGraphEdgeForCurrentFloor = graphRoot
					.getFloorRootEdge(currentFloor);

		}
		repaint();
	}

}
