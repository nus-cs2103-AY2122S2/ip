import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    
    //Level 1
    /*
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        System.out.println("");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")){
            System.out.println(hyphenate + "\n    " + command + "\n" + hyphenate);
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }
    */


    //Level 2
    /*
    public static void main(String[] args) {
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandStorage = new String[100];
        int ID = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println(hyphenate);
                for (int i=0; i<ID; i++){
                    System.out.println("    " + (i+1) + ". " + commandStorage[i]);
                }
                System.out.println(hyphenate);
            }else{
                commandStorage[ID] = command; 
                System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
                ID++;
            }
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }*/
    
    //Level 3
    /*
    public static void main(String[] args) {
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandStorage = new String[100];
        boolean[] commandMark = new boolean[100];
        int ID = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println(hyphenate);
                for (int i=0; i<ID; i++){
                    System.out.println("    " + (i+1) + "." + "[" + (commandMark[i]==true?"X":" ") + "]" + " " + commandStorage[i]);
                }
                System.out.println(hyphenate);
            } else if (command.split(" ")[0].equals("mark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                commandMark[markIndex] = true;
                System.out.println(hyphenate);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] " + commandStorage[markIndex]);
                System.out.println(hyphenate);
            } else if (command.split(" ")[0].equals("unmark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                commandMark[markIndex] = false;
                System.out.println(hyphenate);
                System.out.println("    Okay, I've marked this task as not done yet:");
                System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] " + commandStorage[markIndex]);
                System.out.println(hyphenate);
            } else {
                commandStorage[ID] = command; 
                System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
                ID++;
            }
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }
    */

    //Level 4, 5, 6, UiTesting
    //Abstract class Task that Todos, Events, and deadlines inherit from.
    abstract class Task{
        String name;    //Name of task
        boolean completed;  //Completion of task
        
        //Constructor for Task
        public Task(String taskName){
            this.name = taskName;
            this.completed = false;
        }

        public Task(String taskName, boolean completion){
            this.name = taskName;
            this.completed = completion;
        }

        //Completion of task
        void completeTask(){
            this.completed = true;
        }

        //Uncomplete the task
        void uncompleteTask(){
            this.completed = false;
        }

        //Get timing of task - overriden in todos and events
        String getTiming(){
            return "";
        }

        //Get name of task
        String getName(){
            return this.name;
        }

        //Get completion status of the task
        Boolean getCompletion(){
            return this.completed;
        }

        //toString return tasks
        public String toString(){
            return "";
        }
    }

    //A variant of task
    class Todos extends Task{
        
        //Constructor of Todos
        public Todos(String taskName){
            super(taskName);
        }

        public Todos(String taskName, boolean completion){
            super(taskName, completion);
        }

        //Save to database format
        public String toDatabaseString(){
            return "T | " + (this.getCompletion()==true?"X":" ") + " | " + super.getName() + "\n";
        }

        //toString returning Todos
        public String toString(){
            return "[T][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName();
        }
    }

    //A variant of task 
    class Deadlines extends Task{
        String deadline;    //Deadline to complete deadline task

        //Constructor
        public Deadlines(String taskName, String deadline){
            super(taskName);
            this.deadline = deadline;
        }

        public Deadlines(String taskName, boolean completion, String deadline){
            super(taskName, completion);
            this.deadline = deadline;
        }

        //Get deadline of task
        String getTiming(){
            return "(by: " + deadline + ")";
        }

        //Save to database format
        public String toDatabaseString(){
            return "D | " + (this.getCompletion()==true?"X":" ") + " | " + super.getName()
                + " | " + deadline + "\n";
        }

        //toString returning task
        public String toString(){
            return "[D][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName() 
                + " (by: " + deadline + ")";
        }
    }

    //A variant of task
    class Events extends Task{
        String timing;  //Timing of event
        
        //Constructor
        public Events(String taskName, String timing){
            super(taskName);
            this.timing = timing;
        }

        public Events(String taskName, boolean completion, String timing){
            super(taskName, completion);
            this.timing = timing;
        }

        //Get timing of event
        String getTiming(){
            return "(at: " + timing + ")";
        }

        //Format of saving to database
        public String toDatabaseString(){
            return "E | " + (this.getCompletion()==true?"X":" ") + " | " + super.getName()
                + " | " + timing + "\n";
        }

        //toString returning event
        public String toString(){
            return "[E][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName() 
                + " (at: " + timing + ")";
        }
    }

    //DukeException
    class DukeException extends Exception{
        public DukeException(String errorMessage){
            super(errorMessage);
        }
    }

    //Enums of commands
    public enum Commands{
        bye, list, mark, unmark, delete, todo, deadline, event
    }
    
    //Print file content method
    public static void printFileContent(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        int counter = 0;
        while(sc.hasNext()) {
            counter++;
            String taskData = sc.nextLine();
            String[] taskDataSplit = taskData.split(" \\| ");
            switch (taskDataSplit[0]) {
            case "T":
                Task currentTask = new Duke().new Todos(taskDataSplit[2], taskDataSplit[1] == "X"? true : false);
                System.out.println("       " + counter + ". " + currentTask.toString());
                break;
            case "E":
                currentTask = new Duke().new Events(taskDataSplit[2], taskDataSplit[1] == "X"? true : false, taskDataSplit[3]);
                System.out.println("       " + counter + ". " + currentTask.toString());
                break;
            case "D":
                currentTask = new Duke().new Deadlines(taskDataSplit[2], taskDataSplit[1] == "X"? true : false, taskDataSplit[3]);
                System.out.println("       " + counter + ". " + currentTask.toString());
                break;
            }
        }
        System.out.println(String.format("\n    You have %s tasks in your list. :)", counter));
        sc.close();
    }

    //Erase and rewrite to file method
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    //Append to file method
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); //Append instead of rewriting over
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        //Database path
        String databasePath = "./DukeDatabase.txt";
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandSplit = command.split(" ", 2);
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!command.equals("bye")){
            if (command.equals("list")){
                try{
                System.out.println(hyphenate);
                System.out.println("    Here are the tasks in your list:");
                printFileContent(databasePath);
                System.out.println(hyphenate);
                } catch ( FileNotFoundException err){
                    System.out.println("File is not found");
                }
            } else if (commandSplit[0].equals("mark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                taskList.get(markIndex).completeTask();
                System.out.println(hyphenate);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("       " + taskList.get(markIndex));
                System.out.println(hyphenate);
            } else if (commandSplit[0].equals("unmark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                taskList.get(markIndex).uncompleteTask();
                System.out.println(hyphenate);
                System.out.println("    Okay, I've marked this task as not done yet:");
                System.out.println("       " + taskList.get(markIndex));
                System.out.println(hyphenate);
            } else if (commandSplit[0].equals("delete")) {
                try{
                    Task deletedTask = taskList.remove(Integer.parseInt(commandSplit[1])-1);
                    System.out.println(hyphenate);
                    System.out.println("    I have successfully removed this task from the system:");
                    System.out.println("       " + deletedTask);
                    System.out.println("    Now you have " + taskList.size() + " tasks left in the list!");
                    System.out.println(hyphenate);
                } catch (IndexOutOfBoundsException err) {
                    System.out.println(hyphenate);
                    System.out.println("");
                    System.out.println("       YIKES!!! We failed to delete the task, make sure that the task ID entered exists.");
                    System.out.println(hyphenate);
                }
            } else {
                switch(commandSplit[0]){
                    case "todo":
                        try {
                            Todos newTask = new Duke().new Todos(commandSplit[1]);
                            taskList.add(newTask); 
                            appendToFile(databasePath, newTask.toDatabaseString());
                            System.out.println(hyphenate);
                            System.out.println("    Wow, sounds fun! I have successfully added this task:");
                            System.out.println("       " + taskList.get(taskList.size()-1));
                            System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                            System.out.println(hyphenate);
                        } catch(IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! The description of a todo cannot be empty.");
                            System.out.println(hyphenate);
                        } catch(IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    case "deadline":
                        try{
                            Deadlines newTask = new Duke().new Deadlines(commandSplit[1].split(" /by ", 2)[0], commandSplit[1].split(" /by ", 2)[1]);
                            taskList.add(newTask); 
                            appendToFile(databasePath, newTask.toDatabaseString());
                            System.out.println(hyphenate);
                            System.out.println("    Wow, sounds fun! I have successfully added this task:");
                            System.out.println("       " + newTask);
                            System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                            System.out.println(hyphenate);
                        } catch (IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! We faced an issue creating an deadline, make sure to have both a \"/by\" and a description.");
                            System.out.println(hyphenate);
                        } catch (IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    case "event":
                        try {
                            Events newTask = new Duke().new Events(commandSplit[1].split(" /at ",2)[0], commandSplit[1].split(" /at ",2)[1]);
                            taskList.add(newTask); 
                            appendToFile(databasePath, newTask.toDatabaseString());
                            System.out.println(hyphenate);
                            System.out.println("    Wow, sounds fun! I have successfully added this task:");
                            System.out.println("       " + newTask);
                            System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                            System.out.println(hyphenate);
                        } catch(IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! We faced an issue creating an Event, make sure to have both an \"/at\" and a description.");
                            System.out.println(hyphenate);
                        } catch (IOException e) {
                            System.out.println("File does not exist.");
                        }
                        break;

                    default:
                        System.out.println(hyphenate);
                        System.out.println("\n       " + "YIKES!!! I'm sorry, but I don't know what that means :-("); 
                        System.out.println(hyphenate);
                        break;
                }
            }
            System.out.println("");
            command = sc.nextLine();
            commandSplit = command.split(" ", 2);
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }
}
