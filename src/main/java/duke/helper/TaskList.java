package duke.helper;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {
//    private static String filePath = "/data/tasks.txt";
//    private static String directoryPath = "/data";

    private ArrayList<Task> taskList = new ArrayList<>();


    public TaskList(){
        this.taskList = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }


//    public static void initFile(){
//        try {
//            File file = new File(System.getProperty("user.dir") + filePath);
//            File dir = new File(System.getProperty("user.dir") + directoryPath);
//            dir.mkdir();
//            file.createNewFile();
//        } catch (IOException e) {
//            System.out.println("error initiating file");
//        }
//    }

    public Task get(int i){
        return taskList.get(i);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
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

//    public void save() {
//        try{
//            FileWriter fw = new FileWriter(System.getProperty("user.dir") + filePath);
//            for(duke.tasks.Task task : taskList){
//                fw.write(task.getDetail());
//            }
//            fw.close();
//            System.out.println("saving in progress");
//        } catch(IOException e) {
////            System.out.println("saving failed");
//            System.out.println(e.getMessage());
//        }
//    }

//    public duke.tasks.Task parse(String nextLine) {
//            String[] strArr = nextLine.split(" \\| ");
//            String[] subStrArr;
//            String command = strArr[0];
//            boolean isDone = strArr[1].equals("1") ? true : false;
//            switch (command) {
//                case "T":
//                    return new duke.tasks.Todo(strArr[2], isDone);
//                case "D":
//                    return new duke.tasks.Deadline(strArr[2], strArr[3], isDone);
//                case "E":
//                    return new duke.tasks.Event(strArr[2], strArr[3], isDone);
//                default:
//                    return null;
//            }
//    }

//    public void load(){
//        try {
//            File f = new File(System.getProperty("user.dir") + filePath);
//            Scanner sc = new Scanner(f);
//            String nextLine;
//            while (sc.hasNextLine()) {
//                nextLine = sc.nextLine();
//                duke.tasks.Task task = parse(nextLine);
//                taskList.add(task);
//            }
//        } catch (FileNotFoundException e){
//                System.out.println(e.getMessage());
//        }
//    }
}
