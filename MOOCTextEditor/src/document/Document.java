package document;

import java.io.ObjectInputStream.GetField;
/** 
 * A class that represents a text document
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	public int sentences = 0;
	public int syllables = 0;
	public int words=0;
	public double fleschScore = 0.0;
	

	/**
	 * Create a new document from the given text. Because this class is abstract,
	 * this is used only from subclasses.
	 * 
	 * @param text The text of the document.
	 */
	protected Document(String text) {
		this.text = text;
	}

	/**
	 * Returns the tokens that match the regex pattern from the document text
	 * string.
	 * 
	 * @param pattern A regular expression string specifying the token pattern
	 *                desired
	 * @return A List of tokens from the document text that match the regex pattern
	 */
	protected List<String> getTokens(String pattern) {
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);

		while (m.find()) {
			tokens.add(m.group());
		}

		return tokens;
	}

	/**
	 * This is a helper function that returns the number of syllables in a word. You
	 * should write this and use it in your BasicDocument class.
	 * 
	 * You will probably NOT need to add a countWords or a countSentences method
	 * here. The reason we put countSyllables here because we'll use it again next
	 * week when we implement the EfficientDocument class.
	 * 
	 * For reasons of efficiency you should not create Matcher or Pattern objects
	 * inside this method. Just use a loop to loop through the characters in the
	 * string and write your own logic for counting syllables.
	 * 
	 * @param word The word to count the syllables in
	 * @return The number of syllables in the given word, according to this rule:
	 *         Each contiguous sequence of one or more vowels is a syllable, with
	 *         the following exception: a lone "e" at the end of a word is not
	 *         considered a syllable unless the word has no other syllables. You
	 *         should consider y a vowel.
	 */
	protected int countSyllables(String word) {
		// TODO: Implement this method so that you can call it from the
		// getNumSyllables method in BasicDocument (module 2) and
		// EfficientDocument (module 3).
		
		//Method was obtained from google, but the code needs refractored
		//to fit Coursera's rules on how their logic works. 

		int numSyllables = 0;

		String upperCaseWord = word.toUpperCase();
		// The loop will run from 1 to the character before the last
		for (int i = 1; i < upperCaseWord.length() - 1; i++) {
			char ch = upperCaseWord.charAt(i);
			char c = (upperCaseWord.charAt(i - 1));
			// Only adds if the char is in the index AND if there is no
			// other letter in the index fore i
			if ("AEIOUY".indexOf(ch) >= 0 && "AEIOUY".indexOf(c) == -1) {
				numSyllables++;
			}

		}
		// Check the first character
		char a = upperCaseWord.charAt(0);
		// Check the last character
		char b = upperCaseWord.charAt(upperCaseWord.length() - 1);

		if(word.length()==1) {
			if(a=='A' || a == 'I') {
				return 1;
			}
		}
		// Not count if 'E' is the last char
		if (b == 'E') {
			numSyllables = numSyllables;
		} else if ("AIOUY".indexOf(b) >= 0) { // Add if the last char is not 'E'
			numSyllables++;
		}
		if(b =='I') {
			if(numSyllables>2) {
				numSyllables--;
			}
		}
		// Add if the first character is in the index
		if ("AEIOUY".indexOf(a) >= 0) {
			numSyllables++;
		}
		// There must be at least one syllable
		if (numSyllables <= 0) {
			numSyllables = 1;
		}
		return numSyllables;
	}

	/**
	 * A method for testing
	 * 
	 * @param doc       The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words     The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed. False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences) {
		System.out.println("Testing text: " + doc.getText());
		System.out.print("....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound + ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound + ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound + ", expected " + sentences);
			passed = false;
		}

		if (passed) {
			System.out.println("passed.\n");
		} else {
			System.out.println("FAILED.\n");
		}
		
		return passed;
	}

	/** Return the number of words in this document */
	public abstract int getNumWords();

	/** Return the number of sentences in this document */
	public abstract int getNumSentences();

	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();

	/** Return the entire text of this document */
	public String getText() {
		return this.text;
	}

	/** return the Flesch readability score of this document */
	/**
	 * how to count the number of words in a string. parse through the string and
	 * count how many spaces are present in the string and add 1 to account for the
	 * first word in the string
	 * 
	 * how to count the number of sentences parse through the string and count how
	 * many "." are found but what about abbreviated words? how to account for
	 * those? example. "inc." how to count for this without counting it as a
	 * sentence?
	 */
	public double getFleschScore() {
		// TODO: You will play with this method in week 1, and
		// then implement it in week 2
		
		String input = getText();
		words = getNumWords();
		sentences = getNumSentences();
		syllables = getNumSyllables();
		fleschScore =0.0;
		
		fleschScore = 206.835 - (1.015 * (words/sentences)) - (84.6 * (syllables/words));
		return fleschScore;
		
		//assignment for week1
//		int lengthOfInput = input.length();
//	    return (double)lengthOfInput;

		// we can refractor this piece of code and make it high level.
//		String input = getText();
//		return (double) input.length();
	}

}
