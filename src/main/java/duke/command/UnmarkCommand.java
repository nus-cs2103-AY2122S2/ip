package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

public class UnmarkCommand extends Command<String>{

    private String text;
    private TaskList list;
    private Storage storage;

    public UnmarkCommand(String text, TaskList list, Storage storage) {
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
            intendedTask.setDone(false);
            storage.writeToFile(list);
            System.out.println("  " + "AIYO! I've marked this task as not done yet:\n"
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
