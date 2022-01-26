package duke.managers;

import duke.commands.*;
import duke.exceptions.DukeException;

public class Parser {
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        switch (tokens[0]) {
            case "list":
                return list();
            case "mark":
                return mark(tokens, true);
            case "unmark":
                return mark(tokens, false);
            case "delete":
                return delete(tokens);
            case "bye":
                return exit();
            case "find":
                return find(tokens);
            case "todo":
                return new StoreTodoCommand(tokens);
            case "deadline":
                return new StoreDeadlineCommand(tokens);
            case "event":
                return new StoreEventCommand(tokens);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    protected FindCommand find(String[] tokens) throws DukeException {
        if (tokens.length < 2)
            throw new DukeException("Invalid input! Please specify a description for the tasks to search!");
        String searchString = "";
        for (String token : tokens) {
            if (token.equals("find")) {
                continue;
            }
            else {
                searchString += " " + token;
            }
        }
        return new FindCommand(searchString.trim());
    }
    protected ExitCommand exit() {
        return new ExitCommand();
    }

    protected DeleteCommand delete(String[] tokens) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to delete.");
        }

        return new DeleteCommand(index);
    }

    protected MarkCommand mark(String[] tokens, boolean isMark) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(tokens[1]) - 1;
        } catch (Exception exception) {
            throw new DukeException("Invalid input! Please enter the number of the task you want to mark.");
        }
        return new MarkCommand(isMark, index);
    }

    protected ListCommand list() {
        return new ListCommand();
    }
}
