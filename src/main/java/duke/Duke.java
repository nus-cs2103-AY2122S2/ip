package duke;

import duke.command.Command;
import duke.datetime.DateTable;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.BotStoring;
import duke.util.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * The bot main class
 */
public class Duke {

    private final static String DIRECTORY = "data";
    private final static String FILE_NAME = "duke.Duke.txt";
    private final static String PATH = DIRECTORY + "/" + FILE_NAME;

    private final BotStoring botStorage;
    private final TaskList taskList;
    private final Ui ui;
    private final DateTable dateTable;

    public Duke(String path, String directory) throws IOException {
        TaskList taskList1 = new TaskList();

        botStorage = new BotStoring(path, directory);
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
