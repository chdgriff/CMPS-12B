// Christopher Griffis <chdgriff>
// CS12B - 02/26/18
// QueueTest.java - Test Queue ADT
public class QueueTest{
    public static class testObject {
        private int item;
        public testObject(int item){
            this.item = item;
        }
        
        public String toString() {
            return Integer.toString(item);
        }
    }
    public static void main(String[] args) {
        testObject a = new testObject(0);
        testObject b = new testObject(1);
        testObject c = new testObject(2);
        testObject d = new testObject(3);
        
        Queue testQueue = new Queue();
        
        testQueue.enqueue(a);
        
        System.out.println("Added 1 item(s):\n" + testQueue);
        
        System.out.println("\ndequeue() when 1 item(s):\n" + testQueue.dequeue() + "\n" + testQueue);
        
        testQueue.enqueue(a);
        testQueue.enqueue(b);
        System.out.println("\nAdded 2 item(s):\n" + testQueue);
        
        System.out.println("\ndequeue() when 2 item(s):\n" + testQueue.dequeue() + "\n" + testQueue);
        
        testQueue.enqueue(c);
        testQueue.enqueue(d);
        System.out.println("\nAdded 2 item(s):\n" + testQueue);
        
        System.out.println("\nTest of peek:\n" + testQueue.peek());
        
        testQueue.dequeueAll();
        System.out.println("\nTest of dequeueAll():\n" + testQueue);
    }
}
