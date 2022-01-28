package bob.command;

import bob.exception.BobException;

import bob.TaskList;
import bob.Ui;
import bob.Storage;

/**
 * {@inheritDoc}
 */
public class ListCommand extends Command {

    private boolean isGreetList;

    /**
     * Constructor for the ListCommand class
     */
    public ListCommand() {}

    /**
     * Constructor for the ListCommand Class
     * @param isGreetList boolean representing if the list is invoked as a greeting message
     */
    public ListCommand(boolean isGreetList) {
        this.isGreetList = isGreetList;
    }

    /**
     * {@inheritDoc}
     * Lists out the tasks present in the task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        if (tasks.isEmpty()) {
            if (isGreetList) {
                ui.noTasks();
            } else {
                ui.free();
            }
        } else {
            if (!isGreetList) {
                ui.preListReply();
            }
            for (int i = 1; i <= tasks.size(); i++) {
                ui.say(String.format("\t %o . %s", i, tasks.getTask(i - 1).printStatus()));
            }
            ui.postListFace();
        }
    }
}
