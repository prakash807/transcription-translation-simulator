import java.util.Scanner; // Import Scanner class
import java.io.*;

/**
 * The Main class is used to run the program: a simulation of amino acid synthesis (DNA transcription and translation).
 * 
 * When I first learned about transcription and translation in school, I believed it would be a suitable process to create a simulation of. 
 * As I gained more programming knowledge, I started to better see how that could be done. For example, when I learned about queues, it reminded me
 * of like the codons are translated in ordered so they could be put into a queue. Recently, it felt about time to finally create this project.
 * 
 * This program was written with two main purposes in mind:
 * 1) To help students understand transcription and translation. The main steps of these processes are illustrated when this program is run, 
 *    for as many different examples as the number of times the program is run. Note: some background knowledge is required to BEST understand 
 *    the simulation (e.g. what DNA and RNA are)
 * 2) To be able to quickly determine the amino acid sequence produced by transcription and translation of a given DNA strand
 * 
 * When this program is run, the user is prompted as follows, until valid inputs are given:
 * -Enter a DNA strand unless you want one to be randomly generated
 *  -For a randomly generated one, indicate whether you desired default of custom settings regarding how long the different parts of the DNA strand are
 * Then, the program illustrates the transcription process (mRNA being created complementary to the DNA) followed by
 * the translation process (an amino acid chain being put together), ending with the sequence of the resulting amino acid chain shown in three ways 
 * (using their one-letter codes, their three-letter codes, and their full names)
 * 
 * Notes:
 * - There is room for improvements and new features in this program.
 *   - e.g. This program is currently only designed to show synthesis of one amino acid chain from one DNA strand.
 *   - e.g. The use of a queue may not have been necessary since currently all enqueuing happens before all dequeuing, but in a simulation 
 *     in which translation starts before all transcription is done, there may be more advantages to using a queue
 * 
 * @author Prakash
 *
 */
public class Main {

	/**
	 * Label printed in front of the DNA strand in each transcription step's illustration
	 */
	static final String DNALabel = "\nDNA Template:                               ";
	/**
	 * Label printed in front of the mRNA strand in each transcription step's illustration
	 */
	static final String mRNALabel = "mRNA being Constructed by RNA Polymerase:   ";
	
	/**
	 * Integer that will be used to index a string that represents mRNA
	 */
	static int iCodon;
	
	/**
	 * Main function to run the simulator
	 * @param args Any command line arguments. This is not currently set up to use any.
	 */
	public static void main(String[] args) {
		
		// Print title
		System.out.println("AMINO ACID SYNTHESIS (TRANSCRIPTION AND TRANSLATION) SIMULATOR\n\n");
		
		// Print heading #1
		System.out.println("1) REQUESTING INPUT:");
		
		// Declare variable that will store the inputted (or randomly generated DNA strand string)
		String dNA3to5Inputted;
		
		// Initialize this variable (later used to index mRNA at certain codons)
		iCodon = -1;
		// Initialize codon that will hold codons
		Queue<String> myCodonQueue = new Queue();
		// Initialize this as well. It will be eventually updated to be the index at which the Start codon occurs in the mRNA
		int iAUGCodon = -1;
		// Initialize the string representing the mRNA strand that comes from transcription
		String mRNA5to3Transcript = "";
		
		// Initialize these variables that indicate whether the DNA string is valid (when all three are true)
		// Whether the string has only the characters A,C,T and G
		boolean hasOnlyACTG = false;
		// Whether the string has DNA for a Start codon
		boolean hasStartCodonDNA = false;
		// Whether the string has DNA for a Stop codon
		boolean hasStopCodonDNA = false;
		
		// Create a scanner to take in input
		Scanner myScanner = new Scanner(System.in);
		
		// Until a valid DNA string has been provided
		// However, this loop condition won't ever be evaluated as false since I set the loop up to break early once a string is determined to 
		// be valid. This loop condition was written just in case
		while (!hasOnlyACTG || !hasStartCodonDNA || !hasStopCodonDNA) {
			
			// Function call to get either an inputted DNA string or a randomly generated one
			dNA3to5Inputted = takeDNAInput(myScanner);
			
			// Function call to check that it is only A,C,T,G
			hasOnlyACTG = checkOnlyACTG(dNA3to5Inputted);
			
			// If it has any other characters, continue to the next iteration (get a new input)
			if (!hasOnlyACTG) continue;
			
			// Otherwise, keep going
			
			// Print the DNA strand, labelled
			System.out.println("DNA Template Sequence:\n3' " + dNA3to5Inputted + " 5'");
			
			
			// Print heading #2 
			System.out.println("\n2) TRANSCRIPTION:\n");
			
			
			// Function call to run the transcription process on the DNA strand, to get an mRNA transcript
			mRNA5to3Transcript = transcribe(dNA3to5Inputted);
			
			// Function call to find the index of the start codon in the mRNA transcript
			iAUGCodon = findStartCodon(mRNA5to3Transcript);
			
			// If this is still the initialized value of -1, then it never got updated, meaning a Start codon was never reached
			if (iAUGCodon == -1) {
				
				// Print an error message
				System.out.println("The inputted DNA strand yielded no START codon (AUG).");
				// This should already be set to false, but set it to false again in case
				hasStartCodonDNA = false;
				// Continue onto the next iteration to get a new DNA strand
				continue;
				
			} else { // Otherwise, Start codon DNA was found
				
				// So set this to true
				hasStartCodonDNA = true;
				
			}
			
			// This shouldn't be needed since a continue statement was already written in the if statement above, but this is written too in case
			if (!hasStartCodonDNA) continue;
			
			// Function call to queue all codons into the codon queue
			myCodonQueue = queueCodons(mRNA5to3Transcript, iAUGCodon);
			// That function call would make the codon queue null if no Stop codon DNA was reached
			hasStopCodonDNA = (myCodonQueue != null);
			
			// If not Stop codon DNA was reached, continue to the next iteration to get a new DNA strand
			if (!hasStopCodonDNA) continue;
			
		}
		
		// Now we the valid DNA strand, mRNA transcript, and codons queued ready for translation
		
		// Close the scanner object to prevent any data leak. We no longer need to take in input
		myScanner.close();
		
		// The newest value of iCodon is the index at which the Stop codon occurs in the mRNA, since queueCodons() upd
		int iStopCodon = iCodon;
		// Print spaces until the position of the Stop codon
		for (int i = iAUGCodon; i < iStopCodon - 1; i++) {
			System.out.print(' ');
		}
		// Print label for Stop codon
		System.out.println("^STOP");
		
		// Print heading #3
		System.out.println("\n3) TRANSLATION:\n");
		
		// Function call to illustrate translation process and get amino acid sequence
		AminoAcidSequence ourAASeq = translate(myCodonQueue, iAUGCodon, iStopCodon, mRNA5to3Transcript);
		
		// Print heading #4
		System.out.println("\n4) FINAL AMINO ACID SEQUENCE:");
		
		// Function call to display the amino acid sequence in terms of one-letter codes, three-letter codes, and full names, each
		displayAASequence(ourAASeq);

	}
	
	/**
	 * Method used in main function to get user input to get a DNA strand string
	 * @param scannerToUse Scanner object used to allow user input
	 * @return The string representing a DNA strand
	 */
	public static String takeDNAInput(Scanner scannerToUse) {
		
		// Print message clearly explaining what a valid DNA strand will look like for this program
		System.out.println("\nA valid DNA template sequence for this process will contain the following in order:");
		System.out.println("1) Any sequence of characters with only A,C,T and/or G, that does not contain the subsequence 'TAC'.");
		System.out.println("2) 'TAC'.");
		System.out.println("3) Any sequence of triples of characters with only A,C,T and/or G, as long as none of these triples is 'ATT', 'ATC', or 'ACT'.");
		System.out.println("4) Either 'ATT', 'ATC', or 'ACT'.");
		System.out.println("5) Any sequence of characters with only A,C,T and/or G.");
		System.out.println("For example: CGAAATACGAAAACGTCATCTTAAC");
		System.out.println("             ^    ^  ^        ^  ^");
		
		// Prompt user to enter a valid DNA sequence or 'R' if they want randomly generated valid DNA sequence
		System.out.println("\nEnter a valid DNA template sequence (3' to 5') or 'R' to randomly generate one: ");
		
		// Take in input and convert all to upper case, in case any characters were lower case
		String dNA3to5 = scannerToUse.nextLine().toUpperCase();
		
		// If the user entered 'R', generate a random valid DNA string
		if (dNA3to5.equals("R")) {
			
			// Create a DNA generator object for randomly generating a valid DNA string
			RandomDNAStrandGenerator randDNAGen = new RandomDNAStrandGenerator();
			// Get the default length settings of the generator, to include in the following print statement to inform the user
			int[] defaultLengths = randDNAGen.getLengthBounds();
			
			// Explain the default settings of the generator to the user
			System.out.printf("By default, the randomly generated sequence will have %d to %d bases before the Start codon DNA, %n"
					+ "%d to %d triples of DNA bases between the Start codon DNA and Stop codon DNA, and %d to %d bases after the Stop "
					+ "codon DNA.%n", defaultLengths[0], defaultLengths[1], defaultLengths[2], defaultLengths[3], defaultLengths[4], 
					defaultLengths[5]);
			// Prompt the user to choose to use default or custom settings
			System.out.printf("Enter 'D' to use these default length boundary settings for the random DNA template sequence generator. %n"
					+ "Enter anything else if you would like to select custom length settings:%n");
			String defaultOrCustom = scannerToUse.nextLine().toUpperCase();
			
			// If the user chose to use custom settings
			if (!defaultOrCustom.equals("D")) {
				
				// Initialize a variable for the number of bases before the Start codon DNA, as a string that will be converted to an integer
				String numPreStartBasesStr = "";
				// Declare a variable that will be the integer from converting the above string
				int numPreStartBases;
				// This loop will break not via the loop condition by via a break statement at the end of the try block
				while (true) {
					try {
						// Prompt the user to enter the number of bases desired before the Start codon DNA
						System.out.println("How many bases do you want before the Start codon of the resulting mRNA? e.g. 5, 20, etc.");
						numPreStartBasesStr = scannerToUse.nextLine().toUpperCase();
						// Convert the input from string to integer
						numPreStartBases = Integer.parseInt(numPreStartBasesStr);
						// If this did not throw a NumberFormatException, break from the loop
						break;
					} catch (NumberFormatException nfe) { // If it did throw a NumberFormatException
						// print an error message and continue to the next iteration to reprompt
						System.out.println("Invalid input. " + numPreStartBasesStr + " is not an integer.");
					}
				}
				
				// // Initialize a variable for the number of codons desired to be between the Start and Stop codons, as a string that will be converted to an integer
				String numCodonsDNAStr = "";
				// Declare a variable that will be the integer from converting the above string
				int numCodonsDNA;
				// This loop will break not via the loop condition by via a break statement at the end of the try block
				while (true) {
					try {
						// Prompt the user to enter the number of codons desired to be between the Start and Stop codons
						System.out.println("How many codons do you want between the Start codon and the Stop codon of the resulting mRNA? e.g. 5, 20, etc.");
						numCodonsDNAStr = scannerToUse.nextLine().toUpperCase();
						// Convert the input from string to integer
						numCodonsDNA = Integer.parseInt(numCodonsDNAStr);
						// If this did not throw a NumberFormatException, break from the loop
						break;
					} catch (NumberFormatException nfe) { // If it did throw a NumberFormatException
						// print an error message and continue to the next iteration to reprompt
						System.out.println("Invalid input. " + numCodonsDNAStr + " is not an integer.");
					}
				}
				
				// Initialize a variable for the number of bases after the Stop codon DNA, as a string that will be converted to an integer
				String numPostStopBasesStr = "";
				// Declare a variable that will be the integer from converting the above string
				int numPostStopBases;
				// This loop will break not via the loop condition by via a break statement at the end of the try block
				while (true) {
					try {
						// Prompt the user to enter the number of bases desired after the Stop codon DNA
						System.out.println("How many bases do you want after the Stop codon of the resulting mRNA? e.g. 5, 20, etc.");
						numPostStopBasesStr = scannerToUse.nextLine().toUpperCase();
						// Convert the input from string to integer
						numPostStopBases = Integer.parseInt(numPostStopBasesStr);
						// If this did not throw a NumberFormatException, break from the loop
						break;
					} catch (NumberFormatException nfe) { // If it did throw a NumberFormatException
						// print an error message and continue to the next iteration to reprompt
						System.out.println("Invalid input. " + numPostStopBasesStr + " is not an integer.");
					}
				}
				
				// Change the random DNA generator to one that uses these custom settings
				randDNAGen = new RandomDNAStrandGenerator(numPreStartBases, numCodonsDNA, numPostStopBases);
				
			}
			
			// Get a random DNA string using the random DNA generator
			dNA3to5 = randDNAGen.getStrandDNA();
		}
		
		// Return the DNA strand string
		return dNA3to5;
		
	}
	
	/**
	 * Method used in main function to check whether the given DNA strand string has only the bases that can be used in DNA (A,C,T, and G).
	 * In the main function, it is called inside a while loop. If this returns false, another iteration occurs
	 * @param dNA3to5 String representing the DNA which will be checked to have only A,C,T and G
	 * @return true if the DNA strand has only A,C,T and/or G. false if it has any other bases
	 */
	public static boolean checkOnlyACTG(String dNA3to5) {
		
		// Declare a variable that will hold a base character
		char base;
		// Initialize the boolean that will ultimately be returned by this function
		boolean hasOnlyACTG = false;
		
		// For each character in the inputted DNA string
		for (int i = 0; i < dNA3to5.length(); i++) {
			
			// Get the base character at index i
			base = dNA3to5.charAt(i);
			// If it is an invalid character
			if (base != 'A' && base != 'C' && base != 'T' && base != 'G') {
				
				// print an error message 
				System.out.println("Invalid character(s) used: " + dNA3to5);
				// set the boolean to false
				hasOnlyACTG = false;
				// Break from the loop
				break;
				
			}
			else { // If it is a valid character
				
				// set the boolean as true
				hasOnlyACTG = true;
				
			}
			
		}
		
		// Return the boolean
		return hasOnlyACTG;
		
	}
	
	/**
	 * Method used in main function to illustrate creation of mRNA from the given DNA strand, and return a string representing the mRNA
	 * @param dNA3to5 String representing the DNA strand to be transcribed
	 * @return A string representing the mRNA transcript of the given DNA strand
	 */
	public static String transcribe(String dNA3to5) {
		
		// Initialize a string that will represent the mRNA strand from transcription
		String mRNA5to3 = "";
		// Declare variable to hold a base in inputted DNA strand
		char dNABase;
		// Declare variable to hold base that will be appended to mRNA strand
		char mRNABaseToAdd;
		
		// For each base in DNA strand
		for (int i = 0; i < dNA3to5.length(); i++) {
			
			// Get the base
			dNABase = dNA3to5.charAt(i);
			// Based on that base, set the new mRNA base as the complementary one
			if (dNABase == 'A') {
				mRNABaseToAdd = 'U';
			} else if (dNABase == 'C') {
				mRNABaseToAdd = 'G';
			} else if (dNABase == 'T') {
				mRNABaseToAdd = 'A';
			} else {
				mRNABaseToAdd = 'C';
			}
			// Append the new mRNA base
			mRNA5to3 = mRNA5to3 + mRNABaseToAdd;
			
			// Print subheading for this iteration in transcription
			System.out.println("Adding RNA Base #" + (i+1) + ':');
			
			// Illustrate DNA strand and complementary mRNA strand that has so far been constructed by this iteration
			System.out.println(DNALabel + "3' " + dNA3to5 + " 5'");
			System.out.println(mRNALabel + "5' " + mRNA5to3 + " 3'");
			// If this is the last iteration, print an extra newline character for spacing in the console
			if (i != dNA3to5.length() - 1) System.out.print('\n');
		}
		
		// Return the fully constructed mRNA string
		return mRNA5to3;
		
	}
	
	/**
	 * Method used in main function to find the index in the mRNA transcript string, where the Start codon occurs
	 * In the main function, it is called inside a while loop. If this returns -1, another iteration occurs
	 * @param mRNA5to3 String representing the mRNA being searched in for a Start codon
	 * @return The index at which the Start codon occurs, or -1 if there is no Start codon
	 */
	public static int findStartCodon(String mRNA5to3) {
		
		// Initialize the index of the Start codon as -1
		int iAUG = -1;
		// For each character in the mRNA, until the loop is broken
		for (int i = 0; i < mRNA5to3.length() - 2; i++) {
			// If this character begins the Start codon
			if (mRNA5to3.charAt(i) == 'A') {
				if (mRNA5to3.charAt(i+1) == 'U') {
					if (mRNA5to3.charAt(i+2) == 'G') {
						// then the current index is the index of the Start codon, so store it and break the loop
						iAUG = i;
						break;
					}
				}
			}
		}
		
		// Return the index of the Start codon (or -1 if no Start codon was found)
		return iAUG;
		
	}
	
	/**
	 * Method used in main function to put the codons that are before the Stop codon, into a queue that will be used for translation
	 * In the main function, it is called inside a while loop. This function will return a null queue if no Stop codon is found, 
	 * in which case another iteration of the while loop will occur
	 * @param mRNA5to3 String representing the mRNA molecule with the codons that will be added to the queue
	 * @param iAUG Index in the mRNA string, at which the Start codon occurs
	 * @return The queue with the codons in it, to be used for translation
	 */
	public static Queue<String> queueCodons(String mRNA5to3, int iAUG) {
		
		// Initialize the queue that will hold the codons
		Queue<String> codonQueue = new Queue();
		// We will start indexing the mRNA at the index of the Start codon
		iCodon = iAUG;
		// Label this spot on the mRNA strand using a ^
		for (int i = 0; i < iAUG - 2 + mRNALabel.length(); i++) {
			System.out.print(' ');
		}
		System.out.print("START^");
		
		// Initialize the codon variable as the first codon (which will be the Start codon, AUG)
		String codon5to3 = mRNA5to3.substring(iCodon, iCodon + 3);
		
		// Initialize a variable to indicate whether there is a Stop codon. As false since we have not yet reached it
		boolean hasStopCodonDNA = false;
		
		// Until a Stop codon is reached
		while (!hasStopCodonDNA) {
			
			// If we have reach the codon but did not yet break from the loop, then there is no Stop codon
			if (iCodon + 2 > mRNA5to3.length() - 1) {
				
				// So print an error message
				System.out.println("The inputted DNA strand yielded no STOP codon (UAA, UAG, or UGA).");
				// Set the boolean as false in case, although it already should be
				hasStopCodonDNA = false;
				// Set the queue as null. This will later be used to indicate that there was no Stop codon
				codonQueue = null;
				// Break from the loop
				break;
				
			} else { // If we are not yet at the last codon
				
				// Set the codon as the triplet starting at the index specified by iCodon (updated at the end of the loop)
				codon5to3 = mRNA5to3.substring(iCodon, iCodon + 3);
				
				// If this is a Stop codon
				if (codon5to3.equals("UAA") || codon5to3.equals("UAG") || codon5to3.equals("UGA")) {
					
					// Change the boolean since we found a Stop codon
					hasStopCodonDNA = true;
					// Break from the loop so we don't want to enqueue any more triplets
					break;
					
				} else { // If we have not reached the last codon or a Stop codon
					
					// Enqueue this codon
					codonQueue.enqueue(codon5to3);
					
				}
				
			}
			
			// Update the codon index variable
			iCodon = iCodon + 3;
			
		}
		
		// Return the queue containing the codons
		return codonQueue;
		
	}
	
	/**
	 * Method used in the main function to illustrate translation and get the amino acid sequence
	 * @param codonQueue A queue containing the codons to be used in translation
	 * @param iAUG The index of the Start codon in the mRNA string, used to position the illustration of a given step in translation
	 * @param iStop The index of the Stop codon in the mRNA string, used to position the illustration of a given step in translation
	 * @param mRNA5to3 The string representing the mRNA, used here when printing all the codons in each step's illustration
	 * @return The amino acid sequence yielded by the translation process
	 */
	public static AminoAcidSequence translate(Queue<String> codonQueue, int iAUG, int iStop, String mRNA5to3) {
		
		// Initialize an object that will be used to get tRNA objects
		TRNAList tRNAList = new TRNAList();
		
		// Declare a variable to hold the anticodon of a tRNA (complementary to a codon)
		String anticodon3to5;
		
		// Declare a variable that will hold a character from a codon
		char charCodon;
		
		// Position of bases in queue (so increases by 3 each iteration)
		int position = 4;
		
		// Get the next codon
		String codon5to3 = codonQueue.dequeue();
		
		// Initialize a list of amino acids
		AminoAcidSequence outputAASeq = new AminoAcidSequence();
		
		// Initialize a variable to count codons, which will be used in subheadings
		int numCurrCodon = 1;
		// Loop through codons
		while (codon5to3 != null) {
			
			// Initialize the codon as an empty string
			anticodon3to5 = "";
			for (int i = 0; i < 3; i++) {
				
				// Get the current base from the codon
				charCodon = codon5to3.charAt(i);
				// Append the complementary base to the anticodon
				if (charCodon == 'A') {
					anticodon3to5 = anticodon3to5 + 'U';
				} else if (charCodon == 'C') {
					anticodon3to5 = anticodon3to5 + 'G';
				} else if (charCodon == 'U') {
					anticodon3to5 = anticodon3to5 + 'A';
				} else {
					anticodon3to5 = anticodon3to5 + 'C';
				} 
				
			}
			// Now we have the anticodon
			
			// Get the tRNA object that has that anticodon
			TRNAMolecule tRNACurr = tRNAList.getMolecule(anticodon3to5);
			// Now we have the tRNA molecule
			
			// Create a node with this tRNA's amino acid (the amino acid that will be added to the growing amino acid chain)
			DoublyLinkedListNode<AminoAcid> nodeToInsert = new DoublyLinkedListNode(tRNACurr.getAminoAcid());
			// Insert this node into the list of amino acids (at the tail)
			outputAASeq.insert(nodeToInsert, outputAASeq.getTail());
			
			// Print subheading for this iteration in translation
			System.out.println("Adding Amino Acid #" + numCurrCodon + ':');
			
			// Illustrate this iteration in translation
			// Don't use the toString() method. Do it manually so we can show the chain growing
			//System.out.println(tRNACurr.toString(position - 1));
			/*
			 * Example:
			 *     tRNA in P
			 *   Met-Ala
			 *       | |
			 *    3' CGC 5'
			 *5' AUG GCG GGU AUG UUC CCC GUC GAU CGA 3'
			 */
			for (int i = 0; i < position - 1; i++) System.out.print(' ');
			System.out.println("tRNA in P site");
			System.out.println("   " + outputAASeq.getStrThreeLetter());
			for (int i = 0; i < position - 1; i++) System.out.print(' ');
			System.out.println("| |");
			for (int i = 0; i < position - 4; i++) System.out.print(' ');
			System.out.println("3' " + anticodon3to5 + " 5'");
			
			//Print mRNA (mRNA5to3) spaced out in codons
			// Label the 5' end
			System.out.print("5'");
			// For all codons starting with Start and before Stop
			for (int i = iAUG; i < iStop; i = i + 3) {
				
				// Each codon is separated by a space
				System.out.print(" ");
				System.out.print(mRNA5to3.substring(i, i+3));
				
			}
			// Label the 3' end
			System.out.println(" 3'");
			
			// Print a label just to explain what some of the components of the illustration are
			for (int i = 0; i < position; i++) System.out.print(' ');
			System.out.println("^Current pair of anticodon(top, in tRNA molecule in P site) and codon(bottom)\n");
			
			// Update the position of bases in queue for the next iteration
			position = position + 4;
			
			// Get the next codon
			codon5to3 = codonQueue.dequeue();
			// Update the counter of codons
			numCurrCodon++;
			
		}
		
		// Return the object of holding the sequence of amino acids
		return outputAASeq;
		
	}
	
	/**
	 * Method used in the main function to display the amino acid sequence in terms of one-letter codes, then in terms of three-letter codes, then in 
	 * terms of full names of amino acids
	 * @param aASeq The amino acid sequence object from the translation
	 */
	public static void displayAASequence(AminoAcidSequence aASeq) {
		
		// Print the one-letter codes of the amino acid sequence
		System.out.println("\nAmino Acid Sequence (1-Letter Codes):");
		System.out.println(aASeq.getStrOneLetter());
		
		// Print the three-letter codes of the amino acid sequence
		System.out.println("\nAmino Acid Sequence (3-Letter Codes):");
		System.out.println(aASeq.getStrThreeLetter());
		
		// Print the full names of the amino acid sequence
		System.out.println("\nAmino Acid Sequence (Full Names):");
		System.out.println(aASeq.getStrFullName());
		
	}

}
