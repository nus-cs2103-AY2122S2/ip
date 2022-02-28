package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;




/**
 * Represents a storage which stores the data in the list to a file found in the hard disk.
 */
public class Storage {

    private File file;
    private final File directory;

    /**
     * Constructs a storage. Creates the directory if it does not exist.
     * @param filePath Storage path of the file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
        directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        assert file.exists() : "Data save file does not exist";
    }
    /**
     * Writes the data of list to the file.
     *
     * @param filePath File path of file.
     * @param taskList Task list of tasks.
     * @throws IOException If an error occurs while writing to file.
     */
    public void writeToFile(String filePath, List taskList) throws IOException {
        assert !filePath.isEmpty() : "Save data file does not exist";
        FileWriter fw = new FileWriter(filePath);
        fw.write(taskList.toString());
        fw.close();
    }

    /**
     * Loads the list of tasks in the file to an array list and returns it.
     *
     * @return Returns an array list of the tasks loaded from the file.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> newTaskList = new ArrayList<>();
        File file = new File("data/duke.txt");
        assert file.exists() : "Save data file does not exist";
        try {
            Scanner sc = new Scanner(file);
            sc.nextLine();
            while (sc.hasNext()) {
                String line;
                line = sc.nextLine();
                Character c = line.charAt(4);
                if (c.equals('T')) {
                    loadTodoTask(line, newTaskList);
                }
                if (c.equals('E')) {
                    loadEventTask(line, newTaskList);
                }
                if (c.equals('D')) {
                    loadDeadlineTask(line, newTaskList);
                }
            }
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
        return newTaskList;
    }
    private static void loadTodoTask(String line, ArrayList<Task> list) {
        String description = line.substring(10);
        ToDo todo = new ToDo(description);
        list.add(todo);
    }
    private static void loadEventTask(String line, ArrayList<Task> list) {
        int indexOfOpenBracket = line.indexOf('(');
        int indexOfCloseBracket = line.indexOf(')');
        String description = line.substring(10, indexOfOpenBracket - 1);
        String time = line.substring(indexOfOpenBracket + 4, indexOfCloseBracket);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(time, outputFormat);
        Event event = new Event(description, dateTime);
        list.add(event);
    }
    private static void loadDeadlineTask(String line, ArrayList<Task> list) {
        int indexOfOpenBracket = line.indexOf('(');
        int indexOfCloseBracket = line.indexOf(')');
        String description = line.substring(10, indexOfOpenBracket - 1);
        String time = line.substring(indexOfOpenBracket + 4, indexOfCloseBracket);
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern(" MMM dd yyyy h:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(time, outputFormat);
        Deadline deadline = new Deadline(description, dateTime);
        list.add(deadline);
    }
}
