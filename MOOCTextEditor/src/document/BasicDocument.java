package document;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * A naive implementation of the Document abstract class. 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	private char space = ' ';
	private char questionMark = '?';
	private char period = '.';
	private char exclamationMark = '!';
	
	//to account for repeating characters. 
	private final String specialCharacters = "([.?!]+)";
	Pattern sentencePattern = Pattern.compile(specialCharacters);
	
	private final String wordCharacters = "[a-zA-Z]*";
	Pattern wordPattern = Pattern.compile(wordCharacters);
	
	private final String numberOnly = "[0-9]";
	Pattern numberPattern = Pattern.compile(numberOnly);
	
	private final String deciamlPattern = "^[1-9][.][1-9][.]";
	Pattern decimal = Pattern.compile(deciamlPattern);
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
	}
	
	
	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		//TODO: Implement this method in week 2 according to the comments above.  
		// See the Module 2 support videos if you need help.
		int numberOfSpace =0;
		String str = new String(getText());
		
		str.trim();
		//accounts for any double spaces. 
		String [] words = str.split(" +");
		for(int i = 0; i<words.length; i++) {
			//need to add in a regex check in order to account for any numbers only in an element. 
			/*
			 * if words[i] is equals a number only with no characters. Do not increment. 
			 */
			String currentWord = words[i];
			Matcher matcher = wordPattern.matcher(currentWord);
			if(matcher.find()) {
				numberOfSpace++;
			}
			Matcher matcherNumber = numberPattern.matcher(currentWord);
			if(matcherNumber.find()) {
				numberOfSpace--;
			}
		}
		//need to replace double space with single space but string is immutable....
//		
//		for(int i=0; i<str.length(); i++) {
//			if(space == str.charAt(i)) {
//				numberOfSpace++;
//			}
//		}
		
	    return numberOfSpace;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
	    //TODO: Implement this method.  See the Module 2 support videos 
        // if you need help.
		
		//use a regex check for each element. If the element contains (. ! ?) increment
		String str = new String(getText());
		int numberOfSentence =0;
		str.trim();
		String [] words = str.split(" ");

		for (int i = 0; i < words.length; i++) {
			String currentWord = words[i];
			Matcher matcher = sentencePattern.matcher(currentWord);
			Matcher decimalMatcher = decimal.matcher(currentWord);
			// how does 7.5. count as two sentences
			if (matcher.find()) {
				numberOfSentence++;
			}
			
			if(decimalMatcher.find()) {
				numberOfSentence++;
			}
			
		}

		Matcher matcher = sentencePattern.matcher(words[words.length-1]);
		if(!matcher.find()) {
			numberOfSentence++;
		} 
		
		
		
		//need to account for repeats.... example ??? should not count as 3 sentences but as 1
		//taken care of in the instance variable declaration. 
		
		//this logic does not work due to the inability of able to account for more then 1 special character.
		//a simple if-else blocks can be written, but that will take up performance and be harder to read.
//		for (int i = 0; i < str.length(); i++) {
//			if ((str.charAt(i) == period) || (str.charAt(i) == questionMark) || (str.charAt(i) == exclamationMark)) {
//				numberOfSentence++;
//			}
//		}
		
        return numberOfSentence;
	}
	
	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To count the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method should process the entire text string each time it is called.  
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		
	    //TODO: Implement this method in week 2.  See the Module 2 support videos 
        // if you need help.  And note that there is no need to use a regular
		// expression for the syllable counting.  We recommend you implement 
		// the helper function countSyllables in Document.java using a loop, 
		// and then call it here on each word.
		
		/*
		 * in order to write this method the following is needed to be understood. 
		 * The "Written method" rule for how to count syllables in a word. 
		 * 
		 * however if we have a file in which contains the number of syllables of a word
		 * I can create an object for that, have it loop through the dictionary. 
		 * hopefully in a binary search to save some performance. Linear search could work but the runtime could take
		 * longer then expected. Once the word is found, a get method for the number of syllables 
		 * could to the trick. 
		 */
		String str = new String(getText());
		str.trim();
		String [] words = str.split(" ");
        return 0;
	}
	
	
	/* The main method for testing this class. 
	 * You are encouraged to add your own tests.  */
	public static void main(String[] args)
	{
		/* Each of the test cases below uses the method testCase.  The first 
		 * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences 
		 * in the string, respectively.  You can use these examples to help clarify 
		 * your understanding of how to count syllables, words, and sentences.
		 */
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				16, 13, 5);
		//testCase(new BasicDocument(""), 0, 0, 0);
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), 15, 11, 4);
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"), 6, 3, 2);
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), 49, 33, 3);
		testCase(new BasicDocument("Segue"), 2, 1, 1);
		testCase(new BasicDocument("Sentence"), 2, 1, 1);
		testCase(new BasicDocument("Sentences?!"), 3, 1, 1);
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
		         32, 15, 1);
	}
	
}
