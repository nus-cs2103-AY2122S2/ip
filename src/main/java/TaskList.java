import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static String filePath = "/data/tasks.txt";
    private static String directoryPath = "/data";

    private ArrayList<Task> taskList = new ArrayList<>();


    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public static void initFile(){
        try {
            File file = new File(System.getProperty("user.dir") + filePath);
            File dir = new File(System.getProperty("user.dir") + directoryPath);
            dir.mkdir();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("error initiating file");
        }
    }

    public Task get(int i){
        return taskList.get(i);
    }

    public void insert(Task task){
        taskList.add(task);
    }

    public void delete(int i){
        taskList.remove(i);
    }

    public int size(){
        return taskList.size();
    }

    public void save() {
        try{
            FileWriter fw = new FileWriter(System.getProperty("user.dir") + filePath);
            for(Task task : taskList){
                fw.write(task.getDetail());
                fw.close();
            }
            System.out.println("saving in progress");
        } catch(IOException e) {
//            System.out.println("saving failed");
            System.out.println(e.getMessage());
        }
    }
}
