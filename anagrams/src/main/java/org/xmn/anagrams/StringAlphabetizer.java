package org.xmn.anagrams;


import java.util.Arrays;


public class StringAlphabetizer {

    public static String alphabetize( String s ) {
        char[] a = s.toCharArray();
        Arrays.sort( a );
        return new String( a );
    }
}
