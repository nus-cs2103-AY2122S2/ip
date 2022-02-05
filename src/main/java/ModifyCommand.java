public class ModifyCommand implements Command {

    protected String[] commandAndDetails;

    public ModifyCommand(String[] commandAndDetails) {
        this.commandAndDetails = commandAndDetails;
    }

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String command = this.commandAndDetails[0];
        int taskNumber = Integer.parseInt(this.commandAndDetails[1]);
        switch (command) {
            case "mark":
                this.mark(taskNumber, taskList);
                break;
            case "unmark":
                this.unmark(taskNumber, taskList);
                break;
            case "delete":
                this.delete(taskNumber, taskList);
                break;
        }
        storage.writeToFile(taskList);
        return false;
    }

    public void mark(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.markAsDone();
        System.out.println("Good job! This task is done:");
        System.out.println("  " + task);
    }

    public void unmark(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        task.unmarkAsDone();
        System.out.println("Hurry up and get it done!");
        System.out.println("  " + task);
    }

    public void delete(int taskNumber, TaskList taskList) {
        Task task = taskList.getTask(taskNumber - 1);
        taskList.removeTask(taskNumber - 1);
        System.out.println("This task has been removed:");
        System.out.println("  " +  task);
        System.out.println("Now you have " + taskList.size() + " task(s).");
    }
}
