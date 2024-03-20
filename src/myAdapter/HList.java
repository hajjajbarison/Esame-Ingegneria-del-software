package myAdapter;


/**
 * 
 * @author Barison Hajjaj (Matricola: 2014481)
 * <br> <br>
 * Java2 Collections Framework version 1.4.2 (J2SE 1.4.2)
 * <br> <br>
 *  
 * An ordered collection (also known as a sequence). The user of this interface has precise control over where
 * in the list each element is inserted. The user can access elements by their integer index (position in the list), and search for elements in the list.
 * <br> <br>
 * Unlike sets, lists typically allow duplicate elements. 
 * More formally, lists typically allow pairs of elements e1 and e2 such that e1.equals(e2), and they typically allow multiple null elements if they allow null elements at all.
 * It is not inconceivable that someone might wish to implement a list that prohibits duplicates, by throwing runtime exceptions when the user attempts to insert them,
 * but we expect this usage to be rare.
 * <br> <br>
 * The List interface places additional stipulations, beyond those specified in the Collection interface, on the contracts of the iterator, add, remove, equals, and hashCode methods.
 * Declarations for other inherited methods are also included here for convenience.
 * <br> <br>
 * The List interface provides four methods for positional (indexed) access to list elements. Lists (like Java arrays) are zero based.
 * Note that these operations may execute in time proportional to the index value for some implementations (the LinkedList class, for example).
 * Thus, iterating over the elements in a list is typically preferable to indexing through it if the caller does not know the implementation.
 * <br> <br>
 * The List interface provides a special iterator, called a ListIterator, that allows element insertion and replacement, and bidirectional access in addition to the normal 
 * operations that the Iterator interface provides.
 * A method is provided to obtain a list iterator that starts at a specified position in the list.
 * <br> <br>
 * The List interface provides two methods to search for a specified object. From a performance standpoint, these methods should be used with caution. 
 * In many implementations they will perform costly linear searches.
 * <br> <br>
 * The List interface provides two methods to efficiently insert and remove multiple elements at an arbitrary point in the list.
 * <br> <br>
 * Note: While it is permissible for lists to contain themselves as elements, extreme caution is advised: the equals and hashCode methods are no longer well defined on a such a list.
 * <br> <br>
 * Some list implementations have restrictions on the elements that they may contain. For example, some implementations prohibit null elements, and some have restrictions on the types 
 * of their elements. Attempting to add an ineligible element throws an unchecked exception, typically NullPointerException or ClassCastException.
 * Attempting to query the presence of an ineligible element may throw an exception, or it may simply return false; some implementations will exhibit the former behavior and some
 * will exhibit the latter. More generally, attempting an operation on an ineligible element whose completion would not result in the insertion of an ineligible element
 * into the list may throw an exception or it may succeed, at the option of the implementation. Such exceptions are marked as "optional" in the specification for this interface.
 *  
 *
 * @see HCollection
 * 
 */
public interface HList extends HCollection {
	
	int size();
	
	boolean isEmpty();
	
	boolean contains(Object o);
	
	HIterator iterator();
	
	Object[] toArray();
	
	Object[] toArray(Object[] a);
	
	boolean add(Object o);
	
	boolean remove(Object o);
	
	boolean containsAll(HCollection c);
	
	boolean addAll(HCollection c);
	
	/**
	 * Inserts all of the elements in the specified collection into this list at the specified position (optional operation). 
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices). 
	 * The new elements will appear in this list in the order that they are returned by the specified collection's iterator. 
	 * The behavior of this operation is unspecified if the specified collection is modified while the operation is in progress. (Note that this will occur if the specified collection is this list, and it's nonempty.)
	 * 
	 * @param index - index at which to insert first element from the specified collection
	 * @param c - elements to be inserted into this list
	 * @return true if this list changed as a result of the call
	 * @throws UnsupportedOperationException if the addAll method is not supported by this list.
	 * @throws ClassCastException if the class of one of elements of the specified collection prevents it from being added to this list.
	 * @throws NullPointerException if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null.
	 * @throws IllegalArgumentException if some aspect of one of elements of the specified collection prevents it from being added to this list.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size()).
	 */
	boolean addAll(int index, HCollection c);
	
	boolean removeAll(HCollection c);
	
	boolean retainAll(HCollection c);
	
	void clear();
	
	boolean equals(Object o);
	
	int hashCode();
	
	/**
	 * Returns the element at the specified position in this list
	 * 
	 * @param index - index of element to return
	 * @return the element at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range(index &lt; 0 || index &gt;= size())
	 */
	Object get(int index);
	
	/**
	 * Replaces the element at the specified position in this list with the specified element (optional operation)
	 * 
	 * @param index - index of element to replace
	 * @param element - element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws UnsupportedOperationException if the set method is not supported by this list.
	 * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
	 * @throws NullPointerException if the specified element is null and this list does not support null elements.
	 * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size()).
	 */
	Object set(int index, Object element);
	
	/**
	 * Inserts the specified element at the specified position in this list (optional operation). 
	 * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
	 * 
	 * @param index - index at which the specified element to be inserted
	 * @param element - element to be inserted
	 * @throws UnsupportedOperationException if the add method is not supported by this list.
	 * @throws ClassCastException if the class of the specified element prevents it from being added to this list.
	 * @throws NullPointerException if the specified element is null and this list does not support null elements.
	 * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size()).
	 */
	void add(int index, Object element);
	
	/**
	 * Removes the element at the specified position in this list (optional operation). 
	 * Shifts any subsequent elements to the left (subtracts one from their indices). Returns the element that was removed from the list.
	 * 
	 * @param index - the index of the element to removed
	 * @return the elements previously at the specified position
	 * @throws UnsupportedOperationException if the remove method is not supported by this list.
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt;= size()).
	 */
	Object remove(int index);
	
	/**
	 * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element. 
	 * More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * 
	 * @param o - element to search for
	 * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element
	 * @throws ClassCastException if the type of the specified element is incompatible with this list (optional).
	 * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
	 */
	int indexOf(Object o);
	
	/**
	 * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element. 
	 * More formally, returns the highest index i such that (o==null ? get(i)==null : o.equals(get(i))), or -1 if there is no such index.
	 * 
	 * @param o - element to search for
	 * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element
	 * @throws ClassCastException if the type of the specified element is incompatible with this list (optional).
	 * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
	 */
	int lastIndexOf(Object o);
	
	/**
	 * Returns a list iterator of the elements in this list (in proper sequence)
	 * 
	 * @return a list iterator of the elements in this list (in proper sequence)
	 */
	HListIterator listIterator();
	
	/**
	 * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list. 
	 * The specified index indicates the first element that would be returned by an initial call to the next method. 
	 * An initial call to the previous method would return the element with the specified index minus one.
	 * 
	 * @param index - index of first element to be returned from the list iterator (by a call to the next method)
	 * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list
	 * @throws IndexOutOfBoundsException if the index is out of range (index &lt; 0 || index &gt; size()).
	 */
	HListIterator listIterator(int index);
	
	/**
	 * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive. (If fromIndex and toIndex are equal, the returned list is empty.) 
	 * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa. 
	 * The returned list supports all of the optional list operations supported by this list. 
	 * <br> <br>
	 * This method eliminates the need for explicit range operations (of the sort that commonly exist for arrays).
	 * Any operation that expects a list can be used as a range operation by passing a subList view instead of a whole list. 
	 * For example, the following idiom removes a range of elements from a list: 
	 * <br>
	 * 		list.subList(from, to=.clear():
	 * <br>
	 * Similar idioms may be constructed for indexOf and lastIndexOf, and all of the algorithms in the Collections class can be applied to a subList. 
	 * <br> <br>
	 * The semantics of the list returned by this method become undefined if the backing list (i.e., this list) is structurally modified in any way other than via the returned list. 
	 * (Structural modifications are those that change the size of this list, or otherwise perturb it in such a fashion that iterations in progress may yield incorrect results.)
	 * 
	 * @param fromIndex - low endpoint (exclusive) of the subList
	 * @param toIndex - high endpoint (exclusive) of the subList
	 * @return a view of the specified range within this list
	 * @throws IndexOutOfBoundsException for an illegal endpoint index value (fromIndex &lt; 0 || toIndex &gt; size || fromIndex &gt; toIndex).
	 */
	HList subList(int fromIndex, int toIndex);
	
	
	
}



