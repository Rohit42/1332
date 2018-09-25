import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;
/**
 * Your implementation of a binary search tree.
 *
 * @author YOUR NAME HERE
 * @userid YOUR USER ID HERE (i.e. gburdell3)
 * @GTID YOUR GT ID HERE (i.e. 900000000)
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("List is Null");

        }
        for (T iter : data) {
            if (iter == null) {
                throw new IllegalArgumentException("Element is Null");
            } else {
                add(iter);
            }
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is Null");
        }
        root = addRecursive(root, data);

    }

    /**
     * The recrusive helper function to iterate through the tree
     * Adds elements to the tree
     * @param root the root of the current iteration of the tree
     * @param data the data to be added to the tree
     * @return node that was added
     */
    private BSTNode<T> addRecursive(BSTNode root, T data) {
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
            return root;
        }
        if (root.getData().compareTo(data) < 0) {
            root.setRight(addRecursive(root.getRight(), data));
        } else if (root.getData().compareTo(data) > 0) {
            root.setLeft(addRecursive(root.getLeft(), data));
        }

        return root;

    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is Null");
        }
        if (root == null) {
            throw new NoSuchElementException("Tree is empty");
        }
        BSTNode dummyNode = new BSTNode(null);
        root = removeRecursive(root, data, dummyNode);
        size--;
        return (T) dummyNode.getData();
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Removes elements in the tree and returns what elements have been removed
     * @param root the root of the current iteration of the tree
     * @param data the data to be added to the tree
     * @param  dummyNode the dummynode used to store the data during recursion
     * @throws NoSuchElementException if data does not exist
     * @return Node that was removed
     */
    private BSTNode removeRecursive(BSTNode root, T data, BSTNode dummyNode) {
        if (root == null) {

            return root;
        }
        if (root.getData().compareTo(data) < 0) {

            root.setRight(removeRecursive(root.getRight(), data, dummyNode));
        } else if (root.getData().compareTo(data) > 0) {
            root.setLeft(removeRecursive(root.getLeft(), data, dummyNode));
        } else {

            if (!root.getData().equals(data)) {
                throw new java.util.NoSuchElementException("No Element found");
            }
            dummyNode.setData(root.getData());
            if (root.getLeft() == null) {
                return root.getRight();
            }
            if (root.getRight() == null) {
                return root.getLeft();
            }



            BSTNode copy = root.getLeft();
            T maxV = (T) copy.getData();
            while (copy.getRight() != null) {
                maxV = (T) copy.getRight().getData();
                copy = copy.getRight();
            }
            BSTNode<T> fakeDummy = new BSTNode<>(null);
            root.setData(maxV);
            root.setLeft(removeRecursive(root.getLeft(), maxV, fakeDummy));

        }
        return root;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Argument is Null");
        }
        if (root == null) {
            throw new NoSuchElementException("No element in tree");
        }
        return getRecursive(root, data);
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Gets the data sent in
     * @param root the root of the current iteration of the tree
     * @param data the data to be got
     * @throws NoSuchElementException if the element is not in the tree
     * @return the data being searched for
     */
    private T getRecursive(BSTNode root, T data) {
        if (root.getData().compareTo(data) < 0) {
            if (root.getRight() == null) {
                throw new NoSuchElementException("No element in tree");
            }
            return getRecursive(root.getRight(), data);
        } else if (root.getData().compareTo(data) > 0) {
            if (root.getLeft() == null) {
                throw new NoSuchElementException("No element in tree");
            }
            return getRecursive(root.getLeft(), data);
        }
        if (!root.getData().equals(data)) {
            throw new NoSuchElementException("No element in tree");
        }
        return (T) root.getData();

    }

    @Override
    public boolean contains(T data) {
        try {
            get(data);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<>();
        return preorderRecursive(root, list);
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Prints out pre ordering of the tree
     * @param root the root of the current iteration of the tree
     * @param list stores the ordering of the iteration
     * @return the List of the order
     */
    public List<T> preorderRecursive(BSTNode root, ArrayList<T> list) {
        if (root == null) {
            return list;
        }
        list.add((T) root.getData());
        preorderRecursive(root.getLeft(), list);
        preorderRecursive(root.getRight(), list);

        return list;
    }

    @Override
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<>();
        return postorderRecursive(root, list);
    }
    /**
     * The recrusive helper function to iterate through the tree
     * Prints out post ordering of the tree
     * @param root the root of the current iteration of the tree
     * @param list stores the ordering of the iteration
     * @return the List of the order
     */
    public List<T> postorderRecursive(BSTNode root, ArrayList<T> list) {
        if (root == null) {
            return list;
        }
        postorderRecursive(root.getLeft(), list);
        postorderRecursive(root.getRight(), list);
        list.add((T) root.getData());

        return list;
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<>();
        return inorderRecursive(root, list);
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Prints out in order ordering of the tree
     * @param root the root of the current iteration of the tree
     * @param list stores the ordering of the iteration
     * @return the List of the order
     */
    public List<T> inorderRecursive(BSTNode root, ArrayList<T> list) {
        if (root == null) {
            return list;
        }
        inorderRecursive(root.getLeft(), list);
        list.add((T) root.getData());
        inorderRecursive(root.getRight(), list);
        return list;
    }


    @Override
    public List<T> levelorder() {

        Queue<BSTNode> queue = new LinkedList<BSTNode>();
        ArrayList list = new ArrayList();
        if (root == null) {
            return list;
        }
        int count = 0;
        queue.add(root);
        while (!queue.isEmpty()) {
            count = queue.size();
            while (count > 0) {
                BSTNode node = queue.remove();
                list.add(node.getData());
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
                count--;
            }
        }
        return list;
    }

    @Override
    public int distanceBetween(T data1, T data2) {
        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        BSTNode<T> ancestor = ancestor(root, data1, data2);

        return findAncestorDifference(ancestor, data1)
                + findAncestorDifference(ancestor, data2);
    }

    /**
     * Finds distance between the ancestor and the child node be searched on
     * @param ancestor a parent to the data being searched up
     * @param data the data to find in the tree
     * @throws NoSuchElementException if either element is not in the tree
     * @return the difference in height of the ancestor and the data
     */
    private int findAncestorDifference(BSTNode ancestor, T data) {
        if (ancestor == null) {
            throw new NoSuchElementException("1 or more elements do not exist");
        }
        if (ancestor.getData().equals(data)) {
            return 0;
        }
        if (ancestor.getData().compareTo(data) < 0) {
            return findAncestorDifference(ancestor.getRight(), data) + 1;
        } else if (ancestor.getData().compareTo(data) > 0) {
            return findAncestorDifference(ancestor.getLeft(), data) + 1;
        }
        throw new NoSuchElementException("One or more elements do not exist");

    }
    /**
     * Finds lowest common ancestor between the nodes containing this data
     * @param root the root node the search is happening under
     * @param data1 the first data to find in the tree
     * @param data2 the first data to find in the tree
     * @return the ancestor node of the two inputs
     */
    private BSTNode ancestor(BSTNode root, T data1, T data2) {
        if (root == null) {
            return null;
        }
        if (root.getData().compareTo(data1) > 0
                && root.getData().compareTo(data2) > 0) {
            return ancestor(root.getLeft(), data1, data2);
        }
        if (root.getData().compareTo(data1) < 0
                && root.getData().compareTo(data2) < 0) {
            return ancestor(root.getRight(), data1, data2);
        }
        return root;
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return heightRecursive(root) - 1;

    }

    /**
     * The recrusive helper function to iterate through the tree
     * Gets the height of the tree
     * @param node for recursive iteration
     * @return the height of the tree in that iteration
     */
    private int heightRecursive(BSTNode node) {
        if (node == null) {
            return 0;
        }
        int a = heightRecursive(node.getLeft());
        int b = heightRecursive(node.getRight());
        return Math.max(a, b) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
