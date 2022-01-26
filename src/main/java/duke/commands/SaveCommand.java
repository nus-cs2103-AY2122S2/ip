package duke.commands;

import duke.tasks.TaskManager;
import duke.ui.UiManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command {
    private UiManager uiManager;
    private TaskManager taskManager;
    private String directory;
    private Type type;
    private File file;

    public SaveCommand(UiManager um, TaskManager tm, Type t) {
        this.uiManager = um;
        this.taskManager = tm;
        this.directory = "storage/list.txt";
        this.type = t;
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

    public void execute()  {
        try {
            this.writeToFile(this.directory);
            this.uiManager.printSave();
        } catch (IOException e) {
            uiManager.errorMessage("Oops! This is not a valid path!\nCheck if the directory exists!");

        }
    }
}
