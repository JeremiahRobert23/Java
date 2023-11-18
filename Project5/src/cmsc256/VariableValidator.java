package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *  VariableValidator.java
 *  CMSC 256
 *  Project 5
 *  Purpose - This program will create an AVL tree of the reserved words and operators in the Java language, and the query language keywords
 *  Fall 2023
 *  Robert, Jeremiah
 */
public class VariableValidator implements ProgramParserInterface{
    //file with Java keywords
    private File keyWordFile;
    //file with check variables
    private File codeFile;
    private AVLTree<String>keywordTree;

    public VariableValidator(){
        this.keyWordFile = null;
        this.codeFile = null;
    }

    public VariableValidator (String keyWordFileName){
        this.codeFile = null;
        setKeywordFile(keyWordFileName);

    }

    public VariableValidator(String keywords, String code){
        setKeywordFile(keywords);
        setJavaFile(code);
    }

    @Override
    public String getJavaFileName() {
        return codeFile.getName();
    }

    @Override
    public void setJavaFile(String javaFileName) {
        //test parameter for null
        if(javaFileName==null){
            //if null throw Illegal exception Argument
            throw new IllegalArgumentException("fileName is null");
        }

        //Create a File Object--> keyWord File

        codeFile = new File(javaFileName);
        //test that File exists
        if(!codeFile.exists()){
            throw new IllegalArgumentException("File does not exist");
        }

    }

    @Override
    public String getKeywordFileName() {
        return keyWordFile.getName();
    }

    @Override
    public void setKeywordFile(String keywordFileName) {
        //test parameter for null
        if(keywordFileName==null){
            //if null throw Illegal exception Argument
            throw new IllegalArgumentException("fileName is null");
        }

        //Create a File Object--> keyWord File

        keyWordFile = new File(keywordFileName);
        //test that File exists
        if(!keyWordFile.exists()){
            throw new IllegalArgumentException("File does not exist");
        }

    }

    @Override
    public AVLTree<String> createKeywordTree() throws FileNotFoundException {
        if (!keyWordFile.exists()){
            throw new FileNotFoundException();
        }

        keywordTree = new AVLTree<>();

        // scanner to read through the file
        Scanner input = new Scanner(keyWordFile);

        if(input!=null){
            while(input.hasNext()){
                String keyWord = input.next().trim();
                keywordTree.insert(keyWord);

            }
        }

        return keywordTree;
    }

    @Override
    public String getInorderTraversal() {
       /*if(keywordTree == null){
           try {
               createKeywordTree();
           } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
           }
       }*/
        return inorder(keywordTree.getRoot());
    }

    protected String inorder(AVLTree.AVLNode node){
        String inorderString = "";
        if(node!=null){
            if(node.getLeft()!=null){
                inorderString = inorderString+inorder(node.getLeft());
            }

            inorderString = inorderString+node.getElement()+" ";

            if(node.getRight()!=null){
                inorderString = inorderString+inorder(node.getRight());
            }



        }
        return inorderString;

    }

    @Override
    public String getPreorderTraversal() {

        return preorder(keywordTree.getRoot());
    }
    protected String preorder(AVLTree.AVLNode node){
        String preOrderString = "";
        if(node!=null){

            preOrderString = preOrderString+node.getElement()+" ";
            if (node.getLeft() != null) {
                preOrderString = preOrderString+preorder(node.getLeft());
            }

            if(node.getRight()!=null){
                preOrderString = preOrderString+preorder(node.getRight());
            }

        }
        return preOrderString;

    }


    @Override
    public String getPostorderTraversal() {

        return postorder(keywordTree.getRoot());
    }
    protected String postorder(AVLTree.AVLNode node){
        String postOrderString = "";
        if(node!=null){

            if(node.getLeft()!=null){
                postOrderString = postOrderString+postorder(node.getLeft());
            }

            if(node.getRight()!=null){
                postOrderString = postOrderString+postorder(node.getRight());
            }

            postOrderString = postOrderString+node.getElement()+" ";

        }
        return postOrderString;

    }


    public static void main(String[] args) throws FileNotFoundException{
        String javaFile = "MathExpressions2.java";
        VariableValidator validator = new VariableValidator("JavaKeywordList.txt",javaFile);
        AVLTree<String> aTree = validator.createKeywordTree();

        //display root, height
        System.out.println("\nRoot of trees is "+ aTree.getRoot().getElement()+ " Expected:false");
        System.out.println("Height of tree is "+ aTree.getRoot().getHeight()+ " Expected:7");

        //display traversals
        String inorder = validator.getInorderTraversal().trim();
        System.out.println("InOrder:" +inorder);
        System.out.println(inorder.startsWith("!=")&& inorder.endsWith("||"));

        String preorder = validator.getPreorderTraversal().trim();
        System.out.println("Preorder:" + preorder);
        System.out.println(preorder.startsWith("false")&& preorder.endsWith("||"));

        String postorder = validator.getPostorderTraversal().trim();
        System.out.println("Postorder:" + postorder);
        System.out.println(postorder.startsWith("%")&& postorder.endsWith("false"));

    }

}
