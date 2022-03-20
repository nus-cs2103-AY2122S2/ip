package pac;

import pac.command.Command;
import pac.parser.Parser;
import pac.storage.Storage;
import pac.task.TaskList;
import pac.ui.Ui;

import java.io.IOException;

public class Pac {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Pac(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.readTasks());
        } catch (IOException e) {
            ui.showLoadingError();
        } catch (PacException e) {
            ui.showPacError(e);
            tasks = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, ui, storage);
        } catch (PacException e) {
            return ui.showPacError(e);
        } catch (IOException e) {
            return ui.showIOError(e);
        } catch (NumberFormatException e) {
            return ui.showFormatError();
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.showFormatError();
        }
    }
}