/*
 * ProjectTestCase.java
 * Created by Jtest on 5/1/13 1:16:16 PM.
 */

package jtest;

import junit.framework.TestCase;

/**
 * ProjectTestCase is an abstract unit test class for unit test classes in
 * project 'HSSDR-trunk.jtest'.
 * @author Parasoft Jtest 9.2
 */
public abstract class ProjectTestCase extends TestCase {

	/**
	 * Constructs a test case for the test specified by the name argument.
	 * @param name the name of the test case
	 * @author Parasoft Jtest 9.2
	 */
	public ProjectTestCase(String name) {
		super(name);
		/*
		 * This constructor should not be modified.  Any initialization code
		 * should be placed in the setUp() method instead.
		 */
	}

	/**
	 * Used to set up the test. This method is called by JUnit before each of
	 * the tests are executed.
	 * @see junit.framework.TestCase#setUp()
	 * @author Parasoft Jtest 9.2
	 */
	public void setUp() throws Exception {
		super.setUp();
		projectSetUp(this);
	}

	/**
	 * Used to set up any tests for this project. This method is called before each
	 * JUnit or other type of test is executed.
	 * @param test an instance object representing the current test
	 * @author Parasoft Jtest 9.2
	 */
	public static void projectSetUp(Object test) throws Exception {
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
	 * @see junit.framework.TestCase#tearDown()
	 * @author Parasoft Jtest 9.2
	 */
	public void tearDown() throws Exception {
		super.tearDown();
		projectTearDown(this);
	}

	/**
	 * Used to clean up any tests for this project. This method is called after each
	 * JUnit or other type of test is executed.
	 * @param test an instance object representing the current test
	 * @author Parasoft Jtest 9.2
	 */
	public static void projectTearDown(Object test) throws Exception {
		/*
		 * Add any necessary cleanup code here (e.g., close a socket).
		 */
	}

	/**
	 * Get the class object of the class which will be tested.
	 * @return the class which will be tested
	 * @author Parasoft Jtest 9.2
	 */
	public abstract Class getTestedClass();

}
