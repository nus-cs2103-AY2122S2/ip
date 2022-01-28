package main.java.ari.command;

/**
 * Marks a Task
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    private static final String MARK_MESSAGE = "Yes Master, I have marked this task as done:\n    %s";

    private int indexToMark;

    public MarkCommand(int index) {
        this.indexToMark = index - 1;
    }

    @Override
    public String execute() {
        if (indexToMark < 0 || indexToMark >= taskList.getSize()) {
            return "Sorry Master, the item you chose is not in the list";
        }

        taskList.markTask(indexToMark);
        return String.format(MARK_MESSAGE, taskList.getTask(indexToMark));
    }

}
