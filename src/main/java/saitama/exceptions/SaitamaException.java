package saitama.exceptions;

/**
 * Exceptions that are thrown by Saitama chatbot.
 */
public class SaitamaException extends Exception {
    public SaitamaException(String message) {
        super("huh?!\n" + message);
    }
}
