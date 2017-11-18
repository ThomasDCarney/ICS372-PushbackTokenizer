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
 * This is my implementation of the provided PushbackableTokenizer interface. This
 * class takes a string delimited by white space and stores it as tokens ready to be 
 * supplied. The tokens can be retrieved in the order they were provided via the 
 * string. The added functionality is that previously supplied tokens can be moved
 * back to the "supply" side in "last supplied, first pushed back" order. This 
 * allows the user to go back through the string as many tokens/times as they wish.
 * 
 * @author Thomas Carney
 * @version 1.0
 * @since 09/11/2015
 */

import java.util.Stack;
import java.util.StringTokenizer;

public class PushbackTokenizer implements PushbackableTokenizer {

    /* Invariant of PushbackTokenizer class:
     * 
     * This class gets a string at instantiation and immediately "tokenized" by a
     * StringTokenizer and two stacks. Those tokens are then shuffled between 
     * the stacks depending on the desired action. Tokens are forever retained, 
     * never forgotten!
     * 
     * 1. The last token read must be at the top of the supplied stack. If no 
     *    tokens have been read, or all tokens pushed back then the supplied
     *    stack will be empty. 
     * 
     * 2. The next token to read must be at the top of the "supply" stack.
     */
    
    private StringTokenizer tokenString;    // Breaks down the string.
    private Stack<String> supplyStack;      // Stores tokens to be read.
    private Stack<String> suppliedStack;    // Stores tokens already read.
    
    
    /** PushbackTokenizer(String data)
     * This will create a new PushbackTokenizer based on the provided
     * String. This object is immutable, if a new string is to be used then
     * a new PushbackTokenizer must be created.
     * 
     * @precondition - The provided tokens must be separated by whitespace which
     *                 is used to delimit the string.
     * 
     * @postcondition - The provided string will be broken down and stored as tokens.
     * 
     * @param inputString - The string to tokenize.
     */
    public PushbackTokenizer(String data) {
        
        tokenString = new StringTokenizer(data);
        
        /* Since tokens are read in order, they are also pushed onto the stack
         * in order which means reverse start condition. We'll correct that by 
         * storing them on the supplied side temporarily ( correct order for 
         * supplied ) and then moving to supply so the invariant is maintained. 
         */
        
        suppliedStack = new Stack<String>();
        while(tokenString.hasMoreTokens()) {
            
            suppliedStack.push(tokenString.nextToken());
            
        }
        
        supplyStack = new Stack<String>();
        while(!suppliedStack.isEmpty()) {
            
            supplyStack.push(suppliedStack.pop());
            
        }
        
    }// end PushbackTokenizer constructor
    
    
    /** int tokensSupplied()
     * This method will return how many tokens are marked as being supplied.
     * 
     * @return - The number of tokens already supplied.
     */
    public int tokensSupplied() {
        
        return suppliedStack.size();
        
    }// end tokensSupplied
    
    
    /** int tokensRemaining()
     * This method will return how many tokens waiting to be supplied.
     * 
     * @return - The number of tokens left to supply.
     */
    public int tokensRemaining() {
        
        return supplyStack.size();
        
    }// end tokensRemaining
    
    
    /** String nextToken()
     * This method will return the next token to be supplied. If all tokens
     * have been supplied then a notice is sent instead.
     * 
     * @postcondition - A token will have been removed and returned from the
     *                  supply stack.
     * 
     * @return - The next token if any or a notice if none left.
     */
    @Override
    public String nextToken() {
        
        if(!supplyStack.isEmpty()) {
            
            suppliedStack.push(supplyStack.pop());
            
            return suppliedStack.peek();
            
        } else {
            
            return "All tokens sent!";
            
        }
    
    }// end nextToken
    
    
    /** boolean hasMoreTokens()
     * This method will state whether more tokens are left to read from the 
     * supply side.
     * 
     * @return - A boolean true if tokens remain, false if all tokens are on
     *           marked as supplied.
     */
    @Override
    public boolean hasMoreTokens() {
        
        return !supplyStack.isEmpty();
        
    }// end hasMoreTokens

    
    /** void pushback()
     * This method will return previously supplied tokens to the supply side. 
     * This can be done for all previously supplied tokens but they will be
     * "restocked" in order of "most recently supplied" first.
     * 
     * @postcondition - The most recently supplied token will be pushed back 
     *                  to the supply side. If no tokens to pushback, a notice
     *                  is printed to the terminal.
     */
    @Override
    public void pushback() {

        if(!suppliedStack.isEmpty()) {
            
            supplyStack.push(suppliedStack.pop());
            
        } else {
            
            System.out.println("All tokens pushed back!");
        }
        
    }// end pushback
    
    
    /** String toString()
     * This method will return a string representation of the PushbackTokenizer.
     * 
     * @return - A string representing the PushbackTokenizer object.
     */
    @Override
    public String toString() {
        
        return tokensSupplied() + " tokens supplied, " + tokensRemaining() + " left." ;
        
    }// end toString
    
    
}// end PushbackTokenizer class