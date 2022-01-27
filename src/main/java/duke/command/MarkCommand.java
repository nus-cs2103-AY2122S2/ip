package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Mark Command, in charge of marking a task as done/undone.
 */
public class MarkCommand extends Command {
    public enum Mark {
        MARK,
        UNMARK;
    }

    private int index;
    private Mark mark;

    /**
     * Instantiates a MarkCommand object with index, mark enum, and commandArray.
     *
     * @param index int Index of task to be marked.
     * @param mark Mark Type of marking (mark/unmark).
     * @param commandArray String[] String array from input command.
     */
    public MarkCommand(int index, Mark mark, String[] commandArray) {
        super(commandArray);
        this.index = index;
        this.mark = mark;
    }

    /**
     * Executes the mark/unmark command using TaskList object.
     *
     * @param taskList TaskList input taskList object from Duke.
     * @param ui       Ui input ui object from Duke.
     * @param storage  Storage input storage object from Duke.
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        if (mark.equals(Mark.MARK)) {
            taskList.markTask(index);
            storage.save(taskList);
        } else if (mark.equals(Mark.UNMARK)) {
            taskList.unmarkTask(index);
            storage.save(taskList);
        }
    }

    /**
     * Checks the equality of two Command objects based on the index and mark enum.
     *
     * @param command Object Another command object to be compared to.
     * @return boolean Boolean value to show if this equals command.
     */
    @Override
    public boolean equals(Object command) {
        MarkCommand mark = (MarkCommand) command;
        return this.index == mark.index && this.mark.equals(mark.mark);
    }
}
