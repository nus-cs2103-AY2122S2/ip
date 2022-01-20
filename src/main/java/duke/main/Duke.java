package duke.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;


public class Duke {
    private TaskList toDoList;
    public static Storage storage;
    private Ui ui;
    private final String filepath;

    public Duke(String filepath) {
        this.filepath = filepath;
        storage = new Storage(this.filepath);
        ui = new Ui();
        // Attempt to open the storage file. If file does not exist, then create a new file.
        try {
            toDoList = storage.addFileContent();
        } catch (FileNotFoundException e) {
            Ui.handleLoadError();
            // creates a new file in the current directory
            File f = new File(this.filepath);
            toDoList = new TaskList();
        }
    }

    public void run() throws DukeException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
        Ui.showWelcome();
        while (!(cmd = br.readLine()).equals("bye")) {
            String commandType = cmd.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, cmd);
        }
        Ui.showBye();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("./tasklist.txt").run();
    }
}
