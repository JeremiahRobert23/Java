import java.io.File;
import java.io.FileNotFoundException;

import static cmsc256.MyStack.isBalanced;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        File webpage = new File("G:\\My Drive\\CMSC 256\\Project4\\eighttagtagpage.html");
        try {
            boolean isBalanced = isBalanced(webpage);
            if (isBalanced) {
                System.out.println("The HTML file has balanced tags.");
            } else {
                System.out.println("The HTML file does not have balanced tags.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}