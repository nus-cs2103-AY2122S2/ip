package duke;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Parser for the <code>Duke</code> that includes static methods to interpret user inputs.
 * Also includes a method to parse and read save files.
 */
public class Parser {

    /**
     * Reads the String input and determines the type of command to execute and returns it as an integer
     *
     * @param input User input for the <code>Duke</code> program
     * @return An integer to represent the type of command to execute
     * @throws DukeException When String input doesn't match any type of command
     */
    public static Command getCommand(String input) throws DukeException {
        String str = input.split(" ", 2)[0].toLowerCase();

        switch (str) {
        case "list":
            return Command.LIST;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        case "mark":
            return Command.MARK;
        case "unmark":
            return Command.UNMARK;
        case "delete":
            return Command.DELETE;
        case "find":
            return Command.FIND;
        case "bye":
            return Command.BYE;
        case "hello":
            return Command.HELLO;
        case "note":
            return Command.NOTE;
        case "checknote":
            return Command.CHECKNOTE;
        case "deletenote":
            return Command.DELETENOTE;
        case "help":
            return Command.HELP;
        default:
            throw new DukeException("Hmm BMO doesn't understand what that means...");
        }
        
    }

    /**
     * Reads the String input and returns the description of the Task according to the input line
     *
     * @param input User input for the <code>Duke</code> program
     * @return The description of the <code>Task</code> of the user input
     * @throws DukeException If <code>Task</code> description not specified correctly.
     */
    public static String getDescription(String input) throws DukeException {
        try {
            return input.split(" ", 2)[1].split("/", 2)[0];  
        } catch (Exception e) {
            throw new DukeAbsentInfoException("Description of task not specified, \n"
                        + "eg. todo [description]");
        }
        
    }

    /**
     * Reads the String input and returns the index to apply the command
     *
     * @param input User input for the <code>Duke</code> program.
     * @return The index of the <code>Task</code> to apply the command.
     * @throws DukeException If parsing of the String input fails.
     */
    public static int getIndex(String input) throws DukeException {
        try {
            return Integer.parseInt(input.split(" ", 2)[1].split("/", 2)[0]);  
        } catch (Exception e) {
            throw new DukeException("description parsing problem");
        }
        
    }

    /**
     * Reads the String input and returns the date of the Task according to the input line
     *
     * @param input User input for the <code>Duke</code> program.
     * @return The date of the <code>Task</code> of the user input
     * @throws DukeException If date provided is not in correct format ([task] [description]/yyyy-mm-dd)
     */
    public static LocalDate getDate(String input) throws DukeException {
        try {
            return LocalDate.parse(input.split(" ", 2)[1].split("/", 2)[1]);
        } catch (Exception e) {
            throw new DukeException("Date not provided or not specified in correct format ([task] [description]/yyyy-mm-dd)");
        }
        
    }

    /**
     * Returns a <code>Task</code> that is derived from the input String
     *
     * @param input String inputs from the save file.
     * @return <code>Task</code> corresponding to the given line of input.
     * @throws DukeException If the input line is not is the correct save format.
     */
    public static Task getTask(String input) throws DukeException {
        
        String[] stringArr = input.split("#");

        switch (stringArr[0]) {
        case "T":
            return new ToDo(stringArr[2], stringArr[1].equals("true"));
        case "D":
            return new Deadline(stringArr[2], stringArr[1].equals("true"), LocalDate.parse(stringArr[3]));
        case "E":
            return new Event(stringArr[2], stringArr[1].equals("true"), LocalDate.parse(stringArr[3]));
        default:
            throw new DukeException("Problem retrieving files from data");

        }
        
    }

    public static Note getNote(String input) throws DukeException {

        String[] stringArr = input.split(": ");

        if (stringArr.length < 2) {
            throw new DukeAbsentInfoException("Please provide notes in the following format: " +
                    "note [keyword]: [description]");
        }

        return new Note(stringArr[0], stringArr[1]);
    }
    
}
