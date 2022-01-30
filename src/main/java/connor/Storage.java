package connor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<File> filePathDirectories = new ArrayList<>();
        File currentFilePath = new File(this.filePath);
        while (currentFilePath.getParentFile() != null) {
            File newFilePath = currentFilePath.getParentFile();
            filePathDirectories.add(newFilePath);
            currentFilePath = newFilePath;
        }
        for (int i = filePathDirectories.size() - 1; i >= 0; i--) {
            filePathDirectories.get(i).mkdirs();
        }
        new File(this.filePath).createNewFile();
        copyTasks = new ArrayList<>();
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
    public static String taskToString(Task t) {
        String spacing = " | ";
        String taskType = t.getLetter();
        String doneStatus = t.isDone() ? "[#] " : "[ ] ";
        String desc = t.getDesc();
        StringBuilder sb = new StringBuilder(doneStatus + taskType + spacing + desc);
        if (t instanceof Deadline) {
            sb.append(spacing + "By: ");
            sb.append(((Deadline) t).getBy());
        }
        if (t instanceof Event) {
            sb.append(spacing + "At: ");
            sb.append(((Event) t).getAt());
        }
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
            String[] parts = s.split(" \\| ", 2);
            String desc = parts[1];
            ToDo todo = new ToDo(desc);
            if (doneStatus == '#') {
                todo.mark();
            }
            return todo;
        }
        case 'D': {
            String[] parts = s.split(" \\| ", 3);
            String desc = parts[1];
            String when = parts[2].replaceFirst("By: ", "");
            Deadline deadline;
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                deadline = new Deadline(desc, ld);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                deadline = new Deadline(desc, when);
            }
            if (doneStatus == '#') {
                deadline.mark();
            }
            return deadline;
        }
        case 'E': {
            String[] parts = s.split(" \\| ", 3);
            String desc = parts[1];
            String when = parts[2].replaceFirst("At: ", "");
            Event event;
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                event = new Event(desc, ld);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                event = new Event(desc, when);
            }
            if (doneStatus == '#') {
                event.mark();
            }
            return event;
        }
        default: {
            throw new InvalidTaskFileException("This task file is invalid!");
        }
        }
    }

    /**
     * Updates the text file stored in the hard drive using the task list stored in {@code TaskList}.
     */
    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToUpdate = new StringBuilder();
            copyTasks = TaskList.getTasks();
            for (Task t : copyTasks) {
                textToUpdate.append(taskToString(t));
            }
            fw.write(textToUpdate.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(ERROR_GENERAL);
            e.printStackTrace();
        }
    }
}
