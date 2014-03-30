package org.xmn.anagrams;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Class for generation of anagrams. Can be used to solve the Wordchallenge game
 * printing the solution into the keyboard automatically.Execute main method to
 * see an example.
 * <p>
 * 
 * 
 * @author Xabier
 * 
 */
public class AnagramsGenerator {
	/**
	 * runs a program that resolves "WordCallenge" game
	 * 
	 * @param args
	 *            path to the dictionary.Default "3esl.txt" located in the
	 *            classpath will be used.The dictionary is a list of words.
	 */
	public static void main(String[] args) {

		if (args.length == 0) {
			args = new String[1];
			args[0] = "3esl.txt";
		}

		Map<String, Set<String>> mapaAnagramas = dictionaryToAnagramsMap(
				args[0], 3, 6);

		while (true) {
			System.out
					.println("introduce 6 letras y pulsa enter . <exit> para salir");
			String entrada = readInput().trim();
			if (entrada.toUpperCase().equals("EXIT"))
				break;
			if (entrada.length() == 6) {
				System.out.println("ha introducido: " + entrada);
				Collection<Set<String>> returnListOfSets = getPermutedAnagrams(
						entrada, mapaAnagramas, 3);
				System.out.println("switch to wordchallenge's window !!! you have 3 seconds !!!");
				printToKeyBoard(returnListOfSets);
			} else
				System.out.println("la longitud debe ser de 6 caracteres");
		}
	}

	/**
	 * Prints anagrams contained in the collection to the keyboard port solving
	 * the "wordCallenge" game. Waits 3 seconds for the user to switch to the
	 * "wordchallenge" window.
	 * 
	 * @param anagramsCollection
	 */
	public static void printToKeyBoard(
			Collection<Set<String>> anagramsCollection) {
		Robot aRobot = null;
		try {
			aRobot = new Robot();
		} catch (AWTException e) {
			System.err.println("No se pudo crear un Robot para tu plataforma");
			e.printStackTrace();
		}

		aRobot.delay(3000);

		for (Set<String> aSet : anagramsCollection) {
			for (String aString : aSet) {
				String uperCase = aString.toUpperCase();
				for (int i = 0; i < aString.length(); i++) {
					aRobot.keyPress((int) uperCase.charAt(i));
					aRobot.delay(25);
				}
				aRobot.keyPress(KeyEvent.VK_ENTER);
				aRobot.delay(25);

			}
		}

	}

	/**
	 * Creates a map of anagrams.The key will be the alphabetized set of letters
	 * and each value will contain a set of anagrams from the key.
	 * 
	 * @param pathDictionary
	 *            path to the dictionary.The dictionary is a list of words.
	 * @return a Map with <alphabetized String><Set of anagrams> entries.
	 */
	public static Map<String, Set<String>> dictionaryToAnagramsMap(
			String pathDictionary) {
		return dictionaryToAnagramsMap(pathDictionary, 0, Integer.MAX_VALUE);
	}

	/**
	 * Creates a map of anagrams.The key will be the alphabetized set of letters
	 * and each value will contain a set of anagrams from the key.
	 * 
	 * @param pathDictionary
	 *            path to the dictionary.The dictionary is a list of words.
	 * @param minLength
	 *            min length of the words that are going to be mapped.
	 * @param maxLength
	 *            max length of the words that are going to be mapped.
	 * @return a Map with <alphabetized String><Set of anagrams> entries.
	 */
	public static Map<String, Set<String>> dictionaryToAnagramsMap(
			String pathDictionary, int minLength, int maxLength) {

		Map<String, Set<String>> m = new HashMap<String, Set<String>>();
		try {
			Scanner s = new Scanner(new File(pathDictionary));
			while (s.hasNext()) {
				String word = s.next();
				if (word.length() >= minLength && word.length() <= maxLength) {
					String alpha = alphabetize(word);
					Set<String> l = m.get(alpha);
					if (l == null)
						m.put(alpha, l = new HashSet<String>());
					l.add(word);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading your Dictionary");
			System.err.println(e);
			System.exit(1);
		}
		return m;
	}

	public static String alphabetize(String s) {
		char[] a = s.toCharArray();
		Arrays.sort(a);
		return new String(a);
	}

	/**
	 * reads input from user until "enter"
	 * 
	 * @return input string
	 */
	public static String readInput() {
		StringBuffer sb = null;
		sb = new StringBuffer();
		char c;
		try {
			while ((c = (char) System.in.read()) != '\n') {
				sb.append(c);
			}
		} catch (IOException ex) {
		}

		return sb.toString();
	}

	/**
	 * Generates a collection of sets with the permuted anagrams.f.e: from
	 * "aelrst" will generate: "alerts" ,"least","seat","tea","as" etc if the
	 * map of anagrams contains this words and its words length was not limited
	 * during creation.
	 * 
	 * @param input
	 *            from witch generate the permutation.
	 * @param m
	 *            mapped anagrams
	 * @return collection of sets of anagrams
	 */
	public static Collection<Set<String>> getPermutedAnagrams(String input,
			Map<String, Set<String>> m) {
		return getPermutedAnagrams(input, m, 0);
	}

	/**
	 * Generates a collection of sets with the permuted anagrams.f.e: from
	 * "aelrst" with minlength of 4 will generate: "alerts","least","seat" etc
	 * if the map of anagrams contains this words and its words length was not
	 * limited
	 * 
	 * @param input
	 *            from witch generate the permutation.
	 * @param m
	 *            mapped anagrams
	 * @param minLength
	 *            of the output anagrams
	 * @return collection of sets of anagrams
	 */
	public static Collection<Set<String>> getPermutedAnagrams(String input,
			Map<String, Set<String>> m, int minLength) {
		Set<String> permSet = getPermutations(alphabetize(input), minLength);
		Map<String, Set<String>> returnmap = new HashMap<String, Set<String>>(m);
		returnmap.keySet().retainAll(permSet);
		return returnmap.values();

	}

	private static Set<String> getPermutations(String inputString, int minLength) {
		Set<String> returnSet = new HashSet<String>();

		returnSet.add(inputString);
		if (inputString.length() > minLength) {
			StringBuffer stringBuffer = new StringBuffer(inputString);
			for (int charPosToRemove = 0; (charPosToRemove < stringBuffer
					.length() - 1); charPosToRemove++) {
				// \r
				returnSet.addAll(getPermutations(new StringBuffer(stringBuffer)
						.deleteCharAt(charPosToRemove).toString(), minLength));
			}
		}
		return returnSet;
	}
}
