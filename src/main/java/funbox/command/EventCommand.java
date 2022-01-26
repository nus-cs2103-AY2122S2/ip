package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.util.Parser;
import funbox.task.Event;

public class EventCommand extends Command {
    String description;
    Parser parser;
    public EventCommand(String description) {
        super(false);
        this.description = description;
        this.parser = new Parser();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        if (description.equals("")) {
            throw new FunBoxExceptions("`event` command is missing a field!");
        } else {
            String[] resultArr = this.parser.getDescriptionAndDate(this.description, "event");
            Event event = new Event(resultArr[0],
                    parser.stringToLocalDate(resultArr[1]),
                    parser.getTime(resultArr[1]), "event");
            taskList.add(event);
            ui.printTask(taskList.getSize(), event);
            storage.writeTaskToStorage(event, ui);
        }
    }
}
