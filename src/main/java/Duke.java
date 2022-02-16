import batman.parser.Parser;
import batman.storage.Storage;
import batman.tasks.TaskList;
import batman.ui.Ui;

public class Duke {

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    /**
     * Creates a chatbot that assists in
     * keeping track of tasks a person may have.
     *
     * @param filePath Path to indicate where the
     *                 storage of the tasks will be located at.
     */
    public Duke(String filePath) {
        ui = new Ui("Batman");
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
        }
    }

    /**
     * Gets response from application and parse user's
     * input to return a String result.
     *
     * @param input User's input.
     * @return String object of result from user's input.
     */
    public String getResponse(String input) {
        try {
            ui.greeting();
            String result = Parser.parseInput(input).toString();
            if (input.equals("bye")) {
                storage.writeToFile(taskList.getTaskList());
                return ui.exit();
            }
            return result;
        } catch (Exception e) {
            return ui.showLoadingError(e.getMessage());
        }
    }
}
