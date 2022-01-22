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

        String[] splitted = input.split(" ", 2);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                Command c = new ListCommand();
                c.execute(ui, storage, taskList);
            } else if (splitted[0].equals("delete")) {
                int serialNumber = Integer.parseInt(splitted[1]);
                Command c = new DeleteCommand(serialNumber);
                c.execute(ui, storage, taskList);
            } else if (splitted[0].equals("todo")) {
                if (input.equals("todo") || input.equals("todo ")) {
                    Ui.outputError("Please describe your todo :-(");
                } else {
                    Command c = new TodoCommand(splitted[1]);
                    c.execute(ui, storage, taskList);
                }
            } else if (splitted[0].equals("deadline")) {
                String[] time = splitted[1].split("/by ", 2);
                Command c = new DeadlineCommand(time[0], time[1]);
                c.execute(ui, storage, taskList);
            } else if (splitted[0].equals("event")) {
                String[] time = splitted[1].split("/at ");
                Command c = new EventCommand(time[0], time[1]);
                c.execute(ui, storage, taskList);
            } else if (splitted[0].equals("mark")) {
                int serialNumber = Integer.parseInt(splitted[1]);
                Command c = new MarkCommand(serialNumber);
                c.execute(ui, storage, taskList);
            } else if (splitted[0].equals("unmark")) {
                int serialNumber = Integer.parseInt(splitted[1]);
                Command c = new UnmarkCommand(serialNumber);
                c.execute(ui, storage, taskList);
            } else {
                // throw new DukeException("I'm sorry, but I don't know what that means :-(");
                Ui.outputError(" I'm sorry, but I don't know what that means :-(");
            }
            input = ui.getCommand();
            splitted = input.split(" ", 2);
        }

        ui.exit();

    }
}
