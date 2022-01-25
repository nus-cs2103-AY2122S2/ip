package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        Writer writer = new FileWriter(this.filePath);
        String data = "";

        for (Task task : tasks) {
            Task.Type T = task.getType();

            switch (T) {
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
            }
        }
        writer.write(data);
        writer.close();
    }

    public void load(TaskList taskList) throws FileNotFoundException {
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
                        LocalDateTime.parse(taskString[3]), Task.Type.DEADLINE);
                if (taskString[1].equals("1")) {
                    taskList.markTask(idx);
                }
            } else if (taskString[0].equals("E")) {
                taskList.addTask(taskString[2],
                        LocalDateTime.parse(taskString[3]), Task.Type.EVENT);
                if (taskString[1].equals("1")) {
                    taskList.markTask(idx);
                }
            }
            idx++;
        }
        scanner.close();
    }
}
