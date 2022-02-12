package duke;

import duke.common.Constant;
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
            assert task != null : "task must not be null";

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
                data += ((Deadline) task).getBy().format(Constant.OUT_TIME_FORMATTER) + "\n";
                break;
            case EVENT:
                data += "E | ";
                data += task.getStatusIcon().equals("X") ? "1 | " : "0 | ";
                data += task.getDescription() + " | ";
                data += ((Event) task).getAt().format(Constant.OUT_TIME_FORMATTER) + "\n";
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
            String[] taskArr = dataLine.split("\\s+\\|\\s+");
            assert taskArr.length >= 3 : "There should be at least 3 parts, type, mark and description";

            String taskType = taskArr[0];
            assert taskType.matches("T|D|E") : "task type should be T, D or E";

            String isDoneStr = taskArr[1];
            assert isDoneStr.matches("1|0") : "isDone should be 1 or 0";

            boolean isDone = isDoneStr.equals("1");
            String description = taskArr[2];
            if (taskType.equals("T")) {
                taskList.addTask(description, Task.Type.TODO);
            } else if (taskType.equals("D")) {
                assert taskArr.length == 4 : "There should be a dateTime string";
                String dateTimeStr = taskArr[3];
                taskList.addTask(description,
                        LocalDateTime.parse(dateTimeStr, Constant.OUT_TIME_FORMATTER), Task.Type.DEADLINE);
            } else if (taskType.equals("E")) {
                assert taskArr.length == 4 : "There should be a dateTime string";
                String dateTimeStr = taskArr[3];
                taskList.addTask(description,
                        LocalDateTime.parse(dateTimeStr, Constant.OUT_TIME_FORMATTER), Task.Type.EVENT);
            }
            if (isDone) {
                taskList.markTask(idx);
            }
            idx++;
        }
        scanner.close();
    }
}
