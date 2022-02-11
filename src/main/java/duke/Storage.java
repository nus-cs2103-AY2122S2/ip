package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Class containing UI functions
 */

class Storage {
    private final String filePath;
    private final String fileDirectory;
    private final static int TASK_TYPE = 0;
    private final static int TASK_COMPLETED = 1;
    private final static int TASK_ITEM = 2;
    private final static int TASK_DATE = 3;


    Storage(String filePath, String fileDirectory) {
        this.filePath = filePath;
        this.fileDirectory = fileDirectory;
    }

    public void createFile() {
        try {
            Path path = Paths.get(fileDirectory);
            if (!Files.isDirectory(path)) {
                Files.createDirectory(path);
            }
            Path file = Paths.get(filePath);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }


    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public TaskList readData() {
        TaskList taskList = new TaskList();
        try {
            Scanner sc = new Scanner(new File(filePath));
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] values = task.split(" \\| ");
                String taskType = values[TASK_TYPE];
                String taskCompletion = values[TASK_COMPLETED];
                String taskItem = values[TASK_ITEM];
                switch (taskType) {
                    case "T":
                        ToDo todo = new ToDo(taskItem);
                        if (taskCompletion == "1") {
                            todo.setCompleted();
                        }
                        taskList.add(todo);
                        break;
                    case "E":
                        String eventDate = values[TASK_DATE];
                        LocalDate date = LocalDate.parse(eventDate);
                        Event event = new Event(taskItem, date);
                        if (taskCompletion == "1") {
                            event.setCompleted();
                        }
                        taskList.add(event);
                        break;
                    case "D":
                        String deadlineDate = values[3];
                        LocalDate localDate = LocalDate.parse(deadlineDate);
                        Deadline deadline = new Deadline(taskItem, localDate);
                        if (taskCompletion == "1") {
                            deadline.setCompleted();

                        }
                        taskList.add(deadline);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return taskList;
    }
}
