package jtest;

import static org.junit.Assert.*;
import hyperGraphs.HLH;
import hyperGraphs.LayoutUtils;
import hyperGraphs.Node;

import java.util.ArrayList;

import org.junit.Test;

public class LayoutUtilsTest {

	@Test
	public void testCalculateMergingNodesCoord1() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node1 = new Node();
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 20, 10));
		vect.add(node1);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		int []resCoord = LayoutUtils.getWallCoord(res);
		
		assertArrayEquals(new int[]{10, 10, 20, 10}, resCoord);
	}
	
	@Test
	public void testCalculateMergingNodesCoord_testXSimple() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node1 = new Node();
		node1.setDirection("S");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 20, 10));
		vect.add(node1);
		
		Node node2 = new Node();
		node2.setDirection("N");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(20, 10, 30, 10));
		vect.add(node2);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		int []resCoord = LayoutUtils.getWallCoord(res);
		
		assertArrayEquals(new int[]{10, 10, 30, 10}, resCoord);
	}
	
	@Test
	public void testCalculateMergingNodesCoord_testYSimple() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node1 = new Node();
		node1.setDirection("E");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 10, 30));
		vect.add(node1);
		
		Node node2 = new Node();
		node2.setDirection("W");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 30, 10, 50));
		vect.add(node2);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		int []resCoord = LayoutUtils.getWallCoord(res);
		
		assertArrayEquals(new int[]{10, 10, 10, 50}, resCoord);
	}
	
	@Test
	public void testCalculateMergingNodesCoord_testXReverse() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node2 = new Node();
		node2.setDirection("S");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(20, 10, 30, 10));
		vect.add(node2);
		
		Node node1 = new Node();
		node1.setDirection("S");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 20, 10));
		vect.add(node1);
		
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		int []resCoord = LayoutUtils.getWallCoord(res);
		
		assertArrayEquals(new int[]{10, 10, 30, 10}, resCoord);
	}
	
	@Test
	public void testCalculateMergingNodesCoord_testYReverse() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node2 = new Node();
		node2.setDirection("E");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 30, 10, 50));
		vect.add(node2);
		
		Node node1 = new Node();
		node1.setDirection("E");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 10, 30));
		vect.add(node1);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		int []resCoord = LayoutUtils.getWallCoord(res);
		
		assertArrayEquals(new int[]{10, 10, 10, 50}, resCoord);
	}
	
	@Test
	public void testCalculateMergingNodesCoord_notInLine_Prependicular() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node2 = new Node();
		node2.setDirection("E");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 10, 50));
		vect.add(node2);
		
		Node node1 = new Node();
		node1.setDirection("S");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 50, 10));
		vect.add(node1);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		
		assertNull(res);
	}
	@Test
	public void testCalculateMergingNodesCoord_notInLine() {
		ArrayList<Node> vect = new ArrayList<Node>();
		
		Node node2 = new Node();
		node2.setDirection("E");
		node2.setAttribute(HLH.COORD, LayoutUtils.getLineString(10, 10, 10, 50));
		vect.add(node2);
		
		Node node1 = new Node();
		node1.setDirection("S");
		node1.setAttribute(HLH.COORD, LayoutUtils.getLineString(20, 10, 20, 50));
		vect.add(node1);
		
		String res =LayoutUtils.calculateMergingNodesCoord(vect);
		
		assertNull(res);
	}
	
	
	@Test
	public void testMin() {
		assertEquals(0, LayoutUtils.minValue(0));
		assertEquals(0, LayoutUtils.minValue(0,1,2,3));
		assertEquals(0, LayoutUtils.minValue(3,3,1,0));
		assertEquals(0, LayoutUtils.minValue(0,3,1,0,0));
		assertEquals(-100, LayoutUtils.minValue(0,3,1,-100,-99));
	 
	}
	
	
	@Test
	public void testMax() {
		assertEquals(0, LayoutUtils.maxValue(0));
		assertEquals(3, LayoutUtils.maxValue(0,1,2,3));
		assertEquals(3, LayoutUtils.maxValue(3,3,1,0));
		assertEquals(3, LayoutUtils.maxValue(0,3,1,0,0));
		assertEquals(-99, LayoutUtils.maxValue(-200,-100,-99));
	 
	}
	
	
	

}
