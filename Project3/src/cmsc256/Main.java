package cmsc256;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        LinkedSack<Integer> sack = new LinkedSack<>();

        // Add some items to the sack
        sack.add(1);
        sack.add(2);
        sack.add(3);
        sack.add(4);

        // Display the original sack
       /* System.out.println("Original Sack:");
        for ( LinkedSack.Node<T> node : sack) {
            System.out.print(node.value + " ");
        }
        System.out.println();
*/
        // Duplicate all items in the sack
        //boolean success = sack.duplicateAll();

        LinkedSack<Integer> sack2 = new LinkedSack<>();

        // Add some items to the sack
        sack.add(1);
        sack.add(1);
        sack.add(2);
        sack.add(4);
        sack.removeDuplicates();
    }
}