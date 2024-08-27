/**
 * An object of this class will be used to create a TRNAMolecule object with a given anticodon
 * The class implements a chart of anticodons and amino acids for tRNA molecules from the reference cited below.
 * @author Prakash
 * 
 * Reference:
 * Rye, C., Wise, R., Jurukovski, V., DeSaix, J., Choi, J., & Avissar, Y. (2016). 
 * 15.1 The Genetic Code. In Biology. essay, OpenStax. 
 * Retrieved from https://openstax.org/books/biology/pages/15-1-the-genetic-code. 
 * 
 */

public class TRNAList {
	
	/**
	 * An array which will store the tRNA molecules
	 */
	private TRNAMolecule[] moleculeArray;
	/**
	 * An object used to get amino acids when given their three-letter codes 
	 */
	private AminoAcidList aAList;
	
	/**
	 * Constructor to create the TRNAList object, containing all possible the tRNA molecules
	 */
	public TRNAList() {
		
		// Create an AminoAcidList object that will be used to get AminoAcid objects from their three-letter codes
		aAList = new AminoAcidList();
		
		// String containing the possible bases that can be in anticodons (RNA)
		String possibleBases = "UCAG";
		// Declare string representing the codon complementary to the anticodon of a given tRNA
		String codon5to3;
		// Declare string that is the three-letter code of a amino acid of a given tRNA
		String aA3L;
		// Declare string representing the anticodon of a given tRNA
		String anticodon3to5;
		// Initialize variable used to index into the array in which the tRNA objects will be stored
		int iMoleculeArray = 0;
		
		// Initialize array in which the tRNA objects will be stored
		moleculeArray = new TRNAMolecule[64];
		
		// Initialize variables holding the three letters representing the bases in a codon
		char firstLetterCodon;
		char secondLetterCodon;
		char thirdLetterCodon;
		
		// For each of the 4 possible first letters of codons
		for (int i = 0; i < 4; i++) {
			// For each of the 4 possible second letters of codons
			for (int j = 0; j < 4; j++) {
				// For each of the 4 possible third letters of codons
				for (int k = 0; k < 4; k++) {
					
					// Initialize the codon as an empty string
					codon5to3 = "";
					
					// Get the codon for this iteration of nested nested for loop
					firstLetterCodon = possibleBases.charAt(i);
					secondLetterCodon = possibleBases.charAt(j);
					thirdLetterCodon = possibleBases.charAt(k);
					codon5to3 = codon5to3 + firstLetterCodon + secondLetterCodon + thirdLetterCodon;
					
					// Implement the chart from the cited resource, to get the three-letter code of the correct amino acid for this codon
					// Each row of the chart has an if/else statement here, nested if/else statements in those for each column, 
					// and possibly nested if/else statements within those for each codon
					if (firstLetterCodon == 'U') {
						if (secondLetterCodon == 'U') {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "Phe";
							} else {
								aA3L = "Leu";
							}
						} else if (secondLetterCodon == 'C') {
							aA3L = "Ser";
						} else if (secondLetterCodon == 'A') {
							// Assume stop codon is not given.
							// Can assume this since the queue in Main only enqueues up to there
							aA3L = "Tyr";
						} else {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "Cys";
							} else {
								// Assume stop codon is not given.
								// Can assume this since the queue in Main only enqueues up to there
								aA3L = "Trp";
							}
						}
					} else if (firstLetterCodon == 'C') {
						if (secondLetterCodon == 'U') {
							aA3L = "Leu";
						} else if (secondLetterCodon == 'C') {
							aA3L = "Pro";
						} else if (secondLetterCodon == 'A') {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "His";
							} else {
								aA3L = "Gln";
							}
						} else {
							aA3L = "Arg";
						}
					} else if (firstLetterCodon == 'A') {
						if (secondLetterCodon == 'U') {
							if (thirdLetterCodon == 'G') {
								aA3L = "Met";	
							} else {
								aA3L = "Ile";
							}
						} else if (secondLetterCodon == 'C') {
							aA3L = "Thr";
						} else if (secondLetterCodon == 'A') {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "Asn";
							} else {
								aA3L = "Lys";
							}
						} else {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "Ser";
							} else {
								aA3L = "Arg";
							}
						}
					} else {
						if (secondLetterCodon == 'U') {
							aA3L = "Val";
						} else if (secondLetterCodon == 'C') {
							aA3L = "Ala";
						} else if (secondLetterCodon == 'A') {
							if (thirdLetterCodon == 'U' || thirdLetterCodon == 'C') {
								aA3L = "Asp";
							} else {
								aA3L = "Glu";
							}
						} else {
							aA3L = "Gly";
						}
					}
					
					// So now the amino acid name has been determined for this tRNA
					// Convert the codon to the anticodon, since we prefer to use the anticodon to identify the tRNA
					
					// Declare character variable that will hold a character(base) from the codon
					char charCurrCodon;
					// Initialize string representing anticodon, as empty string
					anticodon3to5 = "";
					
					// For each base in the codon, append the complementary base to the anticodon string
					for (int l = 0; l < 3; l++) {
						charCurrCodon = codon5to3.charAt(l);
						if (charCurrCodon == 'U') {
							anticodon3to5 = anticodon3to5 + 'A';
						} else if (charCurrCodon == 'C') {
							anticodon3to5 = anticodon3to5 + 'G';
						} else if (charCurrCodon == 'A') {
							anticodon3to5 = anticodon3to5 + 'U';
						} else {
							anticodon3to5 = anticodon3to5 + 'C';
						}
					}
					
					// Get the amino acid object with the three-letter code of this iteration
					AminoAcid aminoAcidCurr = aAList.getAA(aA3L);
					// Create a tRNA object with this amino acid object and the anticodon of this iteration, and put it into the tRNA array
					moleculeArray[iMoleculeArray] = new TRNAMolecule(anticodon3to5, aminoAcidCurr);
					// Update the number at which to index the tRNA array for the next iteration
					iMoleculeArray++;
					
				}
			}
		}
		
	}
	
	/**
	 * Method to get a TRNA object using only its anticodon
	 * @param anticodon3to5 Anticodon of the desired tRNA
	 * @return tRNA object with the given anticodon
	 */
	public TRNAMolecule getMolecule(String anticodon3to5) {
		
		// Declare a variable that will represent the current tRNA
		TRNAMolecule tRNACurr;
		
		// For each tRNA (until the desired tRNA if it gets reached)
		for (int i = 0; i < 64; i++) {
			
			// Update the current tRNA
			tRNACurr = moleculeArray[i];
			// If it is the desired tRNA based on the given anticodon, return it
			if (tRNACurr.getAnticodon().equals(anticodon3to5)) {
				return tRNACurr;
			}
			
		}
		
		//If nothing has been returned
		// print an error message and return null
		System.out.println("Error: Invalid anticodon.");
		return null;
		
	}

}
