/**
 * Your implementation of an ArrayList.
 *
 * @author ROHIT MITTAPALLI
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        size = 0;
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data was null");
        }
        if (index > size || index < 0) {
            throw new java.lang.IndexOutOfBoundsException("Index was illegal");
        }
        if (backingArray.length < size + 1) {
            T[] copyArray = (T[]) new Object[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                copyArray[i] = backingArray[i];
            }
            backingArray = copyArray;
        }

        for (int i = size; i > index; i--) {
            backingArray[i] = backingArray[i - 1];
        }
        size++;
        backingArray[index] = data;
    }

    @Override
    public void addToFront(T data) {
        addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data) {
        addAtIndex(size, data);
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new java.lang.IndexOutOfBoundsException("Index was illegal");
        }
        if (size == 0) {
            return null;
        }
        if (backingArray[index] == null) {
            return null;
        }
        T info = backingArray[index];
        for (int i = index; i < backingArray.length - 1; i++) {
            backingArray[i] = backingArray[i + 1];
        }
        backingArray[size - 1] = null;
        size--;
        return info;
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(size - 1);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index was illegal");
        }
        return backingArray[index];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
