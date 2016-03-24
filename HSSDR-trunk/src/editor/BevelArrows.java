package editor;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class BevelArrows
{
    public static void main ( String...args )
    {
        SwingUtilities.invokeLater ( new Runnable () {
            BevelArrows arrows = new BevelArrows();

            @Override
            public void run () {
                JFrame frame = new JFrame ( "Bevel Arrows" );

                frame.add ( new JPanel() {
                    public void paintComponent ( Graphics g ) {
                        arrows.draw ( ( Graphics2D ) g, getWidth(), getHeight() );
                    }
                }
                , BorderLayout.CENTER );

                frame.setSize ( 800, 400 );
                frame.setDefaultCloseOperation ( JFrame.EXIT_ON_CLOSE );
                frame.setVisible ( true );
            }
        } );
    }

    interface Arrow {
        void draw ( Graphics2D g );
    }

    Arrow[] arrows = { new LineArrow(), new CurvedArrow() };

    void draw ( Graphics2D g, int width, int height )
    {
        g.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );

        g.setColor ( Color.WHITE );
        g.fillRect ( 0, 0, width, height );
        g.setColor ( Color.BLACK );
        g.draw(createArrowShape(new Point(100,100), new Point(200,200)));
        
    }

    
    public static Shape createArrowShape(Point fromPt, Point toPt) {
        Polygon arrowPolygon = new Polygon();
        arrowPolygon.addPoint(-6,1);
        arrowPolygon.addPoint(3,1);
        arrowPolygon.addPoint(3,3);
        arrowPolygon.addPoint(6,0);
        arrowPolygon.addPoint(3,-3);
        arrowPolygon.addPoint(3,-1);
        arrowPolygon.addPoint(-6,-1);


        Point midPoint = midpoint(fromPt, toPt);

        double rotate = Math.atan2(toPt.y - fromPt.y, toPt.x - fromPt.x);

        AffineTransform transform = new AffineTransform();
        transform.translate(midPoint.x, midPoint.y);
        double ptDistance = fromPt.distance(toPt);
        double scale = ptDistance / 12.0; // 12 because it's the length of the arrow polygon.
        transform.scale(scale, scale);
        transform.rotate(rotate);

        return transform.createTransformedShape(arrowPolygon);
    }

    private static Point midpoint(Point p1, Point p2) {
        return new Point((int)((p1.x + p2.x)/2.0), 
                         (int)((p1.y + p2.y)/2.0));
    }
    
    static class LineArrow  implements Arrow
    {
        public void draw ( Graphics2D g )
        {
            // where the control point for the intersection of the V needs calculating
            // by projecting where the ends meet

            float arrowRatio = 0.5f;
            float arrowLength = 80.0f;

            BasicStroke stroke = ( BasicStroke ) g.getStroke();

            float endX = 350.0f;

            float veeX;

            switch ( stroke.getLineJoin() ) {
                case BasicStroke.JOIN_BEVEL:
                    // IIRC, bevel varies system to system, this is approximate
                    veeX = endX - stroke.getLineWidth() * 0.25f;
                    break;
                default:
                case BasicStroke.JOIN_MITER:
                    veeX = endX - stroke.getLineWidth() * 0.5f / arrowRatio;
                    break;
                case BasicStroke.JOIN_ROUND:
                    veeX = endX - stroke.getLineWidth() * 0.5f;
                    break;
            }

            // vee
            Path2D.Float path = new Path2D.Float();

            path.moveTo ( veeX - arrowLength, -arrowRatio*arrowLength );
            path.lineTo ( veeX, 0.0f );
            path.lineTo ( veeX - arrowLength, arrowRatio*arrowLength );

            g.setColor ( Color.BLUE );
            g.draw ( path );

            // stem for exposition only
            g.setColor ( Color.YELLOW );
            g.draw ( new Line2D.Float ( 50.0f, 0.0f, veeX, 0.0f ) );

            // in practice, move stem back a bit as rounding errors
            // can make it poke through the sides of the Vee
            g.setColor ( Color.RED );
            g.draw ( new Line2D.Float ( 50.0f, 0.0f, veeX - stroke.getLineWidth() * 0.25f, 0.0f ) );
        }
    }

    static class CurvedArrow  implements Arrow
    {
        // to draw a nice curved arrow, fill a V shape rather than stroking it with lines
        public void draw ( Graphics2D g )
        {
            // as we're filling rather than stroking, control point is at the apex,

            float arrowRatio = 0.5f;
            float arrowLength = 80.0f;

            BasicStroke stroke = ( BasicStroke ) g.getStroke();

            float endX = 350.0f;

            float veeX = endX - stroke.getLineWidth() * 0.5f / arrowRatio;

            // vee
            Path2D.Float path = new Path2D.Float();

            float waisting = 0.5f;

            float waistX = endX - arrowLength * 0.5f;
            float waistY = arrowRatio * arrowLength * 0.5f * waisting;
            float arrowWidth = arrowRatio * arrowLength;

            path.moveTo ( veeX - arrowLength, -arrowWidth );
            path.quadTo ( waistX, -waistY, endX, 0.0f );
            path.quadTo ( waistX, waistY, veeX - arrowLength, arrowWidth );

            // end of arrow is pinched in
            path.lineTo ( veeX - arrowLength * 0.75f, 0.0f );
            path.lineTo ( veeX - arrowLength, -arrowWidth );

            g.setColor ( Color.BLUE );
            g.fill ( path );

            // move stem back a bit
            g.setColor ( Color.RED );
            g.draw ( new Line2D.Float ( 50.0f, 0.0f, veeX - arrowLength * 0.5f, 0.0f ) );
        }
    }
}