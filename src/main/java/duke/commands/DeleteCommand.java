package duke.commands;

import duke.commands.Command;

import duke.tasks.Task;
import duke.tasks.TaskList;

import duke.storage.Storage;
import duke.exception.DukeException;
import java.io.IOException;
import duke.ui.Ui;

public class DeleteCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    public DeleteCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        execute();
    }

    private void execute() throws DukeException {
        String err = "Oh no! Which task do you wish to delete? Try again :)\n";
        String wrongNumber = "Oh no! This task number does not exist. Try again :)\n";
        String wrongFormat = "Oh no! Please do not spell out the number. Try again :)\n";
        int targetIndex;
        int size = list.getSize();
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String taskNum = echo[1];
        if (taskNum.isEmpty()) {
            throw new DukeException(err);
        }
        try {
            targetIndex = Integer.parseInt(taskNum);
        } catch (Exception e) {
            throw new DukeException(wrongFormat);
        }
        if (targetIndex > size || targetIndex <= 0) {
            throw new DukeException(wrongNumber);
        } else {
            Task curr = list.getTask(targetIndex - 1);
            list.deleteTask(targetIndex - 1);
            Ui.showDeleteResponse(curr.toString(), size -1);
        }
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExit(){
        return false;
    }
}
