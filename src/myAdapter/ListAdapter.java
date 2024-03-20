package myAdapter;

import java.util.NoSuchElementException;

/**
 * 
 * @author Barison Hajjaj (Matricola: 2014481)
 * <br> <br> 
 * This class implements the HList and HCollection interfaces and will develop the methods of the J2SE 1.4.2 List, starting from the Vector class as adaptee defined in CLDC 1.1.
 * <br> <br>
 * The ListAdapter class provide a default constructor, a private constructor associated with the implementation of the subList method and a constructor for the creation of the object
 * starting from a collection.
 * <br> <br> 
 * The ListAdapter class provides: add, insert, remove, inspect methods, and allow duplicates and null values, also provides a ListIterator.
 * <br> <br>
 * @see HCollection
 * @see HList
 *
 */
public class ListAdapter implements HList{
	
	/**
	 * Vector used as container of values of the list.
	 */
	private Vector list;
	
	/**
	 * Variable used as the initial index of our subList, set to 0 if it's not a subList. 
	 */
	private final int from;
	
	/**
	 * Variable used as the final index of our subList, set to size() if it's not a subList
	 */
	private int to;
	
	/**
	 * Variable used as a reference to the parentList of a subList. If the list isn't a subList, parent must be set to null.
	 */
	private final ListAdapter parent;
	
	/**
	 * Constructs an empty ListAdapter that hasn't a parent list and start/end from zero.
	 */
	public ListAdapter() {
		list = new Vector();
		from = 0;
		to = 0;
		parent = null;
	}
	
	/**
	 * Create a new ListAdapter (a subList) from an existing ListAdapter (the parent). <br>
	 * This private constructor is fundamental for the concept and creation of a subList.
	 * 
	 * @param f - start of the new ListAdapter created
	 * @param t - end of the new ListAdapter created
	 * @param listAdapter - the ListAdapter that will be our parent 
	 */
	private ListAdapter(int from, int to, ListAdapter listAdapter) {
		this.from = from;
		this.to = to;
		list = listAdapter.list;
		parent = listAdapter;
	}
	
	/**
	 * Construct which create a new ListAdapter object initialized from a starting collection received as parameter. <br>
	 * Our new ListAdapter object will contains all the elements of the collection.
	 * 
	 * @param collection - starting collection for the initialization  
	 */
	public ListAdapter(HCollection collection) {
		this();
		HIterator iter = collection.iterator();
		while(iter.hasNext()) {
			add(iter.next());
		}
	}
	
	/**
	 * Method that calculate the size of the list
	 * 
	 * @return number of elements in the list
	 */
	@Override
	public int size() {
		return to - from;
	}
	
	/**
	 * Method that check if the list is empty
	 * 
	 * @return true if the list is empty; false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return to == from;
	}
	
	/**
	 * Method that scan the list searching at least one element equals to the object received as parameter. <br>
	 * The method stops when it finds the first occurrence or when it reaches the end of the list.
	 * 
	 * @param o - element to find
	 * @return true if the elements was found; false otherwise
	 */
	@Override
	public boolean contains(Object o) {
		HListIterator iter = listIterator();
		Object current;													
		if(o == null) {													
			while(iter.hasNext()) {
				if(iter.next() == null) return true;
			}
		} else {														
			while(iter.hasNext()) {
				current = iter.next();
				if(current != null && current.equals(o)) return true;
			}
		}
		return false;
	}
	
	/**
	 * Method that returns an iterator over the elements in this collection (in proper sequence).
	 * 
	 * @return a new iterator over the elements in this collection (in proper sequence)
	 */
	@Override
	public HIterator iterator() {
		return new ListAdapterIterator();
	}
	
	/**
	 * Method that returns an array containing all of the elements in this list. <br>
	 * If the collection makes any guarantees as to what order its elements are returned by its iterator,this method must return the elements in the same order. <br>
	 * The size of the returned array is the same as the size of the list.
	 * 
	 * @return an array containing all the element of the list 
	 */
	@Override
	public Object[] toArray() {
		Object[] a = new Object[size()];   
		HIterator iter = listIterator();		
		int i = 0;								
		while(iter.hasNext()) {					
			a[i] = iter.next();				
			i++;
		}
		return a;
	}
	
	/**
	 * Method that returns the array received as parameter containing all of the elements in this list. <br>
	 * If the collection makes any guarantees as to what order its elements are returned by its iterator,this method must return the elements in the same order. <br>
	 * If the array is smaller than the list, we call the method {@link #toArray()}, otherwise if the array has the same size of the list, the elements of the list 
	 * will be stored into the array. <br>
	 * If the size of the array is bigger than the size of the list, the remaining space will be filled by null elements.
	 * 
	 * @param a - array to fill with the elements of the list 
	 * @return a new array containing all the elements of the list
	 * @throws NullPointerException if the array received as parameter is null
	 */
	@Override
	public Object[] toArray(Object[] a) {
		if(a == null) throw new NullPointerException();
		HListIterator iter = listIterator();	
		int i = 0;								
		if(a.length < size()) {
			return toArray();
		} else {
			while(iter.hasNext()) {					
				a[i] = iter.next();				
				i++;
			}
			for(; i < a.length; i++) {
				a[i] = null;
			}
			return a;
		}
		
	}
	
	/**
	 * Private and recursive method that has the function to update the indexes after a call to {@link #add(int, Object)} or
	 * {@link #remove(int)} method. <br>
	 * If the actual list where this method is invoked is a subList, and consequently has a parent,
	 * we will also need to modify the indexes of the parent and we do this with the recursion of this method.
	 * 
	 * @param n - offset used to increment/decrement the size of the subList and the size of the subList ancestors
	 */
	private void updateIndex(int n) {
		to += n;
		if(parent != null) parent.updateIndex(n);
	}
	
	/**
	 * Method that add the received object at the end of the list.
	 * 
	 * @param o - object to add in the list
	 * @return true, always
	 */
	@Override
	public boolean add(Object o) {	
		add(size(), o);				
		return true;
	}
	
	/**
	 * Method scan the list searching a occurrence of the element received as parameter to delete it. <br>
	 * The method stops when it finds the first occurrence or when it reaches the end of the list.
	 * 
	 * @param o - element to remove from the list
	 * @return true if the element was found and removed; false otherwise
	 */
	@Override
	public boolean remove(Object o) {	
		HListIterator iter = listIterator();
		Object current;
		if(o == null) {
			while(iter.hasNext()) {
				current = iter.next();
				if(current == null) {
					remove(iter.previousIndex());
					return true;
				}
			}
		} else {
			while(iter.hasNext()) {
				current = iter.next();
				if(current != null && current.equals(o)) {
					remove(iter.previousIndex());
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method that check if the list contains all the elements contained in the collection received as parameter. <br>
	 * The method stops when each element of the collection has been searched in the list or if during the search we find an element not present in the list.
	 * 
	 * @param c - collection that contains the elements to find
	 * @return true if all the elements of the collection were found; false otherwise
	 * @throws NullPointerException if the collection received as parameter is null
	 */
	@Override
	public boolean containsAll(HCollection c) {
		if(c == null) throw new NullPointerException();
		Object[] a = c.toArray();
		for(Object o : a){
			if(!contains(o)) return false;
		}
		return true;
	}
	
	/**
	 * Method that add at the end of the list all the elements contained in the collection received as parameter.
	 * 
	 * @param c - collection that contains the elements to add
	 * @return true, always
	 * @throws NullPointerException if the collection received as parameter is null
	 */
	@Override
	public boolean addAll(HCollection c) {
		if(c == null) throw new NullPointerException();
		HIterator iter = c.iterator();
		HListIterator listIter = listIterator(size()); 	
		while(iter.hasNext()) {
			listIter.add(iter.next());
		}
		return true;
	}
	
	/**
	 * Method that insert into the list all the element of the collection received as parameter starting from a certain index. <br>
	 * The method end when all the elements of the collection were added to the list.
	 * 
	 * @param index - where to start adding the element of the collection received as parameter
	 * @param c - collection that contains all the elements to add in the list
	 * @return true, always
	 * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
	 * 										OR bigger than or equal to the size of the list
	 * @throws NullPointerException if the collection received as parameter is null 
	 */
	@Override
	public boolean addAll(int index, HCollection c) {		
		if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
		if(c == null) throw new NullPointerException();
		HIterator iter = c.iterator();
		HListIterator listIter = listIterator(index);
		while(iter.hasNext()) {
			listIter.add(iter.next());
		}
		return true;
	}
	
	/**
	 * Method that remove from the list all the elements contained in the collection received as parameter. <br>
	 * At the end of the method there will be no more occurrences between the collection and the list.
	 * 
	 * @param c - collection that contains all the elements to remove from the list
	 * @return true if there were at least a deletion from the list; false otherwise
	 * @throws NullPointerException if the collection received as parameter is null
	 */
	@Override
	public boolean removeAll(HCollection c) {
		if(c == null) throw new NullPointerException();
		HIterator iter = c.iterator();
		HListIterator listIter = listIterator();
		Object current;
		boolean bool = false;
		while(iter.hasNext()) {
			current = iter.next();
			while(listIter.hasNext()) {
				if(current.equals(listIter.next())){
					remove(listIter.previousIndex());
					bool = true;
				}
			}
			listIter = listIterator();
		}
		return bool;
	}
	
	
	/**
	 * Method that retain in the list only the elements that are also present in the collection received as parameter. <br>
	 * At the end of the method, the list will contain only the elements that are present in the collection.
	 * 
	 * @param c - collection that contains all the elements to retain from the list
	 * @return true if there were at least a deletion from the list; false otherwise
	 * @throws NullPointerException if the collection received as parameter is null
	 */
	@Override
	public boolean retainAll(HCollection c) {		// Mantiene nella lista solo gli elementi che sono presenti anche nella collection
		if(c == null) throw new NullPointerException();
		HListIterator iter = listIterator();
		Object current;
		boolean bool = false; 		 
		while(iter.hasNext()) {
			current = iter.next();
			if(!c.contains(current)) {
				iter.remove();
				bool = true;
			}
		}
		return bool;
	}
	
	/**
	 * Method that removes all elements of the list making it an empty list by resetting it size to zero (to = 0). <br>
	 */
	@Override
	public void clear() {
		HListIterator iter = listIterator();
		Object current;
		while(iter.hasNext()) {
			current = iter.next();
			remove(current);
		}
		to = 0;
	}
	
	/**
	 * Method for comparing the list and the generic element received as parameter.
	 * 
	 * @param o - element to be compared with the list
	 * @return true if the list and the object received as parameter contains the same elements in the same order; false otherwise
	 */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ListAdapter)) return false;
        ListAdapter adapted_o = (ListAdapter) o;
        if(this.size() != adapted_o.size()) return false;		
        HListIterator iter = listIterator();
        HListIterator iterAdapted = adapted_o.listIterator();
        Object current;
        Object currentAdapted;
        int i = 0;
        while(iter.hasNext()) {								
        	current = iter.next();
        	currentAdapted = iterAdapted.next();
        	if(current != currentAdapted) return false;
        	i++;
        }
        return true;

    }
    
    /**
     * Method that generates a hashCode of the list for comparison, with other list, purposes. <br>
     * If two list are equals ({@link #equals(Object)}), their hashCode need to be the same.
     * 
     * @return the hashCode of the list
     */
    @Override
    public int hashCode() {			
        int hash = 5;
        HListIterator iter = listIterator();
        Object current;
        while(iter.hasNext()) {
        	current = iter.next();
        	hash = 31 * hash + (current != null ? current.hashCode() : 0);
        }
        return hash;
    }
    
    /**
     * Method that get the element in the position specified by the index received as parameter.
     * 
     * @param index - index of the element to return
     * @return the element of the list in the position specified by the index
     * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
     * 										OR bigger than or equal to the size of the list
     */
	@Override
	public Object get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		return list.elementAt(from + index);
	}
	
	/**
	 * Method that replace an element from the list at the specified index with the element received as parameters.
	 * 
	 * @param index - index of the element to replace
	 * @param element - element that will replace the element at the specified index of the list
	 * @return the replaced element
	 * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
	 * 										OR bigger than or equal to the size of the list
	 */
	@Override
	public Object set(int index, Object element) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		Object prev = get(index);
		list.setElementAt(element, from + index);
		return prev;
	}
	
	/**
	 * Method that add an element in a specific position of the list specified by the index received as parameter. <br>
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right. <br>
	 * This method invoke {@link #updateIndex(int)} to update the size of the list and to keep the connections with the parent. 
	 * 
	 * @param index - position in the list where to insert the element
	 * @param element - element to be added 
	 * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
	 * 										OR bigger than the size of the list
	 */
	@Override
	public void add(int index, Object element) {
		if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
		list.insertElementAt(element, index + from);
		updateIndex(1);		
	}
	
	/**
	 * Method that remove and element from the list at the index received as parameter. <br>
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list. <br>
	 * This method invoke {@link #updateIndex(int)} to update the size of the list and to keep the connections with the parent.  
	 * 
	 * @param index - index of the element to delete
	 * @return the deleted element
	 * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
	 * 										OR bigger than or equal to the size of the list
	 */
	@Override
	public Object remove(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		Object prev = get(index);
		list.removeElementAt(from + index);
		updateIndex(-1);
		return prev;
	}
	
	/**
	 * Method that find in the list the position of the first occurrence of the element received as parameter.
	 * 
	 * @param o - the element to find
	 * @return the position of the researched element; -1 if the list doesn't contains the element
	 * @throws NullPointerException if the element received as parameter is null
	 */
	@Override
	public int indexOf(Object o) {						
		if(o == null) throw new NullPointerException();
		HListIterator iter = listIterator();
		Object current;
		while(iter.hasNext()) {
			current = iter.next();
			if(o == null && current == null) return iter.previousIndex();		
			else if(current.equals(o)) return iter.previousIndex();
		}
		return -1;
	}
	
	/**
	 * Method that find in the list the position of the last occurrence of the element received as parameter.
	 * 
	 * @param o - the element to find
	 * @return the position of the researched element; -1 if the list doesn't contains the element
	 * @throws NullPointerException if the element received as parameter is null
	 */
	@Override
	public int lastIndexOf(Object o) {								 
		if(o == null) throw new NullPointerException();
		HListIterator iter = listIterator(size());
		Object current;
		while(iter.hasPrevious()) {												
			current = iter.previous();
			if(o == null && current == null) return iter.nextIndex();	
			else if(current.equals(o)) return iter.nextIndex();
		}
		return -1;
	}
	
	/**
	 * Method that returns a list iterator of the elements in this list (in proper sequence).
	 * 
	 * @return a list iterator of the elements in this list (in proper sequence)
	 */
	@Override
	public HListIterator listIterator() {
		return new ListAdapterIterator();
	}
	
	/**
	 * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
	 * The specified index indicates the first element that would be returned by an initial call to the next method.
	 * 
	 * @param index - the first element that would be returned by an initial call to the next method 
	 * @return  a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list
	 */
	@Override
	public HListIterator listIterator(int index) {
		return new ListAdapterIterator(index);
	}
	
	/**
	 * Method for creating a subList from this list. <br>
	 * This list will become a parent of the new instance of ListAdapter returned from this method.
	 * 
	 * @param fromIndex - low endpoint (exclusive) of the subList
	 * @param toIndex - high endpoint (exclusive) of the subList
	 * @return a new ListAdapter instance (the subList of this list)
	 * @throws IndexOutOfBoundsException if the low endpoint (fromIndex) is less than zero 
	 * 									    OR if the high endpoint (toIndex) is greater than the size of this list 
	 * 										OR if the low endpoint (fromIndex) is bigger than the high endpoint (toIndex)
	 */
	@Override
	public HList subList(int fromIndex, int toIndex) {
		if(fromIndex < 0 || toIndex > size() || fromIndex > toIndex) throw new IndexOutOfBoundsException(); 
		return new ListAdapter(fromIndex + from, toIndex + from , this);
	}
	
	
	/**
	 * 
	 * @author Barison Hajjaj (Matricola: 2014481)
	 * <br> <br>
	 * This class implements the HListIterator and HIterator interfaces and will recreate the iterator methods of the J2SE 1.4.2 List, working on a Vector element defined in CLDC 1.1.
	 * <br> <br>
	 * The ListAdapterIterator class provide a default constructor which make starts the iterator at the beginning of the list 
	 * and a parameterized constructor which make starts the iterator at the indicated point.
	 * <br> <br> 
	 * The ListAdapterIterator class provides inspect methods like: next, previous, hasNext, hasPrevious, nextIndex, previousIndex. <br>
	 * The class also provide modifiers method like: add, remove, set.
	 * <br> <br>
	 * @see HIterator
	 * @see HListIterator
	 *
	 */
	private class ListAdapterIterator implements HListIterator{
		
		/**
		 * Variable which indicate the starting position of the iterator.
		 */
		private int current;
		
		/**
		 * This variable used to keep track of the last invoked action. This is necessary to implement the modifiers methods. <br> <br>
		 * Values assumed by the variable:
		 *  <br>
		 * <b> -1 </b> if the previous last method invoked between next() and previous() was previous() 
		 *  <br>
		 *  <br>
		 * <b> 0 </b>  no previous actions between next() and previous()
		 *  <br>
		 *  <br>
		 * <b> 1 </b> if the previous last method invoked between next() and previous() was next() 
		 *  <br>
		 * 
		 */
		private int prevAction = 0;

		/**
		 * Position the iterator at the start of the list.
		 */
		public ListAdapterIterator() {
			current = 0;
		}
		
		/**
		 * Position the iterator at the specified position of the list.
		 * 
		 * @param index - starting position of the iterator 
		 * @throws IndexOutOfBoundsException if the index received as parameter is less than zero 
		 * 										OR bigger than the size of the list 
		 */
		public ListAdapterIterator(int index) {
			if(index < 0 || index > size()) throw new IndexOutOfBoundsException();
			current = index;
		}
		
		/**
		 * Method that check if there are other elements after the current position of the iterator.
		 * 
		 * @return true if the iterator is not yet positioned at the end of the list before the call to this method; false otherwise 
		 */
		@Override
		public boolean hasNext() {
			return current < size();
		}
		
		/**
		 * Method that move forward the iterator by one position a time inside the list.
		 * 
		 * @return the next element in the list 
		 * @throws NoSuchElementException if the iterator is at the end of the list 
		 */
		@Override
		public Object next() {
			if(!hasNext()) throw new NoSuchElementException();
			Object n = get(current);
			current++;
			prevAction = 1;
			return n;
		}

		/**
		 * Method that check if there are other elements before the current position of the iterator
		 * 
		 * @return true if the iterator is not yet positioned at the start of the list before the call to this method; false otherwise 
		 */
		@Override
		public boolean hasPrevious() {
			return (current-1) >= 0;
		}

		/**
		 * Method that move backward the iterator by one position a time inside the list.
		 * 
		 * @return the previous element in the list 
		 * @throws NoSuchElementException if the iterator is at the start of the list 
		 */
		@Override
		public Object previous() {
			if(!hasPrevious()) throw new NoSuchElementException();
			Object n = get(current - 1);
			current--;
			prevAction = -1;
			return n;
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent call to {@link #next()}. <br>
		 * Returns list size if the list iterator is at the end of the list.
		 * 
		 * @return the index of the element that would be returned by a subsequent call to next
		 */
		@Override
		public int nextIndex() {
			if(hasNext()){
				return current;
			}
			return size();
		}
		
		/**
		 * Returns the index of the element that would be returned by a subsequent call to {@link #previous()}. <br>
		 * Returns -1 if the list iterator is at the start of the list.
		 * 
		 * @return the index of the element that would be returned by a subsequent call to previous
		 */
		@Override
		public int previousIndex() {
			if(hasPrevious()){
				return current - 1;
			}
			return -1;
		}
		
		/**
		 * Method that remove the last visited element by the iterator.
		 * 
		 * @throws IllegalStateException() if neither next nor previous have been called, or remove or add have been called after the last call to {@link #next()}
		 */
		@Override
		public void remove() {
			if(prevAction == 0) throw new IllegalStateException();
			if(prevAction == -1) {				   
				ListAdapter.this.remove(current);
				prevAction = 0;		
			} else if(prevAction == 1) {		
				ListAdapter.this.remove(current - 1);		
				prevAction = 0;
			}
		}
		
		/**
		 * Method that replace the last visited element by the iterator with the element received as parameters.
		 * 
		 * @param o - element that will replace the last visited element by the iterator
		 * @throws IllegalStateException() if neither next nor previous have been called, or remove or add have been called after the last call to {@link #next()} or {@link #previous()}.
		 */
		@Override
		public void set(Object o) {		
			if(prevAction == 0) throw new IllegalStateException();
			if(prevAction == -1) {				  
				ListAdapter.this.set(current, o);
			} else if(prevAction == 1) {		
				ListAdapter.this.set(current - 1, o);		
			}
		}
		
		/**
		 * Method that insert the element received as parameter immediately before the next element that would be returned by next, if any, and after the next element 
		 * that would be returned by previous, if any.
		 * 
		 * @param o - element to be added in the list
		 */
		@Override
		public void add(Object o) {
			ListAdapter.this.add(current, o);
			current++;
			prevAction = 0;
		}
		
	}
}
