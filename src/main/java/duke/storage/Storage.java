package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import duke.tasklist.TaskList;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class
 */
public class Storage {

    private String storedFilePath;

    /**
     * Constructor for storage object
     * @param filePath filepath of where data will be stored
     */
    public Storage(String filePath) {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdir();
            try {
                File saveTask = new File(filePath);
                saveTask.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (directory.exists()) {
            storedFilePath = filePath;
        }
        this.storedFilePath = filePath;
    }

    /**
     * modify contents of data
     * @param taskList list of tasks be saved
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(storedFilePath);
            int counter = 1;
            for (int i = 0; i < taskList.getSize(); i++) {
                try {
                    fw.write(counter + "." + taskList.getTask(i) + "\n");
                    counter++;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loading data in file to program
     * @return data in file
     * @throws DukeException error occurred
     */
    public ArrayList<Task> loadFile() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            FileInputStream readFile = new FileInputStream(storedFilePath);
            Scanner sc = new Scanner(readFile);
            diskParser(sc, taskList);
            sc.close();
        } catch (FileNotFoundException e) {
            try {
                File saveTask = new File(storedFilePath);
                saveTask.createNewFile();
            } catch (IOException err) {
                e.printStackTrace();
            }
        }
        return taskList;
    }

    /**
     * parse the text file to load the content
     * @param scanner scanner object to scan text file
     * @param taskList task list to add to current task list object
     */
    public void diskParser(Scanner scanner, ArrayList<Task> taskList) {
        while (scanner.hasNextLine()) {
            String currTask = scanner.nextLine();
            char taskType = currTask.charAt(3);
            boolean isDone = false;
            if (currTask.charAt(6) == 'X') {
                isDone = true;
            }

            if (taskType == 'T') {
                char priorityLevel = currTask.charAt(9);
                ToDo freshTodo = new ToDo(currTask.substring(12), Character.toString(priorityLevel));
                if (isDone) {
                    freshTodo.setDone(true);
                }
                taskList.add(freshTodo);
            } else if (taskType == 'D') {
                char priorityLevel = currTask.charAt(9);
                String[] splicedString = currTask.split(" \\(by: ");
                String description = splicedString[0].substring(12);
                String date = splicedString[1].substring(0, splicedString[1].length() - 1);
                Deadline freshDeadline = new Deadline(description, date, Character.toString(priorityLevel),true);
                if (isDone) {
                    freshDeadline.setDone(true);
                }
                taskList.add(freshDeadline);
            } else {
                char priorityLevel = currTask.charAt(9);
                String[] splicedString = currTask.split(" \\(at: ");
                String description = splicedString[0].substring(12);
                String date = splicedString[1].substring(0, splicedString[1].length() - 1);
                Event freshEvent = new Event(description, date, Character.toString(priorityLevel),true);
                if (isDone) {
                    freshEvent.setDone(true);
                }
                taskList.add(freshEvent);
            }
        }
    }
}
