package myTest;

//JUnit imports
import org.junit.*;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

//import org.junit.Assert.assertEquals;

//package import
import myAdapter.*;

/**
 * 
 * @author Barison Hajjaj (Matricola: 2014481)
 * <br> <br>
 * <b> Summary: </b> The TestListAdapterIterator class deals with testing the functionalities of all the ListAdapterIterator class methods.
 * <br>
 * <br>
 * <b> Test Suite Design: </b> This class test the multiple methods of ListAdapterIterator starting from a list of elements. The list contains some programming languages.
 * At the end of each method, the @After clause will invoke the associated method that will clear the starting list.
 * Then, at the start of each method, the list is recreated thanks to the @Before clause actions.
 * <br>
 * <br>
 * <b> Pre-Condition: </b> not present 
 * <br>
 * <br>
 * <b> Post-Condition: </b> not present
 * <br>
 * <br>
 * <b> Test Cases: </b> 
 * <br>
 * {@link #defaultConstructorTest()}
 * <br>
 * {@link #constructorTest()}
 * <br>
 * {@link #hasNextTest()}
 * <br>
 * {@link #nextTest()}
 * <br>
 * {@link #hasPreviousTest()}
 * <br>
 * {@link #previousTest()}
 * <br>
 * {@link #nextIndexTest()}
 * <br>
 * {@link #previousIndexTest()}
 * <br>
 * {@link #removeTest()}
 * <br>
 * {@link #setTest()}
 * <br>
 * {@link #addTest()}
 * <br>
 * <br>
 * <b> Test Suite Execution records: </b>
 * <br>
 * JVM from J2ME CLDC 1.1
 * <br>
 * JUnit 4.13.2
 * <br>
 * Hamcrest 1.3
 * <br>
 * <br>
 * <b> Execution variables: </b> 
 * <br>
 * HList pList - main ListAdapter on which all methods are tested
 * <br>
 * HList empty - empty ListAdapter which is used to test pList.
 * <br>
 * HListIterator iter - main ListAdapter iterator
 * <br>
 * HListIterator iter - listIterator associated to pList
 * <br>
 * String[] programmingLanguages - array used to initialized pList before every methods; sometimes it is also used for comparisons 
 *   
 * 
*/
public class TestListAdapterIterator {
	
	HList pList = new ListAdapter();;
	HList emptyList = new ListAdapter();;
	HListIterator iter;
	String[] programmingLanguages = {"Java", "Python", "C", "Ruby", "Go"};
	
	/**
	 *When writing tests, it is common to find that several tests need similar objects created before they can run. 
	 *Annotating a public void method with @Before causes that method to be run before the Test method. 
	 *The @Before methods of superclasses will be run before those of the current class, unless they are overridden in the current class. No other ordering is defined.
	 *
	 */
	@Before
	public void setUp() {
		iter = pList.listIterator();
		for(String pL : programmingLanguages) {
			pList.add(pL);
		}
		iter = pList.listIterator();	
	}
	
	
	/**
	 * If you allocate external resources in a Before method you need to release them after the test runs. 
	 * Annotating a public void method with @After causes that method to be run after the Test method. 
	 * All @After methods are guaranteed to run even if a Before or Test method throws an exception. 
	 * The @After methods declared in superclasses will be run after those of the current class, unless they are overridden in the current class.
	 */
	@After
	public void tearDown() {
		pList.clear();
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the default constructor of a ListAdapterIterator checking if the iterator start from the start
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the default constructor with a non empty list and then with an empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in the non empty list and the iterator in the empty list, checking if they have next or previous elements. 
	 * Before that, it is necessary to ascertain the contents of the two lists in their size and their emptiness or non emptiness.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list.  
	 * Methods: next(), previous(), hasNext(), hasPrevious(), nextIndex(), previousIndex() are working correctly.
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * The position of the iterator when created must be where indicated by the index parameter. 
	 * The iterator can be in a different position from the starting one  
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator can go trough the non-empty list with the use of next() or previous() methods as it has elements in the associated list. 
	 * The constructor generate correctly the iterator at the start of the list
	 */
	@Test
	public void defaultConstructorTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Iterator of a Non-Empty List
		HList trial = new ListAdapter(pList);
		HListIterator trialIter = trial.listIterator();
		assertTrue("Trial list size is not zero", trial.size() != 0);
		assertFalse("Trial list is not empty", trial.isEmpty());
		assertTrue("The iterator is placed at the start of a non empty list, therefore the iterator must have a next element", trialIter.hasNext()); 
		assertFalse("The iterator is placed at the start of a non empty list, therefore the iterator doesn't have a previous element ", trialIter.hasPrevious());
		assertEquals("If the iterator is placed at the start of Trial, the first element should be the first element of the array programming languages", programmingLanguages[0], trialIter.next());
		
		// Iterator of an Empty List
		HList trialEmpty = new ListAdapter();
		HListIterator trialEmptyIter = trialEmpty.listIterator();
		assertTrue("TrialEmpty list size is zero", trialEmpty.size() == 0);
		assertTrue("TrialEmpty list is empty", trialEmpty.isEmpty());
		assertFalse("The iterator has not a next element", trialEmptyIter.hasNext());
		assertFalse("The iterator has not a previous element", trialEmptyIter.hasPrevious());	
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the parameterized constructor of a ListAdapterIterator checking if the iterator start where indicated
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the parameterized constructor with a non empty list 
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of an iterator in the non empty list after a call to next() method and a previous() method.
	 * Before that, its necessary to handle exceptions that can occur within the test.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list.  
	 * Methods: next(), previous(), hasNext(), hasPrevious(), nextIndex(), previousIndex() are working correctly.
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * The position of the iterator when created must be where indicated by the index parameter. 
	 * The iterator can be in a different position from the starting one  
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator can go trough the non-empty list with the use of next() or previous() methods as it has elements in the associated list. 
	 * The constructor generate correctly the iterator in the expected position
	 * 
	 */
	@Test
	public void constructorTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			iter = pList.listIterator(-1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			iter = pList.listIterator(pList.size() + 1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than the list size", exceptionThrown);
		
		// Non-Empty List
		iter = pList.listIterator(2);
		// next() method
		assertTrue("The iterator has next element in the actual position", iter.hasNext());
		assertTrue("The index of the next element from the actual position of the iterator is 2", iter.nextIndex() == 2);
		assertEquals("The element returned by a next call from the actual position of the iterator is C", "C", iter.next());
		// previous() method
		assertTrue("The iterator has previous elements in the actual position", iter.hasPrevious());
		assertTrue("The index of the previous element from the actual position of the iterator is 2", iter.previousIndex() == 2);
		assertEquals("The element returned by a previous call from the actual position of the iterator is C", "C", iter.previous());
		
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the hasNext() method of a ListAdapterIterator checking if return true if there's a next element 
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the hasNext() method with an empty list and a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in an empty list and in a non empty list, checking the correct return of hasNext() method after
	 * multiple calls to next() method.
	 * During the test, the iterator go through the non empty list with multiple calls to previous() method starting from the end and stopping at the end of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), next() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the end of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> 
	 * The iterator can go trough the non empty list with the use of next() method as it has elements in the associated list
	 * and hasPrevious() method report correctly if there is a previous element or not. At the end the iterator is located at the end of the list.
	 * 
	 */
	@Test
	public void hasNextTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Empty List
		iter = emptyList.listIterator();
		assertFalse("The iterator has not a next element", iter.hasNext());
		
		// Non-Empty List
		iter = pList.listIterator();
		assertTrue("The iterator has a next element in the actual position (start of the list)", iter.hasNext());
		iter.next();	
		iter.next();	
		assertTrue("The iterator has a next element in the actual position", iter.hasNext());
		iter.next();	
		iter.next();	
		iter.next();	
		assertFalse("The iterator has not a next element in the actual position (end of the list)", iter.hasNext());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the next() method of a ListAdapter, checking the correctly return of the next element, if any
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the next() method on a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to next() method. 
	 * During the test, the iterator go through the non empty list with multiple calls to next() method and stops at the end of the list.
	 * The test consists in comparing in order the elements of the list with the elements of an array which contains the same elements in the same order.
	 * After going through the list, its check if the iterator is at the end of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), hasNext() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the end of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator is located at the start of the list when created. 
	 * The iterator can go trough the list with the use of next() methods as it has elements in the associated list and the nextIndex() method has worked properly with the indexes.
	 * The iterator is located at the end of the list.
	 */
	@Test
	public void nextTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Non-Empty List
		iter = pList.listIterator();
		int i = 0;
		while(iter.hasNext()) {
			assertEquals("The list and the array it was initialized with have the same element", programmingLanguages[i], iter.next());
			i++;
		}
		
		boolean exceptionThrown = false;
		boolean noNextElements = !iter.hasNext();
		try {
			iter.next();
		} catch(NoSuchElementException e) {
			exceptionThrown = true;
		}
		assertTrue("The iterator has reached the end of the list and has no more next elements", exceptionThrown && noNextElements);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the hasPrevious() method of a ListAdapterIterator checking if return true if there's a previous element 
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the hasPrevious() method with an empty list and a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in an empty list and in a non empty list, checking the correct return of hasPrevious() method after
	 * multiple calls to previous() method.
	 * During the test, the iterator go through the non empty list with multiple calls to previous() method starting from the end and stopping at the start of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), size(), previous() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the start of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> 
	 * The iterator can go trough the non empty list with the use of previous() method as it has elements in the associated list
	 * and hasPrevious() method report correctly if there is a previous element or not. At the end the iterator is located at the start of the list.
	 */
	@Test
	public void hasPreviousTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Empty List
		iter = emptyList.listIterator();
		assertFalse("An iterator on an empty string has not a previous element", iter.hasPrevious());
		
		// Non-Empty List
		iter = pList.listIterator(pList.size());
		assertTrue("The iterator has a previous element in the actual position (end of the list)", iter.hasPrevious());
		iter.previous();	
		iter.previous();	
		assertTrue("The iterator has a previous element in the actual position", iter.hasPrevious());
		iter.previous();	
		iter.previous();	
		iter.previous();	
		assertFalse("The iterator has not a previous element in the actual position (start of the list)", iter.hasPrevious());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the previous() method of a ListAdapter, checking if return the right previous element, if any 
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the previous() method with a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to previosu() method. 
	 * During the test, the iterator go through the non empty list with multiple calls to previous() method and stops at the start of the list.
	 * The test consists in comparing in order the elements of the list with the elements of an array which contains the same elements in the same order.
	 * After going through the list, its check if the iterator is at the start of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), size(), hasPrevious() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the start of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator is located at the end of the list when created. At the end, the iterator is located at the start of the list.
	 * The iterator can go trough the list with the use of previous() methods as it has elements in the associated list.
	 * 
	 */
	@Test
	public void previousTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		iter = pList.listIterator(pList.size());
		int i = programmingLanguages.length - 1;
		while(iter.hasPrevious()) {
			assertEquals("The list and the array it was initialized with have the same element", programmingLanguages[i], iter.previous());
			i--;
		}
		
		boolean exceptionThrown = false;
		boolean noPreviousElements = !iter.hasPrevious();
		try {
			iter.previous();
		} catch(NoSuchElementException e) {
			exceptionThrown = true;
		}
		assertTrue("The iterator has reached the start of the list and has no more previous elements", exceptionThrown && noPreviousElements);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the nextIndex() method of a ListAdapter, checking the correctly return of a next index, if any
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the nextIndex() method on a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to nextIndex() method. 
	 * During the test, the iterator go through the non empty list with multiple calls to next() method and stops at the end of the list.
	 * The test consists in comparing in order the indexes of the elements of the list with the indexes of the elements of an array which contains the same elements in the same order.
	 * After going through the list, its check if the iterator is at the end of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), size(), hasNext(), next() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the end of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator is located at the start of the list when created. 
	 * The iterator can go trough the list with the use of next() methods as it has elements in the associated list and the nextIndex() method has worked properly with the indexes.
	 * The iterator is located at the end of the list.
	 * 
	 */
	@Test
	public void nextIndexTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		iter = pList.listIterator();
		int i = 0;
		while(iter.hasNext()) {
			assertEquals("The list and the array it was initialized with have the same size", i, iter.nextIndex());
			iter.next();
			i++;
		}
		assertEquals("The iterator has reached the end of the list and has no more next elements", pList.size(), iter.nextIndex());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the previousIndex() method of a ListAdapter, checking the correctly return of a previous index, if any
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the previousIndex() method on a non empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to previousIndex() method. 
	 * During the test, the iterator go through the non empty list with multiple calls to previous() method and stops at the start of the list.
	 * The test consists in comparing in order the indexes of the elements of the list with the indexes of the elements of an array which contains the same elements in the same order.
	 * After going through the list, its check if the iterator is at the start of the list.
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(int index), size(), hasPrevious(), previous() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The iterator is located at the start of the list
	 * <br>
	 * <br>
	 * <b> Expected result: </b> The iterator is located at the end of the list when created. 
	 * The iterator can go trough the list with the use of previous() methods as it has elements in the associated list and the previousIndex() method has worked properly with the indexes.
	 * The iterator is located at the start of the list.
	 * 
	 */
	@Test
	public void previousIndexTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		iter = pList.listIterator(pList.size());
		int i = programmingLanguages.length - 1;
		while(iter.hasPrevious()) {
			assertEquals("The list and the array it was initialized with have the same size", i, iter.previousIndex());
			iter.previous();
			i--;
		}
		assertEquals("The iterator has reached the start of the list and has no more previous elements ", -1, iter.previousIndex());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the remove() method of ListAdapter, checking the correct result of a call to the method
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test of the remove() method on a non empty list, after the invocation of next() method and previous() method
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to remove() method. 
	 * During the test, the iterator go through the non empty list with multiple calls to next() and previous() method an then try to remove an element
	 * with the remove() method.
	 * After a call to remove, its necessary to check that there will be no further invocations of the remove method until the next invocation of the previous() or next() methods.
	 * Its also necessary to check that there will be no call to the add method between the last invocations of previous()/next() and the next call to the remove method.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), listIterator(int index), size(), next(), contains(Object o), toArray(), previous() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The list contains less elements.
	 * <br>
	 * <br>
	 * <b> Expected result: </b>
	 * The remove method worked properly, and at the end of the test, the list should contains less elements. The removed elements must be the expected ones.
	 */
	@Test
	public void removeTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		iter = pList.listIterator();
		
		// Call to next() method
		iter.next();	
		iter.next();	
		iter.remove();
		assertFalse("The list no longer contains the element Python", pList.contains("Python"));
		String[] updateProgLang = {"Java", "C", "Ruby", "Go"};
		assertArrayEquals("The actual list has to be equals to the expected list", updateProgLang, pList.toArray());
		
		boolean exceptionThrown = false;
		try {
			iter.remove();
		} catch(IllegalStateException e) {
			exceptionThrown = true;
		}
		assertTrue("A call to remove method cannot be subsequent to a call to remove method", exceptionThrown);
		
		
		// Call to previous() method
		iter = pList.listIterator(pList.size());
		iter.previous();
		iter.previous();	
		iter.remove();
		
		assertFalse("The list no longer contains the element Ruby", pList.contains("Ruby"));
		String[] updateProgLang2 = {"Java", "C", "Go"};
		assertArrayEquals("The actual list has to be equals to the expected list", updateProgLang2, pList.toArray());
		
		exceptionThrown = false;
		try {
			iter.next();
			iter.add("PHP");
			iter.remove();
		} catch(IllegalStateException e) {
			exceptionThrown = true;
		}
		assertTrue("A call to remove method cannot be subsequent to a call to add method", exceptionThrown);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the set(Object o) method of ListAdapter, checking the correct result of a call to the method
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test of the set(Object o) method on a non empty list, after the invocation of next() method and previous() method
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to set method. 
	 * During the test, the iterator go through the non empty list with multiple calls to next() and previous() method an then try to update an element
	 * with the set method.
	 * Its also necessary to check that there will be no call to the set method until an invocations of previous()/next().
	 * Its necessary to check that there will be no call to the remove method between the last invocations of previous()/next() and the next call to the set method.
	 * Its also necessary to check that there will be no call to the add method between the last invocations of previous()/next() and the next call to the set method.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), listIterator(int index), size(), next(), contains(Object o), toArray(), previous() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The list contains the same number of elements.
	 * <br>
	 * <br>
	 * <b> Expected result: </b>
	 * The set method worked properly and, at the end of the test, the list should contains the same number of elements. The updated elements must be the expected ones.
	 */
	@Test
	public void setTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		iter = pList.listIterator();
		
		boolean exceptionThrown = false;
		try {
			iter.set("PHP");
		} catch(IllegalStateException e) {
			exceptionThrown = true;
		}
		assertTrue("A call to set method can be only after the invocation of next and previous methods", exceptionThrown);
		
		// Call to next() method
		iter.next();	
		iter.next();	
		iter.set("Swift");	
		// pList = {"Java", "Swift", "C", "Ruby", "Go"}
		assertFalse("The list no longer contains the element Python", pList.contains("Python"));
		String[] updateProgLang = {"Java", "Swift", "C", "Ruby", "Go"};
		assertArrayEquals("After its update, the list must be equals to the updated list of programming languages", updateProgLang, pList.toArray());
		
		// Call to previous() method
		iter = pList.listIterator(pList.size());
		iter.previous();	
		iter.previous();	
		iter.set("Kotlin");
		// pList = {"Java", "Swift", "C", "Kotlin", "Go"}
		assertFalse("The list no longer contains the element Ruby", pList.contains("Ruby"));
		String[] updateProgLang2 = {"Java", "Swift", "C", "Kotlin", "Go"};
		assertArrayEquals("After its update, the list must be equals to the updated list of programming languages", updateProgLang2, pList.toArray());
		
		exceptionThrown = false;
		try {
			iter.next();
			iter.remove();
			iter.set("JavaScript");
		} catch(IllegalStateException e) {
			exceptionThrown = true;
		}
		assertTrue("A call to set method cannot be subsequent to a call to remove method", exceptionThrown);
		
		exceptionThrown = false;
		try {
			iter.add("C++");
			iter.set("C#");
		} catch(IllegalStateException e) {
			exceptionThrown = true;
		}
		assertTrue("A call to set method cannot be subsequent to a call to add method", exceptionThrown);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the add(Object o) method of ListAdapter, checking the correct result of a call to the method
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test of the add(Object o) method on a non empty list, starting from the start of the list and then starting from a certain index
	 * <br>
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behaviors of the iterator in a non empty list, checking the correct return after a call to add() method. 
	 * The test is divided in two "subTest", the first one start adding element from the start of the list and the second one start adding element from
	 * a specified index of the list.
	 * The call to add method can be subsequently, there's no restriction unlike remove and set methods.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. 
	 * Methods: listIterator(), listIterator(int index), size(), indexOf(Object o), contains(Object o), toArray() are working correctly
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> An iterator cannot scan an empty list. An iterator can scan a non empty list. The list contains a bigger number of elements.
	 * <br>
	 * <br>
	 * <b> Expected result: </b>
	 * The add method worked properly and, at the end of the test, the list should contains a bigger number of elements. The added elements must be in the expected position.
	 */
	@Test
	public void addTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// add(Object o) method with default iterator constructor
		iter = pList.listIterator();
		iter.add("Kotlin");
		assertTrue("The list now contains the element Kotlin", pList.contains("Kotlin"));
		assertTrue("The list still contains the element Java", pList.contains("Java"));
		assertTrue("Kotlin is located at the start of the list", pList.indexOf("Kotlin") == 0);
		assertTrue("The Java element has been shifted to the right", pList.indexOf("Java") == 1);
		String[] updateProgLang = {"Kotlin", "Java", "Python", "C", "Ruby", "Go"};
		assertArrayEquals("After its update, the list must be equals to the updated list of programming languages", updateProgLang, pList.toArray());
		// pList = {"Kotlin", "Java", "Python", "C", "Ruby", "Go"}
		
		// add(Object o) method with Parameterized iterator constructor
		iter = pList.listIterator(3);	
		iter.add("R");	
		// pList = {"Kotlin", "Java", "Python", "R", "C", "Ruby", "Go"}
		iter.add("JavaScript");	
		// pList = {"Kotlin", "Java", "Python", "R", "JavaScript",  "C", "Ruby", "Go"}
		assertTrue("The list still contains the element C", pList.contains("C"));
		assertTrue("The list now contains the element R", pList.contains("R"));
		assertTrue("The list now contains the element JavaScript", pList.contains("JavaScript"));
		assertTrue("The R element is located before the C element", pList.indexOf("R") == 3);
		assertTrue("The JavaScript element is located after the R element due to the behavior of the iterator after an add method", pList.indexOf("JavaScript") == 4);
		String[] updateProgLang2 = {"Kotlin", "Java", "Python", "R", "JavaScript",  "C", "Ruby", "Go"};
		assertArrayEquals("After its update, the list must be equals to the updated list of programming languages", updateProgLang2, pList.toArray());
	}
	
}
