package org.xmn.anagrams;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


public class StringPermutatorTest {

    @Test
    public void testStringPermutator() {
        // TODO test
    }

    @Test
    public void testGetPermutedAnagramsString() {
        // TODO test
    }

    @Test
    public void testGetPermutedAnagramsStringInt() {
        StringPermutator sp = new StringPermutator( new HashMap< String, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.getPermutedAnagrams( "aca", 2 );
        assertEquals( new HashSet< String >( Arrays.asList( "aac", "aa", "ac" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsACA() {
        StringPermutator sp = new StringPermutator( new HashMap< String, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( "aca" );
        assertEquals( new HashSet< String >( Arrays.asList( "ca", "c", "a", "ac", "aa", "aca" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsACAAlphabetized() {
        StringPermutator sp = new StringPermutator( new HashMap< String, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( StringAlphabetizer.alphabetize( "aca" ) );
        assertEquals( new HashSet< String >( Arrays.asList( "aa", "a", "ac", "c", "aac" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testPermutate() {
        // TODO test
    }

    protected void getIntersectionValuesTest() {
        // TODO test
    }

}
