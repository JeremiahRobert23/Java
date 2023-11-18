/**
 * Jeremiah Robert
 * Project 1
 * Course Number : CMSC 256
 * Section:901
 */

package cmsc256;

public class RamString implements WackyStringInterface{
    private  String value;

    //parameterized constructor that throw new Illegal Argument Exception if its null
    public RamString(String string) throws IllegalArgumentException{
        if(string == null){
            throw new IllegalArgumentException();
        }
        //setWackyString
        setWackyString(string);
    }
    //default constructor that set value to CS@VCU!
    public RamString(){
        value = "CS@VCU!";
    }

    //setWackyString method that set value to the current string
    @Override
    public void setWackyString(String string)throws IllegalArgumentException {
        if(string!=null){
            value= string;
        }

        else {
            throw new IllegalArgumentException();
        }
    }

    //returns the current string
    @Override
    public String getWackyString() {

        return value;
    }

    //returns a string consist of the characters in every fourth position
    @Override
    public String getEveryFourthCharacter() {
        String result = "";
        //for loop that goes through the string
        for(int i=0; i<value.length();i++){
            //checks i+1 because position 1 is when i is 0
            if((i+1)%4==0){
                //every fourth position character is added to result
                result = result +value.charAt(i);
            }
        }
        return result;
    }

    //this method returns the odd or even characters in a string as needed
    @Override
    public String getEvenOrOddCharacters(String evenOrOdd)throws IllegalArgumentException {

        String result = "";
        if(evenOrOdd.equalsIgnoreCase("even")){
            // we will check for even index because position 1 is index 0
            for(int i=0; i<value.length();i++){
                if(i%2!=0){
                    result = result+value.charAt(i);
                }
            }
        }else{
            //because position 1 is index 0 which is even
            //therefore we check for even index to find odd position characters
            for(int i=0; i<value.length();i++){
                if(i%2==0){
                    result = result+value.charAt(i);
                }
            }
        }

        //if the evenOrOdd is neither even or odd then the new IllegalArgumentException is thrown
        if((!evenOrOdd.equalsIgnoreCase("even")&&(!evenOrOdd.equalsIgnoreCase("odd")))){
            throw new IllegalArgumentException();
        }

        return result;
    }

    //this method returns the number of digits in the string
    @Override
    public int countDigits() {
        int count = 0;
        for(int i=0; i<value.length();i++){
            if (Character.isDigit(value.charAt(i))){
                count++;
            }
        }
        return count;
    }

    //this method checks if a mentioned email is valid vcu email or not
    @Override
    public boolean isValidVCUEmail() {
        //checks if there is one or more characters before the index and vcu.edu after it
        int index=value.indexOf("@");
        if((index>1)&& (value.substring(index + 1)).equals("vcu.edu")){
            return true;
        }
       return false;
    }

    //this method standardizes a given phone number and throws exception it is not one
    @Override
    public String standardizePhoneNumber()throws IllegalArgumentException {
        String phone = "";
        String message = "This WackyString is not a phone number.";
        //phone is only digits and no other characters
        for(int i=0; i<value.length();i++){
            if(Character.isDigit(value.charAt(i))){
                phone = phone+value.charAt(i);
            }
        }
        //checks if there is 10 digits then formats it
        if (phone.length()==10){
            phone = "(" + phone.substring(0,3)+ ")"+ " "+ phone.substring(3,6)+"-" +phone.substring(6);
            return phone;
        }else{
            throw new IllegalArgumentException(message);
        }
    }

    //this method replaces occurrences of single zeroes with Go Rams and double zeroes with CS@VCU
    @Override
    public void ramifyString() {
        String ram = "";
        for(int i=0;i<value.length();i++){

            if(value.charAt(i)=='0' && value.charAt(i+1)=='0' && (i+1)<value.length()
                    && value.charAt(i-1)!='0' && value.charAt(i+2)!='0' && i-1>-1 && i+2<value.length()){
                ram = ram+"CS@VCU";
                //i++ to skip that next index
                i++;

            }else if (value.charAt(i)=='0' && value.charAt(i+1)!='0' && value.charAt(i-1)!='0' &&
                     (i+1)<value.length()){
                ram=ram+"Go Rams";
            }
            else{
                ram = ram+value.charAt(i);
            }

        }
        value=ram;

    }

    //this method replaces individual digits from the startPosition to the endPosition values that would be entered
    @Override
    public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition) throws IllegalArgumentException, IndexOutOfBoundsException {
        if(startPosition<1||startPosition>value.length()||endPosition>value.length()||endPosition<1){
            throw new IndexOutOfBoundsException();
        }
        if(startPosition>endPosition){
            throw new IllegalArgumentException();
        }

        String result= "";
        //below result is th value before the startPosition
        result+= value.substring(0,startPosition-1);
        //then using for loop we replace the digits to romans within startPosition and endPosition
        for (int i=startPosition-1; i<endPosition;i++){


                if (value.charAt(i) == '0') {
                    result += "0";
                }
                else if (value.charAt(i) == '1') {
                    result += "I";
                }
                else if (value.charAt(i) == '2') {
                    result += "II";
                }
                else if (value.charAt(i) == '3') {
                    result += "III";
                }
                else if (value.charAt(i) == '4') {
                    result += "IV";
                }
                else if (value.charAt(i) == '5') {
                    result += "V";
                }
                else if (value.charAt(i) == '6') {
                    result += "VI";
                }
                else if (value.charAt(i) == '7') {
                    result += "VII";
                }
                else if (value.charAt(i) == '8') {
                    result += "VIII";
                }
                else if (value.charAt(i) == '9') {
                    result += "IX";
                }
                else{
                    result+= value.charAt(i);
                }


        }
        //result is the value after the endPosition
        result+=value.substring(endPosition);
        value = result;

    }



}
