public class MarkCommand extends Command<String>{

    private String text;
    private TaskList list;
    private Storage storage;

    public MarkCommand(String text, TaskList list, Storage storage) {
        this.text = text;
        this.list = list;
        this.storage = storage;
        runCommand();
    }

    @Override
    public void runCommand() {
        int taskNumber = intSearch(text) - 1;
        if (taskNumber < list.getSize() && taskNumber >= 0) {
            Task intendedTask = list.getTask(taskNumber);
            intendedTask.setDone(true);
            storage.writeToFile(list);
            System.out.println("  " + "SOLID! I've marked this task as done:\n"
                    + "    " + intendedTask);
        } else {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        }
    }

    public int intSearch(String text) {
        String[] splicedString = text.split(" ");
        return Integer.parseInt(splicedString[1]);
    }
}
