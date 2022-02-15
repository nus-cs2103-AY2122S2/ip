package duke.exceptions;

/**
 * The DukeException class will duke to handle
 * the various exceptions encountered while
 * using duke.
 *
 * Will definitely make this class an abstract class
 * and create more specific DukeException classes that
 * extends from this class.
 *
 * @author  Melvin Chan Zijun
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
