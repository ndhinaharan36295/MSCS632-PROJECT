package javaApp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Stack;

public class AVLTree<T> {
    
    // Node class for the AVL tree.
    private class Node {
        T key;
        Node left;
        Node right;
        int height;

        Node(T key) {
            this.key = key;
            this.height = 1; // New nodes are initially added at leaf level.
        }
    }
    
    private Node root;
    private Comparator<T> comparator;
    
    public AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.root = null;
    }
    
    // Helper method: returns height of a node.
    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }
    
    // Helper method: calculates balance factor of a node.
    private int getBalance(Node node) {
        return (node == null) ? 0 : getHeight(node.left) - getHeight(node.right);
    }
    
    // Right rotation utility.
    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        // Perform rotation.
        x.right = y;
        y.left = T2;
        // Update heights.
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }
    
    // Left rotation utility.
    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        // Perform rotation.
        y.left = x;
        x.right = T2;
        // Update heights.
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        return y;
    }
    
    // Iterative insertion method.
    public void insert(T key) {
        if (root == null) {
            root = new Node(key);
            return;
        }
        
        // Use a stack to keep track of the path from root to the insertion point.
        Stack<Node> stack = new Stack<>();
        Node current = root;
        while (true) {
            stack.push(current);
            if (comparator.compare(key, current.key) < 0) {
                if (current.left == null) {
                    current.left = new Node(key);
                    stack.push(current.left);
                    break;
                } else {
                    current = current.left;
                }
            } else {
                if (current.right == null) {
                    current.right = new Node(key);
                    stack.push(current.right);
                    break;
                } else {
                    current = current.right;
                }
            }
        }
        
        // Backtrack up the tree to update heights and rebalance if necessary.
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            // Update height.
            node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
            int balance = getBalance(node);
            
            // Check for imbalance.
            if (balance > 1) { // Left heavy.
                if (comparator.compare(key, node.left.key) < 0) { // Left-Left case.
                    Node newSubtree = rotateRight(node);
                    // Reattach the rotated subtree.
                    if (stack.isEmpty()) {
                        root = newSubtree;
                    } else {
                        Node parent = stack.peek();
                        if (parent.left == node) {
                            parent.left = newSubtree;
                        } else {
                            parent.right = newSubtree;
                        }
                    }
                } else { // Left-Right case.
                    node.left = rotateLeft(node.left);
                    Node newSubtree = rotateRight(node);
                    if (stack.isEmpty()) {
                        root = newSubtree;
                    } else {
                        Node parent = stack.peek();
                        if (parent.left == node) {
                            parent.left = newSubtree;
                        } else {
                            parent.right = newSubtree;
                        }
                    }
                }
            } else if (balance < -1) { // Right heavy.
                if (comparator.compare(key, node.right.key) > 0) { // Right-Right case.
                    Node newSubtree = rotateLeft(node);
                    if (stack.isEmpty()) {
                        root = newSubtree;
                    } else {
                        Node parent = stack.peek();
                        if (parent.left == node) {
                            parent.left = newSubtree;
                        } else {
                            parent.right = newSubtree;
                        }
                    }
                } else { // Right-Left case.
                    node.right = rotateRight(node.right);
                    Node newSubtree = rotateLeft(node);
                    if (stack.isEmpty()) {
                        root = newSubtree;
                    } else {
                        Node parent = stack.peek();
                        if (parent.left == node) {
                            parent.left = newSubtree;
                        } else {
                            parent.right = newSubtree;
                        }
                    }
                }
            }
        }
    }
    
    // Iterative range search: returns a list of keys between min and max (inclusive).
    public List<T> rangeSearch(T min, T max) {
        List<T> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node current = root;
        
        // Iterative in-order traversal.
        while (current != null || !stack.isEmpty()) {
            // Traverse to the leftmost node.
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            // If within range, add to result.
            if (comparator.compare(current.key, min) >= 0 && comparator.compare(current.key, max) <= 0) {
                result.add(current.key);
            }
            // Since the traversal is sorted, if current key is greater than max, we can break early.
            if (comparator.compare(current.key, max) > 0) {
                break;
            }
            current = current.right;
        }
        return result;
    }

    public Expense initializeMinDateExpense(Date min){
        Expense expense = new Expense();
        expense.setDate(min);
        return expense;
    }

    public Expense initializeMaxDateExpense(Date max){
        Expense expense = new Expense();
        expense.setDate(max);
        return expense;
    }

    public Expense initializeMinAmountExpense(double min){
        Expense expense = new Expense();
        expense.setAmount(min);
        return expense;
    }

    public Expense initializeMaxAmountExpense(double max){
        Expense expense = new Expense();
        expense.setAmount(max);
        return expense;
    }

    public Income initializeMinDateIncome(Date min){
        Income expense = new Income();
        expense.setDate(min);
        return expense;
    }

    public Income initializeMaxDateIncome(Date max){
        Income expense = new Income();
        expense.setDate(max);
        return expense;
    }

    public Income initializeMinAmountIncome(double min){
        Income expense = new Income();
        expense.setAmount(min);
        return expense;
    }

    public Income initializeMaxAmountIncome(double max){
        Income expense = new Income();
        expense.setAmount(max);
        return expense;
    }
}


