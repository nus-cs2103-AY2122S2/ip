package main;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private enum Reply {
        LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, DEFAULT
    }

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
            Reply type;
            boolean mark = cmd_split[0].equals("[X]") ? true : false;
            switch (cmd_split[1]) {
            case "T":
                type = Reply.TODO;
                break;
            case "E":
                type = Reply.EVENT;
                break;
            case "D":
                type = Reply.DEADLINE;
                break;
            default:
                type = Reply.DEFAULT;
                break;
            }

            switch (type) {
            case TODO:
                // ToDo(String task, boolean markStatus)
                toDoList.add(new ToDo(cmd_split[2], mark));
                break;
            case EVENT:
                // Event(String task, boolean markStatus, String dateTime)
                toDoList.add(new Event(cmd_split[2], mark, cmd_split[3]));
                break;
            case DEADLINE:
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
