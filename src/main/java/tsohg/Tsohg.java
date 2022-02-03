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

    /**
     * Runs the program.
     */
    public void run() {
        ui.run();
    }

    public static void main(String[] args) {
        new Tsohg("data.txt").run();
    }
}
