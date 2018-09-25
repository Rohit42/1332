/**
 * Your implementation of a circular singly linked list.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;

    @Override
    public void addAtIndex(T data, int index) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is negative!");
        }
        if (index > size) {
            throw new IndexOutOfBoundsException("Index is greater than size!");
        }
        if (index == 0) {
            addToFront(data);
            return;
        }
        if (index == size) {
            addToBack(data);
            return;
        }
        LinkedListNode<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.getNext();
        }
        LinkedListNode<T> oldNode = previous.getNext();
        LinkedListNode<T> newNode = new LinkedListNode<T>(data, oldNode);
        previous.setNext(newNode);
        size++;

    }

    @Override
    public void addToFront(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (size == 0) {
            head = new LinkedListNode<>(data, head);
            head.setNext(head);
            size++;
            return;
        }
        //Store the data
        T frontData = head.getData();
        //Create the next node
        LinkedListNode<T> newNode =
                new LinkedListNode<>(frontData, head.getNext());
        head.setData(data);
        head.setNext(newNode);
        size++;

    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null!");
        }
        if (size == 0) {
            addToFront(data);
            return;
        }
        //Store the data
        T frontData = head.getData();
        //Create the next node
        LinkedListNode<T> newFront =
                new LinkedListNode<>(frontData, head.getNext());
        head.setData(data);
        head.setNext(newFront);
        //Change new head
        head = newFront;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is negative!");
        }
        if (index >= size) {
            String s = "Index is greater or equal to size!";
            throw new IndexOutOfBoundsException(s);
        }
        if (index == 0) {
            return removeFromFront();
        }
        if (index == size - 1) {
            return removeFromBack();
        }
        LinkedListNode<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.getNext();
        }
        LinkedListNode<T> removed = previous.getNext();
        previous.setNext(removed.getNext());
        size--;
        return removed.getData();
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            clear();
            return data;
        }
        if (head.getData() == null) {
            return null;
        }
        T data = head.getData();
        head.setData(head.getNext().getData());
        head.setNext(head.getNext().getNext());
        size--;
        return data;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        if (size == 1) {
            T data = head.getData();
            clear();
            return data;
        }
        LinkedListNode<T> newEnd = head;
        for (int i = 1; i < size - 1; i++) {
            newEnd = newEnd.getNext();
        }
        T data = newEnd.getNext().getData();
        newEnd.setNext(head);
        size--;
        return data;
    }

    @Override
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null!");
        }
        if (head == null) {
            return null;
        }
        LinkedListNode<T> previous = null;
        LinkedListNode<T> previousIterator = head;
        LinkedListNode<T> current = head.getNext();
        for (int i = 1; i < size; i++) {
            if (current.getData().equals(data)) {
                previous = previousIterator;
            }
            previousIterator = previousIterator.getNext();
            current = current.getNext();
        }
        if (head.getData().equals(data) && previous == null) {
            return removeFromFront();
        }
        if (previous != null) {
            T removedData = previous.getNext().getData();
            previous.setNext(previous.getNext().getNext());
            size--;
            return removedData;
        }
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index is negative!");
        }
        if (index >= size) {
            String s = "Index is greater or equal to size!";
            throw new IndexOutOfBoundsException(s);
        }
        if (index == 0) {
            return head.getData();
        }
        LinkedListNode<T> previous = head;
        for (int i = 0; i < index - 1; i++) {
            previous = previous.getNext();
        }
        return previous.getNext().getData();
    }

    @Override
    public Object[] toArray() {
        Object[] data = new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            data[i] = current.getData();
            current = current.getNext();
        }
        return data;
    }

    @Override
    public boolean isEmpty() {
        return (head == null);
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}