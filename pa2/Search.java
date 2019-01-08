// Chris Griffis <chdgriff>
// 12b 01/26/18
// Search.java - takes inputed file and searches through it to find specified word

import java.io.*;
import java.util.Scanner;

class Search {
    
    // Uses merge() to sort String array
    public static void mergeSort(String[] word, int[] lineNumber, int p, int r) {
        int q;
        if (p < r) {
            q = (p+r)/2;
            mergeSort(word, lineNumber, p, q);
            mergeSort(word, lineNumber, q+1, r);
            merge(word, lineNumber, p, q, r);
        }
    }
    
    // Sorts an array by comparing two arrays and merging them
    public static void merge(String[] word, int[] lineNumber, int p, int q, int r) {
        int n1 = q-p+1;
        int n2 = r-q;
        String[] L = new String[n1];
        int[] Lint = new int[n1];
        String[] R = new String[n2];
        int[] Rint = new int[n2];
        int i, j, k;
        
        for (i = 0; i < n1; i++) {
            L[i] = word[p+i];
            Lint[i] = lineNumber[p+i];
        }
        
        for (j = 0; j < n2; j++) {
            R[j] = word[q+j+1];
            Rint[j] = lineNumber[q+j+1];
        }
        
        i = 0; j = 0;
        for (k = p; k <= r; k++) {
            if (i < n1 && j < n2) {
                if (L[i].compareTo(R[j]) < 0) {
                    word[k] = L[i];
                    lineNumber[k] = Lint[i];
                    i++;
                }
                else {
                    word[k] = R[j];
                    lineNumber[k] = Rint[j];
                    j++;
                }
            }
            else if (i < n1) {
                word[k] = L[i];
                lineNumber[k] = Lint[i];
                i++;
            }
            else {
                word[k] = R[j];
                lineNumber[k] = Rint[j];
                j++;
            }
        }
    }
    
    // Searches through array recursively to find target
    // Pre: array must be sorted
    public static int binarySearch(String[] word, int p, int r, String target) {
        int q;
        if (p > r) return -1;
        
        q = (p+r)/2;
        if (target.compareTo(word[q]) == 0 ) {
            return q;
        }
        else if (target.compareTo(word[q]) < 0) {
            return binarySearch(word, p, q-1, target);
        }
        else {
            return binarySearch(word, q+1, r, target);
        }
    }
    
    
    public static void main(String[] args) throws IOException {
        // Checks number of command line arguments
        if (args.length < 2) {
            System.err.println("Usage: Search file target1 [target2 ..]");
            System.exit(1);
        }
        
        // Counts number of lines in file
        Scanner in = new Scanner(new File(args[0]));
        in.useDelimiter("\\Z");
        String s = in.next();
        in.close();
        String[] lines = s.split("\n");
        int lineCount = lines.length;
        
        // Adds file input to String array
        in = new Scanner(new File(args[0]));
        int fileLine = 0;
        String[] word = new String[lineCount];
        
        while (in.hasNextLine()) {
            word[fileLine] = in.nextLine();
            fileLine++;
        }
        
        in.close();
        
        // Creates lineNumber list
        int[] lineNumber = new int[lineCount];
        for (int i = 0; i < lineCount; i++)   lineNumber[i] = i+1;
      
        /*
        // Prints word alone with line number
        for (int i = 0; i < lineCount; i++) {
            System.out.println(word[i] + "    \t" + lineNumber[i]);
        }
        */
        
        // sorts word
        mergeSort(word, lineNumber, 0, word.length-1);
       
        // Searches for target word withing word using binarySearch()
        for (int i = 1; i < args.length; i++) {
            int found = binarySearch(word, 0, word.length-1, args[i]);
            if (found != -1) {
                System.out.println(args[i] + " found on line " + lineNumber[found]);
            }
            else {
                System.out.println(args[i] + " not found");
            }
        }
    }
}
