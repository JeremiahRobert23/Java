package cmsc256;

public class CollegeStudent extends Person{
    private String level;
    public CollegeStudent(){
        super();
        level= "Freshman";
    }
    public CollegeStudent(String firstName, String middleName, String lastName, Address address, String phone, String email, String level) throws IllegalArgumentException {
        super(firstName, middleName, lastName, address, phone, email);
        setLevel(level);

    }

    public String getLevel(){
        return level;
    }
    public void setLevel(String level1) throws IllegalArgumentException {
        if(level1.equalsIgnoreCase("Freshman")||level1.equalsIgnoreCase("Sophomore")||level1.equalsIgnoreCase("Junior")||level1.equalsIgnoreCase("Senior")){
            level = level1;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String toString(){
        String result = super.toString() + "College Level: " + getLevel() + "\n";
        return result;
    }



}
