package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class, containing the parsing logic for the bot.
 *
 * @author Jet Tan
 */
public class Parser {
    /**
     * Processes the input.
     *
     * @param input input of the user
     * @throws DukeException InvalidInputException, EmptyDescDescription, UnknownCommandException
     */
    public static void process(String input) throws DukeException, IOException {
        String[] arr = input.split(" ");
        String command = arr[0]; // first word of the user input
        switch (command) {
        case "bye":
            Ui.exit();
            break;
        case "list":
            if (TaskList.getTasks().size() == 0) {
                System.out.println(Ui.getBorder() + "The list is empty. Why not add some tasks?\n" + Ui.getBorder());
            } else {
                Command.listCommand();
            }
            break;
        case "mark": {
            int index = Integer.parseInt(arr[1]);
            Command.markCommand(index);
            break;
        }
        case "unmark": {
            int index = Integer.parseInt(arr[1]);
            Command.unmarkCommand(index);
            break;
        }
        case "todo": {
            Command.todoCommand(input);
            break;
        }
        case "event": {
            Command.eventCommand(input);
            break;
        }
        case "deadline": {
            Command.deadlineCommand(input);
            break;
        }
        case "delete": {
            int index = Integer.parseInt(arr[1]);
            Command.deleteCommand(index);
            break;
        }
        case "find": {
            Command.findCommand(input);
            break;
        }
        default:
            throw new UnknownCommandException(
                    Ui.getBorder() + "I'm sorry, but I don't know what that means.\n" + Ui.getBorder());
        }
    }
}
