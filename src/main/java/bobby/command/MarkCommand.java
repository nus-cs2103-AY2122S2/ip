package bobby.command;

import bobby.exception.BobbyException;
import bobby.exception.MarkException;
import bobby.Storage;
import bobby.task.Task;
import bobby.task.TaskList;
import bobby.Ui;

public class MarkCommand extends Command {
    private String fullCommand;
    private String[] fullCommandArr;

    public MarkCommand(String fullCommand, String[] fullCommandArr) {
        this.fullCommand = fullCommand;
        this.fullCommandArr = fullCommandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        ui.printLongLine();
        if (fullCommand.substring(4).isBlank()) {                             // no argument
            throw new MarkException("empty");
        } else if (Character.isLetter(fullCommand.charAt(5))) {               // contains letter instead of number
            throw new MarkException("letter");
        } else if (Integer.parseInt(fullCommandArr[1]) > tasks.getSize()) {   // out of bounds
            throw new MarkException("OOB");
        } else if (Integer.parseInt(fullCommandArr[1]) < 1) {
            throw new MarkException("negative");
        }
        Task task = tasks.getIndex(Integer.parseInt(fullCommandArr[1]) - 1);
        if (task.isDone()) {
            throw new MarkException("alr_marked");
        }
        task.markDone();
        storage.saveTasks(tasks.getTaskList());
        ui.markMessage(task);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MarkCommand;
    }
}
