package cmsc256;

import java.util.Arrays;

public class ArrayBasedStack<T>  implements  StackInterface<T> {

    private T[] data;

    private int topOfStack;

    private static final int INITIAL_CAPACITY = 5;

    /**
     * constructor that will initialize the stack with a user-defined initial capacity that must be greater than 0.
     * If the parameter is 0 or less, throw an IllegalArgumentException with the comment “Array initial size error”.
     * The top of the stack is assigned a value of -1 to indicate that the stack is currently empty.
     */

    public ArrayBasedStack(int capacity){

        if(capacity <= 0) {
            throw new IllegalArgumentException("Array initial size error.");
        }
        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[capacity];
        data = tempStack;
        topOfStack = -1;
    }
    public ArrayBasedStack(){
        clear();
    }

    /**
     * double the size of array when called
     */
    private void expandArray(){
        data = Arrays.copyOf(data, data.length*2);
    }

    /**
     *
     * @return  true if the topOfStack exceeds the largest index value in the array
     */
    private boolean isArrayFull(){
        if(topOfStack>=data.length-1){
            return true;
        }
        return false;
    }



    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        if(isArrayFull()){
            expandArray();
        }
        topOfStack++;
        data[topOfStack] = newEntry;

    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        //If stack is empty, throw an EmptyStackException.
        //If there are elements in the stack, get the element at the top of the stack (index is topOfStack).
        //Set the previous top of the stack to null.
        //Decrement the index of the top of the stack and return the previous top.

        if(isEmpty()){
            throw new EmptyStackException();
        }
        T anEntry = data[topOfStack];
        data[topOfStack] = null;
        topOfStack--;
        return anEntry;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    @Override
    public T peek() {
        if (isEmpty()){
            throw new EmptyStackException();
        }
        return data[topOfStack];
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        if(topOfStack<0){
            return true;
        }
        return false;
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {

        @SuppressWarnings("unchecked")

        T[] tempStack = (T[])new Object[INITIAL_CAPACITY];

        data = tempStack;

        topOfStack = -1;

    }
}
