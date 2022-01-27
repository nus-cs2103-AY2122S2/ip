package duke;

/**
 * Parser class which is used to parse user input into a form Duke can understand
 */
public class Parser {
    /**
     * Default constructor
     */
    public Parser() {
    }

    /**
     * Parses the initial input from user and outputs a RESULT
     * RESULT is used in Duke class to handle actions
     *
     * @param input User input
     * @return RESULT of what action to run
     */
    public RESULT parseInput(String input) {
        String[] split = input.split(" ");
        if (input.equals("bye")) {
            return RESULT.BYE;
        } else if (input.equals("list")) {
            return RESULT.LIST;
        } else if (split[0].equals("mark")) {
            return RESULT.MARK;
        } else if (split[0].equals("unmark")) {
            return RESULT.UNMARK;
        } else if (split[0].equals("delete")) {
            return RESULT.DELETE;
        } else if (split[0].equals("todo")) {
            return RESULT.TODO;
        } else if (split[0].equals("deadline")) {
            return RESULT.DEADLINE;
        } else if (split[0].equals("event")) {
            return RESULT.EVENT;
        } else if (split[0].equals("find")) {
            return RESULT.FIND;
        }
        return RESULT.ERROR;
    }

    /**
     * Used to parse user input to figure out what index user is specifying
     * Used in conjunction with mark/unmark/delete
     *
     * @param input User input
     * @return Index of object user is trying to mark/unmark/delete
     * @throws DukeException Throws when user enters invalid number
     */
    public int parseIndex(String input) throws DukeException {
        String[] split = input.split(" ");
        try {
            if (split.length < 2) {
                throw new DukeException("What is this? Missing number!");
            } else {
                return Integer.parseInt(split[1]) - 1;
            }
        } catch (NumberFormatException e) {
            throw new DukeException("That's not a number");
        }
    }

    /**
     * Runs when user tries to create a Todo object
     * Parses the user input and splits it up for the Todo object to read
     *
     * @param input User input
     * @return Todo description
     * @throws DukeException If user enters invalid input, throws an exception
     */
    public String parseTodo(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of event cannot be empty.");
        } else {
            return input.replace("todo ", "");
        }
    }

    /**
     * Runs when user tries to create a Deadline object
     * Parses the user input and splits it up for the Deadline object to read
     *
     * @param input User input
     * @return String array, String[0] contains description and String[0] contains date/time
     * @throws DukeException Throws when user inputs invalid description or date/time
     */
    public String[] parseDeadline(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of deadline cannot be empty.");
        } else {
            String[] newSplit = input.split(" /by ");
            if (newSplit.length > 1) {
                newSplit[0] = newSplit[0].replace("deadline ", "");
                return newSplit;
            } else {
                throw new DukeException("You are missing the date!.");
            }
        }
    }

    /**
     * Runs when user tries to create a Event object
     * Parses the user input and splits it up for the Event object to read
     *
     * @param input User input
     * @return String array, String[0] contains description and String[0] contains date/time
     * @throws DukeException Throws when user inputs invalid description or date/time
     */
    public String[] parseEvent(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The description of deadline cannot be empty.");
        } else {
            String[] newSplit = input.split(" /at ");
            if (newSplit.length > 1) {
                newSplit[0] = newSplit[0].replace("event ", "");
                return newSplit;
            } else {
                throw new DukeException("You are missing the date!.");
            }
        }
    }

    /**
     *
     */
    public String parseFind(String input) throws DukeException {
        String[] split = input.split(" ");
        if (split.length < 2) {
            throw new DukeException("OH NO! The you are missing the keyword!");
        } else {
            return split[1];
        }
    }

    /**
     * Enum which holds all the various user commands
     */
    public enum RESULT {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, ERROR, FIND
    }

}
