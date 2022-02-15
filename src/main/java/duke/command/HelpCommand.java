package duke.command;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.util.TaskList;

/**
 * Represents a command that provides in-App guidance to users.
 */
public class HelpCommand extends Command {
    private static final Map<String, String> commandsMap = Stream.of(new String[][] {
            { "todo", "Adds a task without any date/time attached to it.\nFormat: todo DESC\n" },
            { "deadline", "Adds a task that need to be done before a specific date/time.\n"
                    + "Format: deadline DESC /by YYYY-MM-DD\n" },
            { "event", "Adds a task that start at a specific time and ends at a specific time.\n"
                    + "Format: event DESC /at YYYY-MM-DD\n" },
            { "list", "Shows a list of all tasks.\nFormat: list\n" },
            { "find", "Find tasks whose description matches the given keyword.\nFormat: find KEYWORD\n" },
            { "mark", "Marks the specified task as done.\nFormat: mark INDEX\n" },
            { "unmark", "Unmarks the specified task as done.\nFormat: unmark INDEX\n" },
            { "delete", "Deletes the specified task. Irreversible.\nFormat: delete INDEX\n" },
            { "schedule", "Filters tasks that occur on the specified date.\nFormat: schedule DATE\n" },
            { "bye", "Exits the program.\nFormat: bye\n" },
            { "help", "Shows the list of commands.\nFormat: help [COMMAND]\n" }
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    private String command;

    public HelpCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (command.isBlank()) {
            Map<String, String> linkedMap = new LinkedHashMap<>(commandsMap);
            return linkedMap.entrySet().stream()
                    .map(e -> e.getKey() + ": " + e.getValue())
                    .collect(Collectors.joining("\n"));
        }
        return command + ": " + commandsMap.get(command);
    }

    /**
     * Checks if the specified command exists.
     *
     * @param command The command to check for.
     * @throws DukeException If the command does not exist.
     */
    public static void checkIfCommandExists(String command) throws DukeException {
        if (!commandsMap.containsKey(command)) {
            throw new DukeException(ErrorMessage.MESSAGE_NONEXISTENT_COMMAND);
        }
    }
}
