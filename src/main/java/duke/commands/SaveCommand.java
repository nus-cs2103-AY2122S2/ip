package duke.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.tasks.TaskManager;
import duke.ui.UiManager;

/**
 * SaveCommand Object that allows users to save
 * current task list into a text file
 */
public class SaveCommand extends Command {
    private UiManager uiManager;
    private TaskManager taskManager;
    private String directory;
    private File file;

    /**
     * Constructs the SaveCommand Object
     *
     * @param um UiManager Object to handle input and output processes
     * @param tm TaskManager Object to access Task Objects
     */
    public SaveCommand(UiManager um, TaskManager tm) {
        this.uiManager = um;
        this.taskManager = tm;
        this.directory = "storage/list.txt";
        this.file = new File("storage");
    }

    private void writeToFile(String path) throws IOException {
        if (!file.exists()) {
            file.mkdir();
        }
        FileWriter fw = new FileWriter(path);
        fw.write(this.taskManager.toString());
        fw.close();
    }

    /**
     * Saves and writes current tasks into a text file.
     *
     * @return String containing relevant Task Object
     */
    public String execute() {
        try {
            this.writeToFile(this.directory);
            return this.uiManager.printSave();
        } catch (IOException e) {
            return uiManager.showErrorMessage("Oops! This is not a valid path!\nCheck if the directory exists!");
        }
    }
}
