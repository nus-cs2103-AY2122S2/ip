package duke.command;

import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.task.TaskList;

public class HelpCommand extends Command {
    Parser parser;
    String input;
    TaskList tasks;
    Storage storage;

    public HelpCommand(Parser parser, String input, TaskList tasks, Storage storage) {
        this.parser = parser;
        this.input = input;
        this.tasks = tasks;
        this.storage = storage;
    }

    public String execute() throws DukeException {
        String response = "Here is a list of command!\n\"list\" lists all current tasks\n"
                + "\"find [keyWord]\" lists all tasks with specified keyword\n"
                + "\"mark [taskId]\" marks the specified task as done\n"
                + "\"unmark [taskId]\" unmarks the specified task as done\n"
                + "\"delete [taskId]\" deletes the specified task\n"
                + "\"todo [name]\" creates a todo task with specified name\n"
                + "\"deadline [name] /by[YYYY-MM-DD] [HH:mm]\" creates a "
                + "deadline task with specified name,date,time(optional)\n"
                + "\"event [name] /at[YYYY-MM-DD] [HH:mm]\" creates a event task with specified "
                + "name,date,time(optional)\n";
        return response;
    }
}
