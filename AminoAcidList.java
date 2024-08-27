/**
 * An object of this class will be used to create an AminoAcid object (containing all three identifiers) 
 * when initially given only the amino acid's three-letter code.
 * The class implements a list of amino acid identifiers from the reference cited below.
 * @author Prakash
 * 
 * Reference:
 * Wikipedia. (n.d.). Amino Acids. Wikimedia. Retrieved from 
 * https://upload.wikimedia.org/wikipedia/commons/a/a9/Amino_Acids.svg.
 *
 */

public class AminoAcidList {
	
	/**
	 * An array which will store the amino acids
	 */
	private AminoAcid[] aminoAcidArray;
	
	/**
	 * Constructor to create the AminoAcidList object, containing all the possible amino acids' identifiers
	 */
	public AminoAcidList() {
		
		// Create this 2D array where each row is for an amino acid and each column is for one of the three types of identifiers. This uses the resource cited
		String [][] aminoAcidDetails = {
				{"Glycine", "Gly", "G"},
				{"Alanine", "Ala", "A"},
				{"Valine", "Val", "V"},
				{"Leucine", "Leu", "L"},
				{"Isoleucine", "Ile", "I"},
				{"Methionine", "Met", "M"},
				{"Proline", "Pro", "P"},
				{"Tryptophan", "Trp", "W"},
				{"Phenylalanine", "Phe", "F"},
				{"Tyrosine", "Tyr", "Y"},
				{"Serine", "Ser", "S"},
				{"Threonine", "Thr", "T"},
				{"Asparagine", "Asn", "N"},
				{"Glutamine", "Gln", "Q"},
				{"Cysteine", "Cys", "C"},
				{"Aspartic Acid", "Asp", "D"},
				{"Glutamic Acid", "Glu", "E"},
				{"Lysine", "Lys", "K"},
				{"Arganine", "Arg", "R"},
				{"Histidine", "His", "H"}
		};
		
		// Declare a 1D string array which will contain the identifiers of an amino acid from row i in aminoAcidDetails
		String[] aminoAcidDetailsI;
		
		// Initialize the array of amino acid objects that will eventually be used to get amino acid objects from just their three-letter codes
		aminoAcidArray = new AminoAcid[20];
		
		// For each of the 20 rows in aminoAcidDetails
		for (int i = 0; i < 20; i++) {
			
			// Get the identifiers of the current amino acid
			aminoAcidDetailsI = aminoAcidDetails[i];
			// Put an amino acid object into this array, using the identifiers
			aminoAcidArray[i] = new AminoAcid(aminoAcidDetailsI[2].charAt(0), aminoAcidDetailsI[1], aminoAcidDetailsI[0]);
			
		}
		
	}
	
	/**
	 * Method to get an AminoAcid object using only its three-letter code
	 * @param threeLetter Three-letter code of the desired amino acid
	 * @return Amino acid object with the given three-letter code
	 */
	public AminoAcid getAA(String threeLetter) {
		
		// Declare a variable that will represent the current amino acid
		AminoAcid aAcidCurr;
		
		// For each amino acid (until the desired amino acid if it gets reached)
		for (int i = 0; i < 20; i++) {
			
			// Update the current amino acid
			aAcidCurr = aminoAcidArray[i];
			// If it is the desired amino acid based on the given three-letter code, return it
			if (aAcidCurr.getThreeLetter() == threeLetter) {
				return aAcidCurr;
			}
			
		}
		
		//If nothing has been returned
		// print an error message and return null
		System.out.println("Error: Invalid three-letter code.");
		return null;
		
	}

}