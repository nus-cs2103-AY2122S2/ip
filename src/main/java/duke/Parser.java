package duke;

import java.util.ArrayList;

import duke.exceptions.RequiredInformationMissingException;
import duke.exceptions.UnknownCommandException;

/**
 * Class that has methods for handling user input
 */
public class Parser {
    //constants representing the index in the input string of the start of required information
    //to carry out each command's actions
    private static final int MARK_INFO_INDEX = 5;
    private static final int UNMARK_INFO_INDEX = 7;
    private static final int TODO_INFO_INDEX = 5;
    private static final int EVENT_INFO_INDEX = 5;
    private static final int DEADLINE_INFO_INDEX = 9;
    private static final int DELETE_INFO_INDEX = 7;
    private static final int FIND_INFO_INDEX = 5;

    /**
     * Parses the input typed in by user, returning a nicely formatted arraylist containing the command name as first
     * index, and and relevant arguments from the input as the result.
     *
     * @param input
     * @return List containing the parsedInput strings.
     * @throws RequiredInformationMissingException if required info for a command not present in input.
     * @throws UnknownCommandException             if command is unknown.
     */
    public static ArrayList<String> parseInput(String input) throws RequiredInformationMissingException,
            UnknownCommandException {
        ArrayList<String> parsedInput = new ArrayList<>();
        String command = getCommandFromInput(input);
        parsedInput.add(command);
        if (command.equals("list")) {
            parsedInput.add("list");
        } else if (command.equals("mark")) {
            if (!hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("missing mark index");
            }

            //mark index should not be empty now
            assert !input.substring(MARK_INFO_INDEX).isEmpty();
            parsedInput.add(input.substring(MARK_INFO_INDEX));
        } else if (command.equals("unmark")) {
            if (hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("missing unmark index");
            }
            assert !input.substring(UNMARK_INFO_INDEX).isEmpty();
            parsedInput.add(input.substring(UNMARK_INFO_INDEX));
        } else if (command.equals("todo")) {
            if (!hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("empty todo description");
            }
            assert !input.substring(TODO_INFO_INDEX).isEmpty();
            parsedInput.add(input.substring(TODO_INFO_INDEX));
        } else if (command.equals("event")) {
            if (!hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("empty event description");
            }
            assert !input.substring(EVENT_INFO_INDEX).isEmpty();
            int indexOfStartDate = input.indexOf(" /") + 5;
            parsedInput.add(input.substring(EVENT_INFO_INDEX, input.indexOf(" /")));
            parsedInput.add(input.substring(indexOfStartDate));
        } else if (command.equals("deadline")) {
            if (!hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("empty deadline description");
            }
            assert !input.substring(DEADLINE_INFO_INDEX).isEmpty();
            int indexOfStartDate = input.indexOf(" /") + 5;
            parsedInput.add(input.substring(DEADLINE_INFO_INDEX, input.indexOf(" /")));
            parsedInput.add(input.substring(indexOfStartDate));
        } else if (command.equals("delete")) {
            if (!hasRequiredInformation(command, input)) {
                throw new RequiredInformationMissingException("missing delete index");
            }
            assert !input.substring(DELETE_INFO_INDEX).isEmpty();
            parsedInput.add(input.substring(DELETE_INFO_INDEX));
        } else if (command.equals("find")) {
            parsedInput.add(input.substring(FIND_INFO_INDEX));
        } else if (command.equals("bye") || command.equals("list")) {
            //no additional information for command required
        } else {
            throw new UnknownCommandException("unknown command entered");
        }
        return parsedInput;
    }

    /**
     * Extracts the command from a given input.
     *
     * @param input
     * @return the command
     */
    private static String getCommandFromInput(String input) {
        int indexOfWhitespace = input.indexOf(" ");
        if (indexOfWhitespace == -1) {
            //1 word input
            return input;
        }

        return input.substring(0, indexOfWhitespace);
    }

    /**
     * Checks for a given command, whether the input from user contains information sufficient to
     * carry out the task, if so returns true, if not returns false.
     * @param command command to run
     * @param input input from user
     * @return true if input has sufficient information, false if not
     */
    public static boolean hasRequiredInformation(String command, String input) {
        if (command.equals("mark")) {
            return input.length() > MARK_INFO_INDEX;
        } else if (command.equals("unmark")) {
            return input.length() > UNMARK_INFO_INDEX;
        } else if (command.equals("todo")) {
            return input.length() > TODO_INFO_INDEX;
        } else if (command.equals("event")) {
            if (input.length() <= EVENT_INFO_INDEX) {
                return false;
            }
            //further check that there is a date for this event
            int indexOfSlash = input.indexOf(" /");

            if (indexOfSlash == -1) {
                return false;
            }

            return input.length() > (indexOfSlash + 5);
        } else if (command.equals("deadline")) {
            if (input.length() <= DEADLINE_INFO_INDEX) {
                return false;
            }
            //further check that there is a date for this event
            int indexOfSlash = input.indexOf(" /");

            if (indexOfSlash == -1) {
                return false;
            }

            return input.length() > (indexOfSlash + 5);
        } else if (command.equals("delete")) {
            return input.length() > DELETE_INFO_INDEX;
        }

        //code should not reach here, given that we always pass in a valid command to this method.
        assert "should not reach here" == null;
        return false;
    }
}

