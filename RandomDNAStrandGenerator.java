
import java.util.Random;

/**
 * An object of this class can be used generate a string representing a DNA strand.
 * This DNA strand will be usable in the transcription and translation simulation.
 * To ensure that, it will include a Start codon, non-Stop codons, and a stop codon
 * The lengths of different parts of the strand can either be default or custom, depending which constructor is used.
 * @author Prakash
 *
 */
public class RandomDNAStrandGenerator {

	/**
	 * String representing the DNA strand
	 */
	private String strandDNA;
	/**
	 * A random number generator used to randomize lengths and bases used
	 */
	private Random randNumGen;
	/**
	 * An index used when randomizing bases used
	 */
	private int iBase;
	/**
	 * A character representing a base used
	 */
	private char base;
	
	/**
	 * A string showing which letters can represent DNA bases. It is used when randomizing bases
	 */
	private static final String possBasesDNA = "ACTG";
	/**
	 * A string representing the Start codon
	 */
	private static final String startCodon = "AUG";
	
	/**
	 * The default minimum number of bases before the Start codon DNA
	 */
	private static final int numPreStartBasesMin = 0;
	/**
	 * The default maximum number of bases before the Start codon DNA
	 */
	private static final int numPreStartBasesMax = 10;
	/**
	 * The default minimum number of codons between the Start and Stop codons
	 */
	private static final int numCodonsDNAMin = 3;
	/**
	 * The default maximum number of codons between the Start and Stop codons
	 */
	private static final int numCodonsDNAMax = 25;
	/**
	 * The default minimum number of bases after the Stop codon DNA
	 */
	private static final int numPostStopBasesMin = 0;
	/**
	 * The default maximum number of bases after the Stop codon DNA
	 */
	private static final int numPostStopBasesMax = 10;
	
	/**
	 * The decided number of bases before the Start codon DNA
	 */
	private int numPreStartBases;
	/**
	 * The decided number of codons between the Start and Stop codons
	 */
	private int numCodonsDNA;
	/**
	 * The decided number of bases after the Stop codon DNA
	 */
	private int numPostStopBases;
	
	/**
	 * Constructor to create a random DNA strand generator using default length settings
	 */
	public RandomDNAStrandGenerator() {
		
		randNumGen= new Random();
		
		numPreStartBases = numPreStartBasesMin + randNumGen.nextInt(numPreStartBasesMax - numPreStartBasesMin + 1);
		numCodonsDNA = numCodonsDNAMin + randNumGen.nextInt(numCodonsDNAMax - numCodonsDNAMin + 1);
		numPostStopBases = numPostStopBasesMin + randNumGen.nextInt(numPostStopBasesMax - numPostStopBasesMin + 1);
		
	}
	
	/**
	 * Constructor to create a random DNA strand generator using custom length settings
	 * @param numPreStartBases The custom number of bases before the Start codon DNA
	 * @param numCodonsDNA The custom number of codons between the Start and Stop codons
	 * @param numPostStopBases The custom number of bases after the Stop codon DNA
	 */
	public RandomDNAStrandGenerator(int numPreStartBases, int numCodonsDNA, int numPostStopBases) {
		
		randNumGen= new Random();
		
		this.numPreStartBases = numPreStartBases;
		this.numCodonsDNA = numCodonsDNA;
		this.numPostStopBases = numPostStopBases;
		
	}
	
	/**
	 * Method to generator a random DNA strand using the generator.
	 * It does not return the strand. It updates the instance variable for the strand, which can be accessed using its accessor method
	 */
	public void generateStrandDNA() {
		
		// Initialize the DNA strand string as an empty string
		strandDNA = "";
		
		// 1) Append 5-10 random bases. non-START!!!
		
		String startCodonDNA = mRNAToDNA(startCodon);
		
		// Append the custom or default number of characters representing bases
		for (int i = 0; i < numPreStartBases; i++) {
			
			// Get a random number to decide which base to append
			iBase = randNumGen.nextInt(4);
			// Use that as an index to get the base
			base = possBasesDNA.charAt(iBase);
			
			// This block is meant to ensure we don't introduce a Start codon yet
			// This will be executed for any and all iterations 3rd onwards, 
			// since that is the point starting at which it is possible to accidentally append a Start codon
			if (i > 1) {
				
				// Get a string from concatenating the previous two characters and the most recent randomly generated base, being considering to be appended
				String potentialStartCodonDNA = "" + strandDNA.charAt(i-2) + strandDNA.charAt(i-1) + base;
				// If this string is the DNA complementary to the Start codon (TAC, complementary to AUG), we generate a different base repeatedly until it is not
				while (potentialStartCodonDNA.equals(startCodonDNA)) {
					// Get a random number to decide which base to append
					iBase = randNumGen.nextInt(4);
					// Use that as an index to get the base
					base = possBasesDNA.charAt(iBase);
					// Get a string from concatenating the previous two characters and the most recent randomly generated base, being considering to be appended
					potentialStartCodonDNA = "" + strandDNA.charAt(i-2) + strandDNA.charAt(i-1) + base;
				}
				
			}
			
			// Append this codon DNA to the DNA strand
			strandDNA = strandDNA + base;
			
		}
		
		// 2) Append start codon DNA
		
		strandDNA = strandDNA + startCodonDNA;
		
		// 3) Append 5-15 triplets of random non-STOP codon DNA 
		
		// Here are strings (in an array) representing the Stop codons
		String[] stopCodons = {"", "", ""};
		stopCodons[0] = "UAA";
		stopCodons[1] = "UAG";
		stopCodons[2] = "UGA";
		
		// Use those to make an array of strings representing the DNA that would be transribed into Stop codons
		String[] stopCodonsDNA = {"", "", ""};
		for (int i = 0; i < 3; i++) {
			
			stopCodonsDNA[i] = mRNAToDNA(stopCodons[i]);
			
		}
		
		// Append a custom or default number of triplets of bases (complementary to non-Stop codons)
		for (int i = 0; i < numCodonsDNA; i++) {
			// Initialize a string representing the codon DNA to be appended, as a stop codon so that the following while loop will be entered
			String codonDNA = stopCodonsDNA[0];
			// STOP codons: UAA, UAG, UGA
			// DNA:         ATT, ATC, ACT
			// This while loop is meant to make sure we don't introduce a STOP codon
			while (codonDNA.equals(stopCodonsDNA[0]) || codonDNA.equals(stopCodonsDNA[1]) || codonDNA.equals(stopCodonsDNA[2])) {
				// Now initialize the codon DNA string as an empty string (to which bases will get appended)
				codonDNA = "";
				// 3 times
				for (int j = 0; j < 3; j++ ) {
					
					// Get a random number to decide which base to append
					iBase = randNumGen.nextInt(4);
					// Use that as an index to get the base
					base = possBasesDNA.charAt(iBase);
					// Append this base to the codon DNA string
					codonDNA = codonDNA + base;
					
				}
				// The loop will repeat if this codon DNA string is currently a Stop codon
			}
			// Append the codon DNA string to the DNA strand
			strandDNA = strandDNA + codonDNA;
		}
		
		// 4) Append STOP codon DNA
		
		// Randomly choose one of the three Stop codons
		int iSTOP = randNumGen.nextInt(3);
		String stopCodonDNA = stopCodonsDNA[iSTOP];
		// Append that Stop codon DNA to the DNA string
		strandDNA = strandDNA + stopCodonDNA;
		
		// 5) Append 0-9 random bases
		
		// Append the custom or default number of characters representing bases
		for (int i = 0; i < numPostStopBases; i++) {
			
			iBase = randNumGen.nextInt(4);
			base = possBasesDNA.charAt(iBase);
			strandDNA = strandDNA + base;
			
		}
		
	}
	
	/**
	 * Method to find the DNA strand complementary the given mRNA strand
	 * @param RNA A string representing the mRNA strand complementary to the DNA strand we are looking for
	 * @return A string representing the DNA strand complementary the given mRNA strand
	 */
	public String mRNAToDNA(String RNA) {
		
		// Initialize the output as an empty string
		String DNA = "";
		
		// Declare a variable that will store a character to add in each iteration
		char charI;
		// For each character in the inputs
		for (int i = 0; i < RNA.length(); i++) {
			
			// Get the current character of the input
			charI = RNA.charAt(i);
			
			// Based on that character, append the complementary DNA character to the output (DNA)
			if (charI == 'A') {
				DNA = DNA + 'T';
			} else if (charI == 'C') {
				DNA = DNA + 'G';
			} else if (charI == 'U') {
				DNA = DNA + 'A';
			} else {
				DNA = DNA + 'C';
			}
			
		}
		
		// Return the output (DNA)
		return DNA;
		
	}
	
	/**
	 * Accessor method for the generated DNA strand
	 * @return String representing the generated DNA strand
	 */
	public String getStrandDNA() {
		
		// Generate a random DNA strand string
		generateStrandDNA();
		// Return it
		return strandDNA;
		
	}
	
	/**
	 * Method to get the default length settings
	 * @return An array of integer representing the default minimum and maximum lengths of different parts of the strand
	 */
	public int[] getLengthBounds() {
		
		int[] output = {numPreStartBasesMin, numPreStartBasesMax, numCodonsDNAMin, numCodonsDNAMax, numPostStopBasesMin, numPostStopBasesMax};
		return output;
	}

}
