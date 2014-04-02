package org.xmn.anagrams;


public class AlphabetizedString {

    private final String internalString;

    public AlphabetizedString( String aNormalString ) {
        this.internalString = StringAlphabetizer.alphabetize( aNormalString );
    }

    @Override
    public boolean equals( Object obj ) {
        return internalString.equals( obj );
    }

    @Override
    public int hashCode() {
        return internalString.hashCode();
    }
}
