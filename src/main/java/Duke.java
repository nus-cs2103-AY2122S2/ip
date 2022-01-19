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
    abstract class Task{
        String name;
        boolean completed;
        
        public Task(String taskName){
            this.name = taskName;
            this.completed = false;
        }

        void completeTask(){
            this.completed = true;
        }

        void uncompleteTask(){
            this.completed = false;
        }

        String getTiming(){
            return "";
        }

        String getName(){
            return this.name;
        }

        Boolean getCompletion(){
            return this.completed;
        }

        public String toString(){
            return "Task";
        }
    }

    class Todos extends Task{
        public Todos(String taskName){
            super(taskName);
        }

        public String toString(){
            return "[T][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName();
        }
    }

    class Deadlines extends Task{
        String dateline;

        public Deadlines(String taskName, String dateline){
            super(taskName);
            this.dateline = dateline;
        }

        String getTiming(){
            return "(by: " + dateline + ")";
        }

        public String toString(){
            return "[D][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName() 
                + " (by: " + dateline + ")";
        }
    }

    class Events extends Task{
        String timing;
        
        public Events(String taskName, String timing){
            super(taskName);
            this.timing = timing;
        }

        String getTiming(){
            return "(at: " + timing + ")";
        }

        public String toString(){
            return "[E][" + (this.getCompletion()==true?"X":" ") + "] " + super.getName() 
                + " (at: " + timing + ")";
        }
    }

    class DukeException extends Exception{
        public DukeException(String errorMessage){
            super(errorMessage);
        }
    }

    public static void main(String[] args) {
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandSplit = command.split(" ", 2);
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println(hyphenate);
                System.out.println("    Here are the tasks in your list:");
                for (int i=0; i<taskList.size(); i++){
                    System.out.println("    " + (i+1) + "." + taskList.get(i));
                }
                System.out.println(hyphenate);
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
                        }
                        break;

                    case "deadline":
                        try{
                        Deadlines newTask1 = new Duke().new Deadlines(commandSplit[1].split(" /by ", 2)[0], commandSplit[1].split(" /by ", 2)[1]);
                        taskList.add(newTask1); 
                        System.out.println(hyphenate);
                        System.out.println("    Wow, sounds fun! I have successfully added this task:");
                        System.out.println("       " + newTask1);
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                        System.out.println(hyphenate);
                        } catch(IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! We faced an issue creating an deadline, make sure to have both a \"/by\" and a description.");
                            System.out.println(hyphenate);
                        }
                        break;

                    case "event":
                        try {
                        Events newTask2 = new Duke().new Events(commandSplit[1].split(" /at ",2)[0], commandSplit[1].split(" /at ",2)[1]);
                        taskList.add(newTask2); 
                        System.out.println(hyphenate);
                        System.out.println("    Wow, sounds fun! I have successfully added this task:");
                        System.out.println("       " + newTask2);
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                        System.out.println(hyphenate);
                        } catch(IndexOutOfBoundsException err) {
                            System.out.println(hyphenate);
                            System.out.println("");
                            System.out.println("       YIKES!!! We faced an issue creating an Event, make sure to have both an \"/at\" and a description.");
                            System.out.println(hyphenate);
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
