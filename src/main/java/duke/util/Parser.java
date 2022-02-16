package duke.util;

import duke.command.*;

public class Parser {

    public static Command parse(String input) throws DukeException {

        // Listing command
        if (input.equals("list")) {
            return new ListCommand();

        // Exit command
        } else if (input.equals("bye")) {
            return new ExitCommand();


        // Other commands
        } else {
            // Getting first word
            String[] words = input.split(" ", 2);
            String category = words[0];


            // Mark,unmark,delete
            if (category.equals("mark") || category.equals("unmark") || category.equals("delete")) {
                int index = Integer.parseInt(words[1]) - 1;
                if (category.equals("mark")) {
                    return new MarkCommand(index);
                } else if (category.equals("unmark")) {
                    return new UnmarkCommand(index);
                } else {
                    return new DeleteCommand(index);
                }


            //Adding tasks
            } else {

                // Throw Exception for missing details
                if (words.length == 1 && category.equals("todo") || category.equals("deadline") || category.equals("event")) {
                    throw new DukeException("Description cannot be empty.");
                }


                // Finding out the types of task
                if (category.equals("todo")) {
                    return new AddCommand("T", words[1], null);
                } else if (category.equals("deadline")) {
                    String[] details = words[1].split(" /by ", 0);
                    return new AddCommand("D", details[0], details[1]);
                } else if (category.equals("event")) {
                    String[] details = words[1].split(" /by ", 0);
                    return new AddCommand("E", details[0], details[1]);


                // Invalid command
                } else {
                    throw new DukeException("Invalid command. Follow proper format.");
                }
            }
        }
    }
}
