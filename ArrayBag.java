
import java.util.Arrays;

/**
 * This program implements BagInterface<T> and shows the codes for each method
 * in BagInterface<T>
 *
 */
public class ArrayBag<T> implements BagInterface<T> {

    public T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity];
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } else {
            throw new IllegalStateException("Attempted to create a bag whose capactiy exceeds allowed maximum");
        }

    }

    /**
     * This method get the current size of the bag
     *
     * @return int This returns the number of elements in the bag
     */
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    /**
     * This method checks if the bag is empty
     *
     * @return boolean This returns true if the bag contains 0 elements and
     * false if the bag has an element
     */
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    /**
     * This method add an element to the bag
     *
     * @param newEntry This parameter is used to shows what the user wants to
     * add in the bag
     * @return boolean T This return true if the element the user wants to add
     * is sucessful.
     */
    @Override
    public boolean add(T newEntry) {
        checkIntegrity();
        boolean result = true;
        if (isArrayFull()) {
            doubleCapacity();

        }
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return result;
    }

    /**
     * This method is the remove the last entry in the bag
     *
     * @return T This return the element that the method removed
     */
    @Override
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);
        return result;

    }

    /**
     * This method removes a specific element in the bag
     *
     * @param anEntry This parameter is what the user wants to remove from the
     * bag
     * @return boolean This returns true or false
     */
    @Override
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    /**
     * This method clears the whole bag. It does not retun any value
     */
    @Override
    public void clear() {
        while (isEmpty()) {
            remove();
        }
    }

    /**
     * This method get the frequency of element in the bag
     *
     * @param anEntry This parameter shows which element the user want frequency
     * of
     * @return int This returns the number of time that element is present in
     * the bag
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(bag[i])) {
                counter++;

            }
        }
        return counter;
    }

    /**
     * This method shows if a specific element is contained in the abg
     *
     * @param anEntry This parameter shows which element the user want check if
     * its in the bag
     * @return boolean This returns true if anEntry is in the bag or false if
     * anEntry is not in the bag
     */
    @Override
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /**
     * This method puts the element of the bag into an array
     *
     * @return T[] this returns the array
     */
    @Override
    public T[] toArray() {
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        }
        return result;

    }

    /**
     * This method removes the element in the bag
     *
     * @param givenIndex This parameter is called in the both of the remove
     * methods
     * @return T this returns the reult of the method
     */
    private T removeEntry(int givenIndex) {
        T result = null;
        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex]; // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null; // Remove last entry
            numberOfEntries--;
        } // end if
        return result;
    }

    /**
     * This method get the index of the bag
     *
     * @param anEntry This paramater is used in the method contains and remove(T
     * anEntry)
     * @return It returns the loaction of the elemen in the bag
     */
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;
        while (!found && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }
        return where;
    }

    /**
     * This check if the capacity of the back is larger or lower then the
     * MAX_CAPACITY
     *
     * @param capacity This parameter is used in the doubleCapactiy method
     */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose capacity exeeds allowed maxium of " + MAX_CAPACITY);
        }

    }

    /**
     * This method doubles the capacity of the bag if the bag's capacity is
     * lower than the MAX_CAPACITY
     */
    private void doubleCapacity() {
        int newLength = 2 * bag.length;
        checkCapacity(newLength);
        bag = Arrays.copyOf(bag, newLength);

    }

    /**
     * This method checks if the array is full
     *
     * @return boolean This return true if the array is full or false if the
     * back is not full
     */
    private boolean isArrayFull() {
        return numberOfEntries == bag.length;
    }

    /**
     * This method checks if the integrity of the program is ok
     */
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt");
        }
    }
}
