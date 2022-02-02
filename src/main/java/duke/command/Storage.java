package duke.command;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String path;
    private final TaskList taskList;

    public Storage(String path, TaskList taskList) {
        this.path = path;
        this.taskList = taskList;
        try {
            File myObj = new File(path);
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            FileWriter myWriter =  new FileWriter(path);
            myWriter.write(taskList.writeItem());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
