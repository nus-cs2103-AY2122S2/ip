package duke;

import java.io.IOException;

public class DeadlineCommand extends Command {
    DeadlineCommand(String commandArgument) {
        super(commandArgument);
    }

    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        String[] deadlineTaskDetails = commandArgument.split("/by");
        String deadlineTask = deadlineTaskDetails[0].trim();
        String deadlineTaskBy = deadlineTaskDetails[1].trim();
        if (deadlineTask.isEmpty()) {
            ui.emptyDeadlineDescriptionErrorMessage();
        } else {
            taskList.addTaskToTaskList(new Deadline(deadlineTask, deadlineTaskBy));
            storage.save(taskList);
        }
    }
}
