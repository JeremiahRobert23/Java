package cmsc256;
/*
 * This program is used to show how to find errors in your program
 * Programmer 1:Jeremiah Robert
 * Programmer 2:
 * CMSC 256 Section:001
 * Lines with syntax errors:24,27,63
 * Runtime errors:26, 30
 */
import java.util.*;
public class DebuggingPractice {
    private static int evenPerfectSquareNumbers = 0;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//declare variables
        double number;
        double average, value;
        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        int[] nums = {23, 3, 14, -5, 44, 78, 6, 98, 25};
        String myName = "Jeremiah, son of Robert";//changed my name because it is not my name
        value = 3.75;//missing semicolon was added
        number = value;
        average = (3 + 5 + 8) / 3.0;// changed to 3.0 from 0
        System.out.println(" *** I am " + myName + " *** ");//was missing semicolon and parenthesis
                System.out.println("The first day of the week is: "+
                        daysOfTheWeek[1]);
        System.out.println("The last day of the week is: "+ daysOfTheWeek[6]);//was supposed to be 6 because 6 is the index of last element
        average = (3 + 5 + 8) / 3.0;// was missing parenthesis
        System.out.println("expected average is 5.33, actual average is: " +
                average);
        System.out.println("expected max is 98, actual max is: " + max(nums));
        System.out.println("expected type of number is double: " + number);
        System.out.println("calling countPerfectSquares(100)");
        countPerfectSquares(100);//is supposed to 100 instead of 1000 like before
        //writing different test to check inRange method
        System.out.println("expected true for inRange:" + inRange(nums, -10, 100));
        System.out.println("expected false for inRange:" + inRange(nums, -1, 100));
        System.out.println("expected false for inRange:" + inRange(nums, -1, 90));
    }
    // Returns the maximum value in the array parameter
    public static int max(int[] a) {
        int result = a[0];
        for(int i = 0; i < a.length; i++){
            if(a[i] > result) {
                //changing less than to greater than
                result = a[i];
            }
        }
        return result;
    }
    // Returns true if all values in the parameter array fall with the
// range define by parameters low and high
    public static boolean inRange (int[] array , int low , int high ) {
        for (int i = 0 ; i < array.length ; i++) {
            if (array[i] < low || array[i] > high) {
                return false;
            }
        }
        return true ;
    }
    // Displays the counts the total perfect squares and
// //even perfect squares for a given number
    public static void countPerfectSquares(int num) {
        //int num = 100;
        System.out.println("Total Perfect Squares: " + calculateCount(num));
        System.out.println("Even Perfect Squares : " + evenPerfectSquareNumbers);
    }
    public static int calculateCount(int i) {
        int perfectSquaresCount = 0;
        for (int number = 1; number <= i; number++) {
            if (isPerfectSquare(number)) {
                perfectSquaresCount++;
                if (number % 2 == 0) {
                    evenPerfectSquareNumbers++;
                }
            }
        }
        return perfectSquaresCount;
    }
    private static boolean isPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }

    public static int getEvenPerfectSquareNumbers(){
        return evenPerfectSquareNumbers;
    }
}
