package bobby;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage deals with loading tasks from file and saving tasks in the file.
 */
public class Storage {
    private File file;

    /**
     * Constructor to create a Storage instance.
     *
     * @param filePath file path where the .txt file is loaded from or created.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    private ArrayList<Task> loadFile(File file, ArrayList<Task> taskArray) throws FileNotFoundException {
        Scanner s = new Scanner(file);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] inputs = line.split(" ; ");
            switch (inputs[0]) {
            case "T":
                Todo newToDo = new Todo(inputs[2]);
                if (inputs[1].equals("true")) {
                    newToDo.markAsDone();
                }
                taskArray.add(newToDo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(inputs[2], LocalDate.parse(inputs[3]));
                if (inputs[1].equals("true")) {
                    newDeadline.markAsDone();
                }
                taskArray.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(inputs[2], inputs[3]);
                if (inputs[1].equals("true")) {
                    newEvent.markAsDone();
                }
                taskArray.add(newEvent);
                break;
            default:
                assert false;
            }
        }
        return taskArray;
    }

    /**
     * Loads file from given file path if file exists. Creates an empty file
     * if file does not exist.
     *
     * @return ArrayList of tasks loaded from file.
     */
    public ArrayList<Task> createTaskArray() {
        ArrayList<Task> taskArray = new ArrayList<Task>();
        try {
            if (!file.createNewFile()) {
                loadFile(file, taskArray);
            }
            assert file.exists() : "File not found error.";
        } catch (IOException e) {
            System.out.println("File loading error");
            assert false;
        }
        return taskArray;
    }

    /**
     * Writes into the file with current list of tasks.
     *
     * @param taskArray ArrayList of current tasks to be written into file.
     */
    public void updateFile(ArrayList<Task> taskArray) {
        assert file.exists() : "file does not exist";
        try {
            FileWriter fw = new FileWriter("bobby.txt");
            for (int i = 0; i < taskArray.size(); i++) {
                Task t = taskArray.get(i);
                t.writeToFile(fw);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error with updating file's contents");
        }
    }
}
