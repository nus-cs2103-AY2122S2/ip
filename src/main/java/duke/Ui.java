package duke;

/**
 * Deals with interactions with User and passes accordingly to relevant classes.
 *
 * @author Abdulelah Faisal S Al Ghrairy
 */
public class Ui {

    protected static final String HELP = "Here are the list of commands:\n"
        + "|list [index]|: Show all the tasks currently in the list\n"
        + "|mark [index]|: Mark a task as done\n"
        + "|unmark [index]|: Mark a task as undone\n"
        + "|delete [index]|: Delete a task\n"
        + "|find [keyword]|: Show the list of tasks that match keyword\n"
        + "|todo [description]|: Add a ToDo task. Represents a task without a date/time\n"
        + "|deadline [Description] /by yyyy-mm-dd|: Add a Deadline Task. Represents a task that must be"
        + "\n\tcompleted by certain date\n"
        + "|deadline [Description] /by yyyy-mm-dd/HH:mm|: Add a Deadline task with a time condition.\n"
        + "|event [Description] /at yyyy-mm-dd/HH:mm/HH:mm|: Add an Event task. Represents a task that"
        + "\n\tstarts within a specific time range on a specific day "
        + "\n\t(HH:mm/HH:mm represents Begin/End time)\n";

    private final Parser parser;

    /**
     * Constructor for Ui class. Initializes the Parser object.
     */
    public Ui() {
        parser = new Parser();
    }

    /**
     * Processes the inputs and outputs of the Duke class.
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
}
