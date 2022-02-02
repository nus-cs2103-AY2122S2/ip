package duke;

import duke.taskobjects.*;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.IOException;

/**
 * Storage object that deals with exporting and importing task list items to a file
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

            if (type.equals("T")) {
                newTask = new Todo(description, isDone);
            } else if (type.equals("E")) {
                newTask = new Event(description, isDone, inputArray[3]);
            } else if (type.equals("D")) {
                newTask = new Deadline(description, isDone, inputArray[3]);
            }

            if (newTask != null) {
                taskList.add(newTask);
            }
        }
        return taskList;
    }

    protected boolean exportTasks(List<Task> taskList) { // CHANGE THIS IN THE FUTURE, TEXT HANDLING SHLD BE DONE BY PARSE
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
