/**
 * Class: ICS 372 - Object Oriented Design and Implementation <br>
 * Instructor: Habtamu Bogale <br>
 * Name: Thomas Carney <br>
 * Description: Program #3, This program is to learn/practice the "Adapter" design
 *              pattern via a "Pushback Tokenizer". In this we are told to use the
 *              existing StringTokenizer and Stack classes to implement the provided
 *              PushbackableTokenizer interface. The adapter pattern, in this case, 
 *              the embedding of existing classes in a wrapper class to create a new
 *              type of functionality.<br>
 * Due: 09/25/2015 <br><br>
 * 
 * This is a driver class to put the PushbackTokenizer class through its paces.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/11/2015
 */

public class PushbackTokenizerDriver {
    
    public static void main(String[] args) {
        
        PushbackTokenizer mine = 
                new PushbackTokenizer("This is a short test, it is just a test.");
        
        // Let's see what we are working with
        System.out.println("To start: " + mine + "\n");
        
        // Let's see a few
        System.out.println("Let's see a few...\n");
        
        // Just process a few
        for( int i = 0 ; i < 5 ; i++ ) {
            
            System.out.println(mine.nextToken());
            
        }
        
        System.out.println("\nNow we have: " + mine);
        
        // Push a couple back
        mine.pushback();
        mine.pushback();
        
        System.out.println("\nPush a couple back: " + mine);
        
        System.out.println("\nPrint the rest but should see some repeats...\n");
        
        while(mine.hasMoreTokens()){
            
            System.out.println(mine.nextToken());
            
        }
        
        // And lastly the warnings
        System.out.println("\nThat's it, try printing without checking...\n\n" 
                            + mine.nextToken()); 
        
        
        while(mine.tokensSupplied() != 0) {
            
            mine.pushback();
            
        }
        
        System.out.println("\nCan push back them all: " + mine);
        
        System.out.println("\nBut try it again and...\n");
        
        mine.pushback();

    }// end main
    
}// end PushbackableTokenizerDriver