package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.HandleErrorCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.NotKnownCommand;
import duke.commands.UnmarkCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.DukeException;

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
        String firstWord = commandList[0];
        String remainingWord = commandList[1].trim();
        try {
            switch (firstWord) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "deadline":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a deadline cannot be empty. :<");
                }
                String[] deadlineActions = remainingWord.split("/by", 2);
                Deadline deadlineTask = new Deadline(deadlineActions[0].trim(), deadlineActions[1].trim());
                return new AddCommand(deadlineTask);
            case "event":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a event cannot be empty. :<");
                }
                String[] eventActions = remainingWord.split("/at", 2);
                Event eventTask = new Event(eventActions[0].trim(), eventActions[1].trim());
                return new AddCommand(eventTask);
            case "todo":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! The description of a todo cannot be empty. :<");
                }
                ToDo todoTask = new ToDo(remainingWord);
                return new AddCommand(todoTask);
            case "mark":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new MarkCommand(remainingWord);
            case "unmark":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new UnmarkCommand(remainingWord);
            case "delete":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new DeleteCommand(remainingWord);
            case "find":
                if (remainingWord.equals("")) {
                    return new HandleErrorCommand("OOPS!!! Please input a number. :<");
                }
                return new FindCommand(remainingWord);
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
