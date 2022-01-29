package duke;

/**
 * Duke a chatbot that allows users to keep track of their daily tasks.
 * Duke takes in a text file for it to store the data of TaskList using a Storage.
 * It uses Ui to interact with the users and Parser to process its inputs.
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Create an instance of Duke
     *
     * @param filePath path of text file to store data
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage);
        this.ui = new Ui(taskList);
        this.parser = new Parser(taskList, ui);

        try {
            taskList.load();
        } catch (DukeException e) {
            ui.showErrorMessage(e.toString());
        }
    }

    /**
     * Runs the Duke program
     */
    public void run() {
        ui.startMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readUserInput();
                parser.parseInput(input);
                isExit = parser.commandIsExit();
            } catch (DukeException e) {
                ui.showErrorMessage(e.toString());
            } 
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

}
