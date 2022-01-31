import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.*;
import java.time.format.DateTimeFormatter;

public class Duke {
    private static Storage storage;
    private static TaskList tasks;

    public Duke(String filePath) throws IOException {
        Ui ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(), storage.getNumberOfTasks());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String s = "";
            if ((s = br.readLine()).equals("bye")) {
                storage.wf.close();
                break;
            }
            Parser p = new Parser(s, tasks, storage);
        }

    }
}




