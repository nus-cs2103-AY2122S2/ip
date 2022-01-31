package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.util.Parser;
import funbox.task.Event;

/**
 * Deal with handling command for Event.
 */
public class EventCommand extends Command {
    String description;
    Parser parser;

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
        String result = "";
        if (description.equals("")) {
            throw new FunBoxExceptions("`event` command is missing a field!");
        } else {
            String[] resultArr = this.parser.getDescriptionAndDate(this.description, "event");
            Event event = new Event(resultArr[0],
                    parser.stringToLocalDate(resultArr[1]),
                    parser.getTime(resultArr[1]), "event");
            taskList.add(event);
            result = "Got it. I've added this task:" + "\n" + ui.printTask(taskList.getSize(), event);
            storage.writeTaskToStorage(event, ui);
        }
        return result;
    }
}
