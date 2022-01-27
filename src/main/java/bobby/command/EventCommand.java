package bobby.command;

import bobby.exception.BobbyException;
import bobby.task.Event;
import bobby.exception.EventException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventCommand extends Command {
    private String fullCommand;
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public EventCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public boolean isValidDate(String input) {
        if (input.contains("[a-zA-Z]+") || input.length() != 10) {
            return false;
        }
        try {
            SIMPLE_DATE_FORMAT.setLenient(false);
            SIMPLE_DATE_FORMAT.parse(input);
            return true;
        }
        catch (ParseException e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(5).isBlank()) {                                 // nothing after command
            throw new EventException("blank");
        } else if (!fullCommand.contains("/")) {                                  // no "/"
            throw new EventException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) {         // nothing after time
            throw new EventException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) {        // invalid date
            throw new EventException("invalid_date");
        }
        Event newEvent = new Event(fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
        ui.eventMessage(newEvent);
        tasks.addTask(newEvent);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof EventCommand;
    }
}
