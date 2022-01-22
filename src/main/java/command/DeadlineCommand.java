package command;

import storage.Storage;
import task.Deadline;
import task.TaskList;
import ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String message;
    String time;

    public DeadlineCommand(String message, String time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) { //throw exception if necessary
        taskList.add(new Deadline(this.message, false, LocalDate.parse(this.time)));
        storage.writeToFile(taskList);
        ui.outputMessage("Got it. I've added this task: \n" +
                taskList.get(taskList.size() -1) +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
