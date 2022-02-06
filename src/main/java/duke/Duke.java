package duke;

import java.io.IOException;
import java.util.Scanner;

/**
 * Duke class which is the main entry point when starting up the application
 */
public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke class
     */
    public Duke(String path, String fileDir) {
        this.ui = new Ui();
        this.storage = new Storage(path, fileDir);
        this.parser = new Parser();

        //Reading arraylist from data.txt
        try {
            this.taskList = storage.load();
        } catch (Exception e) {
            System.out.println(e);
            this.taskList = new TaskList();
        }
    }

    /**
     * method to run the application and start taking in puts
     */
    public void run() {
        ui.startUp();
        String inputData;
        Scanner scanner = new Scanner(System.in);
        boolean hasEnded = false;

        while (!hasEnded) {
            try {
                inputData = scanner.nextLine();
                hasEnded = parser.takeInput(inputData, taskList);
            } catch (DukeException e) {
                System.out.println(e);
                ui.printSeparator();
            }
            storage.storeTasks(taskList.getTasks());
        }
    }

    /**
     * point of entry for the application
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("./src/main/data/data.txt", "./src/main/data").run();
    }

    public String getResponse(String input) throws DukeException, IOException {
        try {
            String output = parser.guiTakeInput(input, taskList);
            return output;
        } catch (Exception e) {
            return e.toString();
        }
    }
}
