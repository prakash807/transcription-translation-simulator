/**
 * This class represents a molecule of tRNA. For the purpose of this project, a tRNA molecule has two features:
 * -Its anticodon which binds with a codon on mRNA
 * -Its amino acid which gets added to a growing chain of amino acids
 * @author Prakash
 *
 */
public class TRNAMolecule {
	
	/**
	 * The anticodon is represented by a String of 3 characters, each representing one base
	 */
	private String anticodon;
	/**
	 * The amino acid of this tRNA, which would get added to an amino acid chain
	 */
	private AminoAcid aminoAcidTRNA;
	
	/**
	 * Constructor to initialize a tRNA molecule
	 * @param anticodon Anticodon of the tRNA molecule
	 * @param aminoAcidTRNA Amino acid of the tRNA molecule
	 */
	public TRNAMolecule(String anticodon, AminoAcid aminoAcidTRNA) {
		
		this.anticodon = anticodon;
		this.aminoAcidTRNA = aminoAcidTRNA;
		
	}
	
	/**
	 * Mutator method for anticodon
	 * @param newAnticodon The new desired anticodon for this tRNA
	 */
	public void setAnticodon(String newAnticodon) {
		
		anticodon = newAnticodon;
		
	}

	/**
	 * Mutator method for amino acid
	 * @param newAminoAcid The new desired amino acid for this tRNA
	 */
	public void setAminoAcid(AminoAcid newAminoAcid) {
		
		aminoAcidTRNA = newAminoAcid;
		
	}
	
	/**
	 * Accessor method for anticodon
	 * @return Anticodon of this tRNA
	 */
	public String getAnticodon() {
		
		return anticodon;
		
	}
	
	/**
	 * Accessor method for anticodon
	 * @return Amino acid of this tRNA
	 */
	public AminoAcid getAminoAcid() {
		
		return aminoAcidTRNA;
		
	}
	
	/**
	 * String representation of this tRNA
	 * Does not actually get used in Main since it would prevent illustration of the full amino acid chain
	 * @param numSpacesBefore The desired number of spaces to be printed in front of each line of this string representation
	 * @return String representation of tRNA molecule
	 */
	public String toString(int numSpacesBefore) {
		
		/*
		 * The string representation will be formatted like this, with numSpacesBefore used to print certain lengths of whitespace before each line
		 *    Abc
		 *    | |
		 * 3' AAA 5'
		 */
		
		// Initialize the string representation
		String output = "";
		
		// Print numSpacesBefore spaces before the first line (amino acid)
		for (int i = 0; i < numSpacesBefore; i++) {
			output = output + ' ';
		}
		
		// Print the amino acid string
		output = output + aminoAcidTRNA.getThreeLetter() + '\n';
		
		// Print numSpacesBefore spaces before the second line (vertical bars connecting the amino acid to the anticodon)
		for (int i = 0; i < numSpacesBefore; i++) {
			output = output + ' ';
		}
		
		// Print vertical bars that will connect the amino acid to the anticodon
		output = output + "| |\n";
		
		// Print numSpacesBefore-3 spaces before the third line (anticodon)
		for (int i = 0; i < numSpacesBefore - 3; i++) {
			output = output + ' ';
		}
		
		// Print the anticodon, with 3' and 5' labelled
		output = output + "3' " + anticodon + " 5'";
		
		// Return the string representation
		return output;
		
	}
	
}
