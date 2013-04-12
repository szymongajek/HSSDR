package editor;

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

public class FloorsEditor extends JPanel {

	private int sizeX = 1000, sizeY = 500; // pocztakowy rozmiar planszy (bez
	// zoomowania)

	private final double X_SCALE = .7;
	private final double Y_SCALE = .3;

	private ArrayList<LayoutEditor> layoutEditorsList;

	MainWindow window;

	private Arrow tempArrow;

	private ArrayList<Arrow> arrows = new ArrayList<Arrow>();

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

		int xcorrection = 320;
		int ycorrection = MainWindow.DEFAULT_SIZE_Y + 150;

		AffineTransform saved = g2D.getTransform();

		g2D.translate(150, 20.3);
		g2D.scale(X_SCALE, Y_SCALE);
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

	public void initLayout(int sizeX, int sizeYxxx) {

		this.sizeX = sizeX;
		this.sizeY = (int) ((MainWindow.DEFAULT_SIZE_Y + 150)
				* layoutEditorsList.size() * Y_SCALE);

		this.setPreferredSize(new Dimension(sizeX, sizeY));
		this.setSize(sizeX, sizeY);

		repaint();
	}

	public void addArrow(int arr1x, int arr1y, int a2x, int a2y, boolean isthick) {
		arrows.add(new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y),
				isthick));

	}

	public void setTemporaryArrow(int arr1x, int arr1y, int a2x, int a2y) {

		tempArrow = new Arrow(new Point(arr1x, arr1y), new Point(a2x, a2y),
				false);

	}

	public void removeTempArrow() {
		tempArrow = null;
	}

	int HOR_SIZE = 3;
	int VER_SIZE = 3;

	int arr1x, arr1y;
	boolean aarrStarted = false;
	boolean isThick = false;

	void floorsEditorMouseClicked(MouseEvent e) {
		if (!aarrStarted) {
			arr1x = e.getX();
			arr1y = e.getY();
			aarrStarted = true;

		} else {
			aarrStarted = false;
			this.removeTempArrow();
			this.addArrow(arr1x, arr1y, e.getX(), e.getY(), isThick);
		}

	}

	void floorsEditorMouseMoved(MouseEvent e) {

		if (aarrStarted) {
			this.setTemporaryArrow(arr1x, arr1y, e.getX(), e.getY());
		}

		switch (window.currentLayoutEditor.mode) {
		case OUTLINE_FINISHED:
			window.currentLayoutEditor.markGrid(e.getX(), e.getY());
			window.currentLayoutEditor.highlightPath(e.getX(), e.getY());
			break;
		case AREA_SELECTED:
			window.currentLayoutEditor.markGrid(e.getX(), e.getY());
			window.currentLayoutEditor.highlightPath(e.getX(), e.getY());
			break;

		default:
			window.currentLayoutEditor.markGrid(e.getX(), e.getY());
			break;
		}
		repaint();
	}

}
