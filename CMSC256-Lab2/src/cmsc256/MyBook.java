package cmsc256;

public class MyBook implements Comparable<MyBook>{
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String ISBN10;
    private String ISBN13;

    public MyBook(){
        title = "None Given";
        authorFirstName = "None Given";
        authorLastName = "None Given";
        ISBN10 = "0000000000";
        ISBN13 = "0000000000000";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        if(title!=null){
            this.title = title;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        if(authorFirstName!=null){
            this.authorFirstName = authorFirstName;
        }else {
            throw new IllegalArgumentException();
        }

    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        if(authorLastName!=null){
            this.authorLastName = authorLastName;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) throws IllegalArgumentException{
        for(int i=0; i<ISBN10.length();i++){
            if(!Character.isDigit(ISBN10.charAt(i))){
                throw new IllegalArgumentException();
            }
        }
        if(ISBN10!=null && ISBN10.length()==10) {
            this.ISBN10 = ISBN10;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13)throws IllegalArgumentException {
        for(int i=0; i<ISBN13.length();i++){
            if(!Character.isDigit(ISBN13.charAt(i))){
                throw new IllegalArgumentException();
            }
        }
        if(ISBN13!=null&& ISBN13.length()==13) {
            this.ISBN13 = ISBN13;
        }else {
            throw new IllegalArgumentException();
        }
    }

    public MyBook(String title, String authorFirstName, String authorLastName, String ISBN10, String ISBN13){
        setTitle(title);
        setAuthorFirstName(authorFirstName);
        setAuthorLastName(authorLastName);
        setISBN10(ISBN10);
        setISBN13(ISBN13);
    }

    @Override
    public String toString(){
        String result = "Title: "+title+"\n"+ "Author: "+ authorFirstName+" "+authorLastName+ "\n"+
                "ISBN10: "+ISBN10+"\n"+"ISBN13: "+ISBN13;
        return result;
    }

    public boolean equals(Object otherBook){
        // are the variables referencing the same object?
        if(this == otherBook) {
            return true;
        }

        // is the parameter a MyBook object?
        if(!(otherBook instanceof MyBook)) {
            return false;
        }

        MyBook other = (MyBook)otherBook;

        if(ISBN10.equalsIgnoreCase(other.getISBN10())
                && ISBN13.equalsIgnoreCase(other.getISBN13())){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(MyBook o) {
        int result = authorLastName.compareTo(o.getAuthorLastName());
        if (result==0){
            result = authorFirstName.compareTo(o.getAuthorFirstName());

        }
        if(result==0){
            result = title.compareTo(o.getTitle());

        }
        return result;

    }
}
