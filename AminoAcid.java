/**
 * This class represents an amino acid.
 * An amino acid has three identifiers involved here: one-letter code, three-letter code, and full name
 * These are represented by this class's instance variables.
 * @author Prakash
 *
 */
public class AminoAcid {
	
	/**
	 * The one-letter code of this amino acid
	 */
	private char oneLetter;
	/**
	 * The three-letter code of this amino acid
	 */
	private String threeLetter;
	/**
	 * The full name of this amino acid
	 */
	private String fullName;
	
	/**
	 * Constructor to initialize the amino acid with its identifiers
	 * @param oneLetter The amino acid's one-letter code
	 * @param threeLetter The amino acid's three-letter code
	 * @param fullName The amino acid's full name
	 */
	public AminoAcid(char oneLetter, String threeLetter, String fullName) {
		
		this.oneLetter = oneLetter;
		this.threeLetter = threeLetter;
		this.fullName = fullName;
		
	}
	
	/**
	 * Mutator method to set the one-letter code of the amino acid
	 * @param newOneLetter The desired value of the one-letter code
	 */
	public void setOneLetter(char newOneLetter) {
		
		oneLetter = newOneLetter;
		
	}
	
	/**
	 * Mutator method to set the three-letter code of the amino acid
	 * @param newThreeLetter The desired value of the one-letter code
	 */
	public void setThreeLetter(String newThreeLetter) {
		
		threeLetter = newThreeLetter;
		
	}

	/**
	 * Mutator method to set the full name of the amino acid
	 * @param newFullName The desired value of the full name
	 */
	public void setFullName(String newFullName) {
	
		fullName = newFullName;
	
	}
	
	/**
	 * Accessor method to get the one-letter code of the amino acid
	 * @return The one-letter code
	 */
	public char getOneLetter() {
		
		return oneLetter;
		
	}
	
	/**
	 * Accessor method to get the three-letter code of the amino acid
	 * @return The three-letter code
	 */
	public String getThreeLetter() {
		
		return threeLetter;
		
	}
	
	/**
	 * Accessor method to get the full name of the amino acid
	 * @return The full name
	 */
	public String getFullName() {
		
		return fullName;
		
	}
	
	/**
	 * The method to represent the amino acid as a string
	 * It uses the three-letter code since that is not too long but is still a clearly unique identifier for a given amino acid
	 * @return The string representation of the amino acid
	 */
	public String toString() {
		
		// The string is the amino acid's three-letter code between the parentheses in "AminoAcid()" to show this is an amino acid object representing the amino acid with this three-letter code
		return "AminoAcid(" + threeLetter + ')';
		
	}

}
