import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.getTaskList());
    }

    public void run() {
        ui.start();
        boolean isExit = false;
        while (!isExit) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine().trim(); // Can also convert result to lower-case to handle cases.
            Parser parser = new Parser(storage, tasks, ui);
            parser.parse(command);
            boolean flag = parser.getExitTrigger();
            if(flag) {
                isExit = true;
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}