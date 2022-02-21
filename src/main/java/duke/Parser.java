package duke;

import duke.task.TaskList;

/**
 * Represents a class that validates and interprets user input with a
 * pre-generated list of allowable input formats.
 * Note: Current allowable formats are "list", "bye", "do X", "undo X",
 * "delete X", "todo S", "deadline S by T", "event S at T", "find W",
 * where X is an integer, S is a string descriptor of the task, W is a
 * singular keyword and T is a string descriptor of the date(s) and/or
 * time(s) associated with the task.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 1.0
 */
public class Parser {

    /**
     * Extracts the non-command contents of a given input string.
     * Strips the string of leading whitespaces and removes the first word.
     *
     * @param input input string
     * @return input consisting of the non-command contents of the input string
     */
    public static String handleInput(String input) {
        input = input.trim();
        if (input.contains(" ")) {
            input = input.substring(input.indexOf(" "));
        }
        return input;
    }

    /**
     * Validates input format and extracts command.
     * Note: Current allowable formats are "list", "bye", "do X", "undo X",
     * "delete X", "todo S", "find W", "deadline S by T", "event S at T",
     * where X is an integer, S is a string descriptor of the task, W is a
     * singular keyword and T is a string descriptor of the date(s) and/or
     * time(s) associated with the task.
     *
     * @param input input string to be parsed
     * @return command from input string if input is of a valid format,
     * "" otherwise
     */
    public static String parse(String input, TaskList tasklist) throws Exception {
        input = input.trim();
        String command = input.replaceAll(" .*", "");

        input = input.trim();
        if (input.equals("bye") || input.equals("list")) {
            return command;
        }

        // Handle do, undo, delete
        String firstWord = input.replaceAll(" .*", "");
        input = input.substring(firstWord.length()).trim();

        switch (firstWord) {
        case "do":
            // Fallthrough
        case "undo":
            // Fallthrough
        case "delete":
            int index = Integer.parseInt(input);
            input = input.replaceAll(".* ", "");
            if (input.matches("[0-9]+") &&
                     index <= tasklist.length() && index >= 1) {
                assert index <= tasklist.length() && index >= 1 : "index not within range";
                return command;
            }
            
            throw new Exception("You need to specify the task you want to "
                    + firstWord + " by its index :c");
        case "todo":
            if (input.equals("")) {
                throw new Exception("Oops, you need to mention what the "
                        + "task is :c");
            }
            return command;
        case "deadline":
            if (!input.contains(" by ")) {
                throw new Exception("Oops, you need to format deadline tasks "
                        + "as \"deadline X by Y\" :c");
            }

            String lastWord = input.substring(input.lastIndexOf(" ") + 1);
            if (lastWord.equals("by")) {
                return "";
            }
            return command;
        case "event":
            if (!input.contains(" at ")) {
                throw new Exception("Oops, you need to format event tasks "
                        + "as \"event X at Y\" :c");
            }

            String finalWord = input.substring(input.lastIndexOf(" ") + 1);
            if (finalWord.equals("at")) {
                return "";
            }
            return command;
        case "find":
            if (input.equals("")) {
                throw new Exception("Oops, you need to mention what "
                        + "the keyword is :c");
            }
            if (input.contains(" ")) {
                throw new Exception("Oops, you can only search for "
                        + "one keyword at a time :c");
            }

            return command;
        default:
            return "";
        }
    }
}
