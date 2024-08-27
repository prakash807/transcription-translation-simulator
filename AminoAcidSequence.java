/**
 * This class represents the sequence of an amino acid chain.
 * It extends the class DoublyLinkedList<AminoAcid> so it is a doubly linked list 
 * of AminoAcid objects, with additionally features, regarding the identifiers of the amino acids in the list.
 * @author Prakash
 *
 */
public class AminoAcidSequence extends DoublyLinkedList<AminoAcid> {

	/**
	 * A string of the one-letter codes of the amino acids in the list
	 */
	private String strOneLetter;
	/**
	 * A string of the three-letter codes of the amino acids in the list
	 */
	private String strThreeLetter;
	/**
	 * A string of the full names of the amino acids in the list
	 */
	private String strFullName;
	
	/**
	 * Constructor to initialize an amino acid sequence with empty strings
	 */
	public AminoAcidSequence() {
		
		strOneLetter = "";
		strThreeLetter = "";
		strFullName = "";
		
	}
	
	/**
	 * Method to update the three strings each representing the sequence
	 */
	public void updateSequenceStrings() {
		
		// Declare variables representing the current node and its amino acid
		DoublyLinkedListNode<AminoAcid> nodeCurr;
		AminoAcid aACurr;
		
		// Declare variables that represent identifiers of the current amino acid
		char aA1LCurr;
		String aA3LCurr;
		String aAFNCurr;
		
		// Start with the current node as the front node of the list
		nodeCurr = getFront();
		
		// For every node in the list
		while (nodeCurr != null) {
			
			// Get the amino acid that is in the node
			aACurr = nodeCurr.getData();
			
			// Get its identifiers
			aA1LCurr = aACurr.getOneLetter();
			aA3LCurr = aACurr.getThreeLetter();
			aAFNCurr = aACurr.getFullName();
			
			// In the three-letter code and full name strings, we want amino acid identifiers to be separated by '-'
			// So if this is the first node in the list, append the identifiers but without any '-' before each
			if (nodeCurr.getPrevious() == null) {
				
				strOneLetter = "" + aA1LCurr;
				strThreeLetter = aA3LCurr;
				strFullName = aAFNCurr;
				
			} else { // Otherwise, append them with '-' before each
				
				strOneLetter = strOneLetter + aA1LCurr;
				strThreeLetter = strThreeLetter + '-' + aA3LCurr;
				strFullName = strFullName + '-' + aAFNCurr;
				
			}
			
			nodeCurr = nodeCurr.getNext();
			
		}
		
	}
	
	/**
	 * Accessor method for the string of one-letter codes
	 * It first updates the string
	 * @return String of one-letter codes of amino acids in the sequence
	 */
	public String getStrOneLetter() {
		updateSequenceStrings();
		return strOneLetter;
	}
	
	/**
	 * Accessor method for the string of three-letter codes
	 * It first updates the string
	 * @return String of three-letter codes of amino acids in the sequence
	 */
	public String getStrThreeLetter() {
		updateSequenceStrings();
		return strThreeLetter;
	}
	
	/**
	 * Accessor method for the string of full names
	 * It first updates the string
	 * @return String of full names of amino acids in the sequence
	 */
	public String getStrFullName() {
		updateSequenceStrings();
		return strFullName;
	}

}
