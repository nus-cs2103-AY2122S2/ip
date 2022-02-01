package duke;

/**
 * Represents a class that validates and interprets user input with a pre-generated list of allowable
 * input formats.
 * Note: Current allowable formats are "list", "bye", "do X", "undo X", "delete X", "todo S",
 * "deadline S by T", "event S at T", "find W", where X is an integer, S is a string descriptor of the task, W
 * is a singular keyword and T is a string descriptor of the date(s) and/or time(s) associated with the task.
 *
 * @author  Elumalai Oviya Dharshini
 * @version 0.1
 */
public class Parser {

    /**
     * Extracts the non-command contents of a given input string.
     * Strips the string of leading whitespaces and removes the first word.
     *
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
     * Validates format of input and extracts command from the given input, if any.
     * Note: Current allowable formats are "list", "bye", "do X", "undo X", "delete X", "todo S", "find W",
     * "deadline S by T", "event S at T", where X is an integer, S is a string descriptor of the task, W is a
     * singular keyword and T is a string descriptor of the date(s) and/or time(s) associated with the task.
     *
     * @return command from input string if input is of a valid format, "" otherwise
     */
    public static String parse(String input) {
        input = input.trim();
        String command = input.replaceAll(" .*", "");

        input = input.trim();
        if (input.equals("bye") || input.equals("list")) {
            return command;
        }

        // Handle do, undo, delete
        String firstWord = input.replaceAll(" .*", "");
        if (firstWord.equals("do") || firstWord.equals("undo") || firstWord.equals("delete")) {
            input = input.replaceAll(".* ", "");
            if (input.matches("[0-9]+")) {
                return command;
            }
            System.out.println("You need to specify the task you want to "+ firstWord + " by its index :c");
            return "";
        }

        // Handle todo
        if (firstWord.equals("todo")) {
            input = input.substring(4).trim();
            if (input.equals("")) {
                System.out.println("Oops, you need to mention what the task is :c");
                return "";
            }
            return command;
        }

        // Handle deadline
        if (firstWord.equals("deadline")) {
            input = input.substring(8).trim();
            if (!input.contains(" by ")) {
                System.out.println("Oops, you need to format deadline tasks as \"deadline X by Y\" :c");
                return "";
            }
            String lastWord = input.substring(input.lastIndexOf(" ") + 1);
            if (lastWord.equals("by")) {
                return "";
            }
            return command;
        }

        // Handle event
        if (firstWord.equals("event")) {
            input = input.substring(5).trim();
            if (!input.contains(" at ")) {
                System.out.println("Oops, you need to format event tasks as \"event X at Y\" :c");
                return "";
            }
            String lastWord = input.substring(input.lastIndexOf(" ")+1);
            if (lastWord.equals("at")) {
                return "";
            }
            return command;
        }

        // Handle find
        if (firstWord.equals("find")) {
            input = input.substring(4).trim();
            if (input.equals("")) {
                System.out.println("Oops, you need to mention what the keyword is :c");
                return "";
            }
            if (input.contains(" ")) {
                System.out.println("Oops, you can only search for one keyword at a time :c");
                return "";
            }
            return command;
        }

        return "";
    }
}
