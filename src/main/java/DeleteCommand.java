public class DeleteCommand extends Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int currentTotalTasks = taskList.getTotalTasks();
        if (taskNum <= currentTotalTasks && taskNum > 0) {
            Task taskDeleted = taskList.delete(this.taskNum);
            ui.printOutput("Okay, I've deleted this task:\n  " + ui.showIndent()
                    + taskDeleted + "\n" + ui.showIndent() + taskList.getListStatus());
            storage.saveToHardDisk(taskList);
        } else {
            throw new DukeException("Invalid task number. There are " + currentTotalTasks + " in the list.");
        }
    }

    public boolean isExit() {
        return false;
    }
}
