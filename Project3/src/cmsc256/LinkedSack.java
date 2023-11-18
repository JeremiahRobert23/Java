package cmsc256;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *  LinkedSack.java
 *  CMSC 256
 *  Project 3
 *  Purpose - A program that implements a linked list of Node objects
 *  LinkedSack implements the SackInterface and java.util.Iterable interface
 *  Fall 2023
 *  Robert, Jeremiah
 */

public class LinkedSack <T> implements SackInterface<T>, Iterable<T>{


    private Node <T>firstNode;

    private Node<T>tail;
    private Node<T>curr;
    private int listSize;

    //the default constructor
    public LinkedSack(){
        clear();
    }

    //parameterized constructor for LinkedSack
    public LinkedSack(T head){
        firstNode = new Node(head);
        listSize = 1;
    }


    //private Node class
    private class Node<T>{
        T value;
        Node <T>linkedNode;

        Node(T it, Node<T>inn){
            value = it;
            linkedNode = inn;
        }
        //Node constructor that take in it and set value to it and set linkedNode = null
        Node(T it){
            value= it;
            this.linkedNode = null;
        }



    }

    /** Returns the current number of entries in this sack.
     //        @return The integer number of entries currently in the sack.
     //    */
    @Override
    public int getCurrentSize() {
        return listSize;
    }

    /** Checks if this sack is empty.
     @return True if the sack is empty, or false if not.
     */
    @Override
    public boolean isEmpty() {
        boolean result = false;
        //checks if the listSize is zero
        if (listSize==0){
            result = true;
        }
        return result;
    }

    /** Adds a new entry to this sack.
     @param newEntry The object to be added as a new entry.
     @return True if the addition is successful, or false if not.
     */
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.linkedNode = firstNode;
        firstNode = newNode;


        listSize++;
        return true;

    }

    /**
     * Construct a string representation of the object
     * @return result to show the sack contents
     */
    public String toString(){
        String result = "{Size:"+listSize+ " ";
        Node curr  = firstNode;
        for(int index = 0; index<listSize; index++){
            result+= "["+ curr.value+ "]";
            curr = curr.linkedNode;
        }
        result+="}";
        return result;
    }


    /** Removes one unspecified entry from this sack, if possible.
     @return Either the removed entry, if the removal
     was successful, or null.
     */
    @Override
    public T remove () throws NoSuchElementException {
        if (isEmpty()) {
            // Nothing to remove
            return null;
        }
        T it = firstNode.value;
        firstNode = firstNode.linkedNode;
        //decrease the listSize
        listSize--;
        //return the node
        return it;
    }


    /** Removes one occurrence of a given entry from this sack,
     if possible.
     @param anEntry The entry to be removed.
     @return True if the removal was successful, or false if not
     */
    @Override
    public boolean remove(T anEntry) {


        Node curr = firstNode;
        Node header = null;
        while(curr!=null){
            if( curr.value.equals(anEntry)){
                if(header==null) {
                    firstNode = curr.linkedNode;

                }
                else{
                    header.linkedNode = curr.linkedNode;
                }
                listSize--;
                return true;

            }
            header = curr;
            curr = curr.linkedNode;

        }

        return false;
    }

    /** Removes all entries from this sack.
     */
    @Override
    public void clear() {
        curr = tail = new Node(null);
        firstNode = new Node(tail);
        listSize = 0;

    }

    /** Counts the number of times a given entry appears in this sack.
     @param anEntry The entry to be counted.
     @return The number of times anEntry appears in the sack.
     */
    @Override
    public int getFrequencyOf(T anEntry) {
        int count = 0;
        Node curr = firstNode;
        while(curr!=null){
            if(anEntry.equals(curr.value)){
                count++;
            }
            curr = curr.linkedNode;
        }
        return count;
    }

    /** Tests whether this sack contains a given entry.
     @param anEntry The entry to locate.
     @return True if the sack contains anEntry, or false if not.
     */
    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node curr = firstNode;
        while(!found && (curr!=null)){
            if(anEntry.equals(curr.value)){
                found = true;
            }
            else{
                curr = curr.linkedNode;
            }
        }
        return found;
    }

    /** Retrieves all entries that are in this sack.
     @return A newly allocated array of all the entries in the sack.
     Note: If the sack is empty, the returned array is empty.
     */
    @Override
    public T[] toArray() {
        T[]elements = (T[])new Object[listSize];
        int i = 0;
        Node newNode = firstNode;
        while((i<listSize)&&(newNode!=null)){
            elements[i] = (T) newNode.value;
            newNode = newNode.linkedNode;
            i++;
        }
        return elements;
    }

    /** Check to see if two sacks are equals.
     * @param otherSack Another object to check this sack against.
     * @return True if the two sacks contain the same objects with the same frequencies.
     */

    public boolean equals(LinkedSack<T> otherSack){

        if(otherSack.listSize == listSize ){
            return true;
        }
        return false;


        /*Node<T> curr = firstNode;
        Node<T> newcurr = otherSack.firstNode;

        while (curr != null && newcurr != null) {

            if (!curr.value.equals(newcurr.value)) {
                return false;
            }
            int frequency1 = getFrequencyOf(curr.value);
            int frequency2 = otherSack.getFrequencyOf(newcurr.value);

            if (frequency1 != frequency2) {
                return false;
            }

            curr = curr.linkedNode;
            newcurr = newcurr.linkedNode;
        }


        if (curr != null || newcurr != null) {

            return false;
        }

        return true; */


    }




    /** Duplicate all the items in a sack.
     * @return True if the duplication is possible.
     */

    public boolean duplicateAll(){

        Node <T> curr = firstNode;
        int ogSize = getCurrentSize();

        while((curr!=null) && (curr.linkedNode!=null)){
            add(curr.value);
            curr = curr.linkedNode;

        }
        int newSize = getCurrentSize();
        if(newSize==ogSize*2){
            return true;
        }
        return false;
    }


    /** Remove all duplicate items from a sack
     */

    public void removeDuplicates(){

        Node <T>curr = firstNode;
        while(curr!=null){
            Node<T> newNode = curr;
            while(newNode.linkedNode!=null){
                if(curr.value.equals(newNode.linkedNode.value)){
                    newNode.linkedNode = newNode.linkedNode.linkedNode;
                    listSize--;
                }
                else{
                    newNode = newNode.linkedNode;
                }
            }
            curr = curr.linkedNode;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>();
    }


    private class ListIterator<T> implements Iterator<T>{

        private Node nextNode;


        private ListIterator() {
            nextNode = firstNode;
        }
        @Override
        public boolean hasNext() {
            return nextNode!=null;
        }

        @Override
        public T next()throws NoSuchElementException {
            if (hasNext()) {
                Node returnNode = nextNode; // Get next node
                nextNode = nextNode.linkedNode; // Advance iterator
                return (T)returnNode.value; // Return next entry
            }
            else {
                throw new NoSuchElementException("Illegal call to next() iterator is after end of list.");
            }
        }
    }




}