package duke.main;

import duke.instruction.Instruction;
import duke.storage.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;



/**
 * The main class that creates a Duke -- a task manager, and runs the program.
 */
public class Duke {

    private static final String FILE_PATH = "data/tasks.txt";

    private final Storage storage;
    private final TaskManager taskManager;
    private final Ui ui;

    /**
     * Constructs a Duke -- a personal task manager.
     *
     * @param storage The storage to be used.
     * @param taskManager The task manager to be used.
     * @param ui The ui to be used.
     */
    private Duke(Storage storage, TaskManager taskManager, Ui ui) {
        this.storage = storage;
        this.taskManager = taskManager;
        this.ui = ui;
    }

    /**
     * Constructs a Duke -- a personal task manager.
     * This is the constructor for GUI application, as it does not require a UI
     * anymore.
     *
     * @param storage The storage to be used.
     * @param taskManager The task manager to be used.
     */
    private Duke(Storage storage, TaskManager taskManager) {

        this.storage = storage;
        this.taskManager = taskManager;
        this.ui = null;
    }

    protected static Duke ofGuiApplication() {
        return new Duke(new Storage(FILE_PATH), new TaskManager(new Storage(FILE_PATH)));
    }

    /**
     * The main method to be called.
     * It initializes the task manager and cleans up when the program exits.
     *
     * @param args Not used.
     */
    public static void main(String[] args) {

        Storage storage = new Storage(FILE_PATH);
        TaskManager taskManager = new TaskManager(storage);
        Ui ui = new Ui(System.out, System.in);

        Duke duke = new Duke(storage, taskManager, ui);
        duke.run();

        taskManager.writeBack(storage);

    }

    /**
     * Runs the task manager, by taking user commands and executing them.
     */
    private void run() {

        ui.printWelcomeMessage();

        boolean isExit = false;
        while (!isExit) {
            try {
                Instruction currentInstruction = ui.getNextInstruction(taskManager);
                currentInstruction.act(ui);
                isExit = currentInstruction.isTerminatingInstruction();
            } catch (DukeException e) {
                System.err.println(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.askForInstruction();

                }
            }
        }
    }

    /**
     * Returns the output from duke, given the input from user.
     * This method is designed specifically for GUI, and it is invoked by the GUI to
     * obtain the output and print out to the user.
     *
     * @param input The input from the user.
     * @return The response from duke.
     */
    public String getResponse(String input) {

        try {
            Instruction currentInstruction = Instruction.of(input, taskManager);
            String response = currentInstruction.getOutputMessage();
            taskManager.writeBack(storage);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }


    }
}
