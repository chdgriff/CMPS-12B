// Chris Griffis <chdgriff>
// 12B 01/19/18
// Recursion.java - Contains code to reverse an array in 3 different recursive methods and
// 		    finds the min and max index value of an array

class Recursion {
   
	// reverseArray1()
	// Places the leftmost n elements of X[] into the rightmost n positions in
	// Y[] in reverse order
	static void reverseArray1(int[] X, int n, int[] Y){
		if (n > 0){
			Y[Y.length-n] = X[n-1];
			reverseArray1(X, n-1, Y);
		}
	}

   // reverseArray2()
   // Places the rightmost n elements of X[] into the leftmost n positions in
   // Y[] in reverse order.
	static void reverseArray2(int[] X, int n, int[] Y){
		if (n > 0){
			Y[n-1] = X[X.length-n];
			reverseArray2(X, n-1, Y);
		} 
	}
   
	// reverseArray3()
	// Reverses the subarray X[i...j].
	static void reverseArray3(int[] X, int i, int j){
		if (j > i){
			int temp = X[j];
			X[j] = X[i];
			X[i] = temp;
			reverseArray3(X, i+1, j-1);
		}
	}
   
	// maxArrayIndex()
	// returns the index of the largest value in int array X
	static int maxArrayIndex(int[] X, int p, int r){
		if (p == r)
			return p;
		
		else{
			int q = (p+r)/2;
			int i = maxArrayIndex(X, p, q);
			int j = maxArrayIndex(X, q+1, r);

			if (X[i] >= X[j])
				return i;
			else
				return j;
		}
	}
   
// minArrayIndex()
// returns the index of the smallest value in int array X
	static int minArrayIndex(int[] X, int p, int r){
		if (p == r)
			return p;
		
		else{
			int q = (p+r)/2;
			int i = minArrayIndex(X, p, q);
			int j = minArrayIndex(X, q+1, r);
			
			if (X[i] <= X[j])
				return i;
			else
				return j;
		}
	}

 // main()
public static void main(String[] args){
	int[] A = {-1, 2, 6, 12, 9, 2, -5, -2, 8, 5, 7};
	int[] B = new int[A.length];
	int[] C = new int[A.length];
	int minIndex = minArrayIndex(A, 0, A.length-1);
	int maxIndex = maxArrayIndex(A, 0, A.length-1);

	for(int x: A)
		System.out.print(x+" ");
	System.out.println();
	
	System.out.println( "minIndex = " + minIndex );  
	System.out.println( "maxIndex = " + maxIndex );  
	
	reverseArray1(A, A.length, B);
	for(int x: B)
		System.out.print(x+" ");
	System.out.println();
	
	reverseArray2(A, A.length, C);
	for(int x: C) 
		System.out.print(x+" ");
	
	System.out.println();
	
	reverseArray3(A, 0, A.length-1);
	for(int x: A) 
		System.out.print(x+" ");
	
	System.out.println();        
	}
}
/* Output:
-1 2 6 12 9 2 -5 -2 8 5 7
minIndex = 6
maxIndex = 3
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
7 5 8 -2 -5 2 9 12 6 2 -1
*/
