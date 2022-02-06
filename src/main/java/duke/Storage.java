package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * In charge of reading to file on start up and writing to file on update
 */
public class Storage {

    /**
     * Converts a txt file into a TaskList object
     *
     * @return TaskList object that contains all tasks saved in the hard disk.
     * @throws DukeException
     * @throws IOException
     */
    static TaskList readFile() throws DukeException, IOException {

        File directory = new File("data/");
        if (!directory.exists()){
            directory.mkdir();
        }

        File f = new File("data/duke.txt");

        if (!f.exists()) {
            if (!f.createNewFile()) {
                throw new DukeException("Data file could not be created");
            }
        }
        Scanner sc = new Scanner(f);
        TaskList listOfSavedTasks = new TaskList();

        while (sc.hasNextLine()) {
            String currLine = sc.nextLine();
            addLineToTasks(currLine, listOfSavedTasks);
        }
        return listOfSavedTasks;
    }

    private static void addLineToTasks(String currLine, TaskList listOfSavedTasks) {
        assert currLine.length() > 0 : "Empty line detected";
        String[] currLineContents = currLine.split(" ~ ");
        String taskType = currLineContents[0];
        boolean isDone = currLineContents[1].equals("X") ? true : false ;
        String description = currLineContents[2];
        
        if (taskType.equals("T")) {
            listOfSavedTasks.add(new ToDo(description, isDone));
        } else if (taskType.equals("D")) {
            listOfSavedTasks.add(new Deadline(description, isDone, currLineContents[3]));
        } else if (taskType.equals("E")) {
            listOfSavedTasks.add(new Event(description, isDone, currLineContents[3]));
        }
    }

    /**
     * Converts the given TaskList object and saves it onto the hard disk as a txt file
     *
     * @param tasks TaskList Object to be written to the hard disk
     * @throws IOException
     */
    static void saveToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", false);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String currTask = String.format("%s ~ %s ~ %s ~ %s\n",
                    task.getTaskType(),
                    task.getStatusIcon(),
                    task.getDescription(),
                    task.getDate());
            fw.write(currTask);
        }
        fw.close();
    }
}
