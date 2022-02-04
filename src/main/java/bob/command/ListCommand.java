package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;

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
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        if (tasks.isEmpty()) {
            if (isGreetList) {
                return ui.noTasks();
            } else {
                return ui.free();
            }
        } else {
            StringBuilder reply = new StringBuilder();
            if (!isGreetList) {
                reply.append(ui.preListReply() + "\n");
            }
            for (int i = 1; i <= tasks.size(); i++) {
                reply.append(ui.say(String.format("\t %o . %s\n", i, tasks.getTask(i - 1).printStatus())));
            }
            reply.append(ui.postListFace());
            return reply.toString();
        }
    }
}
