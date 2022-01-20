import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void addTask(ArrayList<TaskStorage> taskStorages, int numTask) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("  " + taskStorages.get(taskStorages.size() - 1));
        System.out.println(" Now you have " + numTask +  " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        //String logo = " ____        _        \n"
        //        + "|  _ \\ _   _| | _____ \n"
        //        + "| | | | | | | |/ / _ \\\n"
        //        + "| |_| | |_| |   <  __/\n"
        //        + "|____/ \\__,_|_|\\_\\___|\n";
        //System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();
        int taskNumber = 0;
        ArrayList<TaskStorage> storingList = new ArrayList<>();
        while (! word.equals("bye")) {
            if (word.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 1; i <= storingList.size(); i++) {
                    System.out.println(" " + i + "." + storingList.get(i - 1));
                }
                System.out.println("____________________________________________________________");
            } else if (word.substring(0,4).equals("mark")) {
                TaskStorage temp = storingList.get(Integer.parseInt(word.substring(5)) - 1);
                temp.taskDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done: ");
                System.out.println("  " + temp);
                System.out.println("____________________________________________________________");
            } else if (word.substring(0,6).equals("unmark")) {
                TaskStorage temp = storingList.get(Integer.parseInt(word.substring(7)) - 1);
                temp.taskUndone();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet: ");
                System.out.println("  " + temp);
                System.out.println("____________________________________________________________");
            } else if (word.substring(0,4).equals("todo")) {
                taskNumber += 1;
                storingList.add(new TaskStorage(word.substring(5), "T"));
                addTask(storingList, taskNumber);
            } else if (word.substring(0,8).equals("deadline")) {
                taskNumber += 1;
                storingList.add(new TaskStorage(word.substring(9), "D"));
                addTask(storingList, taskNumber);
            } else if (word.substring(0,5).equals("event")) {
                taskNumber += 1;
                storingList.add(new TaskStorage(word.substring(6), "E"));
                addTask(storingList, taskNumber);
            }
            word = sc.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
