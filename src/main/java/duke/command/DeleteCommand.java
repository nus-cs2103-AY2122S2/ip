package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;

public class DeleteCommand extends Command<String>{

    private String text;
    private TaskList list;
    private Storage storage;

    public DeleteCommand(String text, TaskList list, Storage storage) {
        this.text = text;
        this.list = list;
        this.storage = storage;
        runCommand();
    }

    @Override
    public void runCommand() {
        int indexToDelete = intSearch(text) - 1;
        if (indexToDelete < 0 || indexToDelete >= list.getSize()) {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        } else {
            Task deleteTask = list.getTask(indexToDelete);
            list.deleteTask(indexToDelete);
            storage.writeToFile(list);
            System.out.println("   " + "ALRIGHTY. I've removed this task:\n"
                    + "    " + deleteTask + "\n" + "   Now you have " + list.getSize() + " tasks in the list.");
        }
    }

    public int intSearch(String text) {
        String[] splicedString = text.split(" ");
        return Integer.parseInt(splicedString[1]);
    }
}
