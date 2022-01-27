package duke;

/**
 * This is the main duke.Duke program that will be able to process a duke.Task of 3 types: todo, deadline and task
 * duke.Duke is able to list, delete and mark/unmark tasks as done/undone.
 *
 * @author Toh Zhan Qing
 */
public class Duke {

    public static void main(String[] args){
        Ui ui = new Ui();
        initialise();
        ui.loop();
    }

    /**
     * Initialises all necessary classes
     */
    public static void initialise(){
        TaskList tasklist = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser();
    }

}
