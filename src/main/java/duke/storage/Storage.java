package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 * Basically, it handles Duke.txt and the CRUD on it.
 */
public class Storage {

    // the arrayList we will keep updating
    public static ArrayList<Task> res = new ArrayList<Task>();
    private String filePath;

    /**
     * Constructor for Storage. We will determine here if the file/directory exists,
     * and whether we cop from it or not.
     * 
     * @param filePath path of the file to write to
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * By loading i believe this should return a valid ArrayList - either empty or
     * prefilled with the duke.txt file.
     * 
     * @throws FileNotFoundException, DukeException cos thats whats declared in
     *                                Duke.java code.
     */
    public ArrayList<Task> load() throws DukeException {

        try {
            File file = new File(filePath);
            if (file.exists()) {
                return readFile(file);
            } else {
                return new ArrayList<>();
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Handles receiving todo command from duke.txt
     * 
     * @param isMarked    to update done attribute of todo
     * @param description of todo
     */
    private static void handleTodoCommand(String isMarked, String description) {
        Task t = new ToDos(description);
        if (isMarked.equals("marked")) {
            t.setDone(true);
        } else {
            t.setDone(false);
        }
        // add the task to the ArrayList
        res.add(t);
    }

    /**
     * Handles receiving deadline command from duke.txt
     * 
     * @param isMarked    to update done attribute of deadline
     * @param description of deadline
     */
    private static void handleDeadlineCommand(String isMarked, String description, String by) {
        Task t = new Deadlines(description, by);
        if (isMarked.equals("marked")) {
            t.setDone(true);
        } else {
            t.setDone(false);
        }
        res.add(t);
    }

    /**
     * Handles receiving event command from duke.txt
     * 
     * @param isMarked    to update done attribute of event
     * @param description of event
     */
    private static void handleEventCommand(String isMarked, String description, String at) {
        Task t = new Deadlines(description, at);
        if (isMarked.equals("marked")) {
            t.setDone(true);
        } else {
            t.setDone(false);
        }
        res.add(t);
    }

    /**
     * Method to write to the duke.txt file.
     * 
     * @param filePath path of the file to write to
     * @param addText  text we want to add to the file
     */
    private static void writeToFile(String filePath, String addText) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(addText);
        fw.close();
    }

    /**
     * Method to append to the end of the present file.
     * 
     * @param filePath path of the file to write to
     * @param addText  text we want to add to the file
     */
    private static void appendToFile(String filePath, String addText) throws IOException {
        // adding the true argument allows
        // changes FileWriter to append mode
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(addText);
        fw.close();
    }

    /**
     * Reads the contents of the file duke.txt,
     * and handles each task's generation for the
     * arrayList to be returned.
     * 
     * @param file that we are parsing
     * @throws FileNotFoundException
     * @return ArrayList res
     */
    public static ArrayList<Task> readFile(File file) throws FileNotFoundException {
        // read the file passed in
        Scanner s = new Scanner(file);

        // in this while loop, we will
        // separate the cases of tasks
        while (s.hasNext()) {
            String line = s.nextLine();

            // split the line up at the "/"
            String[] lineDetails = line.split("/");
            // note the following ->
            // lineDetails[0] denotes the task type
            // lineDetails[1] denotes the marked/unmarked status
            // lineDetails[2] denotes the description of task
            // lineDetails[3] denotes the by or at if event/deadline task

            if (lineDetails[0].equals("todo")) {
                handleTodoCommand(lineDetails[1], lineDetails[2]);
            } else if (lineDetails[0].equals("event")) {
                handleEventCommand(lineDetails[1], lineDetails[2], lineDetails[3]);
            } else if (lineDetails[0].equals("deadline")) {
                handleDeadlineCommand(lineDetails[1], lineDetails[2], lineDetails[3]);
            }
        }
        s.close();
        return res;
    }

    /**
     * Updates the duke.txt file. Method invokes upon receiving "bye" command.
     * 
     * @param file
     * @param arr
     */
    public static void updateDukeTxt(String filePath, ArrayList<Task> arr) throws IOException {
        int counter = 0;
        for (Task item : arr) {
            String task = item.toString();
            String addText = "";

            if (task.substring(1, 2).equals("T")) {
                addText = "todo/" + (item.getIsDone() ? "marked" : "unmarked") + "/" + item.getDescription() + "\n";
            } else if (task.substring(1, 2).equals("E")) {
                Events e = (Events) item;
                addText = "event/" + (item.getIsDone() ? "marked" : "unmarked") + "/" +
                        item.getDescription()
                        + " / " + e.at + "\n";
            } else if (task.substring(1, 2).equals("D")) {
                Deadlines d = (Deadlines) item;
                addText = "deadlines/" + (item.getIsDone() ? "marked" : "unmarked") + "/"
                        + item.getDescription()
                        + "/" + d.by + "\n";
            }

            if (counter == 0) {
                writeToFile(filePath, addText);
                ++counter;
            } else {
                appendToFile(filePath, addText);
                ++counter;
            }
        }
    }
}
