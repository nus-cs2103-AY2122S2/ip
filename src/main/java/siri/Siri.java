package siri;


/**
 * Public class for the main program execution.
 */
public class Siri {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;
    private String startUpString;
    private boolean isInitialised = false;

    /**
     * Constructor for Siri class.
     *
     * @param filePath in which data is to be loaded and saved.
     */
    public Siri(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui("   -----      O      -----       O\n"
                + " /***-***\\   __  |*******\\  __\n"
                + " |***| |__|    |**| |***O***| |**|\n"
                + " |***----\\   |**|  |*******/ |**|\n"
                + "  \\ __****|  |**|  |***---     |**|\n"
                + " |---|****|  |**|  |******\\    |**|\n"
                + "  \\_____/   |_|   |__|\\___\\  |_|\n");

        try {
            tasks = new TaskList(storage.load());
        } catch (SiriException se) {
            tasks = new TaskList();
        }

        startUpString = ui.startUp();

        if (tasks.size() > 0) {
            startUpString = startUpString + Ui.startUpSavedData();
        }

        parser = new Parser(tasks);
    }

    public void setInitialised() {
        isInitialised = true;
    }

    public String getStartUpString() {
        return startUpString;
    }

    public boolean checkInitialised() {
        return isInitialised;
    }

    /**
     * Saves the app data into a file.
     *
     * @return good bye message.
     */
    public String exitApp() {
        String printString = ui.exit();
        storage.save(tasks.saveData());

        return printString;
    }

    public String getResponse(String input) throws SiriException {
        return parser.handleCommand(input);
    }

}
