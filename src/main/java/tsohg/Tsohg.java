package tsohg;

/**
 * The main class of the program.
 */
public class Tsohg {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor of the class.
     * @param filePath The filepath of the stored data.
     */
    Tsohg(String filePath) {
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage);
            ui = new Ui(tasks);
        } catch (TsohgException e) {
            System.out.println(e.getMessage());
        }
    }

    public String takeInput(String input){
        try {
            return ui.takeInput(input);
        } catch (TsohgException e) {
            return e.getMessage();
        }
    }

}
