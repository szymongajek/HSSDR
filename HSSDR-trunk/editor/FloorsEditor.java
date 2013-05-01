package editor;

import hyperGraphs.HLH;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import rectangularBoard.Path;

import util.Logger;

public class FloorsEditor extends JPanel {

	private static final int Y_LAYOUT_GAP = 50;
	
	private static final int Y_LAYOUT_SIZE= MainWindow.DEFAULT_SIZE_Y;

	private static final int X_BASE_TRANSLATION = 150;

	private static final int Y_BASE_TRANSLATION = 10;

	private int sizeX = 1000, sizeY = 500; // pocztakowy rozmiar planszy (bez
	// zoomowania)

	private final double  FLOOR_ZOOM_SCALE = .4;

	private ArrayList<LayoutEditor> layoutEditorsList;

	MainWindow window;

	private Arrow tempArrow;

	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();
	int arr1x, arr1y;
	Path arrowBeg;
	boolean aarrStarted = false;

	public FloorsEditor(ArrayList<LayoutEditor> layoutEditorsList,
			MainWindow window) {
		this.layoutEditorsList = layoutEditorsList;
		this.window = window;
	}

	public void reset(ArrayList<LayoutEditor> layoutEditorsList) {
		this.layoutEditorsList = layoutEditorsList;
		arrows = new ArrayList<Arrow>();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
				RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		g2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);

		g2D.setColor(Color.white);
		g2D.fillRect(0, 0, this.getWidth(), this.getHeight());

		int layoutscount = layoutEditorsList.size();

		int ycorrection = Y_LAYOUT_SIZE + Y_LAYOUT_GAP;

		AffineTransform saved = g2D.getTransform();

		g2D.translate(X_BASE_TRANSLATION, Y_BASE_TRANSLATION);
		g2D.scale(FLOOR_ZOOM_SCALE, FLOOR_ZOOM_SCALE);
		// g2D.shear(-0.5, 0);

		for (int i = 0; i < layoutscount; i++) {
			layoutEditorsList.get(i).paintMe(g2D);
			g2D.translate(0, ycorrection);
		}

		g2D.setTransform(saved);

		drawTempArrow(g2D);
		drawArrows(g2D);

	}

	final static BasicStroke arrow_stroke = new BasicStroke(1.0f,
			BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

	private void drawArrows(Graphics2D g2D) {

		g2D.setColor(Color.BLACK);
		g2D.setStroke(arrow_stroke);
		for (Arrow arr : arrows) {
			g2D.draw(Arrow.createTwoDirArrowShapeNice(arr));

		}

	}

	private void drawTempArrow(Graphics2D g2d) {
		if (tempArrow == null) {
			return;
		}
		g2d.setColor(Color.BLACK);
		// g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));
		// g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));
		// g2d.draw(createArrowBase(tempArrStart, tempArrEnd ));

		g2d.draw(Arrow.createTwoDirArrowShapeNice(tempArrow));

	}

	public void initLayout(int sizeX ) {

		this.sizeX = sizeX;
		this.sizeY = (int) ((MainWindow.DEFAULT_SIZE_Y + Y_LAYOUT_GAP)
				* layoutEditorsList.size() * FLOOR_ZOOM_SCALE);

		this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setSize(sizeX, sizeY);

		repaint();
	}

	public void addArrow(int arr1x, int arr1y, int a2x, int a2y, Path start, Path end, boolean isthick) {
		arrows.add(new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y), start, end,
				isthick));

	}

	public void setTemporaryArrow(int arr1x, int arr1y, int a2x, int a2y) {

		tempArrow = new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y),
				false);

	}

	public void removeTempArrow() {
		tempArrow = null;
	}


	void floorsEditorMouseClicked(MouseEvent e) {
		boolean isThick;
		if (e.getButton()==MouseEvent.BUTTON1){
			isThick =true;
		}else{
			isThick =false;
		}
		
		int floorAreaSize = (int)((Y_LAYOUT_SIZE +Y_LAYOUT_GAP)*this.FLOOR_ZOOM_SCALE);
		int base = e.getY()-Y_BASE_TRANSLATION;
		int floorUnderMouse = base / floorAreaSize;  
		LayoutEditor editorUnderMouse = window.getFloor(floorUnderMouse);
		
		double savedZoom = editorUnderMouse.getZoomedTo();
		editorUnderMouse.setZoomedTo(this.FLOOR_ZOOM_SCALE);

		// przeksztalcenie wspolrzednych lokalnych na te w drabinie pieter
		int xtrans = e.getX();
		int ytrans = e.getY();
		// poczatkowe przesuniecie
		xtrans = xtrans - X_BASE_TRANSLATION;
		ytrans = ytrans - Y_BASE_TRANSLATION;
		// nte pietro
		ytrans = ytrans -  ((int)(( floorUnderMouse)*floorAreaSize));
		
		switch (editorUnderMouse.mode) {
		case OUTLINE_FINISHED:
			if (editorUnderMouse.selectDevelopedPath(xtrans, ytrans)){ // zaznaczamy
//				editorUnderMouse.mode=Mode.AREA_SELECTED;
				window.setSelectedAreaInfo(editorUnderMouse.getDevelopedPath());
				if (!aarrStarted) {
					arr1x = e.getX();
					arr1y = e.getY();
					aarrStarted = true;
					arrowBeg=editorUnderMouse.getDevelopedPath();
					this.setTemporaryArrow(arr1x, arr1y, e.getX(), e.getY());
				} else {
					aarrStarted = false;
					this.removeTempArrow();
					Path arrowEnd = editorUnderMouse.getDevelopedPath();
					this.addArrow(arr1x, arr1y, e.getX(), e.getY(),arrowBeg, arrowEnd, isThick);
					
					// tworzenie relacji miedzy pietrami
					String relKind;
					if (isThick){
						relKind=HLH.KIND_ACC;
					}else{
						relKind=HLH.KIND_VIS;
					}
					
					if(arrowBeg.getFloorNr()>arrowEnd.getFloorNr()){
						window.controller.createMultiFloorRealtion(arrowBeg, arrowEnd, relKind);
					}else{
						window.controller.createMultiFloorRealtion(arrowEnd, arrowBeg , relKind);
					}
					
					
					
					window.clearDevelopedPathSelection();
				}
			}else { // nic nie zaznaczone
				window.setSelectedAreaInfo(null);
				window.clearDevelopedPathSelection();
				aarrStarted = false;
				this.removeTempArrow();
			}
			break;
//		case AREA_SELECTED:
//			if (! editorUnderMouse.selectDevelopedPath(xtrans, ytrans)){ //odznaczamy path
//				editorUnderMouse.mode=Mode.OUTLINE_FINISHED;
//				window.setSelectedAreaInfo(null);
//			}else {
//				window.setSelectedAreaInfo(editorUnderMouse.getDevelopedPath());
//			}
//			break;
		default:
			break;
		}
		
		editorUnderMouse.setZoomedTo(savedZoom);
		repaint();
	}

	synchronized void floorsEditorMouseMoved(MouseEvent e) {
		
		if (aarrStarted) {
			this.setTemporaryArrow(arr1x, arr1y, e.getX(), e.getY());
		}

		int floorAreaSize = (int)((Y_LAYOUT_SIZE +Y_LAYOUT_GAP)*this.FLOOR_ZOOM_SCALE);
		int base = e.getY()-Y_BASE_TRANSLATION;
		int floorUnderMouse = base / floorAreaSize;  
		LayoutEditor editorUnderMouse = window.getFloor(floorUnderMouse);
		
		double savedZoom = editorUnderMouse.getZoomedTo();
		editorUnderMouse.setZoomedTo(this.FLOOR_ZOOM_SCALE);

		// przeksztalcenie wspolrzednych lokalnych na te w drabinie pieter
		int xtrans = e.getX();
		int ytrans = e.getY();
		// poczatkowe przesuniecie
		xtrans = xtrans - X_BASE_TRANSLATION;
		ytrans = ytrans - Y_BASE_TRANSLATION;
		// nte pietro
		ytrans = ytrans -  ((int)(( floorUnderMouse)*floorAreaSize));
		
		switch (editorUnderMouse.mode) {
		case OUTLINE_FINISHED:
			editorUnderMouse.markGrid(xtrans, ytrans);
			editorUnderMouse.highlightPath(xtrans, ytrans);
			break;
		case AREA_SELECTED:
			editorUnderMouse.markGrid(xtrans, ytrans);
			editorUnderMouse.highlightPath(xtrans, ytrans);
			break;

		default:
			editorUnderMouse.markGrid(xtrans, ytrans);
			break;
		}
		
		editorUnderMouse.setZoomedTo(savedZoom);
		repaint();
	}

}
