package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Represents a file storing a list of Tasks keyed into Duke.
 */
public class Storage {
    private String path;

    /**
     * Creates new Storage object pointing to log file provided by path.
     * If there is no file at provided path, creates file at provided path.
     *
     * @param path Path to text file containing tasks entered into Duke.
     * @throws DukeException If file does not exist.
     */
    public Storage(String path) throws DukeException {
        this.path = System.getProperty("user.dir") + "/" + path;

        // Splits pathname into relative path and filename
        String filename = path.substring(path.lastIndexOf("/") + 1);
        String dir = path.split(filename)[0];
        File d = new File(dir);
        File f = new File(path);

        // Creates missing dir/ file
        if (!d.exists()) {
            d.mkdirs();
        }
        try {
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("WHERE YOUR NOTEPAD RECRUIT? YOU BETTER GO FIND IT NOW!");
        }
    }

    /**
     * Updates log file with current list of tasks.
     *
     * @param taskList Current list of tasks.
     * @throws IOException If log file is not found.
     */
    public void updateTasks(TaskList taskList) throws IOException {
        FileWriter log = new FileWriter(path);
        if (taskList.isEmpty()) {
            log.write("");
        } else {
            int size = taskList.size();
            for (int i = 0; i < size; i++) {
                Task curr = taskList.get(i);
                String toWrite = formatTaskForStorage(curr);
                toWrite += "\n";
                log.write(toWrite);
            }
        }
        log.close();
    }

    private String formatTaskForStorage(Task task) {
        String toWrite = task.getType() + " | " + task.getDone() + " | " + task.getName();
        if (task.getType() == "D") {
            toWrite += " | " + task.getDate() + " | " + task.getTime();
        } else if (task.getType() == "E") {
            toWrite += " | " + task.getDate() + " | " + task.getTimeFrom() + " " + task.getTimeTo();
        }
        return toWrite;
    }

    /**
     * Parses each task in log file and returns ArrayList containing each task.
     *
     * @return ArrayList of tasks in log file.
     * @throws DukeException if task type is not T, D, or E.
     */
    public ArrayList<Task> readTasks() throws DukeException {
        File f = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileToRead = new Scanner(f);
            while (fileToRead.hasNext()) {
                String curr = fileToRead.nextLine();
                tasks.add(parseTaskFromStorage(curr));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            return tasks;
        }
    }

    private Task parseTaskFromStorage(String input) throws DukeException {

        // taskComponent[0]: Type of Task
        // taskComponent[1]: Completion of Task, can be either "1" or "0"
        // taskComponent[2]: Name of Task
        // taskComponent[3]: Date of Task
        // taskComponent[4]: Time of Task
        // \\| needed to split "|"
        String[] taskComponents = input.split(" \\| ");
        switch (taskComponents[0]) {
        case "T":
            return parseToDoFromString(taskComponents);
        // Method has already returned, no need for break.
        case "D":
            return parseDeadlineFromString(taskComponents);
        // Method has already returned, no need for break.
        case "E":
            return parseEventFromString(taskComponents);
        // Method has already returned, no need for break.
        default:
            throw new DukeException("WHAT KIND OF RUBBISH IS THIS RECRUIT! WHAT YOU WRITING?");
        }
    }

    private ToDo parseToDoFromString(String[] taskComponents) {

        // taskComponent[0]: Type of Task
        // taskComponent[1]: Completion of Task, can be either "1" or "0"
        // taskComponent[2]: Name of Task
        ToDo newToDo = new ToDo(taskComponents[2]);
        if (taskComponents[1].equals("1")) {
            newToDo.mark();
        }
        return newToDo;
    }

    private Deadline parseDeadlineFromString(String[] taskComponents) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        // taskComponent[0]: Type of Task
        // taskComponent[1]: Completion of Task, can be either "1" or "0"
        // taskComponent[2]: Name of Task
        // taskComponent[3]: Date of Task
        // taskComponent[4]: Time of Task
        LocalDate d1 = LocalDate.parse(taskComponents[3], dateFormatter);
        LocalTime t1 = LocalTime.parse(taskComponents[4], timeFormatter);
        Deadline newDeadline = new Deadline(taskComponents[2], d1, t1);
        if (taskComponents[1].equals("1")) {
            newDeadline.mark();
        }
        return newDeadline;
    }

    private Event parseEventFromString(String[] taskComponents) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");

        // taskComponent[0]: Type of Task
        // taskComponent[1]: Completion of Task, can be either "1" or "0"
        // taskComponent[2]: Name of Task
        // taskComponent[3]: Date of Task
        // taskComponent[4]: Time of Task
        // timeArray[0] = timeFrom
        // timeArray[1] = timeTo

        String[] timeArray = taskComponents[4].split(" ");
        LocalDate d2 = LocalDate.parse(taskComponents[3], dateFormatter);
        LocalTime timeFrom = LocalTime.parse(timeArray[0], timeFormatter);
        LocalTime timeTo = LocalTime.parse(timeArray[1], timeFormatter);
        Event newEvent = new Event(taskComponents[2], d2, timeFrom, timeTo);
        if (taskComponents[1].equals("1")) {
            newEvent.mark();
        }
        return newEvent;
    }
}
