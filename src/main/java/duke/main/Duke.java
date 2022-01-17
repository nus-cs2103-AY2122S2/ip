package duke.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileNotFoundException;


public class Duke {
    private TaskList toDoList;
    private Storage storage;
    private Ui ui;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        ui = new Ui();
        // Attempt to open the storage file. If file does not exist, then create a new file.
        try {
            System.out.println("Loading content from file");
            toDoList = storage.addFileContent("./tasklist.txt");
        } catch (FileNotFoundException e) {
            Ui.handleLoadError();
            // creates a new file in the current directory
            File f = new File("./tasklist.txt");
            toDoList = new TaskList();
        }
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
        Ui.showWelcome();
        while (!(cmd = br.readLine()).equals("bye")) {
            String commandType = cmd.split(" ")[0];
            ui.burpReply(ui.determineType(commandType), toDoList, cmd);
        }
        storage.writeFileContent(toDoList);
        Ui.showBye();
    }

    public static void main(String[] args) throws IOException {
        new Duke("./tasklist.txt").run();
    }
}
