package chatbot;
/**
* Duke class for the bot.
*/
public class DukeException extends Exception {
    /**
    * Class constructor.
    *
    * @param  errorMessage  a string that describes the exception
    * @see    Exception
    */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    /**
    * Returns the String message of the exception
    * 
    * @return      the String describing the exception
    */
    public String toString() {
        return super.getMessage();
    }
}
