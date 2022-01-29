package duke;

import duke.task.TaskList;
import duke.command.Command;

/**
 * @author Pun Hui Min
 */

/**
 * duke.Duke is the main class that
 * It houses a run duke.command to call on other classes such as
 */
public class Duke {

    //    protected ArrayList<task.Task> tasks;
    private Storage storage;
    private String filename;
    private Ui ui;
    private TaskList tasks;

    static final String NO_DESC = "Oops! \\(@.@)/ You have not keyed in a description for the duke.task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";
    static final String BYE_RESPONSE = "Bye~ Hope to see you again soon!\n៚ ⋯⋯⋯ |\\__( o)> ⋯⋯⋯⋯ ༄";
    static final String INVALID_DATE = "Oops, please put a valid time format!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this duke.command";


    /**
     * Processes the string inputted by the user. Filters the duke.command and calls on other functions to print a
     * string.
     *
     * @throws DukeException when the specified ID number is not in the list, if the time is not provided accurately,
     *                       or if there was no description or duke.command provided.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // show the divider line ("_______")
                ui.showLine();
                Command c = new Parser(fullCommand).parse();
                c.execute(tasks, ui, storage);
                storage.saveFile(tasks.formatTasks());
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public Duke(String filepath) {
        filename = filepath;
        storage = new Storage(filepath);
        ui = new Ui();
        try {
            tasks = storage.readFile(filepath);
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Takes in the user input and creates a scanner.
     *
     * @param args Takes in the user input from the CLI
     */
    public static void main(String[] args) {
        Duke ducky = new Duke("duke.txt");
        ducky.run();
    }
}

