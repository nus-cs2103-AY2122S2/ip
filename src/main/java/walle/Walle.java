package walle;

/**
 * Main Walle program
 *
 * @author Toh Zhan Qing
 */
public class Walle {

    static TaskList tasklist;
    public static boolean hasJustBooted = true;

    public Walle() {
        initialise();
    }

    public String getResponse(String input) {
        return Parser.parseIsBye(input, tasklist);
    }

    public void initialise() {
        Parser parser = new Parser();
        Storage storage = new Storage();
        tasklist = storage.tasklist;
    }

}
