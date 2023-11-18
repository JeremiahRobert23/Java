package cmsc256;

public class NonGenericPractice {

    // Method 1
    // If the array is non-empty, then this function returns an element // from near the middle of the array; otherwise, it returns null

    public static Integer middle(Integer[] data) {
        Integer result = null;
        int numElements = data.length;

        if(numElements > 0) {
            // return an element near the center of the array
            result = data[numElements/2];
        }
        return result;
    }



// Method 2
// counts the number of occurrences of the target in the given array

    public static int countTarget(int[] data, int target) {
        int numOccurences = 0;

        for(int i = 0; i < data.length; i++){
            if(data[i] == target) {
                numOccurences++;
            }
        }
        return numOccurences;
    }



// Method 3
// returns the smallest element in the array

    public static int findSmallest(int[] values) {
        int smallest = values[0];

        for(int i = 1; i < values.length; i++){
            if(values[i] < smallest) {
                smallest = values[i];
            }
        }
        return smallest;
    }
}

