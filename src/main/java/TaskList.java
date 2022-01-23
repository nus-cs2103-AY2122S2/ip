import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    List<Task> tasks = new ArrayList<>();
    public TaskList(File f) throws FileNotFoundException, DukeException {
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String str = s.nextLine();
                String[] strings = str.split(",");
                Task task = null;
                switch (strings[0]) {
                    case "E" :
                        task = new Event(str, true);
                        tasks.add(task);
                        break;
                    case "D" :
                        task = new Deadline(str.substring(8));
                        tasks.add(task);
                        break;
                    case "T" :
                        task = new Todo(str.substring(8));
                        tasks.add(task);
                        break;
                }
                if (str.charAt(5) == 'X'){
                    assert task != null;
                    task.setDone();
                }
            }
            s.close();
        } catch (DukeException e){
            System.out.println("yo");
            //tasks = new ArrayList<Task>();
        }
    }
    public List<Task> getTasks(){
        return this.tasks;
    }
}
