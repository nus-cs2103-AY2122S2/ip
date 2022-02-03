package duke.storage;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.data.task.Event;
import duke.data.task.Deadline;

public class Storage {
    
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks from the hard disk when Duke starts up.
     * 
     * @throws IOException TODO
     */
    public List<Task> load() throws IOException {
        File f = new File(filePath);
        // create the folder if it does not exist
        if (f.getParentFile().exists() == false) {
            f.getParentFile().mkdir();
        }
        // create the file if it does not exist
        if (f.exists() == false) {
            f.createNewFile();
        }

        Scanner scanner = new Scanner(f);
        Task t;
        String line;
        String[] splitted;
        List<Task> tasks = new ArrayList<Task>();

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            splitted = line.split(Pattern.quote(" | "));

            if (splitted.length == 4 && splitted[0].equals("D")) {
                // deadline
                t = new Deadline(splitted[2], this.strToDate(splitted[3]));    
            } else if (splitted.length == 4 && splitted[0].equals("E")) {
                // event
                t = new Event(splitted[2], this.strToDate(splitted[3]));
            } else if (splitted.length == 3 && splitted[0].equals("T")) {
                // todo
                t = new Todo(splitted[2]);
            } else {
                // TODO: handle exceptions
                return tasks;
            }

            // marks the task as done if the second parameter is 1
            if (splitted[1].equals("1")) {
                t.markAsDone();
            }

            tasks.add(t);
        }
        return tasks;
    }

    /**
     * Stores the list of tasks in hard disk.
     */
    public void store(List<Task> lst) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String data = "";
        for (int i = 0; i < lst.size(); i++) {
            data += lst.get(i).toStoreInfo();
        }
        fw.write(data);        
        fw.close();
    }
    
    private LocalDate strToDate(String str) {
        return LocalDate.parse(str);
    }
}
