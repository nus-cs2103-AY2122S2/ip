package duke;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Chi {
    /**
     * Stores the messages sent by the user.
     */
    private Storage storage;
    private TaskList taskList;
    private UI ui;
    private Parser parser;
    // delete these
    private List<Task> messages;
    private static final String DATA_FOLDER = "data/";
    private static final String DATA_FILE = "data/tasks.txt";
    private File dataFile;

    public Chi(String filepath) {
        this.storage = new Storage(filepath);
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.taskList = new TaskList(storage.load());
        } catch (ChiException e) {
            // print error for file not found
            ui.printErrorMsg(e.getMessage());
            this.taskList = new TaskList();
        } catch (IOException e) {
            // print error for IO problems
            ui.printErrorMsg("There's something wrong with the IO nyan!");
        }
        // Delete this
        this.messages = new ArrayList<>();
        // Make sure data file exists in directory
    }

    public void run() {
        ui.printWelcome();
        ui.requestInput(this.taskList, this.storage, this.parser);
        ui.printGoodbye();
    }

    public static void main(String[] args) {
        Chi myBot = new Chi("data/tasks.txt");
        myBot.run();
    }
}
