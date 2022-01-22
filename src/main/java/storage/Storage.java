package storage;

import task.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    static String PATH = "data";
    static String FILENAME = "todolist.txt";
    File file;

    public Storage() {
        File directory = new File(PATH + "/");
        if (! directory.exists()){
            boolean created = directory.mkdir();
        }

        this.file = new File(PATH, FILENAME);
        try {
            boolean isNotMadeYet = file.createNewFile();
        } catch (IOException e) {
            Ui.outputError("Sorry, storage file could not be created.");
        }

    }

    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.file, false);
            for (int i=0; i < taskList.size(); i++) {
                fileWriter.write(taskList.get(i).fileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.outputError("Sorry, couldn't write to the file this time!");
        }
    }
}
