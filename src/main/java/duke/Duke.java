package duke;
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
     *
     * @param path the path to the storage file from root
     * @param file_dir the path to the storage directory from root
     */
    public Duke(String path, String file_dir) {
        this.ui = new Ui();
        this.storage = new Storage(path, file_dir);
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
     *
     * @return void
     */
    public static void main(String[] args) {
        new Duke("./src/main/data/data.txt", "./src/main/data").run();
    }
}
