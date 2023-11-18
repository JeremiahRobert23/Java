package cmsc256;

/**
 * This is an interface for a class that represents a string,
 * defined as a sequence of characters. It provides methods
 * that
 */
public interface WackyStringInterface {

	/**
	 * Sets the value of the current string.
	 *
	 * @param string
	 *            The value to be set
	 */
	public void setWackyString(String string);

	/**
	 * Returns the current string
	 *
	 * @return Current string
	 */
	public String getWackyString();

	/**
	 * Returns a string that consists of all and only the characters
	 * in every fourth positions (i.e., fourth, eighth, and so on) in
	 * the current string, in the same order and with the same case as
	 * in the current string. The first character in the string is
	 * considered to be in Position 1.
	 *
	 * @return String made of characters in every fourth positions in the
	 * current string
	 */
	public String getEveryFourthCharacter();

	/**
	 * Returns a string that consists of all and only the characters
	 * in either the odd positions (i.e., first, third, fifth, and so on)
	 * or in the even positions (i.e., second, fourth, sixth, and so on)
	 * current string, in the same order and with the same case as in
	 * the current string. The first character in the string is
	 * considered to be in Position 1.
	 * @return String made of characters in odd positions in the
	 * current string
	 * @throws  IllegalArgumentException if parameter is other than "odd" or "even"
	 */
	public String getEvenOrOddCharacters(String evenOrOdd);

	/**
	 * Returns the number of characters that are digits in the current string.
	 *
	 * @return Number of digits in the current string
	 */
	public int countDigits();

	/**
	 * Returns true if the current string contains on or more characters
	 * before an '@' character, followed by either "vcu.edu" 
     * For example, RodneyTheRam@vcu.edu a valid vcu email address also
	 * and RamFan@gmail.com is not.
	 *
	 * @return true if current string is formated as valid VCU email address
	 * 	        Returns false otherwise.
	 */
	public boolean isValidVCUEmail();

	/**
	 * Extracts a phone number (with area code) from this String and returns it
	 * in a standard format with the area code in parenthesis followed by a
	 * then a dash separating the third and fourth digits "(000) 000-0000"
	 * space if this string does not contain exactly 10 digits, returns the
	 * message "This WackyString is not a phone number."
	 * Note: any characters that are not digits should not be included
	 *   in the returned string.
	 *   For example: "Office phone: 804-828-7135" --> "(804) 828-7135"
	 *
=	 * @return			String containing the formatted phone number
	 */
	public String standardizePhoneNumber();

    /**
     * Replace all occurrences of a single zero (0) with the string "Go Rams"
	  * in the current string,
     * and all occurrences of a double zero (00) with the string "CS@VCU"
     */
	public void ramifyString();

	/**
	 * Replace the _individual_ digits in the current string, between
	 * startPosition and endPosition (included), with the corresponding
	 * Roman numeral symbol(s). The first character in the string is
	 * considered to be in Position 1. Digits are converted individually,
	 * even if contiguous, and digit "0" is not converted (e.g., 460 is
	 * converted to IVVI0). In case you are not familiar with Roman
	 * numerals, see https://en.wikipedia.org/wiki/Roman_numerals
	 *
	 * @param startPosition  Position of the first character to consider
	 * @param endPosition    Position of the last character to consider
	 * @throws IllegalArgumentException
	 *            If "startPosition" > "endPosition" (but both are
	 *            within bounds)
	 *         IndexOutOfBoundsException
	 *         		If either "startPosition" or "endPosition" are out of
	 *           	bounds (i.e., either less than 1 or greater than the
	 *           	length of the string)
	 */
	public void convertDigitsToRomanNumeralsInSubstring(int startPosition, int endPosition)
			throws IllegalArgumentException, IndexOutOfBoundsException;
}
