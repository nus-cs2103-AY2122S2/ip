package duke;

/**
 * Placeholder Exception
 */
public class DukeExceptions extends Exception{
}

/**
 * Exception to be thrown if descriptor is empty
 */
class EmptyDescriptorExceptions extends Exception{
    public EmptyDescriptorExceptions(){
        super(" OOPS!!! The description of a task cannot be empty.");
    }
}
