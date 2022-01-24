public class UnmarkCommand extends Command {
    private final int taskNum;

    public UnmarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task taskUnmarked = taskList.unmark(this.taskNum);
            ui.printOutput("Okay, I've marked this task as undone:\n      " + taskUnmarked);
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " in the list.");
        }
    }

    public boolean isExit() {
        return false;
    }
}