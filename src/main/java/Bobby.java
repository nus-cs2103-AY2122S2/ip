import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Bobby {

    private Storage storage;
    private TaskList tasks;
    private boolean isExit;

    public Bobby(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile(), storage);
        } catch (FileNotFoundException e) {
            //ui.showLoadingError();
            tasks = new TaskList(new ArrayList<Task>(), storage);
        }
        isExit = false;
    }

    public void terminate() {
        isExit = true;
    }

    public void run() throws IOException {
        Scanner scannerObj = new Scanner(System.in);
        while (scannerObj.hasNextLine()) {
            String userInput = scannerObj.nextLine();
            Parser.parse(tasks, userInput, this);
            if (isExit) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Ui.showWelcome();
        new Bobby("bobby.txt").run();
    }
}
