package org.xmn.anagrams;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;


public class StringPermutatorTest {

    @Test
    public void testStringPermutatorNotNull() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        assertNotNull( sp.dictionaryMap );

    }

    @Test
    public void testGetPermutedAnagramsString() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );

        sp.dictionaryMap.put( new AlphabetizedString( "a" ),
                              new HashSet< String >( Arrays.asList( "a" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "acr" ),
                              new HashSet< String >( Arrays.asList( "car", "arc", "rac" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "acirt" ),
                              new HashSet< String >( Arrays.asList( "artic", "cratic", "critar" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "fgor" ),
                              new HashSet< String >( Arrays.asList( "grof", "forg", "frog" ) ) );

        assertEquals( new HashSet< Set< String >>( sp.getPermutedAnagrams( "artic" ) ),
                      new HashSet< Set< String >>( sp.getPermutedAnagrams( "artic", 0 ) ) );
    }

    @Test
    public void testGetPermutedAnagramsFGOR() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );

        sp.dictionaryMap.put( new AlphabetizedString( "a" ),
                              new HashSet< String >( Arrays.asList( "a" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "acr" ),
                              new HashSet< String >( Arrays.asList( "car", "arc", "rac" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "acirt" ),
                              new HashSet< String >( Arrays.asList( "artic", "cratic", "critar" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "fgor" ),
                              new HashSet< String >( Arrays.asList( "grof", "forg", "frog" ) ) );

        // TODO assertTrue( sp.getPermutedAnagrams( "fgor" ). );
    }

    @Test
    public void testGetPermutedAnagramsStringInt() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.getPermutedAnagrams( "aca", 2 );
        assertEquals( new HashSet< String >( Arrays.asList( "aac", "aa", "ac" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsACA() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( "aca" );
        assertEquals( new HashSet< String >( Arrays.asList( "ca", "c", "a", "ac", "aa", "aca" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsCAA() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( "caa" );
        assertEquals( new HashSet< String >( Arrays.asList( "ca", "c", "a", "aa", "caa" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsACAAlphabetized() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( StringAlphabetizer.alphabetize( "aca" ) );
        assertEquals( new HashSet< String >( Arrays.asList( "aa", "a", "ac", "c", "aac" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGeneratePermutationsACAAlphabetizedAndCapital() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.generatePermutations( StringAlphabetizer.alphabetize( "ACA" ) );
        assertEquals( new HashSet< String >( Arrays.asList( "AA", "A", "AC", "C", "AAC" ) ),
                      sp.permutationSet );
    }

    @Test
    public void testGetSubPermutations() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >();
        sp.getSubPermutations( "ACA" );
        assertEquals( new HashSet< String >( Arrays.asList( "AA", "A", "AC", "C", "CA" ) ),
                      sp.permutationSet );
    }

    @Test
    public void getIntersectionValuesTest() {
        StringPermutator sp = new StringPermutator(
                                                    new HashMap< AlphabetizedString, Set< String >>() );
        sp.permutationSet = new HashSet< String >( Arrays.asList( "acr", "ac", "ar", "cr" ) );
        sp.dictionaryMap.put( new AlphabetizedString( "acr" ),
                              new HashSet< String >( Arrays.asList( "car", "arc", "rac" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "artic" ),
                              new HashSet< String >( Arrays.asList( "artic", "cratic", "critar" ) ) );
        sp.dictionaryMap.put( new AlphabetizedString( "frog" ),
                              new HashSet< String >( Arrays.asList( "grof", "forg", "frog" ) ) );

        Map< String, Set< String >> expected = new HashMap< String, Set< String >>();
        expected.put( "acr", new HashSet< String >( Arrays.asList( "car", "arc", "rac" ) ) );
        assertEquals( expected.values().size(), sp.getIntersectionValues().size() );
        assertEquals( sp.getIntersectionValues().size(), 1 );
        assertEquals( expected.values().iterator().next(), sp.getIntersectionValues().iterator()
                                                             .next() );

    }
}
