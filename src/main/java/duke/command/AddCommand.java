package duke.command;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.TaskList;
import duke.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class AddCommand extends Command{

    CommandType commandType;
    String description;
    LocalDate date;
    LocalTime time;

    public AddCommand(CommandType commandType, String description) {
        this.commandType = commandType;
        this.description = description;
    }

    public AddCommand(CommandType commandType, String description, LocalDate date, LocalTime time) {
        this.commandType = commandType;
        this.description = description;
        this.date = date;
        this.time = time;
    }

    public void execute(TaskList taskList, Storage storage) throws IOException, DukeException {
        int taskId = -1;
        switch (commandType) {
        case TODO:
            taskId = taskList.addToDo(description);
            Ui.print(Ui.addTaskMsg((taskList.getTask(taskId)).toString(), taskId + 1));
            break;
        case DEADLINE:
            taskId = taskList.addDeadline(description, date, time);
            Ui.print(Ui.addTaskMsg((taskList.getTask(taskId)).toString(), taskId + 1));
            break;
        case EVENT:
            taskId = taskList.addEvent(description, date, time);
            Ui.print(Ui.addTaskMsg((taskList.getTask(taskId)).toString(), taskId + 1));
            break;
        default:
            break;
        }

        Ui.print(Ui.addTaskMsg(taskList.getTask(taskId).toString(), taskId + 1));
        storage.writeToFile(taskList);
    }
}
