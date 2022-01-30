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
            String[] inputSplit = input.split("\\|"); //split input by |
            String task = inputSplit[0];
            int mark = Integer.parseInt(inputSplit[1]);
            if (task.equals("T")) {
                Todo tempTask = new Todo(inputSplit[2]);
                if (mark == 1) {
                    tempTask.setTaskDone();
                }
                newTaskList.addToList(tempTask);
            } else if (task.equals("D")) {
                Deadline tempTask = new Deadline(inputSplit[2], Parser.formatDate(inputSplit[3]),
                        Parser.formatTime(inputSplit[4]));
                if (mark == 1) {
                    tempTask.setTaskDone();
                }
                newTaskList.addToList(tempTask);
            } else if (task.equals("E")) {
                Event tempTask = new Event(inputSplit[2], Parser.formatDate(inputSplit[3]) ,
                        Parser.formatTime(inputSplit[4]), Parser.formatTime(inputSplit[5]));
                if (mark == 1) {
                    tempTask.setTaskDone();
                }
                newTaskList.addToList(tempTask);
            }
        }
        return newTaskList;
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
