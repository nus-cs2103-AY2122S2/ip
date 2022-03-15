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
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
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
     * A method to run the application and start taking inputs
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
     * The point of entry for the application
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke().run();
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
