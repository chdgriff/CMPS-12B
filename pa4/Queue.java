// Christopher Griffis <chdgriff>
// CS12B - 02/26/18
// Queue.java - implemenets Queue ADT
public class Queue implements QueueInterface {
   
    private class Node {
        Object item;
        Node next;
        
        public Node(Object item) {
            this.item = item;
            next = null;
        }
    }
    
    private int numItems;
    private Node head;
    private Node tail;
    
    public Queue() {
        numItems = 0;
        head = null;
        tail = null;
    }
    
    
    public boolean isEmpty() {
        if (numItems == 0) return true;
        return false;
    }

    public int length() {
        return numItems;
    }
    
    public void enqueue(Object newItem) {
        if (isEmpty()) {
            Node newNode = new Node(newItem);
            head = newNode;
            tail = newNode;
        }
        else {
            Node newNode = new Node(newItem);
            tail.next = newNode;
            tail = newNode;
        }
        numItems++;
    }
    
    public Object dequeue () throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't dequeue() an empty queue");
        }
        
        Node out;
        if (numItems == 1) {
            out = head;
            head = null;
            tail = null;
        }
        else {
            out = head;
            head = head.next;
        }
        numItems--;
        return out.item;
    }
    
    public Object peek() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't peek() an empty queue");
        }
        return head.item;
    }
    
    public void dequeueAll() throws QueueEmptyException {
        if (isEmpty()) {
            throw new QueueEmptyException("Queue Error: Can't dequeueAll() an empty queue");
        }
        numItems = 0;
        head = null;
        tail = null;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        
        Node n = head;
        for (int i = 0; i < numItems; i++) {
            if (i != 0) sb.append(" ");
            sb.append(n.item);
            n = n.next;
        }
        return new  String(sb);
    }
}

