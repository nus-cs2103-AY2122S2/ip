public class DeadlineCommand extends Command<String>{

    private TaskList list;
    private String text;
    private Storage storage;
    private Ui ui;

    public DeadlineCommand(String text, TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.text = text;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }

    @Override
    public void runCommand() {
        String[] splicedString = text.split(" /by ");
        String splicedDescription = splicedString[0].substring(9);
        String dueDate = splicedString[1];
        Deadline freshDeadline = new Deadline(splicedDescription, dueDate, false);
        list.addTask(freshDeadline);
        storage.writeToFile(list);
        ui.addDeadline(freshDeadline, list);
    }
}


