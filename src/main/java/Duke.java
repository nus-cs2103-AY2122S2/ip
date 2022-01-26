import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.*;

public class Duke {
    public static final String botName = "duke";
    private static final String filePath = "data/duke.txt";
    public static final DateTimeFormatter formatter = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("yyyy-MM-dd HHmm")
            .toFormatter(Locale.ENGLISH);

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
        Ui.printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        Ui.printDivider();
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
