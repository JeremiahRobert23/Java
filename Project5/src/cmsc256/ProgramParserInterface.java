package cmsc256;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.List;

public interface ProgramParserInterface {
    
    public String getJavaFileName();
    
    public void setJavaFile(String javaFileName);
    
    public String getKeywordFileName();
    
    public void setKeywordFile(String keywordFileName);
    
    public AVLTree<String> createKeywordTree() throws FileNotFoundException;
    
    public String getInorderTraversal();
    
    public String getPreorderTraversal();
    
    public String getPostorderTraversal();
    
    public default Map<String, Integer> getValidJavaIdentifiers() throws FileNotFoundException{
        return null;
    }
    

    public default Map<String, List<Integer>> getInvalidJavaIdentifiers(){
        return null;
    }
    
}
