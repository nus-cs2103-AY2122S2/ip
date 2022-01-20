import java.io.*;
import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Duke {
    static TaskManager manager = new TaskManager();
    private static final String line = "-------------------------------------------------------------------------------------";
    public static void main(String[] args) {

        intro();
        load();
        Scanner s = new Scanner(System.in);
        String input;

        while (true) {
            input = s.nextLine();

            if (input.equals("bye")) {
                bye();
                return;
            } else if (input.equals("list")) {
                list();
            } else if (input.startsWith("unmark") || input.startsWith("mark")) {
                marking(input);
            } else if (input.startsWith("delete")) {
                delete(input);
            } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")){
                add(input);
            } else {
                invalid();
            }
        }
    }
    public static void add(String input){
        System.out.println(line);
        manager.addTask(input);
        System.out.println(line);
    }
    public static void delete(String input){
        System.out.println(line);
        try {
            int index = Integer.parseInt(input.replaceFirst("delete", "").strip());
            manager.deleteTask(index);
        } catch (NumberFormatException n){
            System.out.println("Invalid number entered! Please enter an integer");
        }
        System.out.println(line);
    }
    public static void list(){
        System.out.println(manager.printTaskList());
    }
    public static void marking(String input){

        if (input.startsWith("mark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("mark", "").strip());
                manager.markTaskDone(index);
            } catch (NumberFormatException n){
                System.out.println("Invalid number entered! Please enter an integer");
            }
        } else if (input.startsWith("unmark")){
            try {
                int index = Integer.parseInt(input.replaceFirst("unmark", "").strip());
                manager.markTaskUndone(index);
            } catch (NumberFormatException n){
                System.out.println("Invalid number entered! Please enter an integer");
            }
        }


    }
    public static void bye(){
        save();
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }
    public static void intro(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
        System.out.println(line);
    }
    public static void invalid(){
        String invalidText =    "Invalid Entry!\n" +
                                "List of available commands:\n\n" +
                                "\"list\": View Current Task List\n" +
                                "\"todo <name>\": Add a new Task\n" +
                                "\"deadline <name> /by <date/time>\": Add a new Deadline\n" +
                                "\"event <name> /at <date/time>\": Add a new Event\n" +
                                "\"delete <number>\": delete the corresponding entry in your task list\n" +
                                "\"mark <number>\": mark the corresponding entry in your task list as done\n" +
                                "\"unmark <number>\": mark the corresponding entry in your task list as not done\n" +
                                "\"bye\": Exit the chatbot application";

        System.out.println(line);
        System.out.println(invalidText);
        System.out.println(line);
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
        System.out.println("File Saved!");
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
                        System.out.println("Task not loaded. Details: " + input);
                    }
                }
                System.out.println("Loading Completed!");
                list();
            } catch (IOException e){
                System.out.println("Error reading from file! Initializing defaults...");
                loadDefault();
                manager = new TaskManager();
            }
        } else {
            System.out.println("File not found! Loading defaults...");
            loadDefault();
            manager = new TaskManager();
        }
    }

    public static void loadDefault(){

        String FILE_PATH = System.getProperty("user.home");
        String DIR_PATH = FILE_PATH + "/Duke";
        FILE_PATH += "/Duke/data";

        File dir = new File(DIR_PATH);
        File f = new File(FILE_PATH);

        if (!dir.exists()){
            System.out.println("Directory not found! Creating /data/...");
            boolean createFile = dir.mkdir();
            if (createFile){
                System.out.println("Directory created. Starting application...");
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
