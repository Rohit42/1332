import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a max heap.
 *
 * @author ROHIT MITTAPALLI
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("List is null");
        }
        size = data.size();
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) == null) {
                throw new IllegalArgumentException("Data member is null");
            }
            backingArray[i + 1] = data.get(i);
        }
        for (int i = size / 2; i > 0; i--) {
            heapify(i);
        }
    }

    /**
     * Auxillary method for add
     * @param i root of heap
     */
    private void heapify(int i) {
        int max = i;
        int left = 2 * i;
        int right = 2 * i + 1;
        if (backingArray[left] != null
                && backingArray[left].compareTo(backingArray[i]) > 0) {
            max = left;
        }
        if (right < backingArray.length && backingArray[right] != null
                && backingArray[right].compareTo(backingArray[max]) > 0) {
            max = right;
        }
        if (max != i) {
            T temp = backingArray[i];
            backingArray[i] = backingArray[max];
            backingArray[max] = temp;

            heapify(max);
        }
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (size >= backingArray.length - 1) {
            T[] temp =  (T[]) new Comparable[backingArray.length * 2];
            for (int i = 0; i < backingArray.length; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size + 1] = item;
        int index = size + 1;
        int parentIndex = (index) / 2;
        boolean inOrder = false;
        while (parentIndex >= 1 && !inOrder) {
            if (backingArray[parentIndex].compareTo(backingArray[index]) < 0) {
                T temp = backingArray[parentIndex];
                backingArray[parentIndex] = backingArray[index];
                backingArray[index] = temp;
            } else {
                inOrder = true;
            }
            index = parentIndex;
            parentIndex = parentIndex / 2;
        }
        size++;

    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("Heap is empty");
        }
        T data = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        int index = 1;
        boolean inOrder = false;
        boolean hasChildren = (backingArray[2 * index] != null
                || backingArray[2 * index + 1] != null);
        while (hasChildren && !inOrder) {
            if (backingArray[2 * index + 1] == null) {
                int a = 2 * index;
                if (backingArray[a].compareTo(backingArray[index]) > 0) {
                    T temp = backingArray[2 * index];
                    backingArray[2 * index] = backingArray[index];
                    backingArray[index] = temp;
                    index = 2 * index;
                } else {
                    inOrder = true;
                }
            } else {
                int a = 2 * index;
                if (backingArray[a + 1].compareTo(backingArray[index]) > 0
                        || backingArray[a].compareTo(backingArray[index]) > 0) {
                    if (backingArray[a + 1].compareTo(backingArray[a]) > 0) {
                        T temp = backingArray[2 * index + 1];
                        backingArray[2 * index + 1] = backingArray[index];
                        backingArray[index] = temp;
                        index = 2 * index + 1;
                    } else {
                        T temp = backingArray[2 * index];
                        backingArray[2 * index] = backingArray[index];
                        backingArray[index] = temp;
                        index = 2 * index;
                    }
                } else {
                    inOrder = true;
                }
            }
            if (2 * index > size) {
                hasChildren = false;
            } else if (!inOrder) {
                int b = 2 * index;
                hasChildren = (backingArray[b] != null
                        || backingArray[b + 1] != null);
            }
        }
        size--;
        return data;
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
