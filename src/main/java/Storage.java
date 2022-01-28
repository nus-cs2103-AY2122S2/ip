import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage{
    File file;

    Storage(String filename) {
        this.file = new File(filename);
    }

    TaskList getData() throws FileNotFoundException {
        Scanner fileInput = new Scanner(this.file);
        TaskList taskList = new TaskList();
        while (fileInput.hasNextLine()) {
            String type = fileInput.nextLine();
            Boolean done = Boolean.parseBoolean(fileInput.nextLine());
            String name = fileInput.nextLine();
            String date = fileInput.nextLine();
            if (date.equals("*** Next Task ***")) {
                date = null;
            } else {
                fileInput.nextLine();
            }
            try {
                taskList.addTask(Task.createTask(type, done, name, date));
            } catch (DukeExceptions e) {
                System.out.println(e.getMessage());
            }
        }
        return taskList;
    }

    void updateData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(taskList.updateDatabase());
        fw.close();
    }
}
