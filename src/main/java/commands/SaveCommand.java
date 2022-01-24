package commands;

import tasks.TaskManager;
import ui.UiManager;

import java.io.FileWriter;
import java.io.IOException;

public class SaveCommand extends Command{
    private UiManager uiManager;
    private TaskManager taskManager;
    private String directory;
    private Type type;

    public SaveCommand(UiManager um, TaskManager tm, Type t) {
        this.uiManager = um;
        this.taskManager = tm;
        this.directory = "src/main/java/storage/list.txt";
        this.type = t;
    }
    private void writeToFile(String path) throws IOException {
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
