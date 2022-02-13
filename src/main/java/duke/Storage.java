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

import duke.misc.Pair;
import duke.taskobjects.Deadline;
import duke.taskobjects.Event;
import duke.taskobjects.Task;
import duke.taskobjects.TaskWithDate;
import duke.taskobjects.Todo;

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

    protected Pair<Boolean, ArrayList<Task>> loadTaskListFile() {
        if (!doesFileExist()) {
            System.out.println("Existing file not found, starting fresh...");
            return new Pair<>(doesFileExist(), new ArrayList<>());
        }
        return new Pair<>(doesFileExist(), importTasks());
    }

    private ArrayList<Task> importTasks() {
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
            ImportTaskParameters taskParameters = new ImportTaskParameters(input);
            Task newTask = generateNewImportedTask(taskParameters);
            if (newTask != null) {
                taskList.add(newTask);
            }
        }
        return taskList;
    }

    private Task generateNewImportedTask(ImportTaskParameters para) {
        switch (para.getType()) {
        case "T":
            return new Todo(para.getDescription(), para.getIsDone());
        case "E":
            assert !para.getDate().isEmpty() : "Error importing date";
            return new Event(para.getDescription(), para.getIsDone(), para.getDate());
        case "D":
            assert !para.getDate().isEmpty() : "Error importing date";
            return new Deadline(para.getDescription(), para.getIsDone(), para.getDate());
        default:
            return null;
        }
    }

    protected boolean exportTasks(List<Task> taskList) {
        // Opening file for writing
        BufferedWriter writer;
        try {
            writer = Files.newBufferedWriter(pathToFile, StandardCharsets.UTF_8);
            // Export task list to String
            writer.write(exportTaskListToString(taskList));
            writer.flush();
        } catch (IOException e) {
            System.out.println("Something went wrong while writing to file");
            return false;
        }
        return true;
    }

    private String exportTaskListToString(List<Task> taskList) {
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            String finalOutput = "";

            switch (task.getType()) {
            case TODO:
                finalOutput = generateExportStringTodo(task);
                break;
            case EVENT: {
                TaskWithDate taskWithDate = (TaskWithDate) task;
                finalOutput = generateExportStringEvent(taskWithDate);
                break;
            }
            case DEADLINE: {
                TaskWithDate taskWithDate2 = (TaskWithDate) task;
                finalOutput = generateExportStringDeadline(taskWithDate2);
                break;
            }
            default:
                assert false : "Error occurred reading exporting task";
            }
            sb.append(finalOutput);
        }
        return sb.toString();
    }

    private String generateExportStringTodo(Task task) {
        return "T`" + (task.isDone() ? "X`" : "O`")
                + task.getTaskName() + "\n";
    }

    private String generateExportStringEvent(TaskWithDate task) {
        return "E`" + (task.isDone() ? "X`" : "O`")
                + task.getTaskName() + "`" + task.getDate() + "\n";
    }

    private String generateExportStringDeadline(TaskWithDate task) {
        return "D`" + (task.isDone() ? "X`" : "O`")
                + task.getTaskName() + "`" + task.getDate() + "\n";
    }

}

class ImportTaskParameters {
    private final String type;
    private final boolean isDone;
    private final String description;
    private final String date;

    protected ImportTaskParameters(String input) {
        String[] inputArray = input.split("`");
        type = inputArray[0];
        isDone = inputArray[1].equals("X");
        description = inputArray[2];

        boolean isTaskWithDate = (type.equals("E") || type.equals("D"));
        if (isTaskWithDate) {
            date = inputArray[3];
        } else {
            date = "";
        }
    }

    // Getter functions
    protected String getType() {
        return type;
    }

    protected boolean getIsDone() {
        return isDone;
    }

    protected String getDescription() {
        return description;
    }

    protected String getDate() {
        return date;
    }
}
