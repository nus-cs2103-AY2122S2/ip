package duke;

import java.io.IOException;
import duke.command.Command;

public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private String name;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a {@code Duke} object with its name and path for storage.
     * @param name Duke's name
     * @param filePath path for storage
     */
    public Duke(String name, String filePath) {
        this.name = name;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (Exception e) {
            ui.showMessage(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs a Duke.
     * @throws IOException if an I/O error occurred
     */
    public void run() throws IOException {
        ui.showLine();
        ui.showMessage(greet());
        ui.showLine();
        while (true) {
            try {
                String input = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
            } catch (ExitException e) {
                storage.update(tasks);
                break;
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Returns the greetings from Duke.
     * @return greetings from Duke.
     */
    public String greet() {
        return "Hello! I'm " + name + "\nWhat can I do for you?";
    }

    /**
     * Asks the storage to update the records with the current list of tasks.
     * @throws IOException if an I/O error occurred
     */
    public void updateRecords() throws IOException {
        storage.update(tasks);
    }

    /**
     * The main method.
     * @param args not used
     * @throws IOException if an I/O error occurred
     */
    public static void main(String[] args) throws IOException {
        new Duke("Enkel", System.getProperty("user.dir") + "../../../").run();
    }

}
