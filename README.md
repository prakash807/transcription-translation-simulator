# transcription-translation-simulator
Amino acid synthesis (DNA transcription and translation) simulator that takes in a string representing a DNA strand and illustrates the transcription and translation of it.

When I first learned about transcription and translation in school, I believed it would be a suitable process to create a simulation of. As I gained more programming knowledge, I started to better see how that could be done. For example, when I learned about queues, it reminded me of like the codons are translated in ordered so they could be put into a queue. Recently, it felt about time to finally create this project.

This program was written with two main purposes in mind:
1) To help students understand transcription and translation. The main steps of these processes are illustrated when this program is run, 
   for as many different examples as the number of times the program is run. Note: some background knowledge is required to BEST understand 
   the simulation (e.g. what DNA and RNA are)
2) To be able to quickly determine the amino acid sequence produced by transcription and translation of a given DNA strand

When this program is run, the user is prompted as follows, until valid inputs are given:
-Enter a DNA strand unless you want one to be randomly generated
 -For a randomly generated one, indicate whether you desired default of custom settings regarding how long the different parts of the DNA strand are
Then, the program illustrates the transcription process (mRNA being created complementary to the DNA) followed by
the translation process (an amino acid chain being put together), ending with the sequence of the resulting amino acid chain shown in three ways 
(using their one-letter codes, their three-letter codes, and their full names)

Notes:
- There is room for improvements and new features in this program.
  - e.g. This program is currently only designed to show synthesis of one amino acid chain from one DNA strand.
  - e.g. The use of a queue may not have been necessary since currently all enqueuing happens before all dequeuing, but in a simulation 
    in which translation starts before all transcription is done, there may be more advantages to using a queue

References:

Rye, C., Wise, R., Jurukovski, V., DeSaix, J., Choi, J., & Avissar, Y. (2016). 
15.1 The Genetic Code. In Biology. essay, OpenStax. 
Retrieved from https://openstax.org/books/biology/pages/15-1-the-genetic-code.

Wikipedia. (n.d.). Amino Acids. Wikimedia. Retrieved from 
https://upload.wikimedia.org/wikipedia/commons/a/a9/Amino_Acids.svg.
