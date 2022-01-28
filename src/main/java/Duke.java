import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private TaskManager manager;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath){
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            manager = storage.loadTaskManagerFromFile();
            ui.showLoadingComplete();
        } catch (DukeException e) {
            ui.showFileReadError();
            manager = new TaskManager();
        }
    }

    public void run(){
        ui.showBanner();
        ui.showList(manager);

        boolean isExit = false;

        while(!isExit){
            try {
                String userInput = ui.getUserInputLine();

                Command command = Parser.parse(userInput);
                command.execute(storage,ui,manager);

                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showMessage(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {

        new Duke("Duke/data").run();
    }
/*
    public static void delete(String input){
        try {
            int index = Integer.parseInt(input.replaceFirst("delete", "").strip());
            manager.deleteTask(index);
        } catch (NumberFormatException n){
            //Ui.showInvalidIntegerError();
        }
    }
/*
    public static void marking(String input){

        if (input.startsWith("mark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("mark", "").strip());
                manager.markTaskDone(index);
            } catch (NumberFormatException n){
                //Ui.showInvalidIntegerError();
            }
        } else if (input.startsWith("unmark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("unmark", "").strip());
                manager.markTaskUndone(index);
            } catch (NumberFormatException n){
                //Ui.showInvalidIntegerError();
            }
        }


    }

 /*
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
        //Ui.showSavingComplete();
    }
    /*
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
                       // Ui.showTaskLoadFail(input);
                    }
                }
                //Ui.showLoadingComplete();
               // Ui.showList(manager);
            } catch (IOException e){
                //Ui.showFileReadError();
                loadDefault();
            }
        } else {
            //Ui.showFileNotFound();
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
            //Ui.showDirNotFound();
            //Ui.showDirCreating(DIR_PATH);
            boolean createFile = dir.mkdir();
            if (createFile){
                //Ui.showDirCreated();
            }
        }
    }
*/

}
