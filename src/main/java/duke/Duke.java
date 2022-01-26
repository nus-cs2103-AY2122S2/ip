package duke;

import duke.command.Parser;
import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.TaskList;

public class Duke {
    private static TaskList taskList;

    public static void main(String[] args) {
        new Duke().init().run();
    }

    private Duke() {

    }

    private Duke init() {
        try {
            taskList = Storage.load();
            taskList.registerListener(store -> {
                try {
                    Storage.save(store);
                } catch (DukeIoException ex) {
                    System.out.println("Warning: An error occurred while saving Task list");
                }
            });
        } catch (DukeIoException ex) {
            System.out.println("Encountered an error during initialization:\n"
                    + "\t " + ex.getMessage() + " \n"
                    + "Please check that you have read / write permissions in the current folder.\n"
                    + "If the saved data file is corrupted, consider deleting the data folder.\n"
                    + "Will not save any changes!");
        }
        return this;
    }

    private void run() {
        Ui ui = Ui.getInstance().greet();

        boolean isRunning = true;
        while (isRunning) {
            String command = ui.readInput();
            isRunning = ui.printCommand((linePrinter) -> {
                try {
                    return Parser.parse(command).execute(linePrinter, taskList);
                } catch (DukeException ex) {
                    ui.printError(linePrinter, ex);
                }
                return true;
            });
            System.out.println();
        }
    }
}
