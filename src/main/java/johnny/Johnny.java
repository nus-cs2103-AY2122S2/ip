package johnny;

import java.io.IOException;
import java.time.DateTimeException;

public class Johnny {
    private Ui ui;
    private Storage store;
    private InputList tasks;

    public Johnny(String filepath) {
        store = new Storage(filepath);

        try {
            tasks = new InputList(store.readTasks());
        } catch (IOException e) {
            e.getMessage();
        }

        ui = new Ui(this.tasks, this.store);
    }

    public void run() {
        try {
            ui.handleUi();
        } catch (InvalidArgumentsException e) {
            System.out.println(e.errorMessage());
        } catch (EmptyDescriptionException e) {
            System.out.println(e.errorMessage());
        } catch (NoDateException e) {
            System.out.println(e.errorMessage());
        } catch (DateTimeException e) {
            System.out.println(e.getMessage() +
                    "--- Invalid date entered. Please enter in format 'yyyy-mm-dd'");
        }
    }

    public static void main(String[] args) throws InvalidArgumentsException, EmptyDescriptionException, NoDateException {
        new Johnny("tasklist.txt").run();
    }


}
