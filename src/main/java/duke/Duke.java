package duke;

/**
 * This is the main duke.Duke program that will be able to process a duke.Task of 3 types: todo, deadline and task
 * duke.Duke is able to list, delete and mark/unmark tasks as done/undone.
 *
 * @author Toh Zhan Qing
 */
public class Duke {

    static TaskList tasklist;

    public Duke() {
        Ui ui = new Ui();
        Parser parser = new Parser();
        Storage storage = new Storage();
        tasklist = storage.tasklist;
    }

    public String getResponse(String input){
        return Parser.parseIsBye(input, tasklist);
    }

}
