package duke;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.taskobjects.Deadline;
import duke.taskobjects.Event;
import duke.taskobjects.Task;
import duke.taskobjects.TaskWithDate;
import duke.taskobjects.Todo;
import duke.taskobjects.Types;

/**
 * Storage object that deals with exporting and importing task list items to a file.
 */
class Storage {
    private final Path pathToFile;

    protected Storage(String fileName) {
        pathToFile = Paths.get(fileName);
    }

    private boolean doesFileExist() {
        return Files.exists(pathToFile);
    }

    protected ArrayList<Task> importTasks() { // CHANGE THIS IN THE FUTURE, TEXT HANDLING SHLD BE DONE BY PARSE
        if (!doesFileExist()) {
            System.out.println("Existing file not found, starting fresh...");
            return new ArrayList<>();
        }

        Scanner s;
        try {
            s = new Scanner(pathToFile);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading saved file");
            return new ArrayList<>();
        }
        ArrayList<Task> taskList = new ArrayList<>();
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] inputArray = input.split("`");
            String type = inputArray[0];
            boolean isDone = inputArray[1].equals("X");
            String description = inputArray[2];

            // Parse input into array
            Task newTask = null;
            switch (type) {
            case "T":
                newTask = new Todo(description, isDone);
                break;
            case "E":
                newTask = new Event(description, isDone, inputArray[3]);
                break;
            case "D":
                newTask = new Deadline(description, isDone, inputArray[3]);
                break;
            default:
                break;
            }

            if (newTask != null) {
                taskList.add(newTask);
            }
        }
        return taskList;
    }

    // CHANGE THIS IN THE FUTURE, TEXT HANDLING SHLD BE DONE BY PARSE
    protected boolean exportTasks(List<Task> taskList) {
        // Opening file for writing
        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file (1)");
            return false;
        }

        // Export taskList to string
        try {
            writer.write(exportTaskListToString(taskList));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file (2)");
            return false;
        }

        return true;
    }

    private String exportTaskListToString(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String finalOutput = "";

            if (task.getType() == Types.TODO) {
                finalOutput = "T`" + (task.isDone() ? "X`" : "O`")
                        + task.getTaskName() + "\n";
            } else if (task.getType() == Types.EVENT) {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                finalOutput = "E`" + (task.isDone() ? "X`" : "O`")
                        + task.getTaskName() + "`" + taskWithDate.getDate() + "\n";
            } else if (task.getType() == Types.DEADLINE) {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                finalOutput = "D`" + (task.isDone() ? "X`" : "O`")
                        + task.getTaskName() + "`" + taskWithDate.getDate() + "\n";
            }
            sb.append(finalOutput);
        }
        return sb.toString();
    }

}
