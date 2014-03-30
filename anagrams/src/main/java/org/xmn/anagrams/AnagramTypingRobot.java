package org.xmn.anagrams;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Set;


public class AnagramTypingRobot {

    /**
     * Prints anagrams contained in the collection to the keyboard port solving
     * the "wordCallenge" game. Waits 3 seconds for the user to switch to the
     * "wordchallenge" window.
     * 
     * @param anagramsCollection
     */
    public static void printToKeyBoard( Collection< Set< String >> anagramsCollection ) {
        Robot aRobot = null;
        try {
            aRobot = new Robot();
        } catch ( AWTException e ) {
            System.err.println( "No se pudo crear un Robot para tu plataforma" );
            e.printStackTrace();
        }

        aRobot.delay( 3000 );

        for ( Set< String > aSet : anagramsCollection ) {
            for ( String aString : aSet ) {
                String uperCase = aString.toUpperCase();
                for ( int i = 0; i < aString.length(); i++ ) {
                    aRobot.keyPress( uperCase.charAt( i ) );
                    aRobot.delay( 25 );
                }
                aRobot.keyPress( KeyEvent.VK_ENTER );
                aRobot.delay( 25 );

            }
        }

    }
}
