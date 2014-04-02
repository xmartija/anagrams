package org.xmn.anagrams;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class StringPermutator {

    protected String input;
    protected int min;
    protected final Map< AlphabetizedString, Set< String >> dictionaryMap;
    protected Set< String > permutationSet;

    public StringPermutator( Map< AlphabetizedString, Set< String >> m ) {
        this.dictionaryMap = new HashMap< AlphabetizedString, Set< String >>( m );
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
    public Set< Set< String >> getPermutedAnagrams( String input ) {
        return getPermutedAnagrams( input, 0 );
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
    public Set< Set< String >> getPermutedAnagrams( String input, int min ) {
        this.input = input;
        this.min = min;
        permutationSet = new HashSet< String >();
        generatePermutations( StringAlphabetizer.alphabetize( input ) );
        return getIntersectionValues();
    }

    protected Set< Set< String >> getIntersectionValues() {
        Map< AlphabetizedString, Set< String >> copy = new HashMap< AlphabetizedString, Set< String >>(
                                                                                                        dictionaryMap );
        copy.keySet().retainAll( permutationSet );
        // We can be certain that any word belongs only to one alphabetized key.
        // for the same reason, every set of the value list is unique
        return new HashSet< Set< String >>( copy.values() );
    }

    protected void generatePermutations( String inputString ) {

        permutationSet.add( inputString );
        if ( inputString.length() > min && inputString.length() > 1 ) {
            getSubPermutations( inputString );
        }
    }

    protected void getSubPermutations( String inputString ) {
        StringBuffer stringBuffer = new StringBuffer( inputString );
        for ( int charPosToRemove = 0; ( charPosToRemove < stringBuffer.length() ); charPosToRemove++ ) {
            generatePermutations( new StringBuffer( stringBuffer ).deleteCharAt( charPosToRemove )
                                                                  .toString() );
        }
    }
}
