package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class handles the file storage, loading and saving the list that the user has entered.
 * After exiting the program, the data will be saved and reloaded again when the program starts again.
 */
public class Storage {

    private String filePath;
    private File file;

    /**
     * Is a constructor for the instance of Storage. This also checks if the directory and file exists, if not
     * it will create a new one in the respective directory.
     * @param filePath the directory of the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        String basePath = new File("").getAbsolutePath();

        String[] path = filePath.split("/");
        for (String s : path) {
            basePath = basePath + "/" + s;
            if (!s.contains(".txt")) {
                file = new File(basePath);
                file.mkdirs();
            } else {
                file = new File(basePath);
            }
        }

        assert file.exists() : "file should exist";
    }

    /**
     *
     * @return an ArrayList of tasks that was saved from previous sessions. If there was no saved data,
     * it will return an empty ArrayList.
     * @throws IOException when the file is corrupted.
     * @throws ParseException when the text in the file is not recognised
     * @throws DukeException when the command given by the user is not recognised.
     */
    public ArrayList<Task> load() throws IOException, ParseException, DukeException {

        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        ArrayList<Task> taskArrayList = new ArrayList<>();
        boolean isTaskCreated = false;

        if (file.length() == 0) {
            return taskArrayList;
        } else {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                isTaskCreated = false;
                String currentLine = scanner.nextLine();
                if (currentLine.contains("[T]")) {
                    isTaskCreated = loadToDo(taskArrayList, currentLine);
                } else if (currentLine.contains("[D]")) {
                    isTaskCreated = loadDeadline(taskArrayList, currentLine);
                } else if (currentLine.contains("[E]")) {
                    isTaskCreated = loadEvent(taskArrayList, currentLine);
                } else {
                    throw new DukeException("Something wrong with loading file");
                }
                assert isTaskCreated : "Task should be created";
            }
            return taskArrayList;
        }
    }

    /**
     * To append the task into the data file provided by the user.
     * @param task the task that is to be stored.
     * @throws IOException when there is an error opening the file.
     */
    public void appendTask(Task task) throws IOException {
        FileWriter appendWriter = new FileWriter(file, true);
        String toPrint = task.toString() + task.getStatus() + task.getDescription() + "$" + task.getPriority();;
        System.out.println("writing");
        appendWriter.write(toPrint + "\n");
        appendWriter.close();
    }

    /**
     * To clear the file and to rewrite all the Tasks in TaskLists into the file
     * @param taskList the tasklist of the user in the current session
     * @throws IOException when there is an error opening the file.
     */
    public void rewriteTask(TaskList taskList) throws IOException {
        FileWriter rewriteWriter = new FileWriter(file, false);
        for (Task task: taskList.getTaskArrayList()) {
            String toPrint = task.toString() + task.getStatus() + task.getDescription() + "$" + task.getPriority();
            rewriteWriter.write(toPrint + "\n");
        }

        rewriteWriter.close();
    }


    /**
     * Read the current line passed in from storage and load a new To-Do object.
     * @param taskArrayList the current TaskList to be loaded up into.
     * @param currentCommand the command that is being read.
     * @return a boolean that returns true when loaded successfully.
     */
    public boolean loadToDo(ArrayList<Task> taskArrayList, String currentCommand) {
        ToDos todo;
        Task.Priority priority;
        int indexOfPriority = currentCommand.indexOf("$");
        String priorityInput = currentCommand.substring(indexOfPriority);
        priority = parsePriority(priorityInput);

        if (currentCommand.charAt(4) == 'X') {
            todo = new ToDos(currentCommand.substring(6, indexOfPriority),
                    true, priority);
            taskArrayList.add(todo);
        } else {
            todo = new ToDos(currentCommand.substring(6, indexOfPriority), priority);
            taskArrayList.add(todo);
        }
        return true;
    }

    /**
     * Read the current line passed in from storage and load a new Deadline object.
     * @param taskArrayList the current TaskList to be loaded up into.
     * @param currentCommand the command that is being read.
     * @return a boolean that returns true when loaded successfully.
     */
    public boolean loadDeadline(ArrayList<Task> taskArrayList, String currentCommand) throws ParseException {

        String date = currentCommand.substring(currentCommand.indexOf('(') + 5, currentCommand.indexOf(')'));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        Date dueDate = (Date) formatter.parse(date);
        Task.Priority priority;
        int indexOfPriority = currentCommand.indexOf("$");
        String priorityInput = currentCommand.substring(indexOfPriority);
        priority = parsePriority(priorityInput);

        Deadline deadline;
        if (currentCommand.charAt(4) == 'X') {
            deadline = new Deadline(currentCommand.substring(6,
                    currentCommand.indexOf('(') - 1), true, dueDate, priority);
        } else {
            deadline = new Deadline(currentCommand.substring(6,
                    currentCommand.indexOf('(') - 1), dueDate, priority);
        }
        taskArrayList.add(deadline);
        return true;
    }

    /**
     * Read the current line passed in from storage and load a new Event object.
     * @param taskArrayList the current TaskList to be loaded up into.
     * @param currentCommand the command that is being read.
     * @return a boolean that returns true when loaded successfully.
     */
    public boolean loadEvent(ArrayList<Task> taskArrayList, String currentCommand) throws ParseException {
        String date = currentCommand.substring(currentCommand.indexOf('(') + 5,
                currentCommand.indexOf(')'));
        DateFormat formatter = new SimpleDateFormat("dd/MM/yy h:mm a");
        Date dueDate = (Date) formatter.parse(date);
        Event event;
        Task.Priority priority;
        int indexOfPriority = currentCommand.indexOf("$");
        String priorityInput = currentCommand.substring(indexOfPriority);
        priority = parsePriority(priorityInput);

        if (currentCommand.charAt(4) == 'X') {
            event = new Event(currentCommand.substring(6,
                    currentCommand.indexOf('(') - 1), true, dueDate, priority);
        } else {
            event = new Event(currentCommand.substring(6,
                    currentCommand.indexOf('(') - 1), dueDate, priority);
        }
        taskArrayList.add(event);
        return true;
    }

    private Task.Priority parsePriority(String command) {
        if (command.equals("$HIGH")) {
            return Task.Priority.HIGH;
        } else if (command.equals("$MEDIUM")) {
            return Task.Priority.MEDIUM;
        } else if (command.equals("$LOW")) {
            return Task.Priority.LOW;
        }
        return Task.Priority.HIGH;
    }

}
