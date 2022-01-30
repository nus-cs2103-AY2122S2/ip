package main.java.duke.main;

import main.java.duke.instruction.Instruction;
import main.java.duke.storage.Storage;
import main.java.duke.task.TaskManager;
import main.java.duke.ui.Ui;

public class Duke {

    private final Storage storage;
    private final TaskManager taskManager;
    private final Ui ui;

    private static final String END_MESSAGE = "Bye!";
    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Constructs a main.Duke -- a personal task manager.
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

    public static void main(String[] args) {

        Storage storage = new Storage(FILE_PATH);
        TaskManager taskManager = new TaskManager(storage);
        Ui ui = new Ui(System.out, System.in);

        Duke duke = new Duke(storage, taskManager, ui);
        duke.run();

        taskManager.writeBack(storage);

    }

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
                ui.askForInstruction();
            }
        }
    }
}
