public class MarkCommand extends Command {
    private final int taskNum;

    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task taskMarked = taskList.mark(this.taskNum);
            ui.printOutput("Nice! You've completed this task:\n      " + taskMarked);
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " in the list.");
        }

    }

    public boolean isExit() {
        return false;
    }
}
