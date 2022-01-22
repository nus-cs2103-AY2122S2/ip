import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;


public class Duke {

    private final Storage storage;
    private TaskList tasks;

    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | FileNotFoundException e) {
            Ui.invalidFileMessage();
            tasks = new TaskList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Ui.welcomeMessage();
        Scanner user_input = new Scanner(System.in).useDelimiter("\n");
        String userMessage = user_input.next();
        while (!userMessage.equals("bye")) {
            try {
                String[] command = new Parser().messageProcess(userMessage);
                tasks.executeCommand(command);
                storage.executeCommand(command);

            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println(Ui.dateTimeErrorMessage());
            }
            userMessage = user_input.next();
        }

        Ui.goodbyeMessage();
        System.exit(0);
    }

    public static void main(String[] args){
        new Duke("src/main/data/duke.txt").run();
    }
}
