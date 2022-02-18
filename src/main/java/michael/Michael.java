package michael;

import javafx.stage.Stage;

/**
 * Main class of Michael
 *
 * @author Justin Ng Jie Ern
 */
public class Michael {
    public static final String FROM_MICHAEL = "OMG IT'S HAPPENING! EVERYBODY STAY CALM! \n\n";
    private static final String BREAK_LINE = "__________________________________________";
    /**
     * Introduction Message for Michael.
     */
    public static final String START = "Hello! I am Michael!\n"
                    + "Welcome to Dunder Mifflin.\n\n"
                    + "Input 'help' for the Command Manual!!\n\n"
                    + "What can I do for you?\n"
                    + BREAK_LINE;

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Stage stage;

    /**
     * Constructor for Michael.
     */
    public Michael(Stage stage) {
        System.out.println(START);
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
        return FROM_MICHAEL + reply;
    }
}
