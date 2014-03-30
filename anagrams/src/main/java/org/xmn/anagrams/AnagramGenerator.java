package org.xmn.anagrams;


import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class AnagramGenerator {

    protected Map< String, Set< String >> anagramsMap;

    public AnagramGenerator( String path, int min, int max ) {
        DictionaryProcessor.dictionaryToAnagramsMap( path, min, max );
    }

    public Collection< Set< String >> generate( String input ) {
        return StringPermutator.getPermutedAnagrams( input, anagramsMap, 3 );
    }

}
