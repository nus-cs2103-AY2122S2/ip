package duke;
/**
 * Represents a chatbot Duke
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath, String fileDirectory) {
        this.ui = new Ui();
        this.storage = new Storage(filePath, fileDirectory);
        try {
            storage.createFile();
            this.taskList = storage.readData();
        } catch (Exception e) {
            this.taskList = new TaskList();
        }
        this.parser = new Parser();
    }

    public String welcome() {
        return ui.welcomeString();
    }

    public void run() {
        ui.showMessage(ui.welcomeString());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                ui.showMessage(c.execute(taskList, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showMessage(ui.errorString(e));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/tasks.txt", "./data").run();
    }

    protected String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, ui, storage);

        } catch (DukeException e) {
            return ui.errorString(e);
        }
    }

}