package duke;

/**
 * The user interface.
 * Interacts with user to receive inputs and print outputs.
 */
public class Ui {

    private static final String OUTPUT_ERROR_MSG = "OOPS BMO cannot generate an appropriate output x_x";
    private static final String START_MSG = "BMO is back online ^-^ \nHow can I help you?";

    /**
     * Prints an error message for failure in loading files
     */
    public void showLoadingError() {
        System.out.println("Failed to retrieve data from storage");
    }

    /**
     * Interprets the user input and returns output according to them
     *
     * @param input User input
     * @param tasks Current TaskList in the Duke program
     */
    public String getDukeOutput(String input, TaskList tasks) {
        try {
            Command cmd = Parser.getCommand(input);

            switch(cmd) {
                case LIST:
                    return tasks.list();
                case TODO:
                    return tasks.add(new ToDo(Parser.getDescription(input)));
                case DEADLINE:
                    return tasks.add(new Deadline(Parser.getDescription(input), Parser.getDate(input)));
                case EVENT:
                    return tasks.add(new Event(Parser.getDescription(input), Parser.getDate(input)));
                case MARK:
                    return tasks.mark(Parser.getIndex(input));
                case UNMARK:
                    return tasks.unmark(Parser.getIndex(input));
                case DELETE:
                    return tasks.delete(Parser.getIndex(input));
                case FIND:
                    return tasks.find(Parser.getDescription(input));
                case BYE:
                    return tasks.exit();
            }
        } catch (DukeException e) {
            return e.toString();
        }

        return OUTPUT_ERROR_MSG;
    }

    public String getStartOutput() {
        return START_MSG;
    }
}
