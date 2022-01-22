import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH = System.getProperty("user.home") + "Duke/data";
    private final String DIR_PATH = System.getProperty("user.home") + "/Duke";
    File file;

    public Storage(){

    }

    public TaskManager loadTaskManagerFromFile() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        file = new File(FILE_PATH);
        try {
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while(scanner.hasNext()){
                    String fileInput = scanner.nextLine();
                    try {
                        Task t = Parser.parseToTaskFromFile(fileInput);
                        tasks.add(t);
                    } catch (FailedTaskParseException exception) {
                        // UI.showError??
                        continue;
                    }
                }
                return new TaskManager(tasks);
            } else {
                return null;
            }
        } catch (SecurityException | IOException exception) {
            return null;
        }
    }
    public boolean saveTaskManager(TaskManager taskManager) {
        file = new File(FILE_PATH);
        try {
            FileWriter writer = new FileWriter(file);

            for ( Task task: taskManager.getTaskList()) {
                String date ="None";
                if (task.getDate() != null) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                    date = task.getDate().format(format).toString();
                }
                writer.write(String.format("%c\t%c\t%s\t%s\n",task.getType(),task.getDone(),task.getTaskName(),date));
            }

            writer.close();
            return true;
        } catch (IOException exception){
            return false;
        }
    }
}





/**

 public static void save(){
 String FILE_PATH = System.getProperty("user.home");
 FILE_PATH += "/Duke/data";

 File f = new File(FILE_PATH);
 try {
 FileWriter fw = new FileWriter(f);
 for (Task t : manager.getTaskList()) {
 String date ="None";
 if (t.getDate() != null) {
 DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
 date = t.getDate().format(format).toString();
 }
 fw.write(String.format("%c\t%c\t%s\t%s\n",t.getType(),t.getDone(),t.getTaskName(),date));
 }
 fw.close();
 } catch (IOException e){
 e.printStackTrace();
 }
 Ui.showSavingComplete();
 }
 public static void load(){
 String FILE_PATH = System.getProperty("user.home");
 FILE_PATH += "/Duke/data";

 File f = new File(FILE_PATH);
 if (f.exists()){
 try{
 Scanner s = new Scanner(f);
 while(s.hasNext()){
 String input = s.nextLine();
 Task t = Task.parse(input);
 if (t != null){ manager.addTask(t); }
 else {
 Ui.showTaskLoadFail(input);
 }
 }
 Ui.showLoadingComplete();
 Ui.showList(manager);
 } catch (IOException e){
 Ui.showFileReadError();
 loadDefault();
 }
 } else {
 Ui.showFileNotFound();
 loadDefault();
 }
 }

 public static void loadDefault(){

 String FILE_PATH = System.getProperty("user.home");
 String DIR_PATH = FILE_PATH + "/Duke";
 FILE_PATH += "/Duke/data";

 File dir = new File(DIR_PATH);
 File f = new File(FILE_PATH);

 manager = new TaskManager();

 if (!dir.exists()){
 Ui.showDirNotFound();
 Ui.showDirCreating(DIR_PATH);
 boolean createFile = dir.mkdir();
 if (createFile){
 Ui.showDirCreated();
 }
 }
 }

 */