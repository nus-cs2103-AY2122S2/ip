package angela;

import java.io.IOException;

import angela.command.Command;
import angela.datetime.DateTable;
import angela.task.TaskList;
import angela.util.BotStorage;
import angela.util.Parser;
import angela.util.Ui;

/**
 * The bot main class
 */
public class Angela {

    private final BotStorage botStorage;
    private final TaskList taskList;
    private final Ui ui;
    private final DateTable dateTable;

    /**
     * Initialize an empty instance of Duke
     */
    public Angela(String... args) {
        botStorage = null;
        taskList = null;
        ui = null;
        dateTable = null;
    }

    /**
     * Initialize the Duke bot
     * @param path Relative path where the database is located
     * @param directory Directory where database is located
     * @throws IOException If an I/O exception occur
     */
    public Angela(String path, String directory) throws IOException {
        TaskList taskList1 = new TaskList();

        botStorage = new BotStorage(path, directory);
        ui = new Ui();
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
    public String run(String input) {
        String temp = "";
        try {
            Command c = Parser.parse(dateTable, input);
            temp = c.execute(taskList, ui, botStorage, dateTable);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }

    public String getResponse(String input) {
        return this.run(input);
    }
}
