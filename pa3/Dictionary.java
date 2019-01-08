// Christopher Griffis <chdgriff>
// 12b 02/08/18
// Dictionary.java - implements the Dictionary ADT

public class Dictionary implements DictionaryInterface {
    
    private class Node {
        String key;
        String value;
        Node next;
        
        Node(String k, String v) {
            key = k;
            value = v;
            next = null;
        }
    }
    
    // Fields for Dictionary
    private Node head;
    private int numItems;
    
    // Constructor
    public Dictionary() {
        head = null;
    }
    
    
    // private helper function to find and return node
    private Node findKey(String key) {
        Node n = head;
        while (n != null) {
            if (key.compareTo(n.key) == 0) return n;
            n = n.next;
        }
        return null;
    }
    
    // Dictionary ADT Functions
                
                
    // Checks if
    public boolean isEmpty() {
        return head == null;
    }
    
    public int size() {
        Node n = head;
        int i = 0;
        while (n != null) {
            i++;
            n = n.next;
        }
        return i;
    }
    
    public String lookup(String key) {
        Node n = findKey(key);
        if (n == null) return null;
        return n.value;
    }
    
    public void insert(String key, String value) throws DuplicateKeyException {
        if (head == null) {
            Node n = new Node(key, value);
            head = n;
        }
        else {
            if (findKey(key) != null) {
                throw new DuplicateKeyException("cannot insert duplicate keys");
            }
            
            Node n = head;
            while (n.next != null) {
                n = n.next;
            }
            n.next = new Node(key, value);
        }
    }
    
    public void delete(String key) throws KeyNotFoundException {
        if (head == null || findKey(key) == null) {
            throw new KeyNotFoundException("cannot delete non-existent key");
        }
        
        Node n = head;
        Node f = findKey(key);
        if (n == f) { // when the element is in the first spot
            head = n.next;
        }
        else { // when the element is in or at the end of the list
            while (n.next != f) {
                n = n.next;
            }
            n.next = f.next;
        }
    }
    
    // Clears Dictionary
    public void makeEmpty() {
        head = null;
    }
    
    // Returns all the values of dictionary linked list as String
    public String toString() {
        StringBuffer sb = new StringBuffer();
        Node n = head;
        while (n != null) {
            sb.append(n.key).append(" ").append(n.value).append("\n");
            n = n.next;
        }
        return new String(sb);
    }
}
