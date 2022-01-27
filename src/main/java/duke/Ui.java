package duke;

import java.util.Scanner;

public class Ui {

    public Ui(){
        startup();
    }

    public void loop(){
        Scanner sc = new Scanner(System.in);
        boolean isBye = false;
        while(!isBye) {
            String input = sc.nextLine();
            isBye = Parser.parseIsBye(input);
        }
    }

    public static void startup(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can i do for you?");
    }

    public static void printBye(){
        System.out.println("Bye. I hope to see you sometime soon! :)");
    }

    public static void printWhatDoesThatMean(){
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what does that mean :-(");
    }

    public static void printEmptyDescriptionException(){
        System.out.println("☹ OOPS!!! The description of a task cannot be empty.");
    }

    public static void printAllTasks(){
        System.out.printf("Here are the tasks in your list:\n");
        for(int i=0; i<Task.totalTask; i++){
            System.out.print(TaskList.tasklist.get(i).toString());
        }
        System.out.println("__________________________________________________________________");
    }

    public static void PrintRemoveThisTask(int num){
        System.out.println(" Noted. I've removed this task: ");
        System.out.printf("  [%s][%s] %s\n",TaskList.tasklist.get(num).type, TaskList.tasklist.get(num).getStatus(), TaskList.tasklist.get(num).name);
    }

    public static void printMarkTaskDone(Task curr){
        System.out.println("Nice! I've marked this task as done: ");
        System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
    }

    public static void printMarkTaskNotDone(Task curr){
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.printf("  [%s][%s] %s\n", curr.type, curr.getStatus(), curr.name);
    }

    public static void printTotalTasks(){
        System.out.printf("Now you have %d task on the list.\n", Task.totalTask);
    }

    public static void printAddThisTask(Task task){
        System.out.println("Got it. I've added this task: ");
        if (task.type.equals("D")) {
            System.out.printf(" [D][%s] %s (by: %s) \n", task.getStatus(), task.name, task.time);
        } else if (task.type.equals("T")) {
            System.out.printf(" [T][%s] %s\n", task.getStatus(), task.name);
        } else {
            System.out.printf(" [E][%s] %s (on: %s) \n", task.getStatus(), task.name, task.time);
        }
    }
}
