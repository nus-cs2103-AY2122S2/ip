package bobby;

import bobby.command.ByeCommand;
import bobby.command.Command;
import bobby.exception.BobbyException;
import bobby.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Bobby {
    private static final String FILE_LOCATION = "./Bobby.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Bobby(String filePath) {
        ui = new Ui(new Scanner(System.in));
        try {
            storage = new Storage(new File(FILE_LOCATION));
            tasks = new TaskList(storage.loadTasks());
        } catch (BobbyException | FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printLogo();
        ui.printGreeting();
        while (true) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                if (c instanceof ByeCommand) {
                    break;
                }
            } catch (BobbyException e) {
                System.out.println(e);
            } finally {
                ui.printLongLine(2);
            }
        }
    }

    public static void main(String[] args) {
        new Bobby(FILE_LOCATION).run();
    }
}
