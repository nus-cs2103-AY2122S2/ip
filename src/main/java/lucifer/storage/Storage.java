package lucifer.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import lucifer.task.Deadline;
import lucifer.task.Event;
import lucifer.task.Task;
import lucifer.task.Todo;

/**
 * Class Lucifer.Storage which stores/load the list of tasks.
 * @author Yu Meng
 */
public class Storage {
    /** directory to save/load. **/
    protected String filePath;
    /**
     * Constructor for Class Lucifer.Storage.
     *
     * @param filePath the path of the file to save/load the tasks
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the list of tasks to the directory.
     *
     * @param list the list of tasks
     * @throws IOException throws exception if unable to save to directory
     *
     */
    public void saveFileList(ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath + "/Lucifer.txt");
        StringBuilder tasks = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                tasks.append(list.get(i).saveFormat());
            } else {
                tasks.append(list.get(i).saveFormat()).append("\n");
            }
        }
        int listSize = list.size();
        assert listSize != 0;
        fw.write(tasks.toString());
        fw.close();
    }

    /**
     * Saves the list of tasks to the directory.
     *
     * @return the list of tasks
     * @throws IOException throws exception if unable to save to directory
     */
    public ArrayList<Task> loadList() throws IOException {
        String directory = filePath + "/Lucifer.txt";
        File file = new File(directory);
        ArrayList<Task> list = new ArrayList<>();
        try {
            Files.createFile(Paths.get(directory));
        } catch (FileAlreadyExistsException e) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String curr = sc.nextLine();
                Character eventType = curr.charAt(0);
                if (eventType.equals('E')) {
                    addEvent(curr, list);
                } else if (eventType.equals('D')) {
                    addDeadline(curr, list);
                } else if (eventType.equals('T')) {
                    addToDo(curr, list);
                }
            }
        }
        return list;
    }

    /**
     * Adds the task to the tasklist if it is an Event.
     *
     * @param input data abstracted from file.
     * @param list the arraylist to save the task into.
     */
    public void addEvent(String input, ArrayList<Task> list) {
        Character markOrNot = input.charAt(3);
        String theTask = input.substring(6);
        String[] getTask = theTask.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime old = LocalDateTime.parse(getTask[1].trim(), formatter);
        Event currTask = new Event(getTask[0].trim(), old);

        if (markOrNot.equals('1')) {
            currTask.markDone();
        } else {
            currTask.unmarkDone();
        }
        list.add(currTask);
    }
    /**
     * Adds the task to the tasklist if it is a Deadline.
     *
     * @param input data abstracted from file.
     * @param list the arraylist to save the task into.
     */
    public void addDeadline(String input, ArrayList<Task> list) {
        Character markOrNot = input.charAt(3);
        String theTask = input.substring(6);
        String[] getTask = theTask.split("\\|");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
        LocalDateTime old = LocalDateTime.parse(getTask[1].trim(), formatter);
        Deadline currTask = new Deadline(getTask[0].trim(), old);
        if (markOrNot.equals('1')) {
            currTask.markDone();
        } else {
            currTask.unmarkDone();
        }
        list.add(currTask);
    }
    /**
     * Adds the task to the tasklist if it is a ToDo.
     *
     * @param input data abstracted from file.
     * @param list the arraylist to save the task into.
     */
    public void addToDo(String input, ArrayList<Task> list) {
        Character markOrNot = input.charAt(3);
        int i = input.lastIndexOf("|") + 2;
        String theTask = input.substring(i);
        Todo currTask = new Todo(theTask);
        if (markOrNot.equals('1')) {
            currTask.markDone();
        } else {
            currTask.unmarkDone();
        }
        list.add(currTask);
    }
}
