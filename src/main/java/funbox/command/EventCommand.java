package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.util.Parser;
import funbox.task.Event;

import java.time.LocalDate;

/**
 * Deal with handling command for Event.
 */
public class EventCommand extends Command {
    private final String description;
    private final Parser parser;

    /**
     * Constructor class for EventCommand.
     *
     * @param description The description of the task.
     */
    public EventCommand(String description) {
        super(false);
        this.description = description;
        this.parser = new Parser();
    }

    /**
     * Execute the command which add Event task.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions If description == ""
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        String result;
        assert ui != null : "ui should not be null";
        assert storage != null : "ui should not be null";

        if (description.equals("")) {
            throw new FunBoxExceptions("`event` command usage: event <task> /at <date> <time>");
        }

        String[] resultArr = parser.getDescAndDate(description, "event");

        if (resultArr.length < 2) {
            throw new FunBoxExceptions("`event` command usage: event <task> /at <date> <time>");
        }

        LocalDate date = parser.stringToLocalDate(resultArr[1]);
        String time = parser.getTime(resultArr[1]);

        Event event = new Event(resultArr[0], date, time,"event");
        taskList.add(event);

        result = ui.taskPrefix().concat(ui.printTask(taskList.getSize(), event));
        storage.writeTaskToStorage(event);

        return result;
    }
}