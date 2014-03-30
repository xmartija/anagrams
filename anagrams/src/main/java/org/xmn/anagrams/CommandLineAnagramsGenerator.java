package org.xmn.anagrams;


import java.io.IOException;
import java.util.Collection;
import java.util.Set;


/**
 * Command line Executable Class for generation of anagrams. Processes the
 * dictionary and listens to use input. Can be used to solve the Wordchallenge
 * game printing the solution into the keyboard automatically.Execute main
 * method to see an example.
 * <p>
 * 
 * 
 * @author Xabier
 * 
 */
public class CommandLineAnagramsGenerator {

    /**
     * runs a program that resolves "WordCallenge" game
     * 
     * @param args
     *            path to the dictionary.Default "3esl.txt" located in the
     *            classpath will be used.The dictionary is a list of words.
     */
    public static void main( String[] args ) {

        if ( args.length == 0 ) {
            args = new String[1];
            args[0] = "3esl.txt";
        }

        AnagramGenerator anagramGenerator = new AnagramGenerator( args[0], 3, 6 );

        while ( true ) {
            System.out.println( "enter 6 characters and click enter. <exit> to exit" );
            String input = readInput().trim();
            if ( input.toUpperCase().equals( "EXIT" ) ) {
                break;
            }
            if ( input.length() == 6 ) {
                System.out.println( "ha introducido: " + input );
                Collection< Set< String >> returnListOfSets = anagramGenerator.generate( input );
                System.out.println( "switch to wordchallenge's window !!! you have 3 seconds !!!" );
                AnagramTypingRobot.printToKeyBoard( returnListOfSets );
            } else {
                System.out.println( "la longitud debe ser de 6 caracteres" );
            }
        }
    }

    /**
     * reads input from user until "enter"
     * 
     * @return input string
     */
    public static String readInput() {
        StringBuffer sb = null;
        sb = new StringBuffer();
        char c;
        try {
            while ( ( c = (char) System.in.read() ) != '\n' ) {
                sb.append( c );
            }
        } catch ( IOException ex ) {
        }

        return sb.toString();
    }
}
