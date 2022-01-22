package duke;

import duke.action.Action;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;

//deals with loading tasks from the file and saving tasks in the file
public class Storage {

    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public Storage() {
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public Storage load() throws DukeException {
        Path directoryExists = Paths.get("C:/repos/ip/data");
        //check if directory exists
        if (!Files.exists(directoryExists)) {
            throw new DukeException("Missing C:/repos/ip/data");
        }
        if (!file.exists()) {
            throw new DukeException("Missing C:/repos/ip/data/tasks.txt");
        }
        return this;
    }

    public void save(TaskList taskList) {
        ArrayList<Action> list = taskList.getList();
        if (!list.isEmpty()) {
            StringBuilder s = new StringBuilder();
            for (Action act : list) {
                s.append(act.toString()).append("\n");
            }
            try {
                writeToFile(file.getPath(), s.toString());
            } catch (IOException e) {
                System.out.println("Save error");
            }
        }
    }
}
