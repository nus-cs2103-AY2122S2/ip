package bobby.command;

import bobby.exception.BobbyException;
import bobby.exception.DeleteException;
import bobby.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.Ui;

public class DeleteCommand extends Command {
    private String fullCommand;
    private String[] fullCommandArr;

    public DeleteCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommandArr[1].equalsIgnoreCase("all")) {
            if (tasks.isEmpty()) {
                throw new DeleteException("list_empty");
            }
            tasks.removeAll();
            ui.deleteAllMessage();
        } else {
            try {
                if (fullCommand.substring(6).isBlank()) {                             // no argument
                    throw new DeleteException("empty");
                } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) {   // out of bounds
                    throw new DeleteException("OOB");
                } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
                    throw new DeleteException("negative");
                }
            } catch (NumberFormatException e) {
                throw new DeleteException("letter");                  // contains letter(s)
            }
            Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
            ui.deleteMessage(task);
            tasks.removeTask(task);
        }
        storage.saveTasks(tasks.getTaskList());
        ui.printNumTasks(tasks);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DeleteCommand;
    }
}

