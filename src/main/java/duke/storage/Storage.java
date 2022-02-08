package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;


/**
 * Stores the tasks into a file and retrieve tasks from file when bot starts.
 */
public class Storage {
    private static String filePath;

    /**
     * Constructor of storage class to store filepath of file.
     *
     * @param filePath file's path where tasks are stored and read from
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Store tasks to file when a task is added or deleted from the list.
     *
     * @param list the list belonging to user to be stored
     * @throws IOException thrown when this file does not exist
     */
    public void writeToFile(TaskList list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < list.getSize(); i++) {
            addTask(list.getTask(i), fw);
        }
        fw.close();
    }
    private void addTask(Task task, FileWriter fw) throws IOException {
        String textToAdd = task.getTaskData();
        fw.write(textToAdd + System.lineSeparator());
    }

    /**
     * Load tasks into the list when the bot starts running.
     *
     * @return tasks belonging to the user
     * @throws FileNotFoundException thrown when the file to load form does not exist
     */
    public ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String currTask = s.nextLine();
                String typeOfTask = Character.toString(currTask.charAt(1));
                boolean isMarked = false;
                if (isTaskMarked(currTask)) {
                    isMarked = true;
                }
                switch (typeOfTask) {
                case "T": {
                    Todo curr = new Todo(currTask.substring(7));
                    if (isMarked) {
                        curr.mark();
                    }
                    assert curr != null : "to do should be created";
                    list.add(curr);
                    break;
                }
                case "D": {
                    String[] info = currTask.split(" \\(by: ");
                    String description = info[0].substring(7);
                    String date = info[1].substring(0, info[1].length() - 1); //Oct 12 2020 1 pm

                    Deadline curr = new Deadline(description, date);
                    if (isMarked) {
                        curr.mark();
                    }
                    assert curr != null : "deadline should be created";
                    list.add(curr);
                    break;
                }
                case "E": {
                    String[] info = currTask.split(" \\(at: ");
                    String description = info[0].substring(7);
                    String date = info[1].substring(0, info[1].length() - 1);
                    Event curr = new Event(description, date);
                    if (isMarked) {
                        curr.mark();
                    }
                    assert curr != null : "event should be created";
                    list.add(curr);
                    break;
                }
                default:
                    throw new DukeException("file contains wrong task format");
                }
            }
        } catch (Exception err) {
            File myObj = new File(filePath);
            try {
                myObj.createNewFile();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
        return list;
    }
    private boolean isTaskMarked(String task) {
        return Character.toString(task.charAt(4)).equals("X");
    }
}
