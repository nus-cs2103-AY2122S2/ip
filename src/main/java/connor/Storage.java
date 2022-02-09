package connor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import connor.exception.ConnorException;
import connor.exception.InvalidTaskFileException;
import connor.task.Deadline;
import connor.task.Event;
import connor.task.Task;
import connor.task.TaskList;
import connor.task.ToDo;

/**
 * Stores the task list in the hard drive via a text file that can be retrieved and edited.
 *
 * @author jaysmyname
 */
public class Storage {
    private static final String ERROR_GENERAL = "An error occurred.";

    private String filePath;
    private ArrayList<Task> copyTasks;

    /**
     * Constructor for {@code Storage} class.
     *
     * @param filePath File path to store text file of task list.
     * @throws IOException If an I/O error occurs.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        makeDirectories(filePath);
        new File(this.filePath).createNewFile();
        copyTasks = new ArrayList<>();
    }

    /**
     * Makes the corresponding directories based on the file path.
     *
     * @param filePath File path to the text file.
     */
    private void makeDirectories(String filePath) {
        ArrayList<File> filePathDirectories = new ArrayList<>();
        File currentFilePath = new File(filePath);
        while (currentFilePath.getParentFile() != null) {
            File newFilePath = currentFilePath.getParentFile();
            filePathDirectories.add(newFilePath);
            currentFilePath = newFilePath;
        }
        for (int i = filePathDirectories.size() - 1; i >= 0; i--) {
            filePathDirectories.get(i).mkdirs();
        }
    }

    /**
     * Loads the tasks from the hard drive in a text file and sets it
     * as the current task list in {@code TaskList}
     *
     * @throws FileNotFoundException If the text file cannot be found.
     * @throws IndexOutOfBoundsException If the text file is an invalid task list.
     * @throws InvalidTaskFileException If the text file is an invalid task list.
     * @see TaskList
     */
    public void loadTasks() throws FileNotFoundException, IndexOutOfBoundsException, InvalidTaskFileException {
        Scanner s = new Scanner(new File(filePath));
        copyTasks = new ArrayList<>();
        while (s.hasNext()) {
            copyTasks.add(stringToTask(s.nextLine()));
        }
        TaskList.setTasks(copyTasks);
    }

    /**
     * Converts a {@code Task} into a {@code String} to store into the text file.
     *
     * @param t {@code Task} to be converted.
     * @return A {@code String} representation of the Task suitable for storing in the text file.
     */
    public static String taskToString(Task t) throws ConnorException {
        switch (t.getTaskType()) {
        case TODO: {
            return todoToString((ToDo) t);
        }
        case DEADLINE: {
            return deadlineToString((Deadline) t);
        }
        case EVENT: {
            return eventToString((Event) t);
        }
        default: {
            // Something has gone wrong
            throw new ConnorException("Unexpected Task Type!");
        }
        }
    }

    /**
     * Converts a {@code ToDo} into a {@code String} to store into the text file.
     *
     * @param t {@code ToDo} to be converted.
     * @return A {@code String} representation of the ToDo suitable for storing in the text file.
     */
    public static String todoToString(ToDo t) {
        String doneStatus = t.isDone() ? "[#] " : "[ ] ";
        String taskType = "T";
        String spacing = " | ";
        String desc = t.getDesc();
        StringBuilder sb = new StringBuilder(doneStatus + taskType + spacing + desc);
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Converts a {@code Deadline} into a {@code String} to store into the text file.
     *
     * @param t {@code Deadline} to be converted.
     * @return A {@code String} representation of the Deadline suitable for storing in the text file.
     */
    public static String deadlineToString(Deadline t) {
        String doneStatus = t.isDone() ? "[#] " : "[ ] ";
        String taskType = "D";
        String spacing = " | ";
        String desc = t.getDesc();
        StringBuilder sb = new StringBuilder(doneStatus + taskType + spacing + desc);
        sb.append(spacing + "By: ");
        sb.append(t.getStorageDate());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Converts an {@code Event} into a {@code String} to store into the text file.
     *
     * @param t {@code Event} to be converted.
     * @return A {@code String} representation of the Event suitable for storing in the text file.
     */
    public static String eventToString(Event t) {
        String doneStatus = t.isDone() ? "[#] " : "[ ] ";
        String taskType = "E";
        String spacing = " | ";
        String desc = t.getDesc();
        StringBuilder sb = new StringBuilder(doneStatus + taskType + spacing + desc);
        sb.append(spacing + "At: ");
        sb.append(t.getStorageDate());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Converts a {@code String} representation of a Task into its {@code Task} form.
     *
     * @param s {@code String} to be converted.
     * @return A {@code Task} that the {@code String} represents.
     * @throws IndexOutOfBoundsException If the {@code String} has an invalid {@code Task} form.
     * @throws InvalidTaskFileException If the {@code String} has an invalid {@code Task} form.
     */
    public static Task stringToTask(String s) throws IndexOutOfBoundsException, InvalidTaskFileException {
        char taskType = s.charAt(4);
        char doneStatus = s.charAt(1);
        switch (taskType) {
        case 'T': {
            return stringToTodo(s, doneStatus);
        }
        case 'D': {
            return stringToDeadline(s, doneStatus);
        }
        case 'E': {
            return stringToEvent(s, doneStatus);
        }
        default: {
            throw new InvalidTaskFileException("This task file is invalid!");
        }
        }
    }

    /**
     * Converts a {@code String} representation of a ToDo into its {@code Task} form.
     *
     * @param s {@code String} to be converted.
     * @param doneStatus The character representing the ToDo's status.
     * @return A {@code ToDo} that the {@code String} represents.
     * @throws IndexOutOfBoundsException If the {@code String} has an invalid {@code ToDo} form.
     */
    private static ToDo stringToTodo(String s, char doneStatus) throws IndexOutOfBoundsException {
        String[] parts = s.split(" \\| ", 2);
        String desc = parts[1];
        ToDo todo = new ToDo(desc);
        if (doneStatus == '#') {
            todo.mark();
        }
        return todo;
    }

    /**
     * Converts a {@code String} representation of a Deadline into its {@code Task} form.
     *
     * @param s {@code String} to be converted.
     * @param doneStatus The character representing the Deadline's status.
     * @return A {@code Deadline} that the {@code String} represents.
     * @throws IndexOutOfBoundsException If the {@code String} has an invalid {@code Deadline} form.
     */
    private static Deadline stringToDeadline(String s, char doneStatus) throws
            IndexOutOfBoundsException, InvalidTaskFileException {
        String[] parts = s.split(" \\| ", 3);
        String desc = parts[1];
        String when = parts[2].replaceFirst("By: ", "");
        Deadline deadline;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(when, formatter);
            deadline = new Deadline(desc, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFileException("A deadline has an invalid date time format!");
        }
        if (doneStatus == '#') {
            deadline.mark();
        }
        return deadline;
    }

    /**
     * Converts a {@code String} representation of an Event into its {@code Task} form.
     *
     * @param s {@code String} to be converted.
     * @param doneStatus The character representing the Event's status.
     * @return An {@code Event} that the {@code String} represents.
     * @throws IndexOutOfBoundsException If the {@code String} has an invalid {@code Event} form.
     */
    private static Event stringToEvent(String s, char doneStatus) throws
            IndexOutOfBoundsException, InvalidTaskFileException {
        String[] parts = s.split(" \\| ", 3);
        String desc = parts[1];
        String when = parts[2].replaceFirst("At: ", "");
        Event event;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(when, formatter);
            event = new Event(desc, dateTime);
        } catch (DateTimeParseException e) {
            throw new InvalidTaskFileException("An event has an invalid date time format!");
        }
        if (doneStatus == '#') {
            event.mark();
        }
        return event;
    }

    /**
     * Updates the text file stored in the hard drive using the task list stored in {@code TaskList}.
     */
    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToUpdate = new StringBuilder();
            copyTasks = TaskList.getTasks();
            assert copyTasks != null;
            for (Task t : copyTasks) {
                textToUpdate.append(taskToString(t));
            }
            fw.write(textToUpdate.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_GENERAL);
            e.printStackTrace();
        } catch (ConnorException e) {
            e.printStackTrace();
        }
    }
}
