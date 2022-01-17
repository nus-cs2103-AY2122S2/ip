package duke.main;

import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    // Reads and adds the file's content into the array
    public static TaskList addFileContent(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        TaskList toDoList = new TaskList();
        // save commands into the file
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            // mark status & type & descriptor & additional
            String cmd = s.nextLine();
            String[] cmd_split = cmd.split("&");
            boolean mark = cmd_split[0].equals("[X]") ? true : false;

            switch (cmd_split[1]) {
            case "T":
                // ToDo(String task, boolean markStatus)
                toDoList.add(new ToDo(cmd_split[2], mark));
                break;
            case "E":
                // Event(String task, boolean markStatus, String dateTime)
                toDoList.add(new Event(cmd_split[2], mark, cmd_split[3]));
                break;
            case "D":
                // Deadline(String task, boolean markStatus, String deadline)
                toDoList.add(new Deadline(cmd_split[2], mark, cmd_split[3]));
                break;
            default:
                // do nothing
                System.out.println("aaaa");
                break;
            }
        }
        return toDoList;
    }

    // Writes the contents of toDoList into storage with specific formatting
    public static void writeFileContent(TaskList toDoList) throws IOException {
        FileWriter fw = new FileWriter("./tasklist.txt");
        for (int i = 0; i < toDoList.size(); i++) {
            Task currentTask = toDoList.get(i);
            fw.write(currentTask.getStringCmd());
            fw.write("\n");
        }
        fw.close();
    }

}
