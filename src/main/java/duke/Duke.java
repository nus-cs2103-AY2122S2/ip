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

    private static final String DEFAULT_PATH = "data/duke.txt";

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
     * Create an instance of Duke with default path.
     */
    public Duke() {
        this(DEFAULT_PATH);
    }

    /**
     * Runs the Duke program in CLI.
     */
    public void run() {
        System.out.println(ui.startMessage());
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readUserInput();
                System.out.println(parser.parseInput(input));
                isExit = parser.commandIsExit();
            } catch (DukeException e) {
                System.out.println(ui.showErrorMessage(e.toString()));
            }
        }
    }

    /**
     * Handles program input and output from GUI.
     *
     * @return string of response from duke.
     */
    public String getResponse(String input) {
        try {
            return parser.parseInput(input);
        } catch (DukeException e) {
            return ui.showErrorMessage(e.toString());
        }
    }

    /**
     * Returns Ui.
     *
     * @return Ui.
     */
    public Ui getUi() {
        return this.ui;
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
