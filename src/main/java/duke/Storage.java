package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class handles loading and saving the list of tasks
 * at the beginning and end of execution.
 */
public class Storage {
    /**
     * The filepath of= the text file that contains the task list.
     */
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the task list from the stored file.
     *
     * @return the tasks in a ArrayList of Task objects
     * @throws IOException if the file doesn't exist
     */
    ArrayList<Task> load() throws IOException, DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File data = new File("prince.txt");
        if (!data.createNewFile()) {
            Scanner s = new Scanner(data);
            while (s.hasNext()) {
                //read tasks and add to arraylist
                String line = s.nextLine();
                Task t = new Parser(line).parse();
                if (line.substring(4, 5).equals("X")) {
                    t.makeDone();
                }
                list.add(t);
            }
        }
        return list;
    }

    boolean store(TaskList list) throws IOException {
        FileWriter fw = new FileWriter("prince.txt");
        for (Integer i = 0; i < list.size(); i++) {
            fw.write(list.get(i).toString());
            fw.write(System.lineSeparator());
        }
        fw.close();
        return true;
    }

}
