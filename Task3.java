//O(n)
public class Task3 {

    // Node class for doubly linked list
    static class Node {
        int data;
        Node prev;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    // Doubly linked list class
    static class DoublyLinkedList {
        Node header; // Sentinel node before the first node
        Node trailer; // Sentinel node after the last node

        public DoublyLinkedList() {
            header = new Node(-1); // Initialize header
            trailer = new Node(-1); // Initialize trailer
            header.next = trailer;
            trailer.prev = header;
        }

        // Add a new node at the end of the list
        public void addNode(int data) {
            Node newNode = new Node(data);
            newNode.prev = trailer.prev;
            newNode.next = trailer;
            trailer.prev.next = newNode;
            trailer.prev = newNode;
        }

        // Find and return the middle node of the list
        public Node findMiddleNode() {
            // create two pointers, slow which moves one node at a time and fast which moves two nodes at a time
            Node slowPtr = header.next;
            Node fastPtr = header.next;

            // check that there is at least two nodes ahead to start the hopping
            while (fastPtr.next != trailer && fastPtr.next.next != trailer) {
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next.next;
            }

            // once the fast pointer reaches the end of the linkedlist, this means that the slow pointer is exactly in the middle
            // or if this list is even then it is on the element exactly to the left of the middle
            return slowPtr;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        // Add nodes to the doubly linked list
        list.addNode(1);
        list.addNode(2);
        list.addNode(3);
        list.addNode(4);
        list.addNode(5);
        list.addNode(6);
        list.addNode(7);
        list.addNode(8);

        // Find the middle node
        Node middleNode = list.findMiddleNode();

        if (middleNode != null) {
            System.out.println("Middle node: " + middleNode.data);
        } else {
            System.out.println("The list is empty.");
        }
    }
}
