package michael;

import javafx.stage.Stage;

/**
 * Main class of Michael
 * Prints the introduction text of Michael
 *
 * @author Justin Ng Jie Ern
 */
public class Michael {
    private static final String FROM_MICHAEL = "From Michael: \n\n";
    private static final String BREAK_LINE = "__________________________________________";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Stage stage;

    private String start = "Hello! I am Michael.\n"
            + "Your Personal Assistant.\n\n"
            + "Input 'help' for the Command Manual!!\n\n"
            + "What can I do for you?\n"
            + BREAK_LINE;

    /**
     * Constructor for Michael.
     */
    public Michael(Stage stage) {
        System.out.println(start);
        this.taskList = new TaskList();
        this.storage = new Storage(taskList);
        this.ui = new Ui(taskList, storage);
        this.stage = stage;
    }

    /**
     * Getter for Stage Object.
     *
     * @return Stage Object.
     */
    public Stage getStage() {
        return this.stage;
    }

    /**
     * Method to get Reply for User input.
     *
     * @param input User input command.
     * @return A reply from Michael for the respective commands.
     */
    public String getResponse(String input) {
        String reply = ui.run(input);
        return FROM_MICHAEL + reply + BREAK_LINE;
    }
}
