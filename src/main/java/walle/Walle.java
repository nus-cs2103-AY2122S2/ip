package walle;

/**
 * This is the main duke.Walle program that will be able to process a duke.Task of 3 types: todo, deadline and task
 * duke.Walle is able to list, delete and mark/unmark tasks as done/undone.
 *
 * @author Toh Zhan Qing
 */
public class Walle {

    static TaskList tasklist;
    public static boolean hasJustBooted = true;

    public Walle() {
        initialise();
    }

    public String getResponse(String input){
        return Parser.parseIsBye(input, tasklist);
    }

    public void initialise() {
        Parser parser = new Parser();
        Storage storage = new Storage();
        tasklist = storage.tasklist;
    }

}
