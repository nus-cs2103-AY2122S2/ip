package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList(); //has items in it
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath, tasks);
        try {
            storage.readTasksFromFile();
            storage.closeReadFile();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read provided file!\n");
        } catch (IOException e) {
            System.out.println("Unable to close file that is read!\n");
        }
    }

    public void run() throws DukeException, IOException {
        boolean isTerminated = false;
        while (!isTerminated) {
            ui.displayCommandMessage(tasks, storage);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        //"C:\\NUS\\CS2103\\iP\\data\\duke.txt"
        new Duke("data/duke.txt").run();
    }
}
