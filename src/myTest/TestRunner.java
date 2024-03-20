package myTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.*;

/**
 * @author Barison Hajjaj (Matricola: 2014481)
 * <br> <br>
 * Main class to execute {@link TestListAdapter} and {@link TestListAdapterIterator}
 */
public class TestRunner {
	
	/**
	 * Counter of the total tests.
	 */
	private static int testCounter = 0;
	
	/**
	 * This main invoke all the test contained in {@link TestListAdapter} and {@link TestListAdapterIterator}
	 * 
	 * @param args - not used
	 */
	public static void main(String[] args) {
		System.out.println("Test in esecuzione...");
		System.out.println();
		System.out.println();
		Result res = JUnitCore.runClasses(myTest.TestListAdapter.class, myTest.TestListAdapterIterator.class);
		testResult(res);
	}
	
	/**
	 * Private method that print the total result of the tests, in particular: the number of tests, how many successful tests and how many failed tests.
	 * 
	 * @param res - results returned from JUnit.runClasses 
	 */
    private static void testResult(Result res)
    {
    	testCounter += res.getRunCount();
    	
        System.out.println("*** All the tests have been completed ***");
        System.out.println("Number of tests runned: " + testCounter);
        System.out.println("Successful tests: "+ (testCounter - res.getFailureCount()) + "/" + testCounter);
        System.out.println("Failed tests: "+ res.getFailureCount() + "/" + testCounter);
        if (!res.wasSuccessful()) {
            List<Failure> fails = res.getFailures();
            for (Failure fail : fails) {
                System.out.println(fail.toString());
            }
        }
        testCounter = 0;
    }
}

