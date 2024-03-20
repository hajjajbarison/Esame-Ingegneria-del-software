package myTest;


// JUnit imports
import org.junit.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

// import org.junit.Assert.assertEquals;

// package import
import myAdapter.*;

/**
 * 
 * @author Barison Hajjaj (Matricola 2014481)
 * <br> <br>
 * <b> Summary: </b> The TestListAdapter class deals with testing the functionalities of all the ListAdapter class methods.
 * <br>
 * <br>
 * <b> Test Suite Design: </b> This class test the multiple methods of a ListAdapter starting from a list of elements. The list contains some programming languages.
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
 * {@link #sizeTest()}
 * <br>
 * {@link #isEmptyTest()}
 * <br>
 * {@link #containsAllTest()}
 * <br>
 * {@link #toArrayTest()}
 * <br>
 * {@link #addTest()}
 * <br>
 * {@link #removeTest()}
 * <br>
 * {@link #containsAllTest()}
 * <br>
 * {@link #addAllTest()}
 * <br>
 * {@link #addAllWithIndexTest()}
 * <br>
 * {@link #removeAllTest()}
 * <br>
 * {@link #retainAllTest()}
 * <br>
 * {@link #clearTest()}
 * <br>
 * {@link #equalsTest()}
 * <br>
 * {@link #hashCodeTest()}
 * <br>
 * {@link #getTest()}
 * <br>
 * {@link #setTest()}
 * <br>
 * {@link #addAllWithIndexTest()}
 * <br>
 * {@link #removeWithIndexTest()}
 * <br>
 * {@link #indexOfTest()}
 * <br>
 * {@link #lastIndexOfTest()}
 * <br>
 * {@link #subListTest()}
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
 * HListIterator iter - main ListAdapter iterator associated to pList
 * <br>
 * String[] programmingLanguages - array used to initialized pList before every methods; sometimes it is also used for comparisons 
 * <br> 
 *
 */
public class TestListAdapter {
	
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
	 * <b> Summary: </b> Method that test the default constructor of a ListAdapter, checking if the initial size of the list is zero and the list is empty
	 * <br>
	 * <br>
	 * <b> Test Case Design: </b> Test the default constructor with an empty list
	 * <br>
	 * <br>
	 * <b> Test Description: </b>
	 * Test the feature of the list created, that must be empty
	 * <br>
	 * <br>
	 * <b> Pre-Condition: </b> 
	 * Methods: isEmpty(), size() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> 
	 * The created list is empty
	 * <br> 
	 * <br>
	 * <b> Expected results: </b> 
	 * The default constructor create a list that has a size of zero and isEmpty.
	 * 
	 */
	@Test
	public void defaultConstructorTest() {
		// Empty List
		HList trial = new ListAdapter();
		assertTrue("The trial list is an empyt list and has to be empty", trial.isEmpty());
		boolean tSize = trial.size() == 0;
		assertTrue("The size of the empty list is zero", tSize);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the parameterized constructor of ListAdapter class
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Check if the constructor generate a list with all elements of the collection passed as parameter
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the feature of non empty list created by the parameterized constructor
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list to receive as parameter is not empty and has the same element of the programmingLanguages array. 
	 * Methods: size(), toArray() are working correctly.
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> 
	 * The created list isn't empty and has the same element of the collection.
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The parameterized constructor must create a non empty list which contains all the elements of pList
	 * 
	 */
	@Test
	public void constructorTest() {
		HList trial = new ListAdapter(pList);
		assertArrayEquals("The created list must have the same elements of the passed collection", pList.toArray(), trial.toArray());
		assertTrue("The instantiated list must have the same size of the list of languages", trial.size() == programmingLanguages.length);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the size() method of a ListAdapter, checking the size of the list where is invoked with the list actual size
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the size() method on an empty list and on a non empty list.
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the correct behavior of the size method on a list with multiple tests and with the use of the add() method.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b>
	 * Methods: add() is working correctly 
	 * <br>
	 * <br>
	 * <b> Post-Condition: </b> The list has not changed as consequence of size method
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The size method must behave correctly on the starting list and also after changes to the list. 
	 * 
	 */
	@Test
	public void sizeTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Empty List
		assertTrue("The empty list is empty", emptyList.size() == 0);
		
		// Non-Empty list
		assertFalse("The non empty list does not have size equals to zero", pList.size() == 0);
		assertTrue("The instantiated list must have the same size of the list of languages", pList.size() == programmingLanguages.length);
		pList.add("Kotlin");
		assertFalse("The list still has a size bigger than zero", pList.size() == 0);
		assertFalse("The instantiated list does not has the same size of the list of languages", pList.size() == programmingLanguages.length);
		assertTrue("The instantiated list is bigger than the size of the list of languages by one element", pList.size() == programmingLanguages.length + 1);
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the isEmpty() method of a ListAdapter, checking if return true when the list used is empty
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the isEmpty() method on a list that is initially non empty and then made empty 
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the correct behavior of the isEmpty method on a non empty list. 
	 * Then, after clearing the list, invoke isEmpty on the same list to see his new behavior.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the isEmpty method is not empty
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the isEmpty method is empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * Initially, the isEmpty method will work correctly reporting that the actual list is not empty. After making the list empty, isEmpty method will
	 * still working correctly reporting that the actual list is empty and so his size must be zero. 
	 * 
	 */
	@Test
	public void isEmptyTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		assertFalse("The list is not empty", pList.isEmpty());
		while(iter.hasNext()) {
			iter.next();
			pList.remove(iter.previous());
		}
		assertTrue("The list has a size equals to zero", pList.size() == 0);
		assertTrue("The list is empty", pList.isEmpty());
	
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the contains(Object o) method of a ListAdapter, checking if the list contains or not a determinate element
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the contains(Object o) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the contains method on a non empty list. 
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the contains method is not empty 
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the contains method is not empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The contains method should return true if the searched element is present in the list; false otherwise
	 * 
	 */
	@Test
	public void containsTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		assertTrue("The list contains the element Ruby", pList.contains("Ruby"));
		assertFalse("The List does not contains the element R", pList.contains("R"));
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the toArray() method of a ListAdapter, checking if the method return an array containing all the element of the list
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the toArray() method on a non empty list and on an empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the toArray method on a non empty list and an empty list
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the toArray method does not change due to toArray method.
	 * Methods: add(Object o) is working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the toArray method is not change due to toArray method
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The toArray method is working correctly if the returned array has the same element of the list and the same size
	 * 
	 */
	@Test
	public void toArrayTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// Empty List
		assertArrayEquals("The empty list has the same dimension of an array with size equals to zero", new String[] {}, emptyList.toArray());
		
		// Non-Empty list
		assertArrayEquals("The array containing the list must be equals to the array of languages", programmingLanguages, pList.toArray());
		pList.add("MATLAB");
		pList.add("JavaScript");
		String[] updateProgLang = {"Java", "Python", "C", "Ruby", "Go", "MATLAB","JavaScript"};
		assertArrayEquals("The array containing the list must be equals to the updated array of languages", updateProgLang, pList.toArray());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the toArray(Object[] a) of a ListAdapter, checking if the method return an array containing all the element of the list 
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the toArray(Object[] a) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the toArray(Object[] a) method on a non empty list. 
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter.
	 * Then, there are two "subTest". The first subTest check the behavior of the method using an array smaller than the list.
	 * The second subTest check the behavior of the method using an array bigger than the list or with the same size of the list.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the toArray method does not change due to toArray method.
	 * Methods: size(), toArray(), listAdapter(Object o) are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the toArray method does not change due to toArray method
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The toArray(Object[] a) method is working correctly if in the first subTest return an array with the same size and 
	 * containing all the element of the list (invoking toArray()) and if in the second subTest return an array containing all the elements
	 * of the list but the returned array not necessary has the same size of the list, it could be bigger. 
	 * 
	 */
	@Test
	public void toArrayWithParamTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.toArray(null);
		}catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		// Lunghezza aray < Lunghezza lista
		Object[] a = new Object[pList.size() - 1];
		assertArrayEquals("The actual list has to be equals to the expected list", pList.toArray(), pList.toArray(a));
		
		// Lunghezza aray >= Lunghezza lista
		Object[] b = new Object[pList.size() + 2];
		HList pList2 = new ListAdapter(pList);
		while(pList2.size() < b.length) {
			pList2.add(null);
		}
		assertArrayEquals("The actual list has to be equals to the expected list", pList2.toArray(), pList.toArray(b));
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the add(Object o) of a ListAdapter, checking if the method add the element in the correct position
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the add(Object o) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the add(Object o) method on a non empty list checking the size variation with the size method, and checking
	 * if the array, containing all the list elements, is equals to the updated array of programming languages
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the add method is non empty.
	 * Methods: size(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the add method is non empty. The list contains a bigger number of elements 
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The add method worked properly and if the list contains a bigger number of elements. 
	 * The added element must be located at the end of the list. 
	 */
	@Test
	public void addTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		pList.add("Swift");
		assertTrue("The actual list is bigger than the same size of the array of languages by one element", pList.size() == programmingLanguages.length + 1);
		String[] updateProgLang = {"Java", "Python", "C", "Ruby", "Go", "Swift"};
		assertArrayEquals("The array containing the list must be equals to the updated array of languages", updateProgLang, pList.toArray());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the remove(Object o) of a ListAdapter, checking if the method remove first occurrence of the element
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the remove(Object o) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the remove(Object o) method on a non empty list checking the size variation with the size method, and checking
	 * if the array, containing all the list elements, is equals to the updated array of programming languages
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the remove method is non empty.
	 * Methods: size(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the remove method is non empty. The list contains a smaller number of elements 
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The remove method worked properly and if the list contains a smaller number of elements. 
	 * The remove element must be the first occurrence of the element in the list.
	 * 
	 */
	@Test
	public void removeTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		pList.remove("Go");
		assertTrue("The actual list is smaller than the same size of the array of languages by one element", pList.size() == programmingLanguages.length - 1);
		String[] updateProgLang = {"Java", "Python", "C", "Ruby"};
		assertArrayEquals("The array containing the list must be equals to the updated array of languages", updateProgLang, pList.toArray());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the containsAll(HCollection c) of a ListAdapter, checking if the list contains all the element of another list
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the containsAll(HCollectionc) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the containsAll(HCollection c) method on a non empty list, checking the list contains all the element of the list received as parameter
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the containsAll method is non empty. 
	 * The list on which to invoke the containsAll method does not change due to toArray method.
	 * Methods: add(), clear(), listAdapter() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the containsAll method is not empty. 
	 * The list on which to invoke the toArray method is not change due to containsAll method
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The containsAll method worked correctly if the list, on which to invoke the method, contains all the element of the received list.
	 * The number of elements of both lists shouldn't change due to this method.
	 * 
	 */
	@Test
	public void containsAllTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.containsAll(null);
		}catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		HList trial = new ListAdapter();
		for(String pL : programmingLanguages) {
			trial.add(pL);
		}
		assertTrue("The trial list should contain all the elements of the list pList", trial.containsAll(pList));
		trial.add("C#");
		trial.add("C++");
		assertTrue("The trial list should contain all the elements of the list pList", trial.containsAll(pList));
		pList.clear();
		pList.add("R");
		assertFalse("The trial list should contain all the elements of the list pList", trial.containsAll(pList));
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the addAll(HCollection c) of a ListAdapter, checking if all the element of a received list have been added to the list
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the addAll(HCollection c) method on an empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b>
	 * Test the behavior of the addAll(HCollection c) method on a empty list checking if all the element of the list received as parameter have been added to the list.
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the addAll method is empty. 
	 * Methods: toArray() is working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the addAll method is not empty and contains all the element of the list received as parameter
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The addAll method worked if the empty list is no more empty and contains all elements of list received as parameter. 
	 * The added element of the list, received as parameter, must be located at the end of the list 
	 */
	@Test
	public void addAllTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.addAll(null);
		}catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		emptyList.addAll(pList);
		assertArrayEquals("The actual list (emptyList) should contain all the element of the expected list (pList)", pList.toArray(), emptyList.toArray());
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the addAll(int index, HCollection c) method of a ListAdapter, checking if all the element of a received list have been added to the list
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the addAll(int index, HCollection c) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the addAll(int index, HCollection c) method on a non empty list, checking if all the element of a received list have been added to the list
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * null value of the array received as parameter, negative index as parameter, index bigger than the size of the list as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the addAll method is non empty.
	 * Methods: listAdapter(), add(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the addAll method is non empty and has a bigger number of elements
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The addAll method is working if the list, received as parameter, was inserted in the actual list at the desired position and with the desired elements. 
	 * The list on which was invoked the addAll method has a bigger number of elements
	 */
	@Test
	public void addAllWithIndexTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.addAll(null);
		} catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.addAll(-1, emptyList);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.addAll(pList.size() + 1, emptyList);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than the list size", exceptionThrown);
		
		String[] programmingLanguages2 = {"C++", "Swift", "PHP"};
		HList trial = new ListAdapter();
		for(String pL : programmingLanguages2) {
			trial.add(pL);
		}
		pList.addAll(2, trial);
		String[] updateProgLang = {"Java", "Python", "C++", "Swift", "PHP", "C", "Ruby", "Go"};
		assertArrayEquals("The actual list has to be equals to the expected list", updateProgLang, pList.toArray());
	}

	
	/**
	 * 
	 * <b> Summary: </b> Method that test the removeAll(HCollection c) method of a ListAdapter, checking if all the element of a received list have been removed from the list
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the removeAll(HCollection c) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b>
	 * Test the behavior of the removeAll(HCollection c) method on a empty list checking if all the element of a received list have been removed from the list.
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the removeAll method is non empty. 
	 * Methods: toArray() is working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the removeAll method contains no more all the elements specified by the list received as parameter
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>   
	 * The removeAll method worked properly if, at the end of the test, the list should contains less elements. The removed elements must be the expected ones
	 */
	@Test
	public void removeAllTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.removeAll(null);
		} catch (NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		String[] programmingLanguages2 = {"Java", "Python", "Ruby", "R"};
		HList trial = new ListAdapter();
		for(String pL : programmingLanguages2) {
			trial.add(pL);
		}
		
		assertTrue("The size of the trial list equals to four", trial.size() == 4);
		pList.removeAll(trial);
		assertTrue("After the removeAll method, the actual list has a size of two", pList.size() == 2);
		String[] updateProgLang = {"C", "Go"};
		assertArrayEquals("The array containing the actual list must be equals to the updated array of languages", updateProgLang, pList.toArray());
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the retainAll(HCollection c) method of a ListAdapter, checking if, at the end, the actual list contain only 
	 * the elements that are present in the list received as parameter
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test of the retainAll(HCollection c) method on a non empty list 
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the retainAll(HCollection c) method on a non empty list checking if, at the end, the actual list contain only 
	 * the elements that are present in the list received as parameter.
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the retainAll method is non empty.
	 * Methods: listAdapter(), size(), add(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the retainAll method contains only the elements specified by the list received as parameter.
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The retainAll method worked properly if the preserved elements are the expected ones 
	 */
	@Test
	public void retainAllTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.retainAll(null);
		} catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the array used as parameter is null", exceptionThrown);
		
		String[] programmingLanguages2 = {"Java", "Python", "Ruby", "R"};
		HList trial = new ListAdapter();
		for(String pL : programmingLanguages2) {
			trial.add(pL);
		}
		
		assertTrue("The size of the trial list equals to four", trial.size() == 4);
		pList.retainAll(trial);
		assertTrue("After the retainAll method, the actual list has a size of three", pList.size() == 3); 
		String[] updateProgLang = {"Java", "Python", "Ruby"};
		assertArrayEquals("The array containing the actual list must be equals to the updated array of languages", updateProgLang, pList.toArray());
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the clear() test of a ListAdapter, checking if the list is empty after the invocation of the method
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the clear() method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the clear() method on a non empty list checking if the list is empty after the invocation of the method
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the clear method is non empty.
	 * Methods: isEmpty(), size() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the clear method is empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The method is working if a non empty list become empty after the invocation to the method.
	 * The actual list is now empty and his size is equal to zero
	 * 
	 */
	@Test
	public void clearTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		pList.clear();
		assertTrue("The actual list is empty after the invocation of clear method", pList.isEmpty());
		assertTrue("The actual size of the list is zero after the invocation of clear method", pList.size() == 0);
		
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the equals(Object o) method of a ListAdapter, checking that return true only if two lists are equals
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Tests the equals(Object o) method on a non empty list comparing it with a non empty list and an empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the equals(Object o) method on a non empty list comparing it with a non empty list and an empty list
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the equals method is non empty.
	 * Methods: listAdapter(), listAdapter(Object o) are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the equals method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The method is working if return true. 
	 * It return true if the comparison is between two list with the same size and the same element; false otherwise
	 * 
	 */
	@Test
	public void equalsTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// comparison with Empty List
		HList trial = new ListAdapter();
		assertFalse("A non empty list cannot be equals to an empty list", pList.equals(trial));
		
		// comparison with Non-Empty List
		assertTrue("A list must be equals to itself", pList.equals(pList));
		trial = new ListAdapter(pList);
		assertTrue("A list is equal to another list that contains the same elements", pList.equals(trial));
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the hashCode() method of a ListAdapter, checking that return true only if two lists has the same hashCode
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Tests the hashCode() method on a non empty list comparing it with a non empty list and an empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the hashCode() method on a non empty list comparing it with a non empty list and an empty list
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the hashCode method is non empty.
	 * Methods: listAdapter(), listAdapter(Object o) are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which to invoke the hashCode method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b> 
	 * The method is working if return true. 
	 * It return true if the comparison is between two list with the same hashCode; false otherwise
	 * 
	 */
	@Test
	public void hashCodeTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		// comparison with Empty List
		HList trial = new ListAdapter();
		assertFalse("A non empty list cannot have the same hashCode of an empty list", pList.hashCode() == trial.hashCode());
		
		// comparison with Non-Empty List
		assertTrue("A list have the same hashCode of itself", pList.hashCode() == pList.hashCode());
		trial = new ListAdapter(pList);
		assertTrue("A list has the same hashCode of another list that contains the same elements", pList.hashCode() == trial.hashCode());
	}
	
 
	/**
	 * 
	 * <b> Summary: </b> Method that test the get(int index) method of a ListAdapter, checking if the selected element is the correct one
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test of the get(int index) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the get(int index) method on a non empty list checking if the selected element is the correct one.
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * negative index as parameter, index bigger than or equal to the size of the list as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the get method is non empty.
	 * Methods: size(), add(Object o) are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the get method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The get method working rightly if the returned element is the expected one and the size of the list is not changed.
	 * 
	 */
	@Test
	public void getTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.get(pList.size());
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than or equal to the list size", exceptionThrown);
		
		assertEquals("The element at the first position of the list is Java", "Java", pList.get(0));
		assertEquals("The element at the last position of the list is Go", "Go", pList.get(pList.size() - 1));
		pList.add(3, "C++");
		assertNotEquals("After the add invocation, the element in the third position is no more Ruby", "Ruby", pList.get(3));
		assertEquals("The element in the third position is C++", "C++", pList.get(3));
	}
	
	
	/**
	 * 
	 * <b> Summary: </b> Method that test the set(int index) method of a ListAdapter, checking if the updated element is the correct one
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test of the set(int index) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the set(int index) method on a non empty list checking if the updated element is the correct one.
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * negative index as parameter, index bigger than or equal to the size of the list as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which to invoke the set method is non empty.
	 * Methods: size(), get(int index), add(Object o), contains(Object), listAdapter(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the set method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The set method worked properly if the list contains the same number of elements and the updated elements are the expected ones.
	 * 
	 */
	@Test
	public void setTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.get(pList.size());
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than or equal to the list size", exceptionThrown);
		
		pList.set(3, "Kotlin");
		assertTrue("After a set method, the size of the list should be the same", pList.size() == 5);
		assertTrue("The list must contains the new element inserted", pList.contains("Kotlin"));
		HList trial = new ListAdapter();
		String[] updateProgLang = {"Java", "Python", "C", "Kotlin", "Go"};
		for(String pL : updateProgLang) {
			trial.add(pL);
		}
		assertArrayEquals("The actual list has to be equals to the expected list", pList.toArray(), trial.toArray());
	}
	
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the add(int index, Object o) of a ListAdapter, checking if the method add the element in the correct position
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the add(int index, Object o) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the add(int index, Object o) method on a non empty list checking the size variation with the size method, and checking
	 * if the array, containing all the list elements, is equals to the updated array of programming languages.
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * negative index as parameter, index bigger than or equal to the size of the list as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the add method is non empty.
	 * Methods: get(index i), size(), add(), contains(Object o), listAdapter(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the add method is non empty. The list contains a bigger number of elements 
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The add method worked properly and if the list contains a bigger number of elements. 
	 * The added element must be located at the specified position of the list. 
	 */
	@Test
	public void addWithIndexTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.get(pList.size() + 1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than the list size", exceptionThrown);
		
		int dim = pList.size();
		pList.add(0, "C#");
		assertTrue("The list size should be increased by one", pList.size() == dim + 1);
		assertTrue("The list should contain the element inserted", pList.contains("C#"));
		HList trial = new ListAdapter();
		String[] updateProgLang = {"C#", "Java", "Python", "C", "Ruby", "Go"};
		for(String pL : updateProgLang) {
			trial.add(pL);
		}
		assertArrayEquals("The actual list has to be equals to the expected list", pList.toArray(), trial.toArray());
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the remove(int index) of a ListAdapter, checking if the method remove the element in the correct position
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the remove(int index) method on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the remove(int index) method on a non empty list checking the size variation with the size method, and checking
	 * if the array, containing all the list elements, is equals to the updated array of programming languages.
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * negative index as parameter, index bigger than or equal to the size of the list as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the remove method is non empty.
	 * Methods: get(index i), size(), add(), contains(Object o), listAdapter(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the remove method is non empty. The list contains a smaller number of elements 
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The add method worked properly and if the list contains a bigger number of elements. 
	 * The added element must be located at the specified position of the list. 
	 */
	@Test
	public void removeWithIndexTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.get(-1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.get(pList.size());
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is greater than or equal to the list size", exceptionThrown);
		
		int dim = pList.size();
		pList.remove(1);
		assertTrue("The list size should be decreased by one", pList.size() == dim - 1);
		assertFalse("The list should not contain the element removed", pList.contains("Python"));
		HList trial = new ListAdapter();
		String[] updateProgLang = {"Java", "C", "Ruby", "Go"};
		for(String pL : updateProgLang) {
			trial.add(pL);
		}
		assertArrayEquals("The actual list has to be equals to the expected list", pList.toArray(), trial.toArray());
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the indexOf(Object o) method of a ListAdapter, checking the correctly return (the first occurrence) of the method, if any. 
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the indexOf(Object o) method of a ListAdapter on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the indexOf(Object o) method on a non empty list checking the correctly return of the method, if any.
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the indexOf method is non empty.
	 * Methods: contains(Object o) is working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the indexOf method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The indexOf element is working rightly if the returned element is the expected one.
	 * 
	 */
	@Test
	public void indexOfTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.indexOf(null);
		} catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the element used as parameter is null", exceptionThrown);
		
		int index;
		index = pList.indexOf("Ruby");
		assertTrue("The list contain the searched element", pList.contains("Ruby"));
		assertTrue("The index of the element Ruby is three", index == 3);
		
		index = pList.indexOf("Kotlin");
		assertFalse("The list does not contain the searched element", pList.contains("Kotlin"));
		assertTrue("The index of the element Kotlin is -1, since it is not in the list", index == -1);
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the lastIndexOf(Object o) method of a ListAdapter, checking the correctly return (the last occurrence) of the method, if any. 
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the lastIndexOf(Object o) method of a ListAdapter on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the behavior of the lastIndexOf(Object o) method on a non empty list checking the correctly return of the method, if any.
	 * First of all, its necessary to handle the possible exception due to the possible null value of the array received as parameter
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the lastIndexOf method is non empty.
	 * Methods: size(), add(Object o), contains(Object o) are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the lastIndexOf method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The lastIndexOf element is working rightly if the returned element is the expected one.
	 * 
	 */
	@Test
	public void lastIndexOfTest() {
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.indexOf(null);
		} catch(NullPointerException e) {
			exceptionThrown = true;
		}
		assertTrue("NullPointerException thrown if the element used as parameter is null", exceptionThrown);
		
		int index;
		pList.add("Python");
		pList.add(3, "Java");
		// pList = {"Java", "Python", "C", "Java", "Ruby", "Go", "Python"}  
		index = pList.lastIndexOf("Python");
		assertTrue("The list contain this element", pList.contains("Python"));
		assertTrue("The last index of the element Python is the last index of the list", index == pList.size() - 1);
		index = pList.lastIndexOf("Java");
		assertTrue("The list contain this element", pList.contains("Java"));
		assertTrue("The last index of the element Java is the index equals three of the list", index == 3);
		
		index = pList.lastIndexOf("Swift");
		assertFalse("The list does not contain this element", pList.contains("Swift"));
		assertTrue("The last index of the element Swift is -1, since it is not in the list", index == -1);
	}
	

	/**
	 * 
	 * <b> Summary: </b> Method that test the subList(int fromIndex, int toIndex) of a ListAdapter, checking the correctly return 
	 * <br> 
	 * <br>
	 * <b> Test Case Design: </b> Test the subList(int fromIndex, int toIndex) of a ListAdapter on a non empty list
	 * <br> 
	 * <br>
	 * <b> Test Description: </b> 
	 * Test the correct behavior of the subList(int fromIndex, int toIndex) method on a non empty list checking the correctly return.
	 * Before that, its necessary to handle exceptions that can occur within the test: 
	 * negative index as parameter, index bigger than the size of the list as parameter, fromIndex bigger than toIndex as indexes received as parameters.
	 * Then, there are two "subTest". The first subTest check the behavior of the connection between the sublist and the parentList after a call to add method.
	 * The second subTest check the behavior of the connection between the sublist and the parentList after a call to remove method.
	 * <br> 
	 * <br>
	 * <b> Pre-Condition: </b> The list on which was invoked the subList method is non empty.
	 * Methods: size(), add(Object o), get(int index), containsAll(HCollection c), listAdapter(), toArray() are working correctly
	 * <br> 
	 * <br>
	 * <b> Post-Condition: </b> The list on which was invoked the subList method is non empty
	 * <br> 
	 * <br>
	 * <b> Expected result: </b>  
	 * The subList method works rightly if a change on the sublist involves a change on the parent list as well.
	 */
	@Test
	public void subListTest() {		
		// pList = {"Java", "Python", "C", "Ruby", "Go"}
		// pList.size() == 5
		
		boolean exceptionThrown = false;
		try {
			pList.subList(-1, pList.size() + 1);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if the index used as parameter is negative or if the index used as parameter is greater than the list size ", exceptionThrown);
		
		exceptionThrown = false;
		try {
			pList.subList(3, 2);
		} catch(IndexOutOfBoundsException e) {
			exceptionThrown = true;
		}
		assertTrue("IndexOutOfBoundsException must be thrown if fromIndex is greater then toIndex", exceptionThrown);
		
		
		HList trial = pList.subList(1, 4);	// subList	
		assertTrue("The expected size of the subList is three", trial.size() == 3);		
		assertTrue("The parentList must contains his subList", pList.containsAll(trial));
		HList trial2 = new ListAdapter();
		String[] updateProgLang = {"Python", "C", "Ruby"};
		for(String pL : updateProgLang) {
			trial2.add(pL);
		}
		assertArrayEquals("The actual list has to be equals to the expected list", trial2.toArray(), trial.toArray());
		
		
		// Connection between subList and parentList - add method
		int dimPL = pList.size();
		int dimT = trial.size();
		trial.add("Kotlin");
		assertTrue("The size of the subList is increased by one", trial.size() == dimT + 1);
		assertTrue("The size of the parentList is increased by one consequentially", pList.size() == dimPL + 1);
		
		String[] updateProgLang2 = {"Java", "Python", "C", "Ruby", "Kotlin", "Go"};
		assertArrayEquals("The actual list has to be equals to the expected list", updateProgLang2, pList.toArray());
		
		
		// Connection between subList and parentList - remove method
		dimPL = pList.size();
		dimT = trial.size();
		assertTrue("The actual size of the parentList is six", dimPL == 6);
		assertTrue("The actual size of the parentList is four", dimT == 4);
	
		assertTrue("The element at the start of the list is Python", trial.get(0) == "Python");
		trial.remove(0);
		assertTrue("The element at the position equals two of the list is Kotlin", trial.get(2) == "Kotlin");
		trial.remove(2);
		
		assertTrue("The size of the subList is decreased by two", trial.size() == dimT - 2); 
		assertTrue("The size of the parentList is decreased by two consequentially", pList.size() == dimPL - 2);
		String[] updateProgLang3 = {"Java", "C", "Ruby", "Go"};
		assertArrayEquals("The actual list has to be equals to the expected list", updateProgLang3, pList.toArray());
	}
	
}
