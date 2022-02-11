package duke.command;

import duke.Duke;
import duke.DukeDateTime;
import duke.DukeException;
import duke.Parser;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class EditCommand extends Command {

    private final int index;
    private final String input;

    /**
     * Constructs a {@code DeadlineCommand} object.
     * @param index the description of the deadline task
     * @param input a {@code DukeDateTime} object specifying the deadline of the task
     */
    public EditCommand(int index, String input) {
        this.index = index;
        this.input = input;
    }

    /**
     * Adds a deadline task into the list of tasks.
     * @param tasks current list of tasks
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        String[] description = input.split(" ", 2);
        String fullInput = input;
        if (!description[0].matches("(todo)|(deadline)|(event)")) {
            switch (tasks.get(index).getIcon()) {
            case D:
                fullInput = "deadline " + input;
                break;
            case E:
                fullInput = "event " + input;
                break;
            case T:
                fullInput = "todo " + input;
                break;
            default:
                throw new DukeException("Unexpected task type");
            }
        }
        Command c = Parser.parse(fullInput);
        c.execute(tasks);
        tasks.set(index, tasks.get(tasks.size()));
        tasks.remove(tasks.size());
        return "Ok, I've updated the task:\n" + index + " " + tasks.get(index);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
