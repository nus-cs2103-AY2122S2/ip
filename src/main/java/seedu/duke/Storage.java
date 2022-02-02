package seedu.duke;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Storage {
    
    private String filepath;
    
    Storage(String filepath) {
        this.filepath = filepath;
    }
    
    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(this.filepath);
            Scanner sc = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<Task>();
            
            while(sc.hasNext()) {
                tasks.add(Parser.getTask(sc.nextLine()));
            }
            
            sc.close();
            
            return tasks;

        } catch (FileNotFoundException e) {

            throw new DukeException("Problem loading data");

        }
    }

    public void save(TaskList tasks) {

        String saveFormat = "";

        for (Task t : tasks.getTasks()) {
            if (t instanceof ToDo) {
                saveFormat += "T#";
                saveFormat += t.isDone ? "true" : "false";
                saveFormat += "#" + t.description;
                continue;
            } else if (t instanceof Deadline) {
                saveFormat += "D";
            } else if (t instanceof Event) {
                saveFormat += "E";
            }
            saveFormat += "#";

            saveFormat += t.isDone ? "true" : "false";
            saveFormat += "#";

            saveFormat += t.description;
            saveFormat += "#";

            saveFormat += t.time.get();
            saveFormat += "\n";
        }

        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(saveFormat);
            fw.close();
        } catch (Exception e) {
            System.out.println("Error while saving file.\n" + e);
        }
    }
    
}
