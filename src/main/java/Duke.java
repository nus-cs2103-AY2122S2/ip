import java.io.IOException;
import java.time.format.DateTimeParseException;

public class Duke {
    private enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARKED, ADDED, DELETE, NUMLEFT
    }

    protected Storage storage;
    protected TaskList tasks;
    protected Ui ui;

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("hi");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                ui.showError("You have no task with that number.\n");
            } catch (DateTimeParseException e){
                ui.showError("Your date and times have not been formatted properly.\n");
            } finally {
                ui.showLine();
            }
        }
    }
}
