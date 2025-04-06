package javaApp;

import java.util.ArrayList;
import java.util.List;

public class CategoryDoublyLinkedList {

    // Node class for the doubly linked list.
    private class Node {
        MoneyMovement data;
        Node prev;
        Node next;

        Node(MoneyMovement data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;

    public CategoryDoublyLinkedList() {
        head = null;
        tail = null;
    }

    public void add(MoneyMovement movement) {
        Node newNode = new Node(movement);
        // If list is empty, add as the only element.
        if (head == null) {
            head = newNode;
            tail = newNode;
            return;
        }
        // If new node should be placed at the beginning.
        if (movement.getCategory().compareTo(head.data.getCategory()) <= 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
            return;
        }
        // Traverse to find the insertion point.
        Node current = head;
        while (current.next != null && movement.getCategory().compareTo(current.next.data.getCategory()) > 0) {
            current = current.next;
        }
        // Insert newNode after current.
        newNode.next = current.next;
        if (current.next != null) {
            current.next.prev = newNode;
        } else {
            // newNode is now the last element.
            tail = newNode;
        }
        current.next = newNode;
        newNode.prev = current;
    }

    public List<MoneyMovement> searchByCategory(String category) {
        List<MoneyMovement> result = new ArrayList<>();
        Node current = head;
        
        // Advance until we find a node with a category that is equal or greater.
        while (current != null && current.data.getCategory().compareTo(category) < 0) {
            current = current.next;
        }
        // Now, if the current node's category matches, collect all consecutive matches.
        while (current != null && current.data.getCategory().equals(category)) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }
    
    // Optionally, you could implement additional methods (like remove or display) as needed.
}

