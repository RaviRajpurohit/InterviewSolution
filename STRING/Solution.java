/**
 * Problem Statement: Find the starting index of sub-string in given string. constraint String does belong to {@link STRING}.
 *  
 */
public class Solution {

	public static void main(String[] args) {

		STRING string = new STRING("RaviRajpurohit");

		STRING subString = new STRING("Raj");

		// index variable to travels over main string
		int index = 0;

		int matchingIndex = -1;
		while (string.charAt(index) != '#') {

			//check if first character of sub string and main string's character is equal
			if (string.charAt(index) == subString.charAt(0)) {
				//check sub string present in main string or not
				if (check(string, subString, index, 0)) {
					matchingIndex = index;
					break;
				}
			}
			index++;
		}

		System.out.println(matchingIndex);

	}

	private static boolean check(STRING s1, STRING s2, int index1, int index2) {

		while (s2.charAt(index2) != '#') {

			if (s1.charAt(index1) != s2.charAt(index2)) {
				return false;
			}
			index1++;
			index2++;
		}
		return true;
	}

}

/**
 * STRING Class build on JDK String class where only one method is allowed.
 * @author Ravi
 *
 */
class STRING {

	private String string;

	/**
	 * Constructor to initialize with normal String.
	 * @param string
	 */
	public STRING(String string) {
		this.string = string;
	}

	/**
	 * Method will return the character from string for given index. if index exceed the length then return #.
	 * @param index
	 * @return
	 */
	public char charAt(int index) {
		if (index >= string.length()) {
			return '#';
		}
		return string.charAt(index);
	}
}
