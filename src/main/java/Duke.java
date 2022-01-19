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

    //Level 4
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
            } else {
                switch(commandSplit[0]){
                    case "todo":
                        taskList.add(new Duke().new Todos(commandSplit[1])); 
                        System.out.println(hyphenate);
                        System.out.println("    Wow, sounds fun! I have successfully added this task:");
                        System.out.println("       " + taskList.get(taskList.size()-1));
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                        System.out.println(hyphenate);
                        break;
                    case "deadline":
                        taskList.add(new Duke().new Deadlines(commandSplit[1].split(" /by ", 2)[0], commandSplit[1].split(" /by ", 2)[1])); 
                        System.out.println(hyphenate);
                        System.out.println("    Wow, sounds fun! I have successfully added this task:");
                        System.out.println("       " + taskList.get(taskList.size()-1));
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                        System.out.println(hyphenate);
                        break;
                    case "event":
                        taskList.add(new Duke().new Events(commandSplit[1].split(" /at ",2)[0], commandSplit[1].split(" /at ",2)[1])); 
                        System.out.println(hyphenate);
                        System.out.println("    Wow, sounds fun! I have successfully added this task:");
                        System.out.println("       " + taskList.get(taskList.size()-1));
                        System.out.println("    Now you have " + taskList.size() + " tasks in the list!");
                        System.out.println(hyphenate);
                        break;
                    default:
                        System.out.println(hyphenate);
                        System.out.println("\n    " + command); 
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
