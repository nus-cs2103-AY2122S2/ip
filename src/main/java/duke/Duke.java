package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.BotStorage;
import duke.util.Parser;
import duke.util.Ui;

/**
 * The bot main class
 */
public class Duke {

    private static final String DIRECTORY = "data";
    private static final String FILE_NAME = "Duke.txt";
    private static final String PATH = DIRECTORY + "/" + FILE_NAME;

    private final BotStorage botStorage;
    private final TaskList taskList;
    private final Ui ui;
    private final DateTable dateTable;

    /**
     * Initialize the Duke bot
     * @param path Relative path where the database is located
     * @param directory Directory where database is located
     * @throws IOException If an I/O exception occur
     */
    public Duke(String path, String directory) throws IOException {
        TaskList taskList1 = new TaskList();

        botStorage = new BotStorage(path, directory);
        Scanner sc = new Scanner(System.in);
        ui = new Ui(sc);
        dateTable = new DateTable(ui);

        try {
            taskList1 = new TaskList(botStorage, ui, dateTable);
        } catch (IOException e) {
            ui.showLoadingError();
            taskList1 = new TaskList();
        }
        this.taskList = taskList1;
    }


    /**
     * Runs the bot
     */
    public void run() {
        boolean isExit = false;

        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(dateTable, command);
                c.execute(taskList, ui, botStorage, dateTable);
                isExit = c.isExit();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke(PATH, DIRECTORY).run();
    }
}
