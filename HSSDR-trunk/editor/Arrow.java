package editor;

import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class Arrow {
	Point start, end;

	boolean thick;

	public Arrow(Point tempArrStart, Point tempArrEnd, boolean thick) {
		super();
		this.start = tempArrStart;
		this.end = tempArrEnd;
		this.thick = thick;
	}

	public static Shape createArrowBase(Point fromPt, Point toPt) {

		Polygon arrowPolygon = new Polygon();
		arrowPolygon.addPoint(-6, 3);
		arrowPolygon.addPoint(6, 3);
		arrowPolygon.addPoint(6, -3);
		arrowPolygon.addPoint(-6, -3);

		Point midPoint = midpoint(fromPt, toPt);

		double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

		AffineTransform transform = new AffineTransform();
		transform.translate(midPoint.x, midPoint.y);
		double ptDistance = fromPt.distance(toPt);
		double scale = ptDistance / 12.0; // 12 because it's the length of the
											// arrow polygon.
		transform.rotate(rotate);

		transform.scale(scale, 1);

		return transform.createTransformedShape(arrowPolygon);
	}

	public static Shape createTwoDirArrowShapeNice(Arrow arrow) {

		Point fromPt = arrow.start;
		Point toPt = arrow.end;

		Polygon arrowPolygon = new Polygon();
		arrowPolygon.addPoint(-5, 1);
		arrowPolygon.addPoint(5, 1);
		arrowPolygon.addPoint(5, 3);
		arrowPolygon.addPoint(6, 0);
		arrowPolygon.addPoint(5, -3);
		arrowPolygon.addPoint(5, -1);
		arrowPolygon.addPoint(-5, -1);
		arrowPolygon.addPoint(-5, -3);
		arrowPolygon.addPoint(-6, 0);
		arrowPolygon.addPoint(-5, 3);

		Point midPoint = midpoint(fromPt, toPt);

		double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

		AffineTransform transform = new AffineTransform();
		transform.translate(midPoint.x, midPoint.y);
		double ptDistance = fromPt.distance(toPt);
		double scale = ptDistance / 12.0; // 12 because it's the length of the
											// arrow polygon.
		transform.rotate(rotate);

		int size;
		if (arrow.thick) {
			size = 6;
		} else {
			size = 2;
		}
		transform.scale(scale, size);

		return transform.createTransformedShape(arrowPolygon);
	}

	public static Shape createOneDirArrowShape(Point fromPt, Point toPt) {
		Polygon arrowPolygon = new Polygon();
		arrowPolygon.addPoint(-6, 1);
		arrowPolygon.addPoint(3, 1);
		arrowPolygon.addPoint(3, 3);
		arrowPolygon.addPoint(6, 0);
		arrowPolygon.addPoint(3, -3);
		arrowPolygon.addPoint(3, -1);
		arrowPolygon.addPoint(-6, -1);

		Point midPoint = midpoint(fromPt, toPt);

		double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

		AffineTransform transform = new AffineTransform();
		transform.translate(midPoint.x, midPoint.y);
		double ptDistance = fromPt.distance(toPt);
		double scale = ptDistance / 12.0; // 12 because it's the length of the
											// arrow polygon.
		transform.scale(scale, scale);
		transform.rotate(rotate);

		return transform.createTransformedShape(arrowPolygon);
	}

	private static Point midpoint(Point p1, Point p2) {
		return new Point((int) ((p1.x + p2.x) / 2.0),
				(int) ((p1.y + p2.y) / 2.0));
	}

}