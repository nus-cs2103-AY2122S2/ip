import DukeComponent.Storage;
import DukeComponent.TaskList;
import DukeComponent.Ui;
import java.util.Scanner;

/**
 *  A class that encapsulates the logic of Duke - A todo list program
 *  that helps user keep track of tasks.
 */
public class Duke {

    /**
     * {@link Storage}
     */
    private final Storage storage;
    /**
     * {@link TaskList}
     */
    private final TaskList tasks;

    /**
     * {@link Ui}
     */
    private final Ui ui;

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Constructor for Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Run Duke program.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        ui.run(sc, tasks, storage);
    }

    /**
     * Driver method for duke.
     * @param args CLI input for Main.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
