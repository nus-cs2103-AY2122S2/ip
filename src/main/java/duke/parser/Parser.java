package duke.parser;

import java.io.IOException;

import duke.command.*;
import duke.dukeexceptions.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.ui.Ui;

public class Parser {

    /**
     * Parse the user's commmand
     * @param input user input
     * @return a respond to user
     * @throws DukeException DukeException
     */
    public static String parseCommand(String input,TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException {
        Command command = null;
        try {
            String[] parts = input.split(" ");
            String userCommand = parts[0];
            Action action = Parser.getAction(userCommand);
            switch (action) {
            case BYE:
                command = new ByeCommand(input);
                break;
            case LIST:
                command = new ListCommand(input);
                break;
            case MARK:
                command = new MarkCommand(input);
                break;
            case UNMARK:
                command = new UnmarkCommand(input);
                break;
            case TODO:
                command = new TodoCommand(input);
                break;
            case DEADLINE:
                command = new DeadlineCommand(input);
                break;
            case EVENT:
                command = new EventCommand(input);
                break;
            case DELETE:
                command = new DeleteCommand(input);
                break;
            case FIND:
                command = new FindCommand(input);
                break;
            default:
                command = new InvalidCommand();
            }
        } catch (IllegalArgumentException | NullPointerException e) {
            command = new InvalidCommand();
        } finally {
            return command.execute(taskList, ui, storage);
        }
    }

    //This method is credited to Janald Ho (link attached from below)
    //https://github.com/janald99/ip/blob/master/src/main/java/duke/parser/Parser.java
    /**
     * Gets Action from input string.
     *
     * @param command input string.
     * @return the Action.
     * @throws IllegalArgumentException exception when not one of enum types.
     * @throws NullPointerException exception when input string is null.
     */
    public static Action getAction(String command) throws IllegalArgumentException, NullPointerException {
        return Action.valueOf(command.toUpperCase());
    }
//    /**
//     * Parse the user's commmand
//     * @param input user input
//     * @return a Command class
//     * @throws DukeException DukeException
//     */
//    public static Command parseUserInput(String input) throws DukeException {
//        String[] parts = input.split(" ");
//        switch (parts[0]) {
//        case "bye":
//            return new ByeCommand(input);
//        case "list":
//            return new ListCommand(input);
//        case "mark":
//            return new MarkCommand(input);
//        case "unmark":
//            return new UnmarkCommand(input);
//        case "todo":
//            return new TodoCommand(input);
//        case "deadline":
//            return new DeadlineCommand(input);
//        case "event":
//            return new EventCommand(input);
//        case "delete":
//            return new DeleteCommand(input);
//        case "find":
//            return new FindCommand(input);
//        default:
//            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
//        }
//    }
}
