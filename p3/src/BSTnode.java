/**
 * BSTnode implements a node for a binary search tree (BST).
 * 
 * DO NOT CHANGE THIS FILE
 * 
 * @author CS 367
 * @param <K> a class which implements the Comparable<K> interface
 */
class BSTnode<K extends Comparable<K>> {
    // Data members
    private K key;                   // the key value
    private BSTnode<K> left, right;  // the left and right children
 
    /**
     * Constructs a BST node with the given key value and whose left and right
     * children are null.
     * @param keyValue
     */
    public BSTnode(K keyValue) {
        this(keyValue, null, null);
    }
    
    /**
     * Constructs a BST node with the given key value, left child, and right 
     * child.
     * @param keyValue the key value
     * @param leftChild the left child
     * @param rightChild the right child
     */
    public BSTnode(K keyValue, BSTnode<K> leftChild, BSTnode<K> rightChild) {
        key = keyValue;
        left = leftChild;
        right = rightChild;
    }
 

    /**
     * Returns the key value for this BST node.
     * @return the key value
     */
    public K getKey() { 
        return key; 
    }
    
    /**
     * Returns the left child for this BST node.
     * @return the left child
     */
    public BSTnode<K> getLeft() { 
        return left; 
    }
    
    /**
     * Returns the right child for this BST node.
     * @return the right child
     */
    public BSTnode<K> getRight() { 
        return right; 
    }
 
    /**
     * Changes the key value for this node to the one given.
     * @param newK the new key value 
     */
    public void setKey(K newK) { 
        key = newK; 
    }
    
    /**
     * Changes the left child for this node to the one given.
     * @param newL the new left child
     */
    public void setLeft(BSTnode<K> newL) { 
        left = newL; 
    }
    
    /**
     * Changes the right child for this node to the one given.
     * @param newR the new right child
     */
    public void setRight(BSTnode<K> newR) { 
        right = newR; 
    }
}
