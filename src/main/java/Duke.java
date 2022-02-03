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
    public String initUi() {
        return ui.initUi();
    }


    public String getResponse(String input) {
        return ui.run(tasks, storage, input);
    }
}
