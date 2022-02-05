public class AddCommand implements Command {
    protected String[] commandAndDetails;

    public AddCommand(String[] commandAndDetails) {
        this.commandAndDetails = commandAndDetails;
    }

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        String command = this.commandAndDetails[0];
        String details = this.commandAndDetails[1];
        Task task = null;
        switch (command) {
            case "todo":
                task = new Todo(details.strip());
                System.out.println("Added a to do task.");
                break;
            case "deadline":
                String[] deadlineAndTime = details.split("/by");
                task = new Deadline(deadlineAndTime[0].strip(), deadlineAndTime[1]);
                System.out.println("Added a deadline.");
                break;
            case "event":
                String[] eventAndTime = details.split("/at");
                task = new Event(eventAndTime[0].strip(), eventAndTime[1]);
                System.out.println("Added an event.");
                break;
        }

        if (task != null) {
            taskList.addTask(task);
            System.out.println("  " + task.toString());
            System.out.println("You have " + taskList.size() + " task(s) in the list.");
            storage.writeToFile(taskList);
        }
        return false;
    }
}
