package org.xmn.anagrams;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class DictionaryProcessor {

    /**
     * Creates a map of anagrams.The key will be the alphabetized set of letters
     * and each value will contain a set of anagrams from the key.
     * 
     * @param pathDictionary
     *            path to the dictionary.The dictionary is a list of words.
     * @return a Map with <alphabetized String><Set of anagrams> entries.
     */
    public static Map< AlphabetizedString, Set< String >> dictionaryToAnagramsMap(
            String pathDictionary ) {
        return dictionaryToAnagramsMap( pathDictionary, 0, Integer.MAX_VALUE );
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
    public static Map< AlphabetizedString, Set< String >> dictionaryToAnagramsMap(
            String pathDictionary, int minLength, int maxLength ) {

        Map< AlphabetizedString, Set< String >> m = new HashMap< AlphabetizedString, Set< String >>();
        try {
            Scanner s = new Scanner( new File( pathDictionary ) );
            while ( s.hasNext() ) {
                String word = s.next();
                if ( word.length() >= minLength && word.length() <= maxLength ) {
                    AlphabetizedString alpha = new AlphabetizedString( word );
                    Set< String > l = m.get( alpha );
                    if ( l == null ) {
                        m.put( alpha, l = new HashSet< String >() );
                    }
                    l.add( word );
                }
            }
            s.close();
        } catch ( IOException e ) {
            System.err.println( "Error reading your Dictionary" );
            System.err.println( e );
            System.exit( 1 );
        }
        return m;
    }

}
