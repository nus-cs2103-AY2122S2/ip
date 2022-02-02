package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.Ui;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

public class ClearCommand implements Command {
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        try {
            ui.showClearListConfirmationMessage();
            Scanner scanner = new Scanner(System.in);
            String response = scanner.next();
            if (response.equals("y")) {
                tasks.clearTaskList(tasks, storage);
            }
            ui.showClearListMessage(response);
        } catch (IOException err) {
            throw new DukeException("OOPS, Ekud could not locate the file!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
