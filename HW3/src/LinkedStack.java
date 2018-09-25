/**
 * Your implementation of a linked stack. It should NOT be circular.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Stack is empty");
        }
        T data = head.getData();
        head = head.getNext();
        size = size  - 1;
        return data;

    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        LinkedNode<T> newHead = new LinkedNode<T>(data, head);
        size = size + 1;
        head = newHead;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return head.getData();
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

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}