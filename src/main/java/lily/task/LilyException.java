package lily.task;

/**
 * Exceptions thrown by Lily-related functions
 * 
 * Author: Hong Yi En, Ian
 * Last Updated: Jan 2022 (AY21/22 Sem 2)
 */
public class LilyException extends Exception {
    /**
     * Creates a LilyException, which is an exception specific to Lily.
     * 
     * @param msg What the exception is about.
     */
    public LilyException(String msg) {
        super(msg);
    }
}