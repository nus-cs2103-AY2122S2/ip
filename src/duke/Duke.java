package duke;

import java.io.IOException;
import duke.command.Command;

public class Duke {

    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String name;
    private final String frame = "______________________________________________";
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String name, String filePath) {
        this.name = name;
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showMessage(e.getMessage());
            tasks = new TaskList();
        }
    }

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
                if (c.getKeyword() == Command.Keyword.BYE) {
                    updateRecords();
                    break;
                }
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public String greet() {
        return "Hello! I'm " + name + "\nWhat can I do for you?";
    }

    public void updateRecords() throws IOException {
        storage.update(tasks);
    }

    public static void main(String[] args) throws IOException {
        new Duke("Enkel", System.getProperty("user.dir")).run();
    }

}
