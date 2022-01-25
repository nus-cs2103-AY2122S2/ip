package connor;

import connor.exception.InvalidTaskFileException;
import connor.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String ERROR_GENERAL = "An error occurred.";

    String filePath;
    String taskDirectory;

    public Storage(String filePath, String taskDirectory) throws IOException {
        this.filePath = filePath;
        this.taskDirectory = taskDirectory;
        new File(taskDirectory).mkdirs();
        new File(filePath).createNewFile();
    }

    public void loadTasks() throws FileNotFoundException, IndexOutOfBoundsException, InvalidTaskFileException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(stringToTask(s.nextLine()));
        }
        TaskList.setTasks(tasks);
    }

    public static String taskToString(Task t) {
        String spacing = " | ";
        String taskType = t.getLetter();
        String doneness = t.isDone() ? "[#] " : "[ ] ";
        String desc = t.getDesc();
        StringBuilder sb = new StringBuilder(doneness + taskType + spacing + desc);
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

    public static Task stringToTask(String s) throws IndexOutOfBoundsException, InvalidTaskFileException {
        char c = s.charAt(4);
        char d = s.charAt(1);
        switch (c) {
        case 'T': {
            String[] parts = s.split(" \\| ", 2);
            String desc = parts[1];
            ToDo todo = new ToDo(desc);
            if (d == '#') {
                todo.mark();
            }
            return todo;
        }
        case 'D': {
            String[] parts = s.split(" \\| ", 3);
            String desc = parts[1];
            String when = parts[2].replaceAll("By: ", "");
            Deadline deadline;
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                deadline = new Deadline(desc, ld);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                deadline = new Deadline(desc, when);
            }
            if (d == '#') {
                deadline.mark();
            }
            return deadline;
        }
        case 'E': {
            String[] parts = s.split(" \\| ", 3);
            String desc = parts[1];
            String when = parts[2].replaceAll("At: ", "");
            Event event;
            try {
                // Check if 'when' is a valid date
                LocalDate ld = LocalDate.parse(when);
                event = new Event(desc, ld);
            } catch (DateTimeParseException e) {
                // Otherwise, treat it as a normal String
                event = new Event(desc, when);
            }
            if (d == '#') {
                event.mark();
            }
            return event;
        }
        default: {
            throw new InvalidTaskFileException("This task file is invalid!");
        }
        }
    }

    public void updateFile() {
        try {
            FileWriter fw = new FileWriter(filePath);
            StringBuilder textToUpdate = new StringBuilder();
            for (Task t : TaskList.getTasks()) {
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
