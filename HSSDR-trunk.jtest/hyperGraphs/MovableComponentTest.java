/*
 * MovableComponentTest.java
 * Created by Jtest on 5/1/13 1:16:16 PM.
 */

package hyperGraphs;

import hyperGraphs.MovableComponent;

/**
 * MovableComponentTest is a unit test class for class MovableComponent
 * 
 * @see hyperGraphs.MovableComponent
 * @author Parasoft Jtest 9.2
 */
public class MovableComponentTest extends PackageTestCase {
	/**
	 * Constructs a test case for the test specified by the name argument.
	 * 
	 * @param name
	 *            the name of the test case
	 * @author Parasoft Jtest 9.2
	 */
	public MovableComponentTest(String name) {
		super(name);
		/*
		 * This constructor should not be modified. Any initialization code
		 * should be placed in the setUp() method instead.
		 */
	}

	/**
	 * Test for method: MovableComponent()
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#MovableComponent()
	 * @author Parasoft Jtest 9.2
	 */
	public void testMovableComponent1() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.setSizeX(858);
		testedObject.setSizeY(2147483647);
		testedObject.setMiddleX(-2147483648);
		testedObject.setMiddleY(-1000);
		assertEquals(-1000, testedObject.getMiddleY()); // jtest_unverified
		assertEquals(858, testedObject.getSizeX()); // jtest_unverified
		assertEquals(-2147483648, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(2147483647, testedObject.getSizeY()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: MovableComponent()
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#MovableComponent()
	 * @author Parasoft Jtest 9.2
	 */
	public void testMovableComponent2() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.setSizeX(5);
		testedObject.setSizeY(-10);
		testedObject.setMiddleX(100);
		testedObject.setMiddleY(1000);
		assertEquals(1000, testedObject.getMiddleY()); // jtest_unverified
		assertEquals(100, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(-10, testedObject.getSizeY()); // jtest_unverified
		assertEquals(5, testedObject.getSizeX()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: MovableComponent()
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#MovableComponent()
	 * @author Parasoft Jtest 9.2
	 */
	public void testMovableComponent3() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		assertEquals(0, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(0, testedObject.getSizeY()); // jtest_unverified
		assertEquals(0, testedObject.getSizeX()); // jtest_unverified
		assertEquals(0, testedObject.getMiddleY()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: moveTo(int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#moveTo(int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testMoveTo1() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.moveTo(256, 920);
		assertEquals(920, testedObject.getMiddleY()); // jtest_unverified
		assertEquals(256, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(0, testedObject.getSizeX()); // jtest_unverified
		assertEquals(0, testedObject.getSizeY()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: moveTo(int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#moveTo(int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testMoveTo2() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.moveTo(5, -10);
		assertEquals(-10, testedObject.getMiddleY()); // jtest_unverified
		assertEquals(0, testedObject.getSizeY()); // jtest_unverified
		assertEquals(0, testedObject.getSizeX()); // jtest_unverified
		assertEquals(5, testedObject.getMiddleX()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: translateBy(int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#translateBy(int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testTranslateBy1() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.translateBy(256, 920);
		assertEquals(0, testedObject.getSizeX()); // jtest_unverified
		assertEquals(0, testedObject.getSizeY()); // jtest_unverified
		assertEquals(256, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(920, testedObject.getMiddleY()); // jtest_unverified
		// No exception thrown
		// jtest_unverified
	}

	/**
	 * Test for method: translateBy(int,int)
	 * 
	 * @throws Throwable
	 *             Tests may throw any Throwable
	 * @see MovableComponent#translateBy(int,int)
	 * @author Parasoft Jtest 9.2
	 */
	public void testTranslateBy2() throws Throwable {
		MovableComponent testedObject = new MovableComponent();
		testedObject.translateBy(5, -10);
		assertEquals(0, testedObject.getSizeX()); // jtest_unverified
		assertEquals(0, testedObject.getSizeY()); // jtest_unverified
		assertEquals(5, testedObject.getMiddleX()); // jtest_unverified
		assertEquals(-10, testedObject.getMiddleY()); // jtest_unverified
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
	 * Usage: java MovableComponentTest
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
				MovableComponentTest.class,
				// fully qualified name of the tested class
				"hyperGraphs.MovableComponent",
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
		return MovableComponent.class;
	}

}
// JTEST_CURRENT_ID=-1469106828.