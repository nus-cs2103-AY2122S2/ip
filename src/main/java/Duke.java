import java.io.*;
import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    static TaskManager manager = new TaskManager();
    public static void main(String[] args) {

        UI.showBanner();
        UI.showStarting();
        load();

        Scanner s = new Scanner(System.in);
        String input;

        while (true) {
            input = s.nextLine();

            if (input.equals("bye")) {
                save();
                UI.showBye();
                return;
            } else if (input.equals("list")) {
                UI.showList(manager);
            } else if (input.startsWith("unmark") || input.startsWith("mark")) {
                marking(input);
            } else if (input.startsWith("delete")) {
                delete(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
                manager.addTask(input);
            } else {
                UI.showHelpMenu();
            }
        }
    }

    public static void delete(String input){
        try {
            int index = Integer.parseInt(input.replaceFirst("delete", "").strip());
            manager.deleteTask(index);
        } catch (NumberFormatException n){
            UI.showInvalidIntegerError();
        }
    }

    public static void marking(String input){

        if (input.startsWith("mark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("mark", "").strip());
                manager.markTaskDone(index);
            } catch (NumberFormatException n){
                UI.showInvalidIntegerError();
            }
        } else if (input.startsWith("unmark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("unmark", "").strip());
                manager.markTaskUndone(index);
            } catch (NumberFormatException n){
                UI.showInvalidIntegerError();
            }
        }


    }

    
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
        UI.showSavingComplete();
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
                        UI.showTaskLoadFail(input);
                    }
                }
                UI.showLoadingComplete();
                UI.showList(manager);
            } catch (IOException e){
                UI.showFileReadError();
                loadDefault();
            }
        } else {
            UI.showFileNotFound();
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
            UI.showDirNotFound();
            UI.showDirCreating(DIR_PATH);
            boolean createFile = dir.mkdir();
            if (createFile){
                UI.showDirCreated();
            }
        }
    }

    public static void test() {
        Task.parse("D\tX\tabcd\tdasd");
    }

    public static void parseDateTime(String s){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

        LocalDateTime dateTime = LocalDateTime.parse(s,format);
        System.out.println(dateTime.format(formatted));

    }
}
