
/**
 * Duke Class contains a scanner to read user input and a TaskList that contains all tasks.
 * Duke only handles the processing of user input and responding to the user. Task list logic is
 * handled by the TaskList class.
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            this.taskList = new TaskList();
        }
    }

    public void run() {
        boolean exit = false;
        ui.displayWelcome();

        while (!exit) {
            try {
                String strCommand = ui.readCommand();
                Command command = Parser.parse(strCommand);
                command.execute(taskList, ui, storage);
                exit = command.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } finally {
                ui.displayLine();
            }
        }

    }
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}