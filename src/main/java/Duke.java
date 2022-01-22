import command.*;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Duke {
    private final Ui ui;
    private final Storage storage;
    private final TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
    }

    public static void main(String[] args) {
        Duke dk = new Duke();
        Ui ui = dk.ui;
        Storage storage = dk.storage;
        TaskList taskList = dk.taskList;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ui.sayHello();

        String input = ui.getCommand();
        while (!input.equals("bye")) {
            Command c = Parser.parse(input);
            c.execute(ui, storage, taskList);
            input = ui.getCommand();
        }

        ui.exit();

    }
}
