// Christopher Griffis <chdgriff>
// CS12B - 02/08/18
// DictionaryTest.java - test client for Dictionary ADT
public class DictionaryTest {
    public static void main(String[] args) {
        Dictionary dict1 = new Dictionary();
        
        System.out.println("Empty: " + dict1.isEmpty());
        
        dict1.insert("1", "A");
        dict1.insert("2", "B");
        dict1.insert("3", "C");
        dict1.insert("4", "D");
        System.out.println("Size: " + dict1.size());
        System.out.print(dict1.toString());
        
        System.out.println("Empty: " + dict1.isEmpty());
        
        // Tests error on inserting with same key
        try {
            dict1.insert("4", "E");
        } catch (DuplicateKeyException e) {
            System.err.println("Caught: " + e.getMessage());
        }
        
        
        //Tests delete()
        dict1.delete("4");
        System.out.println("Size: " + dict1.size());
        System.out.print(dict1.toString());
        
        dict1.delete("2");
        System.out.println("Size: " + dict1.size());
        System.out.print(dict1.toString());
        
       // Tests error reporting on delete()
        try {
            dict1.delete("5");
        } catch (KeyNotFoundException e) {
            System.err.println("Caught: " + e.getMessage());
        }
        
        dict1.delete("3");
        System.out.println("Size: " + dict1.size());
        System.out.print(dict1.toString());
        dict1.delete("1");
        System.out.println("Size: " + dict1.size());
        System.out.print(dict1.toString());
        
        
        // Tests makeEmpty()
        dict1.makeEmpty();
        System.out.println("Empty: " + dict1.isEmpty());
        System.out.print(dict1.toString());
        
    }
}
