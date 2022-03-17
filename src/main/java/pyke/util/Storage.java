package pyke.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import pyke.task.Deadline;
import pyke.task.Event;
import pyke.task.Task;
import pyke.task.ToDo;


public class Storage {
    private static java.nio.file.Path fileDir;
    private static java.nio.file.Path filePath;

    /**
     * Constructs a Storage class.
     *
     * @param dir the file directory.
     * @param fileName the file name.
     */
    public Storage(String dir, String fileName) {
        fileDir = Paths.get(".", dir);
        filePath = Paths.get(".", dir, fileName);
    }

    private void processTodo (TaskList taskList, Integer isMarked, String taskName) {
        Task temp = new ToDo(taskName);
        if (isMarked == 1) {
            temp.setStatus(true);
        }
        taskList.addTask(temp);
    }

    private void processDeadline(TaskList taskList, Integer isMarked, String taskName, String[] parsedList) {
        String taskTime = parsedList[3].substring(1);
        Task temp = new Deadline(taskName, LocalDate.parse(taskTime));
        if (isMarked == 1) {
            temp.setStatus(true);
        }
        taskList.addTask(temp);
    }

    private void processEvent(TaskList taskList, Integer isMarked, String taskName, String[] parsedList) {
        String taskTime = parsedList[3].substring(1);
        Task temp = new Event(taskName, LocalDate.parse(taskTime));
        if (isMarked == 1) {
            temp.setStatus(true);
        }
        taskList.addTask(temp);
    }
    /**
     * Initializes the taskList by the local file.
     * If such file or directory does not exist, it will attempt to create one.
     *
     * @param taskList the class that stores info and operations about tasks.
     * @throws IOException if there is an error when reading file.
     * @throws DateTimeParseException if the dates format in local file is not standard.
     */
    public void init(TaskList taskList) throws IOException, DateTimeParseException {
        if (!Files.exists(fileDir)) {
            Files.createDirectory(fileDir);
        }
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        } else {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String str = null;

            while ((str = reader.readLine()) != null) {
                String[] parsedList = str.split("\\|");
                String taskType = parsedList[0].split(" ")[0];
                Integer isMarked = Character.getNumericValue(parsedList[1].charAt(1));
                String taskName = parsedList[2].substring(1);

                if (taskType.equals("T")) {
                    processTodo(taskList, isMarked, taskName);
                } else if (taskType.equals("D")) {
                    processDeadline(taskList, isMarked, taskName, parsedList);
                } else if (taskType.equals("E")) {
                    processEvent(taskList, isMarked, taskName, parsedList);
                }
            }
        }
        return;
    }

    /**
     * Writes every task in a taskList into a local file.
     *
     * @param taskList the class that stores all tasks.
     * @throws IOException if there is an error when writing to local files.
     */
    public void saveFile(TaskList taskList) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            writer.write(taskList.getTaskSavingStyle(i) + "\n");
        }
        writer.flush();
        writer.close();
        return;
    }
}
