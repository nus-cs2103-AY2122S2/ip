package angela;

import java.io.IOException;
import java.util.ArrayList;

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
     * Initializes the Angela bot
     *
     * @param path Relative path where the database is located
     * @param directory Directory where database is located
     * @throws IOException If an I/O exception occur
     */
    public Angela(String path, String directory) throws IOException {
        botStorage = new BotStorage(path, directory);
        ui = new Ui();
        dateTable = new DateTable(ui);

        TaskList tempTaskList = new TaskList();
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
    public ArrayList<String> run(String input) {
        ArrayList<String> temp = new ArrayList<>();
        try {
            Command c = Parser.parse(input);
            temp = c.execute(taskList, ui, botStorage, dateTable);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return temp;
    }

    public DateTable getDateTable() {
        return dateTable;
    }

    /**
     * Returns the output string generate from the input command
     *
     * @param input The input command
     * @return The display string obtain from the input command
     */
    public ArrayList<String> getResponse(String input) {
        return this.run(input);
    }

    /**
     * Checks whether the current user is a new user
     *
     * @return True if current user is a new user, false otherwise
     */
    public boolean isNewUser() {
        return botStorage.isNewUser();
    }
}
