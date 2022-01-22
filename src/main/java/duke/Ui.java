package duke;

public class Ui {

    public Ui(){
        startup();
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

    public static void printTotalTasks(){
        System.out.printf("Now you have %d task on the list.\n", Task.totalTask);
    }

    public static void printAddingTasks(Task task){
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
