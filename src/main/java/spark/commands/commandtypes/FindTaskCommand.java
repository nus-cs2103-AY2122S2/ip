package spark.commands.commandtypes;

import spark.Ui;
import spark.exceptions.SparkException;
import spark.storage.Storage;
import spark.tasks.TaskList;
import spark.tasks.tasktypes.Task;

import java.util.List;

public class FindTaskCommand extends Command {
    private String[] tokens;

    public FindTaskCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            List<Task> matches = tasks.findTask(tokens);

            StringBuilder results = new StringBuilder();
            if (matches.isEmpty()) {
                results.append("I couldn't find anything that matches what you are looking for.");
            } else {
                results.append("Okay, I've found these tasks: ");
                results.append(System.lineSeparator());

                for (Task t : matches) {
                    results.append("    ");
                    results.append(t.toString());
                    results.append(System.lineSeparator());
                }
            }

            ui.printMessage(results.toString());
        } catch (SparkException e) {
            ui.printException(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
