package duke.parser;

import duke.commands.*;
import duke.ui.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

/**
 * Deals with handling of the user command.
 */
public class Parser {

    /**
     * Handles the user inputs.
     *
     * @param commandList the list of command includes keyword and description.
     * @return the respective commands.
     */
    public static Command parse(String[] commandList) {
        String first_word = commandList[0];
        String remaining_word = commandList[1].trim();
        try {
            switch (first_word) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "deadline":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a deadline cannot be empty. :<");
                }
                String[] deadlineActions = remaining_word.split("/by", 2);
                Deadline deadlineTask = new Deadline(deadlineActions[0].trim(), deadlineActions[1].trim());
                return new AddCommand(deadlineTask);
            case "event":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a event cannot be empty. :<");
                }
                String[] eventActions = remaining_word.split("/at", 2);
                Event eventTask = new Event(eventActions[0].trim(), eventActions[1].trim());
                return new AddCommand(eventTask);
            case "todo":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a todo cannot be empty. :<");
                }
                ToDo todoTask = new ToDo(remaining_word);
                return new AddCommand(todoTask);
            case "mark":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new MarkCommand(remaining_word);
            case "unmark":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new UnmarkCommand(remaining_word);
            case "delete":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new DeleteCommand(remaining_word);
            case "find":
                if (remaining_word.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new FindCommand(remaining_word);
            default:
                return new NotKnownCommand();
            }
        } catch (DukeException error) {
            return new HandleErrorCommand(error.getMessage());
        } catch (ArrayIndexOutOfBoundsException error) {
            return new HandleErrorCommand("Error! Wrong inputs");
        }
    }

}
