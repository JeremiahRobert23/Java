package cmsc256;

public class BinarySearchTree <E extends Comparable<? super E>> {
    //instance variables to hold the root node of the tree (a BinaryNode) and the size of the tree:
    private int size = 0;
    private BinaryNode root = null;

    public BinarySearchTree(){
        size = 0;
        root= null;
    }
    public BinarySearchTree(int size, BinaryNode root) {
        this.size = size;
        this.root = root;
    }

    //To add elements into the tree you add new node to a specific parentNode.
    //return false if the value is equal to an element already present in the tree based on natural ordering
    private boolean addToParent(BinaryNode parentNode, BinaryNode addNode){
        //create an integer that  compare the parent node's value to the child node's value
        int compare = parentNode.value.compareTo(addNode.value);

        //boolean to represent whether node is added
        boolean wasAdded = false;
        if (compare > 0) {
            // if parent has no left node, add new node as left
            if (parentNode.left == null) {
                parentNode.left =addNode;  // fill in this blank
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's left (recursive)
                wasAdded = addToParent(parentNode.left, addNode);
            }
        }
        else if (compare < 0) {
            // if parent has no right node, add new node as right
            if (parentNode.right == null) {
                parentNode.right = addNode;
                wasAdded = true;
            }
            else {
                // otherwise, add to parentNode's right (recursive)
                wasAdded = addToParent(parentNode.right, addNode);
            }
        }
        return wasAdded;

    }
    public boolean add(E inValue) {
        // Create a node reference the new element
        BinaryNode node = new BinaryNode( inValue);
        boolean wasAdded = true;

        //if tree is empty then set root to new node
        if (root == null) {
            root = node;
        }
        else {
            wasAdded = addToParent(root, node);
        }
        if (wasAdded) {
            size++;
        }
        return wasAdded;

    }

    //removes the values from the tree and return true is element was found
    public boolean remove(E removeValue) {
        if (root == null) {
            return false;
        }

        //If the node to remove is root then new rootNode is assigned
        if (root.value.compareTo(removeValue) == 0) {
            //If the left child is null, then make the root's right child the root.
            if (root.left == null) {
                root = root.right;
            }
            //If the right child is null, then make the root's left child the root.
            else if (root.right == null) {
                root = root. left;
            }

            //Otherwise (if neither the left child nor the right child are null),
            // set the left node to the root and then add the right node to the left node using the addToParent method.
            // Recall that the addtoParent method will add to the most appropriate spot in the tree.
            else {
                BinaryNode formerRight = root.right;
                root = root.left;
                addToParent(root, formerRight);
            }
            size--;
            return true;
        }
        //if node to remove is not root then another recursive call must be invoked to find the element
        // to find and remove it
        return removeSubNode(root, removeValue);
    }

    //remove node that is not determined to be the root or work its way down branches to find the right node
    private boolean removeSubNode(BinaryNode parent, E removeValue) {
        //compare parentValue to removeValue
        int compareParent = parent.value.compareTo(removeValue);

        //pick whether value to be removed is on the right or left of the parent
        BinaryNode subTree = null;

        if (compareParent > 0) {

            subTree = parent.left;
        }
        else {

            subTree = parent.right;

        }
        //If that branch is null, then the value doesn't exist in the tree:
        if (subTree == null) {
            return false;
        }

        //If the value is equal to the value in the current branch, then the selected node can be deleted.
        //Because the replacement node is stored locally, it will have to be added back to the parent

        if (subTree.value.compareTo(removeValue) == 0) {
            BinaryNode replacement;
            if (subTree.left == null) {
                replacement = subTree.right;
            }
            else if (subTree.right == null) {
                replacement = subTree.left;
            }
            else {
                BinaryNode formerRight = subTree.right;
                replacement = subTree.left;
                addToParent(replacement, formerRight);
            }

            if (compareParent > 0) {
                parent.left = replacement;
            }
            else {
                parent.right = replacement;
            }

            size--;
            return true;
        }
        //If the value could still exist in the tree but is not the current branch,
        // then invoke the method recursively with the branch node
        return removeSubNode(subTree, removeValue);
    }

    public int size() {

        return size;

    }

    public BinaryNode getRoot() {

        return root;

    }

    public void clear() {
        root = null;
        size = 0;
    }

    class BinaryNode{
        protected E value;
        protected BinaryNode right;
        protected BinaryNode left;


        //constructor to BinaryNode that will store that node's data.

        public BinaryNode(E valueIn) {
            value = valueIn;
        }

    }
}
