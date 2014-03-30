package org.xmn.anagrams;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class StringPermutator {

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
    public static Collection< Set< String >> getPermutedAnagrams(
            String input, Map< String, Set< String >> m ) {
        return getPermutedAnagrams( input, m, 0 );
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
    public static Collection< Set< String >> getPermutedAnagrams(
            String input, Map< String, Set< String >> m, int minLength ) {
        Set< String > permSet = getPermutations( StringAlphabetizer.alphabetize( input ), minLength );
        Map< String, Set< String >> returnmap = new HashMap< String, Set< String >>( m );
        returnmap.keySet().retainAll( permSet );
        return returnmap.values();

    }

    private static Set< String > getPermutations( String inputString, int minLength ) {
        Set< String > returnSet = new HashSet< String >();

        returnSet.add( inputString );
        if ( inputString.length() > minLength ) {
            StringBuffer stringBuffer = new StringBuffer( inputString );
            for ( int charPosToRemove = 0; ( charPosToRemove < stringBuffer.length() - 1 ); charPosToRemove++ ) {
                // \r
                returnSet.addAll( getPermutations( new StringBuffer( stringBuffer ).deleteCharAt( charPosToRemove )
                                                                                   .toString(),
                                                   minLength ) );
            }
        }
        return returnSet;
    }

}
