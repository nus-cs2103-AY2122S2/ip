package siri;


/**
 * Public class for the main program execution.
 */
public class Siri {

    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    /**
     * Constructor for Siri class.
     *
     * @param filePath in which data is to be loaded and saved.
     */
    public Siri(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui("   -----      O    -----      O\n"
                + " /   _   \\   __   |       \\   __\n"
                + " |  | |__|  |  |  |   O   |  |  |\n"
                + " |   ----\\  |  |  |       /  |  |\n"
                + "  \\ __   |  |  |  |   ---    |  |\n"
                + " |---|   |  |  |  |       \\  |  |\n"
                + "  \\______/  |__|  |___|\\___\\ |__|\n");

        try {
            tasks = new TaskList(storage.load());
            Ui.startUpSavedData();
            ui.startUp();
        } catch (SiriException se) {
            tasks = new TaskList();
            ui.startUp();
        }

        parser = new Parser(tasks);
    }

    /**
     * Constructor for Siri Class.
     */
    // public Siri() {
    //     storage = new Storage();
    //     ui = new Ui("   -----      O    -----      O\n"
    //             + " /   _   \\   __   |       \\   __\n"
    //             + " |  | |__|  |  |  |   O   |  |  |\n"
    //             + " |   ----\\  |  |  |       /  |  |\n"
    //             + "  \\ __   |  |  |  |   ---    |  |\n"
    //             + " |---|   |  |  |  |       \\  |  |\n"
    //             + "  \\______/  |__|  |___|\\___\\ |__|\n");

    //     try {
    //         tasks = new TaskList(storage.load());
    //         Ui.startUpSavedData();
    //         ui.startUp();
    //     } catch (SiriException se) {
    //         tasks = new TaskList();
    //         ui.startUp();
    //     }

    //     parser = new Parser(tasks);
    // }

    /*public static void main(String[] args) {
        new Siri("../data/data.txt").runApp();
    }*/



    public String exitApp() {
        String printString = ui.exit();
        storage.save(tasks.saveData());

        return printString;
    }

    public String getResponse(String input) {
        String printString;
        try {
            printString = parser.handleCommand(input);    
        } catch (SiriException se) {
            printString = se.getMessage();
        }
     
        return printString;
    }

}
