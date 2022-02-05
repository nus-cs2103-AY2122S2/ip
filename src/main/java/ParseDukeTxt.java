import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseDukeTxt {

    // the arrayList we will keep updating
    private static ArrayList<Task> res;

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
        System.out.println("reached end of duke.txt");
        return res;
    }

    /**
     * Updates the duke.txt file. Method invokes upon receiving "bye" command.
     * 
     * @param file
     * @param arr
     */
    public static void updateDukeTxt(String filePath, ArrayList<Task> arr) throws IOException {

        // here we need to convert the received ArrayList<Task> arr into
        // lines to be add to the duke.txt file
        // its best to delete whatever is in the file,
        // and then recreate all the lines there,
        // as we may have updated states of tasks

        // split the toString of each task by " ",
        // and check the first element, if its [T], [E] or [D]

        // todo / marked / read a book
        // deadline / unmarked / return the book / June 6th
        // event / marked / project meeting / August 8th 2-4pm

        // to delete the file, we will overwrite the first line
        // then we will append to the file after.

        int counter = 0;
        // String filePath = "data/duke.txt";

        for (Task item : arr) {

            String task = item.toString();
            String[] taskDetails = task.split(" ");
            String addText = "";

            // note that -->
            // taskDetails[0] is command type
            if (taskDetails[0].equals("[T]")) {
                addText = "todo / " + (item.getIsDone() ? "marked" : "unmarked") + " / " + item.getDescription();
            } else if (taskDetails[0].equals("[E]")) {
                Events e = (Events) item;
                addText = "event / " + (item.getIsDone() ? "marked" : "unmarked") + " / " + item.getDescription()
                        + " / " + e.at;
            } else if (taskDetails[0].equals("[D]")) {
                Deadlines d = (Deadlines) item;
                addText = "deadlines / " + (item.getIsDone() ? "marked" : "unmarked") + " / " + item.getDescription()
                        + " / " + d.by;
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
