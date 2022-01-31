package duke;

import java.util.ArrayList;

import duke.exceptions.RequiredInformationMissingException;
import duke.exceptions.UnknownCommandException;

/**
 * Class that has methods for handling user input
 */
public class Parser {

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
            if (input.length() < 6) {
                throw new RequiredInformationMissingException("missing mark index");
            }
            parsedInput.add(input.substring(5));

        } else if (command.equals("unmark")) {
            if (input.length() < 8) {
                throw new RequiredInformationMissingException("missing unmark index");
            }
            parsedInput.add(input.substring(7));
        } else if (command.equals("todo")) {
            if (input.length() < 6) {
                throw new RequiredInformationMissingException("empty todo description");
            }
            parsedInput.add(input.substring(5));
        } else if (command.equals("event")) {
            if (input.length() < 7) {
                throw new RequiredInformationMissingException("empty event description");
            }
            int indexOfStartDate = input.indexOf(" /") + 5;
            parsedInput.add(input.substring(6, input.indexOf(" /")));
            parsedInput.add(input.substring(indexOfStartDate));
        } else if (command.equals("deadline")) {
            if (input.length() < 10) {
                throw new RequiredInformationMissingException("empty deadline description");
            }
            int indexOfStartDate = input.indexOf(" /") + 5;
            parsedInput.add(input.substring(9, input.indexOf(" /")));
            parsedInput.add(input.substring(indexOfStartDate));
        } else if (command.equals("delete")) {
            if (input.length() < 8) {
                throw new RequiredInformationMissingException("missing delete index");
            }
            parsedInput.add(input.substring(7));
        } else if (command.equals("find")) {
            parsedInput.add(input.substring(5));
        } else if (command.equals("bye") || command.equals("list")) {
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
        } else {
            return input.substring(0, indexOfWhitespace);
        }
    }
}

