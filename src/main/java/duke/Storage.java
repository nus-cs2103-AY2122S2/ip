package duke;

import duke.common.Const;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage manages the saving and loading of data from TaskList.
 */
public class Storage {
    private String filePath;

    /**
     * Create an instance of Storage.
     *
     * @param filePath path of text file to be processed.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes data to text file into an array of tasks from TaskList.
     *
     * @param tasks used to write into text file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        Writer writer = new FileWriter(this.filePath);
        String data = "";

        for (Task task : tasks) {
            Task.Type t = task.getType();

            switch (t) {
            case TODO:
                data += "T | ";
                data += task.getStatusIcon().equals("X") ? "1 | " : "0 | ";
                data += task.getDescription() + "\n";
                break;
            case DEADLINE:
                data += "D | ";
                data += task.getStatusIcon().equals("X") ? "1 | " : "0 | ";
                data += task.getDescription() + " | ";
                data += ((Deadline) task).getBy() + "\n";
                break;
            case EVENT:
                data += "E | ";
                data += task.getStatusIcon().equals("X") ? "1 | " : "0 | ";
                data += task.getDescription() + " | ";
                data += ((Event) task).getAt() + "\n";
                break;
            default:
                throw new IOException();
            }
        }
        writer.write(data);
        writer.close();
    }

    /**
     * Reads data from text file to TaskList.
     *
     * @param taskList used create tasks from text file.
     */
    public void load(TaskList taskList) throws FileNotFoundException, DukeException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Scanner scanner = new Scanner(new File(this.filePath));

        int idx = 1;
        while (scanner.hasNext()) {
            String dataLine = scanner.nextLine();
            String[] taskString = dataLine.split("\\s+\\|\\s+");
            if (taskString[0].equals("T")) {
                taskList.addTask(taskString[2], Task.Type.TODO);
                if (taskString[1].equals("1")) {
                    taskList.markTask(idx);
                }
            } else if (taskString[0].equals("D")) {
                taskList.addTask(taskString[2],
                        LocalDateTime.parse(taskString[3], Const.OUT_TIME_FORMATTER), Task.Type.DEADLINE);
                if (taskString[1].equals("1")) {
                    taskList.markTask(idx);
                }
            } else if (taskString[0].equals("E")) {
                taskList.addTask(taskString[2],
                        LocalDateTime.parse(taskString[3], Const.OUT_TIME_FORMATTER), Task.Type.EVENT);
                if (taskString[1].equals("1")) {
                    taskList.markTask(idx);
                }
            }
            idx++;
        }
        scanner.close();
    }
}
