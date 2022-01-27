package duke;

import java.util.List;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.exception.DukeException;
import duke.task.Task;

public class Duke {
    private static final String FILE_PATH = "./data/test.txt";

    private static final Ui UI = new Ui();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        UI.showWelcome();
        boolean isExit = false;
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = Storage.loadFromFile(FILE_PATH);
        while (!isExit) {
            try {
                String commandLine = sc.nextLine();
                Command c = Parser.parse(commandLine);
                c.execute(taskList, UI);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.printContent(e.getMessage());
            }
        }
        UI.showExitMessage();
        sc.close();
    }
}