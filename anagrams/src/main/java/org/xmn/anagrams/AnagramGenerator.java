package org.xmn.anagrams;


import java.util.Map;
import java.util.Set;


public class AnagramGenerator {

    protected Map< AlphabetizedString, Set< String >> anagramsMap;

    public AnagramGenerator( String path, int min, int max ) {
        anagramsMap = DictionaryProcessor.dictionaryToAnagramsMap( path, min, max );
    }

    public Set< Set< String >> generate( String input ) {
        return new StringPermutator( anagramsMap ).getPermutedAnagrams( input, 3 );
    }
}
