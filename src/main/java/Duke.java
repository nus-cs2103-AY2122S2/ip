import duke.command.Command;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;
import duke.storage.Storage;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    public boolean isListening = true;
    private final String FILE_PATH;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.FILE_PATH = filePath;
        this.storage = new Storage(FILE_PATH);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    public void run(String input) {
        try {
            Command c = Parser.parse(input);
            ui.showLine();
            c.execute(tasks, ui, storage);
            isListening = !c.isExit();
            ui.showLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.notEnoughFieldsMessage();
        } catch (NumberFormatException e) {
            ui.invalidIndex();
        } catch (DateTimeParseException e) {
            ui.invalidDate();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke chatbot = new Duke("data/data.bin");
        chatbot.ui.greet();
        while (chatbot.isListening) {
            chatbot.run(sc.nextLine());
        }
    }
}
