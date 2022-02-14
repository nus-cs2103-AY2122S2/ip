package tsohg;

/**
 * The main class of the program.
 */
public class Tsohg {

    private Ui ui;

    /**
     * Constructor of the class.
     * @param filePath The filepath of the stored data.
     */
    Tsohg(String filePath) {
        try {
            Storage storage = new Storage(filePath);
            TaskList tasks = new TaskList(storage);
            ui = new Ui(tasks);
        } catch (TsohgException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Process the given input, then return the response.
     * @param input The given input.
     * @return The response of the input.
     */
    public String takeInput(String input) {
        try {
            return ui.takeInput(input).trim();
        } catch (TsohgException e) {
            return e.getMessage();
        }
    }
}
