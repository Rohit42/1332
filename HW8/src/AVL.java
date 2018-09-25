import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Rohit Mittapalli
 * @userid rmittapalli3
 * @GTID 903309727
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Collection is null");
        }

        for (T d : data) {
            if (d == null) {
                throw new IllegalArgumentException("Data in "
                        + "collection is null");
            }
            add(d);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            root = new AVLNode<>(data);
            size++;
            return;
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
    private AVLNode addRecursive(AVLNode root, T data) {
        //BST Insertion
        if (root == null) {
            size++;
            return new AVLNode(data);
        }
        if (root.getData().compareTo(data) < 0) {
            root.setRight(addRecursive(root.getRight(), data));
        }
        if (root.getData().compareTo(data) > 0) {
            root.setLeft(addRecursive(root.getLeft(), data));
        }
        if (root.getData().equals(data)) {
            return root;
        }
        //Update Height
        updateHeight(root);
        updateBalanceFactor(root);
        //Left Rotation
        if (root.getBalanceFactor() == -2
                && (root.getRight().getBalanceFactor() == 0
                || root.getRight().getBalanceFactor() == -1)) {
            return leftRotation(root);

        }
        //Right Rotation
        if (root.getBalanceFactor() == 2
                && (root.getLeft().getBalanceFactor() == 0
                || root.getLeft().getBalanceFactor() == 1)) {
            return rightRotation(root);
        }
        //Left - Right
        if (root.getBalanceFactor() == 2
                && root.getLeft().getBalanceFactor() == -1) {
            root.setLeft(leftRotation(root.getLeft()));
            return rightRotation(root);
        }
        //Right - Left
        if (root.getBalanceFactor() == -2
                && root.getRight().getBalanceFactor() == 1) {
            root.setRight(rightRotation(root.getRight()));
            return leftRotation(root);
        }
        return root;


    }
    /**
     * Updates height of the node
     * @param root the root
     */
    private void updateHeight(AVLNode root) {
        if (root.getLeft() == null && root.getRight() == null) {
            root.setHeight(0);
            return;
        }
        int leftHeight = 0;
        if (root.getLeft() != null) {
            leftHeight = root.getLeft().getHeight();
        }
        int rightHeight = 0;
        if (root.getRight() != null) {
            rightHeight = root.getRight().getHeight();
        }
        root.setHeight(1 + Math.max(leftHeight, rightHeight));
    }

    /**
     * The recrusive helper function to lefft rotate the tree
     * @param root the root of the current iteration of the tree
     * @return node that became new root
     */
    private AVLNode leftRotation(AVLNode root) {
        AVLNode center = root.getRight();
        AVLNode centerLeft = center.getLeft();

        center.setLeft(root);
        root.setRight(centerLeft);


        updateHeight(root);

        updateHeight(center);
        updateBalanceFactor(root);
        updateBalanceFactor(center);

        return center;

    }
    /**
     * The recrusive helper function to right rotate the tree
     * @param root the root of the current iteration of the tree
     * @return node that became new root
     */
    private AVLNode rightRotation(AVLNode root) {
        AVLNode center = root.getLeft();
        AVLNode centerRight = center.getRight();

        center.setRight(root);
        root.setLeft(centerRight);

        updateHeight(root);
        updateHeight(center);
        updateBalanceFactor(root);
        updateBalanceFactor(center);

        return center;

    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        if (root == null) {
            throw new NoSuchElementException("AVL Tree is null");
        }
        AVLNode dummyNode = new AVLNode(null);
        root = removeRecursive(root, data, dummyNode);
        size--;
        return (T) dummyNode.getData();

    }
    /**
     * The recrusive helper function to iterate through the tree
     * Removes elements in the tree and returns what elements have been removed
     * @param root the root of the current iteration of the tree
     * @param data the data to be added to the tree
     * @param  dData the dummyData used to store the data during recursion
     * @throws NoSuchElementException if data does not exist
     * @return Node that was removed
     */
    private AVLNode removeRecursive(AVLNode<T> root, T data, AVLNode dData) {
        if (root == null) {
            return root;
        }
        if (root.getData().compareTo(data) < 0) {
            root.setRight(removeRecursive(root.getRight(), data, dData));
        } else if (root.getData().compareTo(data) > 0) {
            root.setLeft(removeRecursive(root.getLeft(), data, dData));
        } else if (root.getData().equals(data)) {
            dData.setData(root.getData());
            if (root.getLeft() == null || root.getRight() == null) {
                AVLNode temp = null;
                if (root.getLeft() == null) {
                    temp = root.getRight();
                } else {
                    temp = root.getLeft();
                }
                root = temp;
            } else {

                //Finding sucessor
                AVLNode copy = root.getRight();
                T maxV = (T) copy.getData();
                while (copy.getLeft() != null) {
                    maxV = (T) copy.getLeft().getData();
                    copy = copy.getLeft();
                }
                AVLNode<T> fDummy = new AVLNode<>(null);
                root.setData(maxV);
                root.setRight(removeRecursive(root.getRight(), maxV, fDummy));
            }
        } else {
            throw new NoSuchElementException("No such element");
        }
        if (root == null) {
            return root;
        }

        updateHeight(root);

        updateBalanceFactor(root);
        //Left Rotation
        if (root.getBalanceFactor() == -2
                && (root.getRight().getBalanceFactor() == 0
                || root.getRight().getBalanceFactor() == -1)) {
            return leftRotation(root);

        }
        //Right Rotation
        if (root.getBalanceFactor() == 2
                && (root.getLeft().getBalanceFactor() == 0
                || root.getLeft().getBalanceFactor() == 1)) {
            return rightRotation(root);
        }
        //Left - Right
        if (root.getBalanceFactor() == 2
                && root.getLeft().getBalanceFactor() == -1) {
            root.setLeft(leftRotation(root.getLeft()));
            return rightRotation(root);
        }
        //Right - Left
        if (root.getBalanceFactor() == -2
                && root.getRight().getBalanceFactor() == 1) {
            root.setRight(rightRotation(root.getRight()));
            return leftRotation(root);
        }
        return root;


    }

    /**
     * Updates balance factor of the node
     * @param root the root
     */
    private void updateBalanceFactor(AVLNode<T> root) {
        int leftBalance = -1;
        if (root.getLeft() != null) {
            leftBalance = root.getLeft().getHeight();
        }
        int rightBalance = -1;
        if (root.getRight() != null) {
            rightBalance = root.getRight().getHeight();
        }

        //Rotations
        root.setBalanceFactor(leftBalance - rightBalance);
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        AVLNode dummyNode = new AVLNode(null);
        getRecursive(root, data, dummyNode);
        return (T) dummyNode.getData();
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Gets the data sent in
     * @param root the root of the current iteration of the tree
     * @param data the data to be got
     * @param dummyNode a node to hold data
     * @throws NoSuchElementException if the element is not in the tree
     */
    public void getRecursive(AVLNode root, T data, AVLNode dummyNode) {
        if (root == null) {
            throw new NoSuchElementException("No such element "
                    + "belongs in  the tree");
        }
        if (root.getData().compareTo(data) < 0) {
            getRecursive(root.getRight(), data, dummyNode);
            return;
        }
        if (root.getData().compareTo(data) > 0) {
            getRecursive(root.getLeft(), data, dummyNode);
            return;
        }
        if (root.getData().equals(data)) {
            dummyNode.setData(root.getData());
            return;
        }
        throw new NoSuchElementException("No such element"
                + " belongs in  the tree");

    }
    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null");
        }
        try {
            get(data);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {
        if (size < 2) {
            throw new NoSuchElementException("Less than 2"
                    + "elements in the tree");
        }
        AVLNode parent = root;
        AVLNode child = root.getRight();
        if (child == null) {
            return (T) parent.getLeft().getData();
        }
        AVLNode secondRight = getParentOfLargest(parent, child);
        if (secondRight.getRight().getLeft() == null) {
            return (T) secondRight.getData();
        }
        AVLNode second = getMaximum(secondRight.getRight().getLeft());
        return (T) second.getData();
    }

    /**
     * Gets parent of the largest element in the tree
     * @param parent the root of the current iteration of the tree
     * @param second the the right childe of the root
     * @return the parents of the largest
     */
    private AVLNode getParentOfLargest(AVLNode parent, AVLNode second) {
        if (second.getRight() != null) {
            return getParentOfLargest(parent.getRight(), second.getRight());
        }
        return parent;
    }
    /**
     * Gets maximum of any tree
     * @param root the root of the current iteration of the tree
     * @return largest of a tree
     */
    private AVLNode getMaximum(AVLNode root) {
        if (root.getRight() != null) {
            return getMaximum(root.getRight());
        }
        return root;
    }




    @Override
    public boolean equals(Object obj) {
        if (obj == null && root == null) {
            return true;
        }
        AVL newAVL = null;
        if (obj == null) {
            return false;
        }
        if (obj instanceof AVL) {
            newAVL = (AVL) obj;
        } else {
            return false;
        }

        return sameTree(root, newAVL.root);
    }

    /**
     * The recrusive helper function to iterate through the tree
     * Checks if trees are equal
     * @param root the root of the current iteration of the tree
     * @param root1 the root of the current iteration of the other tree
     * @return wheteher the following trees are equaly
     */
    private boolean sameTree(AVLNode<T> root, AVLNode root1) {
        if (root == null && root1 == null) {
            return true;
        }
        if (root == null || root1 == null) {
            return false;
        }
        if (root1.getData().equals(root.getData())) {
            return (sameTree(root1.getLeft(), root.getLeft())
                    && sameTree(root.getRight(), root1.getRight()));
        }
        return false;
    }



    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
