import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private static final String FILEPATH = "data/duke.txt";

    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        Writer writer = new FileWriter(this.FILEPATH);
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
        System.out.println("System: File saved.");
    }

    public void loadFileToTasks(TaskManager taskManager) throws FileNotFoundException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Scanner scanner = new Scanner(new File(this.FILEPATH));
        
        int idx = 1;
        while (scanner.hasNext()) {
            String dataLine = scanner.nextLine();
            String[] taskString = dataLine.split("\\s+\\|\\s+");
            if (taskString[0].equals("T")) {
                taskManager.addToDo(taskString[2]);
                if (taskString[1].equals("1")) {
                    taskManager.markTask(idx);
                }
            } else if (taskString[0].equals("D")) {
                taskManager.addDeadline(taskString[2], taskString[3]);
                if (taskString[1].equals("1")) {
                    taskManager.markTask(idx);
                }
            } else if (taskString[0].equals("E")) {
                taskManager.addEvent(taskString[2], taskString[3]);
                if (taskString[1].equals("1")) {
                    taskManager.markTask(idx);
                }
            }
            idx++;
        }
        scanner.close();
    }


}
