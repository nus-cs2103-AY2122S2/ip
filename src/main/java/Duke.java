import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class Duke {
    private static final String filePath = "data/duke.txt";


    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load(), ui);
        } catch (FileNotFoundException e) {
            System.out.println("    File not found!");
            this.tasks = new TaskList(ui);
        }
    }

    public void run() {
        ui.showUiForStart();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        while (!input.equals("bye")) {
            try {
                input = inputScanner.nextLine();
                Command cmd = Parser.parse(input);
                cmd.executeCommand(tasks, ui, storage);
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke(filePath);
        duke.run();
    }
}
