package bobby.command;

import bobby.exception.BobbyException;
import bobby.task.Deadline;
import bobby.exception.DeadlineException;
import bobby.Storage;
import bobby.task.TaskList;
import bobby.Ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DeadlineCommand extends Command {
    private String fullCommand;
    private String[] fullCommandArr;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public DeadlineCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    public boolean isValidDate(String input) {
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
        if (fullCommand.substring(8).isBlank()) {                                     // nothing after command
            throw new DeadlineException("blank");
        } else if (!fullCommand.contains("/")) {                                      // no "/"
            throw new DeadlineException("no_slash");
        } else if (fullCommand.substring(fullCommand.indexOf("/") + 1).isBlank()) {             // nothing after time
            throw new DeadlineException("no_date");
        } else if (!isValidDate(fullCommand.substring(fullCommand.length() - 10))) {            // invalid date
            throw new DeadlineException("invalid_date");
        }
        Deadline newDeadline = new Deadline(fullCommand.substring(fullCommand.indexOf(" ") + 1,
                fullCommand.indexOf("/") - 1),
                fullCommand.substring(fullCommand.length() - 10));
        ui.deadlineMessage(newDeadline);
        tasks.addTask(newDeadline);
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }
}
