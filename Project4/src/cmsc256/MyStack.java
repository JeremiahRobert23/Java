package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;
/**
 *  MyStack.java
 *  CMSC 256
 *  Project 4
 *  Purpose - A program that implements a StackInterface and private node class checks if the tags match
 *  Fall 2023
 *  Robert, Jeremiah
 */

public class MyStack<T>implements StackInterface<T>{
    Node firstNode;

    public MyStack(){

    }
    public MyStack(T newEntry){
        firstNode = new Node (newEntry);

    }

    private class Node
    {
        private T data; // Entry in stack
        private Node next; // Link to nexE node
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }
        private Node(T dataPortion, Node linkPortion)
        {
            data = dataPortion;
            next = linkPortion;
        }
        private T getData()
        {
            return data;
        }
        private void setData(T newData)
        {
            data = newData;
        }
        private Node getNextNode()
        {
            return next;
        }
        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }

    /**
     * Adds a new entry to the top of this stack.
     *
     * @param newEntry An object to be added to the stack.
     */
    @Override
    public void push(T newEntry) {
        if(newEntry==null){
            throw new IllegalArgumentException();
        }

        Node curr = new Node(newEntry);
        curr.setNextNode(firstNode);
        firstNode = curr;


    }

    /**
     * Removes and returns this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws //EmptyStackException if the stack is empty before the operation.
     */
    @Override
    public T pop() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        T temp = firstNode.data;
        Node curr = firstNode.getNextNode();
        //curr.setNextNode(null);
        firstNode = curr;


        return temp;
    }

    /**
     * Retrieves this stack's top entry.
     *
     * @return The object at the top of the stack.
     * @throws //EmptyStackException if the stack is empty.
     */
    @Override
    public T peek() {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return firstNode.data;
    }

    /**
     * Detects whether this stack is empty.
     *
     * @return True if the stack is empty.
     */
    @Override
    public boolean isEmpty() {
        if(firstNode==null){
            return true;
        }
        return false;
    }

    /**
     * Removes all entries from this stack.
     */
    @Override
    public void clear() {
        firstNode =null;

    }

    public static boolean isBalanced(File webpage) throws FileNotFoundException {

        //check if the file exists
        if(!webpage.exists()){
            throw new IllegalArgumentException("File does not exist");
        }

        //Create the empty stack to hold the opening tags
        MyStack<String> emptyStack = new MyStack<>();

        //Break apart the html file 'word by word and test for tags
        Scanner input = new Scanner(webpage);
        while(input.hasNext()) {
            String line = input.next().trim();
            //System.out.println(line);

            // skip any non tag tokens

            //if the token doesn't have both a '<' and a '>' skip it
            //Hint: String class indexOfmethod returns -1 if the char isn't present
            if (line.indexOf('<') == -1 || line.indexOf('>') == -1) {
                continue; // Skip this token and move to the next one
            }


            //check for double tags <body><p> A test too </p></body>
            //Hint: string class has a lastIndexOf method

            int firstTagStart = line.indexOf('<');
            int firstTagEnd = line.indexOf('>');
            int lastTagStart = line.lastIndexOf('<');
            int lastTagEnd = line.lastIndexOf('>');

            /*System.out.println(firstTagStart);
            System.out.println(firstTagEnd);
            System.out.println(lastTagStart);
            System.out.println(lastTagEnd);*/

            // if double tags break apart tags into separate tags
            // Hint: use substring and the lastIndexOf


            if (firstTagStart != -1 && firstTagEnd != -1 && lastTagEnd != -1 && lastTagStart != -1
                    && firstTagStart != lastTagStart && firstTagEnd != lastTagEnd) {

                // if double tags break apart tags into separate tags
                // Hint: use substring and the lastIndexOf
                String splitOne = line.substring(firstTagStart, firstTagEnd + 1);
                String splitTwo = line.substring(lastTagStart, lastTagEnd + 1);
                //System.out.println(splitOne);
                //System.out.println(splitTwo);

                //if an opening tag is found push it into onto the stack
                if (splitOne.charAt(1) != '/') {
                    emptyStack.push(splitOne);

                }

                //If a closing tag is found pop tag from stack and check if it's a match

                //If not a match or stack is empty, return false
                //this is for the first split string
                if (splitOne.charAt(1) == '/') {
                    if (emptyStack.isEmpty()) {
                        return false;
                    }
                    else if (!emptyStack.isEmpty()) {
                        String top = emptyStack.pop();
                        if (!(('<' + splitOne.substring(2)).equalsIgnoreCase(top))) {
                            return false;
                        }
                    }
                }

                //if an opening tag is found push it into onto the stack second split
                if (splitTwo.charAt(1) != '/') {
                    emptyStack.push(splitTwo);
                }


                //If a closing tag is found pop tag from stack and check if it's a match

                //If not a match or stack is empty, return false
                //this is for the first split string



                //If a closing tag is found pop tag from stack and check if it's a match

                //If not a match or stack is empty, return false
                //this is for the second split string

                if (splitTwo.charAt(1) == '/') {
                    if (emptyStack.isEmpty()) {
                        return false;
                    }
                    else if(!emptyStack.isEmpty()) {
                        String top1 = emptyStack.pop();
                        if (!(('<' + splitTwo.substring(2)).equalsIgnoreCase(top1))) {
                            return false;
                        }
                    }
                }


            }
            else{  // handle a single tag
                String storeTag = line.substring(firstTagStart, firstTagEnd+1);


                //if opening tag then push it into stack
                if(storeTag.charAt(1)!= '/'){
                    emptyStack.push(storeTag);

                }

                //If a closing tag is found pop tag from stack and check if it's a match
                //If not a match or stack is empty, return false
                if(storeTag.charAt(1)== '/'){
                    if (emptyStack.isEmpty()) {

                        return false;
                    }
                    else if (!emptyStack.isEmpty()){
                        String popped = emptyStack.pop();
                        if (!(('<' + storeTag.substring(2)).equalsIgnoreCase(popped))) {

                            return false;
                        }


                    }

                }


            }



        }// end while

        // done reading file and stack is not empty, return false
        if( !emptyStack.isEmpty()){

            return false;
        }

        //If done reading file and stack is empty, return true

        return true;

        //Part of algorithm
            //if an opening tag is found push it into onto the stack
            //If a closing tag is found pop tag from stack and check if it's a match

            //If not a match or stack is empty, return false

            //If there is a double tag on this line

                //If an opening tag is found push it onto the stack

                //If a closing tag is found pop tag from stack and heck if it's a match
                // If not a match or stack is empty, return false

        //If done reading file and stack is not empty, return false

        //If done reading file and stack is empty, return true
//
    }
}
