package duke.main;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    private Parser parser;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        parser = new Parser();
        storage = new Storage(filePath);
        taskList = new TaskList();
        ui = new Ui();
    }

    private void run() {
        taskList.fetchData(storage.readData());
        ui.showWelcome();

        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            parser.parse(scanner.nextLine());
            isExit = parser.isExit();
        }
        storage.saveData(taskList.getList());
    }

    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
