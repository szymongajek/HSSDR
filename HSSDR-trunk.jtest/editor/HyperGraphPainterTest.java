/*
 * HyperGraphPainterTest.java
 * Created by Jtest on 1/18/16 2:21:14 PM.
 */

package editor;

import editor.HyperGraphPainter;
import hyperGraphs.HyperEdge;
import hyperGraphs.HyperRelation;
import hyperGraphs.MovableComponent;
import hyperGraphs.Node;
import hyperGraphs.ObjectHE;
import hyperGraphs.RelationHE;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Member;
import java.util.ArrayList;
import jtest.Stubs;

/**
 * HyperGraphPainterTest is a unit test class for class HyperGraphPainter
 * 
 * @see editor.HyperGraphPainter
 * @author Parasoft Jtest 9.2
 */
public class HyperGraphPainterTest extends PackageTestCase {
	/**
	 * Constructs a test case for the test specified by the name argument.
	 * 
	 * @param name
	 *            the name of the test case
	 * @author Parasoft Jtest 9.2
	 */
	public HyperGraphPainterTest(String name) {
		super(name);
		/*
		 * This constructor should not be modified. Any initialization code
		 * should be placed in the setUp() method instead.
		 */
	}

	/**
	 * Test for method: checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckNodesForPoint1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_4 = new ObjectHE();
		ObjectHE e_5 = new ObjectHE();
		ArrayList childElements_5 = new ArrayList();
		ObjectHE e_6 = new ObjectHE();
		ArrayList childElements_6 = new ArrayList();
		ObjectHE e_7 = new ObjectHE();
		ObjectHE e_8 = new ObjectHE();
		ArrayList childElements_7 = new ArrayList();
		ObjectHE e_9 = new ObjectHE();
		ArrayList childElements_8 = new ArrayList();
		ObjectHE e_10 = new ObjectHE();
		ArrayList childElements_9 = new ArrayList();
		ObjectHE e_11 = new ObjectHE();
		ArrayList childElements_10 = new ArrayList();
		ObjectHE e_12 = new ObjectHE();
		edge.addNode((Node) null);
		childElements.add(e);
		childElements.add(null);
		childElements.add(null);
		childElements.add(null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		childElements_2.add(e_12);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(256);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		childElements_4.add(e_4);
		childElements_4.add(null);
		childElements_4.add(e_5);
		childElements_4.add(null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		childElements_5.add(e_6);
		childElements_5.add(null);
		childElements_5.add(null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		childElements_6.add(e_7);
		childElements_6.add(null);
		childElements_6.add(e_8);
		childElements_6.add(null);
		e_8.addNode((Node) null);
		childElements_7.add(e_9);
		e_9.addNode((Node) null);
		childElements_8.add(e_10);
		childElements_8.add(e_11);
		e_10.addNode((Node) null);
		e_10.addNode((Node) null);
		childElements_9.add(null);
		childElements_9.add(null);
		childElements_9.add(null);
		e_10.setChildElements(childElements_9);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.setLevel(920);
		int[][] interior_2 = new int[][] {};
		e_10.setInterior(interior_2);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.setChildElements(childElements_10);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.setLevel(5);
		int[][] interior_3 = new int[][] {};
		e_11.setInterior(interior_3);
		e_9.setChildElements(childElements_8);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.setLevel(-10);
		int[][] interior_4 = new int[][] {};
		e_9.setInterior(interior_4);
		e_8.setChildElements(childElements_7);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.setLevel(100);
		int[][] interior_5 = new int[][] {};
		e_8.setInterior(interior_5);
		e_6.setChildElements(childElements_6);
		e_6.addChildElement((HyperEdge) null);
		e_6.setLevel(1000);
		int[][] interior_6 = new int[][] {};
		e_6.setInterior(interior_6);
		e_5.setChildElements(childElements_5);
		e_5.addChildElement((HyperEdge) null);
		e_5.setLevel(858);
		int[][] interior_7 = new int[][] {};
		e_5.setInterior(interior_7);
		e_3.setChildElements(childElements_4);
		e_3.addChildElement((HyperEdge) null);
		e_3.addChildElement((HyperEdge) null);
		e_3.setLevel(2147483647);
		int[][] interior_8 = new int[][] {};
		e_3.setInterior(interior_8);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(-2147483648);
		int[][] interior_9 = new int[][] {};
		e.setInterior(interior_9);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(-1000);
		int[][] interior_10 = new int[][] {};
		edge.setInterior(interior_10);
		Node result = HyperGraphPainter.checkNodesForPoint(-2147483648, -1000,
				edge);
		// NullPointerException thrown, originator is arg 1 to <Method
		// hyperGraphs.ObjectHE.addNode(LhyperGraphs/Node;)V>
		// at editor.HyperGraphPainter.isPointInNode(HyperGraphPainter.java:295)
		// at
		// editor.HyperGraphPainter.isPointInNodesOfHE(HyperGraphPainter.java:157)
		// at
		// editor.HyperGraphPainter.checkNodesForPoint(HyperGraphPainter.java:140)
		// jtest_unverified
	}

	/**
	 * Test for method: checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckNodesForPoint2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		Node result = HyperGraphPainter.checkNodesForPoint(100, 1000, edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkNodesForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckNodesForPoint3() throws Throwable {
		Node result = HyperGraphPainter.checkNodesForPoint(5, -10,
				(ObjectHE) null);
		// NullPointerException thrown, originator is arg 3 to <Method
		// editor.HyperGraphPainter.checkNodesForPoint(IILhyperGraphs/ObjectHE;)LhyperGraphs/Node;>
		// at
		// editor.HyperGraphPainter.isPointInNodesOfHE(HyperGraphPainter.java:156)
		// at
		// editor.HyperGraphPainter.checkNodesForPoint(HyperGraphPainter.java:140)
		// jtest_unverified
	}

	/**
	 * Test for method: checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckObjectHEForPoint1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ObjectHE e_4 = new ObjectHE();
		ObjectHE e_5 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_6 = new ObjectHE();
		ArrayList childElements_5 = new ArrayList();
		ObjectHE e_7 = new ObjectHE();
		ArrayList childElements_6 = new ArrayList();
		ObjectHE e_8 = new ObjectHE();
		ObjectHE e_9 = new ObjectHE();
		ArrayList childElements_7 = new ArrayList();
		ObjectHE e_10 = new ObjectHE();
		ArrayList childElements_8 = new ArrayList();
		ObjectHE e_11 = new ObjectHE();
		ArrayList childElements_9 = new ArrayList();
		ObjectHE e_12 = new ObjectHE();
		ObjectHE e_13 = new ObjectHE();
		ObjectHE e_14 = new ObjectHE();
		edge.addNode((Node) null);
		childElements.add(e);
		childElements.add(null);
		childElements.add(e_14);
		childElements.add(null);
		e.addNode((Node) null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(e_3);
		childElements_2.add(e_4);
		childElements_2.add(e_5);
		childElements_2.add(null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(-10);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		childElements_4.add(e_6);
		childElements_4.add(null);
		childElements_4.add(e_13);
		childElements_4.add(null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		childElements_5.add(e_7);
		childElements_5.add(null);
		childElements_5.add(null);
		e_7.addNode((Node) null);
		e_7.addNode((Node) null);
		e_7.addNode((Node) null);
		e_7.addNode((Node) null);
		e_7.addNode((Node) null);
		e_7.addNode((Node) null);
		childElements_6.add(e_8);
		childElements_6.add(null);
		childElements_6.add(null);
		childElements_6.add(e_9);
		e_9.addNode((Node) null);
		childElements_7.add(e_10);
		e_10.addNode((Node) null);
		childElements_8.add(e_11);
		childElements_8.add(e_12);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		childElements_9.add(null);
		childElements_9.add(null);
		childElements_9.add(null);
		e_11.setChildElements(childElements_9);
		e_11.addChildElement((HyperEdge) null);
		e_11.setLevel(100);
		int[][] interior_2 = new int[][] {};
		e_11.setInterior(interior_2);
		e_10.setChildElements(childElements_8);
		e_10.addChildElement((HyperEdge) null);
		e_10.setLevel(1000);
		int[][] interior_3 = new int[][] {};
		e_10.setInterior(interior_3);
		e_9.setChildElements(childElements_7);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.setLevel(858);
		int[][] interior_4 = new int[][] {};
		e_9.setInterior(interior_4);
		e_7.setChildElements(childElements_6);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.setLevel(2147483647);
		int[][] interior_5 = new int[][] {};
		e_7.setInterior(interior_5);
		e_6.setChildElements(childElements_5);
		e_6.addChildElement((HyperEdge) null);
		e_6.addChildElement((HyperEdge) null);
		e_6.addChildElement((HyperEdge) null);
		e_6.addChildElement((HyperEdge) null);
		e_6.setLevel(-2147483648);
		int[][] interior_6 = new int[][] {};
		e_6.setInterior(interior_6);
		e_5.setChildElements(childElements_4);
		e_5.addChildElement((HyperEdge) null);
		e_5.addChildElement((HyperEdge) null);
		e_5.addChildElement((HyperEdge) null);
		e_5.addChildElement((HyperEdge) null);
		e_5.addChildElement((HyperEdge) null);
		e_5.setLevel(-1000);
		int[][] interior_7 = new int[][] {};
		e_5.setInterior(interior_7);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(256);
		int[][] interior_8 = new int[][] {};
		e.setInterior(interior_8);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(920);
		int[][] interior_9 = new int[][] {};
		edge.setInterior(interior_9);
		ObjectHE result = HyperGraphPainter.checkObjectHEForPoint(1, 5, edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		assertEquals("[null, null, null]", childElements_3.toString()); // jtest_unverified
		assertEquals("[null, null, null, null]", childElements_9.toString()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckObjectHEForPoint2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		edge.addNode((Node) null);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(256);
		int[][] interior = new int[][] {};
		edge.setInterior(interior);
		ObjectHE result = HyperGraphPainter.checkObjectHEForPoint(1, -1000,
				edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		assertEquals("[null]", childElements.toString()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckObjectHEForPoint3() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ObjectHE result = HyperGraphPainter
				.checkObjectHEForPoint(1, 1000, edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkObjectHEForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckObjectHEForPoint4() throws Throwable {
		ObjectHE result = HyperGraphPainter.checkObjectHEForPoint(5, -10,
				(ObjectHE) null);
		// NullPointerException thrown, originator is arg 3 to <Method
		// editor.HyperGraphPainter.checkObjectHEForPoint(IILhyperGraphs/ObjectHE;)LhyperGraphs/ObjectHE;>
		// at
		// editor.HyperGraphPainter.checkObjectHEForPoint(HyperGraphPainter.java:189)
		// jtest_unverified
	}

	/**
	 * Test for method: checkRelationsForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkRelationsForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckRelationsForPoint1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ObjectHE e_4 = new ObjectHE();
		ObjectHE e_5 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_6 = new ObjectHE();
		ArrayList childElements_5 = new ArrayList();
		ObjectHE e_7 = new ObjectHE();
		ArrayList childElements_6 = new ArrayList();
		ObjectHE e_8 = new ObjectHE();
		ObjectHE e_9 = new ObjectHE();
		ArrayList childElements_7 = new ArrayList();
		ObjectHE e_10 = new ObjectHE();
		ArrayList childElements_8 = new ArrayList();
		ObjectHE e_11 = new ObjectHE();
		ArrayList childElements_9 = new ArrayList();
		ObjectHE e_12 = new ObjectHE();
		ObjectHE e_13 = new ObjectHE();
		ArrayList childElements_10 = new ArrayList();
		edge.addNode((Node) null);
		childElements.add(e);
		childElements.add(null);
		childElements.add(null);
		childElements.add(e_5);
		e.addNode((Node) null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		childElements_2.add(e_4);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(920);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(5);
		int[][] interior_2 = new int[][] {};
		e.setInterior(interior_2);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		childElements_4.add(e_6);
		childElements_4.add(null);
		childElements_4.add(null);
		childElements_4.add(null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		childElements_5.add(e_7);
		childElements_5.add(null);
		childElements_5.add(null);
		e_7.addNode((Node) null);
		childElements_6.add(e_8);
		childElements_6.add(null);
		childElements_6.add(null);
		childElements_6.add(e_9);
		e_9.addNode((Node) null);
		childElements_7.add(e_10);
		e_10.addNode((Node) null);
		e_10.addNode((Node) null);
		childElements_8.add(e_11);
		childElements_8.add(null);
		e_11.addNode((Node) null);
		childElements_9.add(e_12);
		childElements_9.add(null);
		childElements_9.add(e_13);
		e_13.addNode((Node) null);
		e_13.setChildElements(childElements_10);
		e_13.addChildElement((HyperEdge) null);
		e_13.addChildElement((HyperEdge) null);
		e_13.addChildElement((HyperEdge) null);
		e_13.setLevel(-10);
		int[][] interior_3 = new int[][] {};
		e_13.setInterior(interior_3);
		e_11.setChildElements(childElements_9);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.setLevel(100);
		int[][] interior_4 = new int[][] {};
		e_11.setInterior(interior_4);
		e_10.setChildElements(childElements_8);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.setLevel(1000);
		int[][] interior_5 = new int[][] {};
		e_10.setInterior(interior_5);
		e_9.setChildElements(childElements_7);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.setLevel(858);
		int[][] interior_6 = new int[][] {};
		e_9.setInterior(interior_6);
		e_7.setChildElements(childElements_6);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.addChildElement((HyperEdge) null);
		e_7.setLevel(2147483647);
		int[][] interior_7 = new int[][] {};
		e_7.setInterior(interior_7);
		e_6.setChildElements(childElements_5);
		e_6.addChildElement((HyperEdge) null);
		e_6.setLevel(-2147483648);
		int[][] interior_8 = new int[][] {};
		e_6.setInterior(interior_8);
		e_5.setChildElements(childElements_4);
		e_5.addChildElement((HyperEdge) null);
		e_5.setLevel(-1000);
		int[][] interior_9 = new int[][] {};
		e_5.setInterior(interior_9);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(256);
		int[][] interior_10 = new int[][] {};
		edge.setInterior(interior_10);
		RelationHE result = HyperGraphPainter.checkRelationsForPoint(-1000,
				256, edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		assertEquals("[null, null, null]", childElements_3.toString()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: checkRelationsForPoint(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#checkRelationsForPoint(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testCheckRelationsForPoint3() throws Throwable {
		ObjectHE edge = new ObjectHE();
		RelationHE result = HyperGraphPainter.checkRelationsForPoint(5, -10,
				edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: dragObject(int,int,hyperGraphs.ObjectHE,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#dragObject(int,int,hyperGraphs.ObjectHE,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testDragObject1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		HyperGraphPainter.dragObject(1000, 858, edge, true);
		// ArithmeticException thrown
		// at editor.HyperGraphPainter.dragObject(HyperGraphPainter.java:77)
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * drawNodes(java.awt.Graphics2D,java.util.ArrayList,int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#drawNodes(java.awt.Graphics2D,java.util.ArrayList,int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testDrawNodes1() throws Throwable {
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.drawNodes(g2D, (ArrayList) null, 5, -10);
		// NullPointerException thrown, originator is arg 2 to <Method
		// editor.HyperGraphPainter.drawNodes(Ljava/awt/Graphics2D;Ljava/util/ArrayList;II)V>
		// at editor.HyperGraphPainter.drawNodes(HyperGraphPainter.java:502)
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * drawNodes(java.awt.Graphics2D,java.util.ArrayList,int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#drawNodes(java.awt.Graphics2D,java.util.ArrayList,int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testDrawNodes2() throws Throwable {
		ArrayList nodes = new ArrayList();
		Node e = new Node();
		nodes.add(e);
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.drawNodes(g2D, nodes, -2147483648, -1000);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawNodes(HyperGraphPainter.java:510)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testDrawNodes2.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsDrawNodes2(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillOval", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method: getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetAreaKind2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ObjectHE e_4 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_5 = new ObjectHE();
		ObjectHE e_6 = new ObjectHE();
		RelationHE egde = new RelationHE();
		edge.addNode((Node) null);
		childElements.add(null);
		childElements.add(e);
		childElements.add(null);
		childElements.add(null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		childElements_2.add(e_4);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(256);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		childElements_4.add(e_5);
		childElements_4.add(null);
		childElements_4.add(e_6);
		childElements_4.add(null);
		e_4.setChildElements(childElements_4);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.setLevel(920);
		int[][] interior_2 = new int[][] {};
		e_4.setInterior(interior_2);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(5);
		int[][] interior_3 = new int[][] {};
		e.setInterior(interior_3);
		edge.setChildElements(childElements);
		edge.addChildElement(egde);
		edge.setLevel(-10);
		int[][] interior_4 = new int[][] {};
		edge.setInterior(interior_4);
		int result = HyperGraphPainter.getAreaKind(-2147483648, -1000, edge);
		// RuntimeException thrown
		// at
		// editor.HyperGraphPainter.isPointInRelationHE(HyperGraphPainter.java:210)
		// at
		// editor.HyperGraphPainter.checkRelationsForPoint(HyperGraphPainter.java:170)
		// at editor.HyperGraphPainter.getAreaKind(HyperGraphPainter.java:28)
		// jtest_unverified
	}

	/**
	 * Test for method: getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetAreaKind3() throws Throwable {
		ObjectHE edge = new ObjectHE();
		int result = HyperGraphPainter.getAreaKind(-1, 2147483647, edge);
		assertEquals(0, result); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetAreaKind4() throws Throwable {
		ObjectHE edge = new ObjectHE();
		int result = HyperGraphPainter.getAreaKind(0, 0, edge);
		assertEquals(4, result); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getAreaKind(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetAreaKind5() throws Throwable {
		int result = HyperGraphPainter.getAreaKind(5, -10, (ObjectHE) null);
		// NullPointerException thrown, originator is arg 3 to <Method
		// editor.HyperGraphPainter.getAreaKind(IILhyperGraphs/ObjectHE;)I>
		// at
		// editor.HyperGraphPainter.checkRelationsForPoint(HyperGraphPainter.java:167)
		// at editor.HyperGraphPainter.getAreaKind(HyperGraphPainter.java:28)
		// jtest_unverified
	}

	/**
	 * Test for method: getObjAt(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getObjAt(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetObjAt1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ObjectHE e_4 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_5 = new ObjectHE();
		ObjectHE e_6 = new ObjectHE();
		RelationHE egde = new RelationHE();
		edge.addNode((Node) null);
		childElements.add(e);
		childElements.add(null);
		childElements.add(e_4);
		childElements.add(null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(-2147483648);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(-1000);
		int[][] interior_2 = new int[][] {};
		e.setInterior(interior_2);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		childElements_4.add(e_5);
		childElements_4.add(null);
		childElements_4.add(e_6);
		childElements_4.add(null);
		e_4.setChildElements(childElements_4);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.setLevel(256);
		int[][] interior_3 = new int[][] {};
		e_4.setInterior(interior_3);
		edge.setChildElements(childElements);
		edge.addChildElement(egde);
		edge.setLevel(920);
		int[][] interior_4 = new int[][] {};
		edge.setInterior(interior_4);
		MovableComponent result = HyperGraphPainter.getObjAt(858, 2147483647,
				edge);
		// RuntimeException thrown
		// at
		// editor.HyperGraphPainter.isPointInRelationHE(HyperGraphPainter.java:210)
		// at
		// editor.HyperGraphPainter.checkRelationsForPoint(HyperGraphPainter.java:170)
		// at editor.HyperGraphPainter.getObjAt(HyperGraphPainter.java:52)
		// jtest_unverified
	}

	/**
	 * Test for method: getObjAt(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#getObjAt(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testGetObjAt2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		MovableComponent result = HyperGraphPainter.getObjAt(0, -1, edge);
		assertEquals(null, result); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: isSelected()
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#isSelected()
	 * @author Parasoft Jtest 9.2
	 */
	public void testIsSelected1() throws Throwable {
		boolean result = HyperGraphPainter.isSelected();
		assertEquals(false, result); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * paintChildElements(java.awt.Graphics2D,hyperGraphs.ObjectHE
	 * ,boolean,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintChildElements(java.awt.Graphics2D,hyperGraphs.ObjectHE,boolean,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintChildElements1() throws Throwable {
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter
				.paintChildElements(g2D, (ObjectHE) null, false, false);
		// NullPointerException thrown, originator is arg 2 to <Method
		// editor.HyperGraphPainter.paintChildElements(Ljava/awt/Graphics2D;LhyperGraphs/ObjectHE;ZZ)V>
		// at
		// editor.HyperGraphPainter.paintChildElements(HyperGraphPainter.java:320)
		// jtest_unverified
	}

	/**
	 * Test for method: paintGraph(hyperGraphs.ObjectHE,java.awt.Graphics2D)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintGraph(hyperGraphs.ObjectHE,java.awt.Graphics2D)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintGraph1() throws Throwable {
		ObjectHE rootEdge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_4 = new ObjectHE();
		ObjectHE e_5 = new ObjectHE();
		ArrayList childElements_5 = new ArrayList();
		ObjectHE e_6 = new ObjectHE();
		ArrayList childElements_6 = new ArrayList();
		ObjectHE e_7 = new ObjectHE();
		ObjectHE e_8 = new ObjectHE();
		ArrayList childElements_7 = new ArrayList();
		ObjectHE e_9 = new ObjectHE();
		ArrayList childElements_8 = new ArrayList();
		ObjectHE e_10 = new ObjectHE();
		ArrayList childElements_9 = new ArrayList();
		ObjectHE e_11 = new ObjectHE();
		ArrayList childElements_10 = new ArrayList();
		ObjectHE e_12 = new ObjectHE();
		ObjectHE e_13 = new ObjectHE();
		ObjectHE e_14 = new ObjectHE();
		ObjectHE e_15 = new ObjectHE();
		ArrayList childElements_11 = new ArrayList();
		ObjectHE e_16 = new ObjectHE();
		ObjectHE e_17 = new ObjectHE();
		Node n = new Node();
		rootEdge.addNode(n);
		childElements.add(e);
		childElements.add(e_14);
		childElements.add(null);
		childElements.add(e_15);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		childElements_2.add(null);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(5);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		childElements_4.add(e_4);
		childElements_4.add(null);
		childElements_4.add(e_5);
		childElements_4.add(null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		childElements_5.add(e_6);
		childElements_5.add(null);
		childElements_5.add(e_13);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		childElements_6.add(e_7);
		childElements_6.add(null);
		childElements_6.add(e_8);
		childElements_6.add(e_12);
		e_8.addNode((Node) null);
		childElements_7.add(e_9);
		e_9.addNode((Node) null);
		childElements_8.add(e_10);
		childElements_8.add(e_11);
		e_10.addNode((Node) null);
		e_10.addNode((Node) null);
		childElements_9.add(null);
		childElements_9.add(null);
		childElements_9.add(null);
		e_10.setChildElements(childElements_9);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.setLevel(-10);
		int[][] interior_2 = new int[][] {};
		e_10.setInterior(interior_2);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.setChildElements(childElements_10);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.setLevel(100);
		int[][] interior_3 = new int[][] {};
		e_11.setInterior(interior_3);
		e_9.setChildElements(childElements_8);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.setLevel(1000);
		int[][] interior_4 = new int[][] {};
		e_9.setInterior(interior_4);
		e_8.setChildElements(childElements_7);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.setLevel(858);
		int[][] interior_5 = new int[][] {};
		e_8.setInterior(interior_5);
		e_6.setChildElements(childElements_6);
		e_6.addChildElement((HyperEdge) null);
		e_6.setLevel(2147483647);
		int[][] interior_6 = new int[][] {};
		e_6.setInterior(interior_6);
		e_5.setChildElements(childElements_5);
		e_5.addChildElement((HyperEdge) null);
		e_5.setLevel(-2147483648);
		int[][] interior_7 = new int[][] {};
		e_5.setInterior(interior_7);
		e_3.setChildElements(childElements_4);
		e_3.addChildElement((HyperEdge) null);
		e_3.addChildElement((HyperEdge) null);
		e_3.setLevel(-1000);
		int[][] interior_8 = new int[][] {};
		e_3.setInterior(interior_8);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(256);
		int[][] interior_9 = new int[][] {};
		e.setInterior(interior_9);
		e_15.addNode((Node) null);
		e_15.addNode((Node) null);
		e_15.addNode((Node) null);
		e_15.addNode((Node) null);
		childElements_11.add(e_16);
		childElements_11.add(null);
		childElements_11.add(e_17);
		childElements_11.add(null);
		e_15.setChildElements(childElements_11);
		e_15.addChildElement((HyperEdge) null);
		e_15.addChildElement((HyperEdge) null);
		e_15.addChildElement((HyperEdge) null);
		e_15.addChildElement((HyperEdge) null);
		e_15.addChildElement((HyperEdge) null);
		e_15.setLevel(920);
		int[][] interior_10 = new int[][] {};
		e_15.setInterior(interior_10);
		rootEdge.setChildElements(childElements);
		rootEdge.addChildElement((HyperEdge) null);
		rootEdge.setLevel(1);
		int[][] interior_11 = new int[][] {};
		rootEdge.setInterior(interior_11);
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintGraph(rootEdge, g2D);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawNodes(HyperGraphPainter.java:510)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:463)
		// at editor.HyperGraphPainter.paintGraph(HyperGraphPainter.java:560)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintGraph1.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintGraph1(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillOval", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method: paintGraph(hyperGraphs.ObjectHE,java.awt.Graphics2D)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintGraph(hyperGraphs.ObjectHE,java.awt.Graphics2D)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintGraph2() throws Throwable {
		ObjectHE rootEdge = new ObjectHE();
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintGraph(rootEdge, g2D);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawString(HyperGraphPainter.java:495)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:488)
		// at editor.HyperGraphPainter.paintGraph(HyperGraphPainter.java:560)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintGraph2.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintGraph2(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "drawRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getFont", argument_types)) {
				return null;
			}
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Font.class };
			if (Stubs.matches(method, "setFont", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method:
	 * paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintHyperEdge1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ObjectHE e_4 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_5 = new ObjectHE();
		edge.addNode((Node) null);
		childElements.add(e);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(e_3);
		childElements_2.add(e_4);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(5);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		e_4.addNode((Node) null);
		childElements_4.add(e_5);
		e_4.setChildElements(childElements_4);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.addChildElement((HyperEdge) null);
		e_4.setLevel(-10);
		int[][] interior_2 = new int[][] {};
		e_4.setInterior(interior_2);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(100);
		int[][] interior_3 = new int[][] {};
		e.setInterior(interior_3);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(1000);
		int[][] interior_4 = new int[][] {};
		edge.setInterior(interior_4);
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintHyperEdge(g2D, edge, false, false);
		// NullPointerException thrown, originator is arg 1 to <Method
		// hyperGraphs.ObjectHE.addChildElement(LhyperGraphs/HyperEdge;)V>
		// at
		// editor.HyperGraphPainter.paintChildElements(HyperGraphPainter.java:323)
		// at
		// editor.HyperGraphPainter.paintHyperEdge(HyperGraphPainter.java:308)
		// at
		// editor.HyperGraphPainter.paintChildElements(HyperGraphPainter.java:326)
		// at
		// editor.HyperGraphPainter.paintHyperEdge(HyperGraphPainter.java:308)
		// at
		// editor.HyperGraphPainter.paintChildElements(HyperGraphPainter.java:326)
		// at
		// editor.HyperGraphPainter.paintHyperEdge(HyperGraphPainter.java:308)
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintHyperEdge2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintHyperEdge(g2D, edge, false, false);
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintHyperEdge3() throws Throwable {
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintHyperEdge(g2D, (HyperEdge) null, false, false);
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method:
	 * paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintHyperEdge(java.awt.Graphics2D,hyperGraphs.HyperEdge,boolean,boolean)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintHyperEdge4() throws Throwable {
		ObjectHE edge = new ObjectHE();
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintHyperEdge(g2D, edge, true, false);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawString(HyperGraphPainter.java:495)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:488)
		// at
		// editor.HyperGraphPainter.paintHyperEdge(HyperGraphPainter.java:306)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintHyperEdge4.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintHyperEdge4(Member method, Object _this,
			Object[] args) throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "drawRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getFont", argument_types)) {
				return null;
			}
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Font.class };
			if (Stubs.matches(method, "setFont", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method:
	 * paintHyperRelationHE(java.awt.Graphics2D,hyperGraphs.HyperRelation)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintHyperRelationHE(java.awt.Graphics2D,hyperGraphs.HyperRelation)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintHyperRelationHE1() throws Throwable {
		Graphics2D g2d = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintHyperRelationHE(g2d, (HyperRelation) null);
		// NullPointerException thrown, originator is arg 2 to <Method
		// editor.HyperGraphPainter.paintHyperRelationHE(Ljava/awt/Graphics2D;LhyperGraphs/HyperRelation;)V>
		// at
		// editor.HyperGraphPainter.paintHyperRelationHE(HyperGraphPainter.java:386)
		// jtest_unverified
	}

	/**
	 * Test for method: paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintObjectHE1() throws Throwable {
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintObjectHE(g2D, (ObjectHE) null);
		// NullPointerException thrown, originator is arg 2 to <Method
		// editor.HyperGraphPainter.paintObjectHE(Ljava/awt/Graphics2D;LhyperGraphs/ObjectHE;)V>
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:453)
		// jtest_unverified
	}

	/**
	 * Test for method: paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintObjectHE2() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		Node n = new Node();
		edge.addNode(n);
		childElements.add(null);
		childElements.add(null);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(1);
		int[][] interior = new int[][] {};
		edge.setInterior(interior);
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintObjectHE(g2D, edge);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawNodes(HyperGraphPainter.java:510)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:463)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintObjectHE2.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintObjectHE2(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillOval", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method: paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintObjectHE4() throws Throwable {
		ObjectHE edge = new ObjectHE();
		ArrayList childElements = new ArrayList();
		ObjectHE e = new ObjectHE();
		ArrayList childElements_2 = new ArrayList();
		ObjectHE e_2 = new ObjectHE();
		ArrayList childElements_3 = new ArrayList();
		ObjectHE e_3 = new ObjectHE();
		ArrayList childElements_4 = new ArrayList();
		ObjectHE e_4 = new ObjectHE();
		ObjectHE e_5 = new ObjectHE();
		ArrayList childElements_5 = new ArrayList();
		ObjectHE e_6 = new ObjectHE();
		ArrayList childElements_6 = new ArrayList();
		ObjectHE e_7 = new ObjectHE();
		ObjectHE e_8 = new ObjectHE();
		ArrayList childElements_7 = new ArrayList();
		ObjectHE e_9 = new ObjectHE();
		ArrayList childElements_8 = new ArrayList();
		ObjectHE e_10 = new ObjectHE();
		ArrayList childElements_9 = new ArrayList();
		ObjectHE e_11 = new ObjectHE();
		ArrayList childElements_10 = new ArrayList();
		ObjectHE e_12 = new ObjectHE();
		ObjectHE e_13 = new ObjectHE();
		ObjectHE e_14 = new ObjectHE();
		edge.addNode((Node) null);
		childElements.add(null);
		childElements.add(null);
		childElements.add(e);
		childElements.add(null);
		e.addNode((Node) null);
		childElements_2.add(e_2);
		childElements_2.add(null);
		childElements_2.add(e_3);
		childElements_2.add(null);
		childElements_2.add(e_14);
		e_2.addNode((Node) null);
		e_2.addNode((Node) null);
		e_2.setChildElements(childElements_3);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.addChildElement((HyperEdge) null);
		e_2.setLevel(5);
		int[][] interior = new int[][] {};
		e_2.setInterior(interior);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		e_3.addNode((Node) null);
		childElements_4.add(e_4);
		childElements_4.add(e_5);
		childElements_4.add(e_13);
		childElements_4.add(null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		e_5.addNode((Node) null);
		childElements_5.add(e_6);
		childElements_5.add(null);
		childElements_5.add(null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		e_6.addNode((Node) null);
		childElements_6.add(e_7);
		childElements_6.add(null);
		childElements_6.add(e_8);
		childElements_6.add(e_12);
		e_8.addNode((Node) null);
		childElements_7.add(e_9);
		e_9.addNode((Node) null);
		childElements_8.add(e_10);
		childElements_8.add(e_11);
		e_10.addNode((Node) null);
		e_10.addNode((Node) null);
		childElements_9.add(null);
		childElements_9.add(null);
		childElements_9.add(null);
		e_10.setChildElements(childElements_9);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.addChildElement((HyperEdge) null);
		e_10.setLevel(-10);
		int[][] interior_2 = new int[][] {};
		e_10.setInterior(interior_2);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.addNode((Node) null);
		e_11.setChildElements(childElements_10);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.addChildElement((HyperEdge) null);
		e_11.setLevel(100);
		int[][] interior_3 = new int[][] {};
		e_11.setInterior(interior_3);
		e_9.setChildElements(childElements_8);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.addChildElement((HyperEdge) null);
		e_9.setLevel(1000);
		int[][] interior_4 = new int[][] {};
		e_9.setInterior(interior_4);
		e_8.setChildElements(childElements_7);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.addChildElement((HyperEdge) null);
		e_8.setLevel(858);
		int[][] interior_5 = new int[][] {};
		e_8.setInterior(interior_5);
		e_6.setChildElements(childElements_6);
		e_6.addChildElement((HyperEdge) null);
		e_6.setLevel(2147483647);
		int[][] interior_6 = new int[][] {};
		e_6.setInterior(interior_6);
		e_5.setChildElements(childElements_5);
		e_5.addChildElement((HyperEdge) null);
		e_5.setLevel(-2147483648);
		int[][] interior_7 = new int[][] {};
		e_5.setInterior(interior_7);
		e_3.setChildElements(childElements_4);
		e_3.addChildElement((HyperEdge) null);
		e_3.addChildElement((HyperEdge) null);
		e_3.setLevel(-1000);
		int[][] interior_8 = new int[][] {};
		e_3.setInterior(interior_8);
		e.setChildElements(childElements_2);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.addChildElement((HyperEdge) null);
		e.setLevel(256);
		int[][] interior_9 = new int[][] {};
		e.setInterior(interior_9);
		edge.setChildElements(childElements);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.addChildElement((HyperEdge) null);
		edge.setLevel(1);
		int[][] interior_10 = new int[][] {};
		edge.setInterior(interior_10);
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintObjectHE(g2D, edge);
		// NullPointerException thrown, originator is arg 1 to <Method
		// hyperGraphs.ObjectHE.addNode(LhyperGraphs/Node;)V>
		// at editor.HyperGraphPainter.drawNodes(HyperGraphPainter.java:503)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:463)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintObjectHE4.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintObjectHE4(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method: paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintObjectHE(java.awt.Graphics2D,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintObjectHE5() throws Throwable {
		ObjectHE edge = new ObjectHE();
		Graphics2D g2D = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintObjectHE(g2D, edge);
		// NullPointerException thrown
		// at editor.HyperGraphPainter.drawString(HyperGraphPainter.java:495)
		// at editor.HyperGraphPainter.paintObjectHE(HyperGraphPainter.java:488)
		// jtest_unverified
	}

	/**
	 * Specifies the stubs to be used when running testPaintObjectHE5.
	 * 
	 * @param method
	 *            The method or constructor to be called
	 * @param _this
	 *            The instance object corresponding to the method or
	 *            <code>null</code> for static methods
	 * @param args
	 *            The arguments passed to the method
	 * @return The stub return value to be used or
	 *         <code>Stubs.NO_STUB_GENERATED</code> to specify that the method
	 *         call should not be stubbed.
	 * @throws Throwable
	 *             Stubs may throw any Throwable
	 * @author Parasoft Jtest 9.2
	 */
	public Object stubsPaintObjectHE5(Member method, Object _this, Object[] args)
			throws Throwable {
		Class[] argument_types;
		if (Stubs.matches(method, Graphics.class)) {
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "drawRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Integer.TYPE, Integer.TYPE,
					Integer.TYPE, Integer.TYPE };
			if (Stubs.matches(method, "fillRect", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] {};
			if (Stubs.matches(method, "getFont", argument_types)) {
				return null;
			}
			argument_types = new Class[] { Color.class };
			if (Stubs.matches(method, "setColor", argument_types)) {
				return Stubs.VOID;
			}
			argument_types = new Class[] { Font.class };
			if (Stubs.matches(method, "setFont", argument_types)) {
				return Stubs.VOID;
			}
		}
		return Stubs.NO_STUB_GENERATED;
	}

	/**
	 * Test for method:
	 * paintRelationHE(java.awt.Graphics2D,hyperGraphs.RelationHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#paintRelationHE(java.awt.Graphics2D,hyperGraphs.RelationHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testPaintRelationHE1() throws Throwable {
		Graphics2D g2d = (Graphics2D) Stubs.makeStubObject(Graphics2D.class);
		HyperGraphPainter.paintRelationHE(g2d, (RelationHE) null);
		// NullPointerException thrown, originator is arg 2 to <Method
		// editor.HyperGraphPainter.paintRelationHE(Ljava/awt/Graphics2D;LhyperGraphs/RelationHE;)V>
		// at
		// editor.HyperGraphPainter.paintRelationHE(HyperGraphPainter.java:331)
		// jtest_unverified
	}

	/**
	 * Test for method: releaseObject(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#releaseObject(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testReleaseObject1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		HyperGraphPainter.releaseObject(100, 1000, edge);
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: selectObject(int,int,hyperGraphs.ObjectHE)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see HyperGraphPainter#selectObject(int,int,hyperGraphs.ObjectHE)
	 * @author Parasoft Jtest 9.2
	 */
	public void testSelectObject1() throws Throwable {
		ObjectHE edge = new ObjectHE();
		HyperGraphPainter.selectObject(0, 1, edge);
		assertEquals(false, HyperGraphPainter.isSelected()); // jtest_unverified
		assertEquals(null, HyperGraphPainter.selected); // jtest_unverified
		assertEquals(false, HyperGraphPainter.debug_mode); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Used to set up the test. This method is called by JUnit before each of
	 * the tests are executed.
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @author Parasoft Jtest 9.2
	 */
	public void setUp() throws Exception {
		super.setUp();
		/*
		 * Add any necessary initialization code here (e.g., open a socket).
		 * Call Repository.putTemporary() to provide initialized instances of
		 * objects to be used when testing.
		 */
		// jtest.Repository.putTemporary("name", object);
	}

	/**
	 * Used to clean up after the test. This method is called by JUnit after
	 * each of the tests have been completed.
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @author Parasoft Jtest 9.2
	 */
	public void tearDown() throws Exception {
		try {
			/*
			 * Add any necessary cleanup code here (e.g., close a socket).
			 */
		} finally {
			super.tearDown();
		}
	}

	/**
	 * Utility main method. Runs the test cases defined in this test class.
	 * 
	 * Usage: java HyperGraphPainterTest
	 * 
	 * @param args
	 *            command line arguments are not needed
	 * @author Parasoft Jtest 9.2
	 */
	public static void main(String[] args) {
		// junit.textui.TestRunner will print the test results to stdout.

		junit.textui.TestRunner.run(suite());
	}

	/**
	 * Create a test suite for running the tests in this class.
	 * IndependentTestSuite will run each test case in a separate classloader.
	 * 
	 * @return a test suite to run all of the tests in this class
	 * @author Parasoft Jtest 9.2
	 */
	public static junit.framework.Test suite() {
		return new jtest.IndependentTestSuite(
		// this class
				HyperGraphPainterTest.class,
				// fully qualified name of the tested class
				"editor.HyperGraphPainter",
				// timeout for each test in milliseconds
				30000);
	}

	/**
	 * Get the class object of the class which will be tested.
	 * 
	 * @return the class which will be tested
	 * @author Parasoft Jtest 9.2
	 */
	public Class getTestedClass() {
		return HyperGraphPainter.class;
	}

}
// JTEST_CURRENT_ID=-1165687163.