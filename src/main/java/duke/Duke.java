package duke;

import duke.command.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filename) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filename);
            this.taskList = storage.getData();
        } catch (IOException e) {
            File newFile = new File("data/");
            if (!newFile.exists()) {
                newFile.mkdir();
            }
            newFile = new File(filename);
            try {
                newFile.createNewFile();
                this.storage = new Storage(filename);
                this.taskList = storage.getData();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    void run() {
        this.ui.showMenu();
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String userInput = sc.nextLine();
                Command cmd = this.ui.showUserCommandLine(userInput);
                cmd.run(this.taskList, this.ui, this.storage);
            } catch (DukeExceptions e) {
                ui.showCommandError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}