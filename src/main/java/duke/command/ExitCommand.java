package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class ExitCommand extends Command {
    static final String BYE_RESPONSE = "Bye~ Hope to see you again soon!\n✧･ﾟ: *✧･ﾟ:*(*❦ω❦)*:･ﾟ✧*:･ﾟ✧";

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Your tasks have been saved in " + storage.getFileName());
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            String message = currentTask.getTask();
            output = output + (i + 1 + ". " + message) + "\n" ;
        }
        System.out.println(BYE_RESPONSE);
        setExit();
    }
}
