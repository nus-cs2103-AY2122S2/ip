package command;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import task.Todo;
import ui.Ui;

import java.time.LocalDate;

public class TodoCommand extends Command {
    String message;

    public TodoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) { //throw exception if necessary
        taskList.add(new Todo(this.message, false));
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() -1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
