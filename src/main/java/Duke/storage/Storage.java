package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.DukeException;


/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String saveFilePath;

    /**
     * Constructor for Storage.
     *
     * @param saveFilePath the directory path for the save file.
     */
    public Storage(String saveFilePath) {
        this.saveFilePath = saveFilePath;
    }

    /**
     * Reads the existing file. If there is existing file, then add to the ArrayList.
     *
     * @return the the list of tasks.
     * @throws DukeException If files cannot be read or created.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File saveFile = new File(saveFilePath);
            saveFile.getParentFile().mkdir();
            saveFile.createNewFile();
            Scanner scanner = new Scanner(saveFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] currTaskLine = currLine.split("\\|");
                switch (currTaskLine[0]) {
                case "T":
                    ToDo toDoTask = new ToDo(currTaskLine[2]);
                    checkIfTaskMark(currTaskLine[1], toDoTask);
                    if (currTaskLine[1].equals("1")) {
                        toDoTask.setMarked();
                    }
                    taskList.add(toDoTask);
                    break;
                case "D":
                    Deadline deadlineTask = new Deadline(currTaskLine[2], currTaskLine[3]);
                    checkIfTaskMark(currTaskLine[1], deadlineTask);
                    taskList.add(deadlineTask);
                    break;
                case "E":
                    Event eventTask = new Event(currTaskLine[2], currTaskLine[3]);
                    checkIfTaskMark(currTaskLine[1], eventTask);
                    taskList.add(eventTask);
                    break;
                default:
                    assert false; //Execution will never reach this point.
                }
            }
        } catch (IOException e) {
            throw new DukeException("Opps! An error occurred. @.@");
        }
        return taskList;
    }

    /**
     * Checks if the task is 1 and set the task as mark if is 1. Else,
     * nothing is done.
     *
     * @param markNum either 1 or 0. 1 as mark, 0 as not mark.
     * @param task task to be checked.
     */
    private void checkIfTaskMark(String markNum, Task task) {
        if (markNum.equals("1")) {
            task.setMarked();
        }
    }

    /**
     * Writes the task in task list into the file.
     *
     * @param taskList the list of tasks.
     * @throws DukeException if you cannot write into the file.
     */
    public void save(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter writer = new FileWriter(saveFilePath);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.get(i).encodeTaskToString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred. @.@");
        }
    }
}
