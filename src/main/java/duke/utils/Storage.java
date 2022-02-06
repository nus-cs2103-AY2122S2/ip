package duke.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * The type Storage.
 */
public class Storage {
    private final String filePath;
    private File taskFile;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path for storing/retrieving the todolist
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        createDirectoryAndFileIfNotExist();
    }

    /**
     * Create data directory and task.txt if not already exist.
     */
    public void createDirectoryAndFileIfNotExist() {
        try {
            File directory = new File(filePath);
            boolean wasDirectoryCreated = directory.mkdir();
            if (wasDirectoryCreated) {
                System.out.println("Created directory " + directory);
            }
            taskFile = new File(filePath + "task.txt");
            boolean wasFileCreated = taskFile.createNewFile();
            if (wasFileCreated) {
                System.out.println("Created task.txt under " + directory);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during creating directory or creating file");
        }
    }

    /**
     * Load tasks from task.txt into an arraylist.
     *
     * @return the arraylist containing all tasks inside task.txt
     */
    public ArrayList<Task> loadFile() {
        createDirectoryAndFileIfNotExist();
        ArrayList<Task> tasksArrayList = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(taskFile);
            Scanner scanner = new Scanner(fileInputStream);
            while (scanner.hasNextLine()) {
                Task taskToAdd = fileToTask(scanner.nextLine());
                tasksArrayList.add(taskToAdd);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        return tasksArrayList;
    }

    /**
     * Write tasks from TaskList into task.txt.
     *
     * @param tasks the TaskList tasks
     * @throws IOException the io exception
     */
    public void writeFile(TaskList tasks) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(taskFile, false);
        for (Task task: tasks.getTaskList()) {
            String taskToWrite = task.toString() + '\n';
            fileOutputStream.write(taskToWrite.getBytes(StandardCharsets.UTF_8));
        }
    }

    /**
     * Parse the task in string from task.txt into actual task.
     *
     * @param taskInString the task in string stored inside task.txt
     * @return the actual task
     */
    public static Task fileToTask(String taskInString) {
        char type = taskInString.charAt(1);
        boolean isDone = taskInString.charAt(4) == 'X';
        try {
            String[] actualTask;
            String description;
            switch (type) {
            /* deadline */
            case 'D':
                actualTask = taskInString.substring(7).split("\\(by: ");
                description = actualTask[0];
                String by = actualTask[1].replaceAll("\\)", "");
                Deadline deadline = new Deadline(description, extractDateTime(by));
                deadline.setDone(isDone);
                return deadline;
            /* event */
            case 'E':
                actualTask = taskInString.substring(7).split("\\(at: ");
                description = actualTask[0];
                String at = actualTask[1].replaceAll("\\)", "");
                Event event = new Event(description, extractDateTime(at));
                event.setDone(isDone);
                return event;
            /* todo */
            default:
                description = taskInString.substring(7);
                Todo todo = new Todo(description);
                todo.setDone(isDone);
                return todo;
            }
        } catch (DateTimeParseException e) {
            new Ui().showErrorMessage(e.getMessage());
            return null;
        }
    }

    public static LocalDateTime extractDateTime(String keyword) {
        DateTimeFormatter formatter;
        LocalDateTime localDateTime;
        LocalDate localDate;
        boolean hasTime = Pattern.compile("[A-Za-z]*, [A-Za-z]* \\d{1,2}, \\d{4} \\d{4}(AM|PM)")
                .matcher(keyword).find();
        if (hasTime) {
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy hhmma", Locale.ENGLISH);
            localDateTime = LocalDateTime.parse(keyword, formatter); //convert from hhmmaa to HHmm
        } else {
            formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
            localDate = LocalDate.parse(keyword, formatter);
            localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
        }
        return localDateTime;
    }
}
