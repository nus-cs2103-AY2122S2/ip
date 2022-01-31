package duke;

/**
 * Deals with interactions with User and passes accordingly to relevant classes
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Ui {
    /**
     * A divider for design purposes (ONLY FOR TEXT-BASED OPERATIONS)
     */
    private static final String DIVIDER = "\n____________________________________________________________\n";

    private final Parser parser;

    /**
     * Constructor for Ui class. Initializes the Parser object.
     */
    public Ui() {
        parser = new Parser();
    }

    /**
     * Processes the inputs and outputs of the Duke class
     *
     * @param tasks   the list of tasks
     * @param storage the storage instance
     * @param input   the user input
     * @return the chatbot response
     */
    public String userInput(TaskList tasks, Storage storage, String input) {
        if (input.equals("bye")) {
            return "Farewell then!";
        }

        try {
            String message = parser.processMessage(input, tasks, storage);

            if (message == null) {
                return "";
            } else {
                return message;
            }
        } catch (DukeException e) {
            return showLoadingError(e);
        }
    }

    /**
     * Outputs an error message during runtime.
     *
     * @param e The exception that was thrown
     * @return the error message
     */
    public String showLoadingError(Exception e) {
        return e.getMessage();
    }

    /**
     * Returns the message the user will be shown upon starting the program.
     */
    public void showWelcomeMessage() {
        //getMessage("Why hello there! My name is Wensleydale.\nWhat do you need?");
    }
}
