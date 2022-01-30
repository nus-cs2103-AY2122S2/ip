package duke.dukeexceptions;

/**
 * Duke exceptions that catch DateTime related errors
 */
public class DukeDateExceptions extends DukeException {

    /**
     * Constructs the DukeDateExceptions exception
     * @param msg
     */
    public DukeDateExceptions(String msg) {
        super(msg);
    }

    /**
     * Prints a error msg
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Please provide a valid date time format");
        System.out.println(
                "____________________________________________________________"
        );
    }
}
