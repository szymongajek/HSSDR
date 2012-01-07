package robotsChecker;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

import hyperGraphs.GraphMessage;
import hyperGraphs.HLH;
import hyperGraphs.HyperEdge;
import hyperGraphs.Node;
import hyperGraphs.ObjectHE;

public class PositoningChecker
{
	private static Logger logger = Logger.getLogger(
			PositoningChecker.class.getName());

	static class Wall
	{
		public int x1, y1, x2, y2;

		public Wall(int x1, int y1, int x2, int y2)
		{
			if (x1 == x2) {
				// œciana pionowa
				this.x1 = this.x2 = x1;
				if (y1 <= y2) {
					this.y1 = y1;
					this.y2 = y2;
				} else {
					this.y1 = y2;
					this.y2 = y1;
				}
			} else if (y1 == y2) {
				// œciana pozioma
				this.y1 = this.y2 = y1;
				if (x1 <= x2) {
					this.x1 = x1;
					this.x2 = x2;
				} else {
					this.x1 = x2;
					this.x2 = x1;
				}
			} else {
				// œciana na ukos, nie obs³ugujemy takich
				throw new RuntimeException("ukoœna œciana");
			}
		}
		
		public Wall mirrored(int x, int y)
		{
			return new Wall(2 * x - x2, 2 * y - y2, 2 * x - x1, 2 * y - y1);
		}

		public int hashCode() {
			final int PRIME = 31;
			int result = 1;
			result = PRIME * result + x1;
			result = PRIME * result + x2;
			result = PRIME * result + y1;
			result = PRIME * result + y2;
			return result;
		}

		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Wall other = (Wall) obj;
			if (x1 != other.x1)
				return false;
			if (x2 != other.x2)
				return false;
			if (y1 != other.y1)
				return false;
			if (y2 != other.y2)
				return false;
			return true;
		}
	}
	

	// WARNING: dla prostoty obliczeñ zak³adam ¿e wspó³rzêdne koñców œcian
	// s¹ parzyste, i wspó³rzêdne œrodka obrotu da siê zapisaæ w int.
	public static boolean isSymmetrical(List<Wall> walls)
	{
		if (walls.isEmpty())
			return false;
		// Oblicz po³o¿enie œrodka layoutu pomieszczeñ
		java.util.Iterator<Wall> i = walls.iterator();
		Wall a = i.next();
		int xmin = a.x1, ymin = a.y1, xmax = a.x2, ymax = a.y2;
		while (i.hasNext()) {
			a = i.next();
			if (a.x1 < xmin)
				xmin = a.x1;
			if (a.x2 > xmax)
				xmax = a.x2;
			if (a.y1 < ymin)
				ymin = a.y1;
			if (a.y2 > ymax)
				ymax = a.y2;
		}
		int xmiddle = (xmin + xmax) / 2, ymiddle = (ymin + ymax) / 2;
		// W pêtli wyrzucaj kolejne œciany + ich symetryczne odbicia
		Wall b;
		while (! walls.isEmpty()) {
			a = walls.remove(0);
			b = a.mirrored(xmiddle, ymiddle);
			if (a.equals(b) || walls.remove(b))
				continue;
			return false;
		}
		// Wszystkie œciany mia³y odbicia lub by³y symetryczne same ze sob¹
		return true;
	}
	
	public static void extractWalls(ObjectHE hyperedge, List<Wall> list)
	{
		logger.finest("entering extractWalls(" + hyperedge + ") on level "
				+ hyperedge.getLevel());
		ArrayList<HyperEdge> children = hyperedge.getChildElements();
		if (! children.isEmpty()) {
			// Zignoruj moje œciany, przetwórz œciany dzieci
			Iterator<HyperEdge> j = children.iterator();
			while (j.hasNext()) {
				HyperEdge h = j.next();
				if (h instanceof ObjectHE)
					extractWalls((ObjectHE) h, list);
			}
		} else {
			// PrzejdŸ przez podpiête wêz³y reprezentuj¹ce œciany
			ArrayList<Node> nodes = hyperedge.getNodes();
			Iterator<Node> i = nodes.iterator();
			while (i.hasNext()) {
				String coord = i.next().getAttribute(HLH.COORD);
				logger.finest("node with wall coord=" + coord);
				String t[] = coord.split(":");
				list.add(new Wall(
					2 * Integer.parseInt(t[0]), 2 * Integer.parseInt(t[1]),
					2 * Integer.parseInt(t[2]), 2 * Integer.parseInt(t[3]) ));
			}
		}
		logger.finest("leaving extractWalls() on level " + hyperedge.getLevel());
	}
	
	public static GraphMessage[] checkStructure(HLH graph)
	{
		LinkedList<Wall> ls = new LinkedList<Wall>();
		extractWalls(graph.getRootEdge(), ls);
		if (isSymmetrical(ls)) {
			GraphMessage m = new GraphMessage();
			m.setMessage("layout jest symetryczny\nroboty mog¹ straciæ orientacjê");
			return new GraphMessage[] { m };
		} else {
			return new GraphMessage[] { };
		}
	}
}
