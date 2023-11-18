package cmsc256;

import java.util.Iterator;

public class HashTableQuadraticProbing<K,V> extends HashTableOpenAddressing<K,V>{
    public int quadraticProbe(final int index, final K keyIn) {
        boolean found = false;

        int removedStateIndex = -1; // Index of first removed location

        if(table[index] == null)    // The hash index is available

            return index;

        int probeIndex = index;

        int i =0;
        while (!found && table[probeIndex] != null) {
            if (table[probeIndex].isIn()) {

                if (keyIn.equals(table[probeIndex].getKey()))

                    found = true;       // Key found

                else                     // Follow probe sequence
                    probeIndex = (index + (i*i)) % table.length;
                    i++;

            }

            else {                              // Skip entries that were removed

                // Save index of first location in removed state

                if (removedStateIndex == -1)

                    removedStateIndex = probeIndex;

                probeIndex = (index + (i*i)) % table.length;
                i++;


            }

        }



        if (found || (removedStateIndex == -1) )

            return probeIndex;             // Index of either key or null

        else

            return removedStateIndex; // Index of an available location

    }

    /**
     * Task: Adds a new entry to the dictionary. If the given search
     * key already exists in the dictionary, replaces the
     * corresponding value.
     *
     * @param keyIn   an object search key of the new entry
     * @param valueIn an object associated with the search key
     * @return either null if the new entry was added to the dictionary
     * or the value that was associated with key if that value
     * was replaced
     */
    @Override
    public V put(K keyIn, V valueIn) {
        if(keyIn==null||valueIn==null){
            throw new IllegalArgumentException("either key or the value is null");
        }

        //Declare a variable to hold a reference to the value that was previously associated with the key
        V oldValue = null;
        Entry<K,V> newEntry = new Entry<>(keyIn, valueIn);
        //Call the getHashCode method to calculate the hash code:
        int index = super.getHashIndex(keyIn);

        //Call the quadraticProbe method to get the actual index to add the value into the hash table
        index = quadraticProbe(index, keyIn);

        //If the location with the probe index is null or has a REMOVED state,
        // add it to the hash table at this location and increment numEntries.
        if(table[index]==null||table[index].isRemoved()){
            table[index]= newEntry;
            numEntries++;
        }

        else {
            //Otherwise, set the oldValue variable to the value of the existing Entry at this location.
            // Then, replace the existing element with the new Entry.
            oldValue = table[index].getValue();
            table[index] = newEntry;
        }

        //check the size against the threshold (based on load factor) and call enlargeHashTable (to expand) if necessary.
        if(isFull()){
            enlargeHashTable();
        }
        return oldValue;
    }

    /**
     * Task: Removes a specific entry from the dictionary.
     *
     * @param keyIn an object search key of the entry to be removed
     * @return either the value that was associated with the search key
     * or null if no such object exists
     */
    @Override
    public V remove(K keyIn) {
        V removedValue = null;
        // calculate the initial index
        int index = super.getHashIndex(keyIn);
        // find the location of the key using linear probing
        index = quadraticProbe(index, keyIn);
        // if Key found; flag entry as removed and return its value
        if(table[index] != null && table[index].isIn()){
            removedValue = getValue(keyIn);
            table[index].setToRemoved();
            // decrement the number of elements
            numEntries--;
        }
        // else key not found; return null
        return removedValue;
    }

    /**
     * Task: Retrieves the value associated with a given search key.
     *
     * @param keyIn an object search key of the entry to be retrieved
     * @return either the value that is associated with the search key
     * or null if no such object exists
     */
    @Override
    public V getValue(K keyIn) {
        int index = quadraticProbe((getHashIndex(keyIn)),keyIn);
        Entry <K, V> item = table[index];

        if ((item  != null) && item.isIn())
            return item.getValue();

        //if no object is found at location
        return null;
    }

    /**
     * Task: Sees whether a specific entry is in the dictionary.
     *
     * @param key an object search key of the desired entry
     * @return true if key is associated with an entry in the
     * dictionary
     */
    @Override
    public boolean contains(K key) {

        int index = getHashIndex(key);

        Entry item = table[index];

        return (item != null) && item.isIn();
    }

    public static void main(String[] args){
        HashTableQuadraticProbing<String, Integer> purchases = new HashTableQuadraticProbing<>();

        String[] names = {"Pax", "Eleven", "Angel", "Abigail", "Jack"};
        purchases.put(names[0], 654);
        purchases.put(names[1], 341);
        purchases.put(names[2], 70);
        purchases.put(names[3], 867);
        purchases.put(names[4], 5309);

        System.out.println("Contents with quadratic probing:\n" + purchases.toString());

        System.out.println("Replaced old value was " + purchases.put(names[1], 170));
        System.out.println("Contents after changing Eleven to 170:\n" + purchases.toString());

        System.out.println("Calling getValue() on Pax, Eleven, & Angel:");
        System.out.println("\tPax: " + purchases.getValue(names[0]));
        System.out.println("\tEleven: " + purchases.getValue(names[1]));
        System.out.println("\tAngel: " + purchases.getValue(names[2]));

        purchases.remove(names[0]);
        purchases.remove(names[2]);
        System.out.println("Contents after removing Pax & Angel:\n" + purchases);

        purchases.put("Gino", 348);
        System.out.println("Contents after adding Gino:\n" + purchases);

        Iterator<String> keyIter = purchases.getKeyIterator();
        Iterator<Integer> valueIter = purchases.getValueIterator();
        System.out.println("Contents of the hash table:");
        while(keyIter.hasNext())
            System.out.println("Key-" + keyIter.next() + " : Value-" + valueIter.next());
    }
}
