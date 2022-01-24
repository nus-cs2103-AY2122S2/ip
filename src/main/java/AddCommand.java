public class AddCommand extends Command {
    public AddCommand(String command, String[] tokenizedCommand) {
        super(command, tokenizedCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = null;
        String desc = "";
        switch (this.tokenizedCommand[0]) {
        case "todo":
            t = new Todo(this.command.substring(5));
            break;
        case "deadline":
            int indexOfBy = command.indexOf("/by");
            desc = command.substring(9, indexOfBy - 1);
            String by = command.substring(indexOfBy + 4);
            t = new Deadline(desc, by);
            break;
        case "event":
            int indexOfAt = command.indexOf("/at");
            desc = command.substring(6, indexOfAt - 1);
            String at = command.substring(indexOfAt + 4);
            t = new Event(desc, at);
            break;
        }
        tasks.addTask(t);
        storage.updateSavedTasks("", t.getSaveFormat());
        ui.printMsg("Got it. I've added this task:\n  " + t + "\n" + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
