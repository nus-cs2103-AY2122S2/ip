import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
            }
            fw.close();
            System.out.println("saving in progress");
        } catch(IOException e) {
//            System.out.println("saving failed");
            System.out.println(e.getMessage());
        }
    }

    public Task parse(String nextLine) {
            String[] strArr = nextLine.split(" \\| ");
            String[] subStrArr;
            String command = strArr[0];
            boolean isDone = strArr[1].equals("1") ? true : false;
            switch (command) {
                case "T":
                    return new Todo(strArr[2], isDone);
                case "D":
                    return new Deadline(strArr[2], strArr[3], isDone);
                case "E":
                    return new Event(strArr[2], strArr[3], isDone);
                default:
                    return null;
            }
    }

    public void load(){
        try {
            File f = new File(System.getProperty("user.dir") + filePath);
            Scanner sc = new Scanner(f);
            String nextLine;
            while (sc.hasNextLine()) {
                nextLine = sc.nextLine();
                Task task = parse(nextLine);
                taskList.add(task);
            }
        } catch (FileNotFoundException e){
                System.out.println(e.getMessage());
        }
    }
}
