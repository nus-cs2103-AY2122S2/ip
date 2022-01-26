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
    
}
