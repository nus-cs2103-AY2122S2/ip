package duke;
import duke.utils.*;


/**
 * Represents an instance of the task
 * manager
 */
public class Duke {


    /**
     * Instance of Ui to handle user interaction
     */
    private Ui ui;


    /**
     * Instance of TaskList to store and
     * manage list of tasks.
     */
    private TaskList tl;



    /**
     * Constructor Method for Duke
     */
    public Duke() {
        this.ui = new Ui();
        tl = new TaskList(Storage.getSavedList());
    }

    /**
     * Handle the overall logic of
     * the task manager
     */
    public void run() {

        Ui.printHello();
        boolean isExit = false;

        while (!isExit) {
            Ui.printLine();

            try {
                Parser.parse(ui.readCommand(), tl);
            } catch (DukeException e) {
                Ui.showError(e);
            }

            isExit = ui.isExit();
            Ui.printLine();
        }

        tl.saveListToStorage();
        Ui.printBye();
    }

    /**
     * Main method for the program
     *
     * @param args Arguments given by the command line
     */
    public static void main(String[] args) {
        new Duke().run();
    }

}
