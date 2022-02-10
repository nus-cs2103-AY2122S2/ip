package aeromon;

import java.util.ArrayList;

import aeromon.command.Command;
import aeromon.command.CommandManager;
import aeromon.task.TaskArrayList;

/**
 * Aeromon class that runs the Aeromon bot.
 */
public class Aeromon {

    private TaskArrayList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Constructs the Aeromon object.
     * @param fileLocation the path of storage file for Aeromon.
     */
    public Aeromon(String fileLocation) {
        ui = new Ui();
        storage = new Storage(fileLocation);

        try {
            taskList = new TaskArrayList(storage.getFile());
        } catch (AeromonException e) {
            ui.print(e.getMessage());
            taskList = new TaskArrayList(new ArrayList<>());
        }
    }

    /**
     * Starts and runs the Aeromon bot.
     */
    public void start() {
        ui.greet();
        boolean isBye = false;

        while (!isBye) {
            try {
                String fullCommand = ui.readCommand();
                Command command = CommandManager.read(fullCommand);
                command.execute(taskList, ui, storage);
                isBye = command.isExit();
            } catch (AeromonException e) {
                ui.print(e.getMessage());
            }
        }
    }

    /**
     * Executes the Aeromon class and runs the bot.
     * @param args
     */
    public static void main(String[] args) {
        Aeromon aeromon = new Aeromon("data/localTasks.txt");
        aeromon.start();
    }
}
