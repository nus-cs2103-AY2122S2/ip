package duke.functionality;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Represents the Storage capabilities of the Duke project. A <code> Storage </code> object corresponds
 * to the actions available to write and read data/task.
 */
public class Storage {
    protected static String pwd;
    protected static String path;
    private static final int FIRST_INPUT = 0;
    private static final int SECOND_INPUT = 1;
    private static final int THIRD_INPUT = 2;
    private static final int FOURTH_INPUT = 3;
    private static final int FIFTH_INPUT = 4;
    private static final int SIXTH_INPUT = 5;

    /**
     * Constructor for Storage class.
     * @param pwd user's current working directory.
     * @param path path to "/data/TaskData.txt".
     */
    public Storage(String pwd, String path) {
        Storage.pwd = pwd;
        Storage.path = path;
    }


    /**
     * Returns nothing, but stores all task into the text file specified by path.
     * @param path path to "/data/TaskData.txt".
     * @param taskList the tasklist to be written.
     * @throws IOException if the text file is missing.
     */
    public static void writeToFile(String path, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < taskList.getListSize(); i++) {
            Task task = taskList.getTask(i);
            fw.write(craftOutput(task));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns nothing, but calls writeToFile to update the text file.
     */
    public static void updateTextFile(TaskList taskList) {
        try {
            writeToFile(Storage.pwd + Storage.path, taskList);
        } catch (IOException e) {
            System.out.println("Something happened to the text file !" + e.getMessage());
        }
    }

    /**
     * Returns a new Tasklist object that is the saved taskList which consist of all the data read
     * from the file provided.
     * @param file the text file that stores all tasks.
     * @return a TaskList object representing the saved taskList.
     * @throws FileNotFoundException if the file is missing.
     */
    public TaskList readFileDataAndStoreInList(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        TaskList newTaskList = new TaskList();
        while ((sc.hasNextLine())) {
            String input = sc.nextLine();
            String[] inputSplit = input.split("\\|");
            String task = inputSplit[FIRST_INPUT];
            int mark = Integer.parseInt(inputSplit[SECOND_INPUT]);
            if (task.equals("T")) {
                Todo tempTask = new Todo(inputSplit[THIRD_INPUT]);
                newTaskList.addToList(checkTaskDone(mark, tempTask));

            } else if (task.equals("D")) {
                Deadline tempTask = new Deadline(inputSplit[THIRD_INPUT], Parser.formatDate(inputSplit[FOURTH_INPUT]),
                        Parser.formatTime(inputSplit[FIFTH_INPUT]));
                newTaskList.addToList(checkTaskDone(mark, tempTask));

            } else if (task.equals("E")) {
                Event tempTask = new Event(inputSplit[THIRD_INPUT], Parser.formatDate(inputSplit[FOURTH_INPUT]) ,
                        Parser.formatTime(inputSplit[FIFTH_INPUT]), Parser.formatTime(inputSplit[SIXTH_INPUT]));
                newTaskList.addToList(checkTaskDone(mark, tempTask));
            }
        }
        return newTaskList;
    }

    /**
     * Returns nothing, but used to check if a Task is done or not.
     * @param mark indicator, 1 is done 0 is not done.
     * @param task the Task object to be checked.
     */
    public Task checkTaskDone(int mark, Task task) {
        if(mark == 1){
            task.setTaskDone();
        }
        return task;
    }

    /**
     * Returns a crafted output to be stored in the text file.
     * @param task the task created in Parser class.
     * @return crafted output.
     */
    public static String craftOutput(Task task) {
        String output = "";
        String doneIcon = task.getStatusIcon();
        if (task instanceof Todo) {
            if (doneIcon.equals("X")) {
                output = "T|1|" + task.getDescription();
            } else {
                output = "T|0|" + task.getDescription();
            }
        } else if (task instanceof Deadline) {
            if (doneIcon.equals("X")) {
                output = "D|1|" + task.getDescription() + "|" + Parser.dateToString(((Deadline) task).getDate())
                        + "|" + Parser.timeToString(((Deadline) task).getTime());
            } else {
                output = "D|0|" + task.getDescription() + "|" + Parser.dateToString(((Deadline) task).getDate())
                        + "|" + Parser.timeToString(((Deadline) task).getTime());
            }
        } else if (task instanceof Event) {
            if (doneIcon.equals("X")) {
                output = "E|1|" + task.getDescription() + "|" + Parser.dateToString(((Event) task).getDate())
                        + "|" + Parser.timeToString(((Event) task).getStartTime())
                        + "|" + Parser.timeToString(((Event) task).getEndTime());
            } else {
                output = "E|0|" + task.getDescription() + "|" + Parser.dateToString(((Event) task).getDate())
                        + "|" + Parser.timeToString(((Event) task).getStartTime())
                        + "|" + Parser.timeToString(((Event) task).getEndTime());
            }
        }
        return output;
    }

    /**
     * Returns a TaskList object after loading all tasks from the specified path.
     * @return a TaskList object representing the saved taskList.
     * @throws IOException if the file or directory is missing.
     */
    public TaskList load() throws IOException {
        File directory = new File(pwd + "/data");
        File inputFile = new File(pwd + path);
        if (directory.mkdir() && inputFile.createNewFile()) {
            throw new IOException("Do not worry! I will create the directory & file for you");

        } else if (inputFile.createNewFile()) {
            throw new IOException("Do not worry! I will create the file for you");
        }

        return readFileDataAndStoreInList(inputFile);
    }
}
