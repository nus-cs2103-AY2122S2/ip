package michael;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import michael.filemanagement.CreateFile;
import michael.filemanagement.WriteFile;
import michael.tasks.Deadline;
import michael.tasks.Event;
import michael.tasks.Task;
import michael.tasks.ToDo;


/**
 * Storage Class handles saving and loading of files to th program.
 *
 * @author Justin Ng Jie Ern
 */
public class Storage {
    /**
     * TaskList object that handles Tasks .
     */
    private TaskList taskList;

    /**
     * Parser object to help parse the file into the program.
     */
    private Parser parser;

    /**
     * Constructor to create a Storage Object
     *
     * @param taskList TaskList object that handles Tasks.
     */
    public Storage(TaskList taskList) {
        this.parser = new Parser();
        this.taskList = taskList;
        CreateFile file = new CreateFile();
        boolean isCreated = file.createFile();
        if (!isCreated) {
            load(file.getFileName());
        }
    }

    /**
     * Method to save the TaskList into a file in the local device.
     *
     * @return Save string to be used for Michael DialogBox.
     */
    public String save() {
        writeTasksToFile();
        System.out.println("Your Tasks has been saved into your device!");
        return "Your Tasks has been saved into your device!";
    }

    /**
     * Helper Method to save Tasks in TaskList into a file in the local device.
     */
    public void writeTasksToFile() {
        WriteFile writeFile = new WriteFile();
        writeFile.clearFile();
        int leng = taskList.getTasks().toArray().length;
        for (int i = 0; i < leng; i++) {
            Task task = taskList.getTasks().get(i);
            int num = i + 1;
            writeFile.writeToFile(num + ": " + task.toString() + System.lineSeparator());
        }
    }

    /**
     * Loads Instructions stored in Text file to the program.
     *
     * @param command Which type of task.
     * @param taskName Name of the task stored in text file.
     * @param isMarkedBool Boolean whether the task is marked or not.
     */
    public void loadInstruction(String command, String taskName, boolean isMarkedBool) {
        if ("T".equals(command)) {
            ToDo task = new ToDo(taskName, isMarkedBool, "T");
            taskList.add(task);
        } else if ("D".equals(command)) {
            String[] detailsArr = taskName.split(" \\(by: ");
            String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
            String detailsFormat = parser.dateFormatHelper(detail);
            Deadline task = new Deadline(detailsArr[0], isMarkedBool, "D", detailsFormat);
            taskList.add(task);
        } else if ("E".equals(command)) {
            String[] detailsArr = taskName.split(" \\(at: ");
            String detail = detailsArr[1].substring(0, detailsArr[1].length() - 1);
            String detailsFormat = parser.dateFormatHelper(detail);
            Event task = new Event(detailsArr[0], isMarkedBool, "E", detailsFormat);
            taskList.add(task);
        } else {
            System.out.println("Should not be in the else block for load function");
        }
    }

    /**
     * Method to load previously saved file in the local device onto the program.
     *
     * @param fileName File "michael.txt" from the local device
     */
    public void load(String fileName) {
        String command = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while ((command = reader.readLine()) != null) {
                // 9: [E][ ] something  (at: Sep 11 2011, 12:12)
                String[] commandArr = command.split("]");
                String cmdTemp = commandArr[0];
                String firstWord = cmdTemp.substring(cmdTemp.length() - 1);
                String taskName = command.substring(10);
                String isMarked = commandArr[1].substring(1, 2);
                boolean isMarkedBool = isMarked.equals("X");
                loadInstruction(firstWord, taskName, isMarkedBool);
            }
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
            e.printStackTrace();
        }
    }

}
