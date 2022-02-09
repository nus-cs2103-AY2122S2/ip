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
     * Initialize the Angela bot
     *
     * @param path Relative path where the database is located
     * @param directory Directory where database is located
     * @throws IOException If an I/O exception occur
     */
    public Angela(String path, String directory) throws IOException {
        TaskList tempTaskList = new TaskList();

        botStorage = new BotStorage(path, directory);
        ui = new Ui();
        dateTable = new DateTable(ui);

        try {
            tempTaskList = new TaskList(botStorage, ui, dateTable);
        } catch (IOException e) {
            ui.showLoadingError();
            tempTaskList = new TaskList();
        }
        this.taskList = tempTaskList;
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

    /**
     * Return the output string generate from the input command
     *
     * @param input The input command
     * @return The display string obtain from the input command
     */
    public String getResponse(String input) {
        return this.run(input);
    }
}
