import java.util.Scanner;

import helperClasses.Task;
import helperClasses.Deadline;
import helperClasses.Event;
import helperClasses.ToDo;

public class Duke {
    private static boolean isEnd = false;
    private static Task[] TaskList = new Task[100];
    private static int TaskNo = 0;

    public static void horizontalLine(){
        System.out.println("____________________________________________________________");
    }

    public static void greet(){
        horizontalLine();
        System.out.println("\tHello I am DDX");
        System.out.println("\tWhat can I do for you?");
        System.out.println();
        horizontalLine();
    }

    public static void echo(){
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String input = myObj.nextLine();
        
        // default message
        String result = null;
        
        // detect bye
        if (input.equals("bye")) {
            Duke.isEnd = true;
            result = "\tBye. Hope to see you again soon!";
        }else if (input.equals("list")){
            // detect list
            Duke.list();
            return;
        } else {
            if (input.startsWith("mark")){
                // detect mark
                int currTask = Character.getNumericValue(input.charAt(input.length() - 1));
                mark(currTask - 1);
                return;
            } else if (input.startsWith("unmark")) {
                // detect unmark
                int currTask = Character.getNumericValue(input.charAt(input.length() - 1));
                unmark(currTask - 1);
                return;
            } else {
                // add task
                if (input.startsWith("todo")) {
                    // generate
                    String description = input.substring(5);
                    ToDo temp = new ToDo(description);
                    TaskList[TaskNo] = temp;
                    TaskNo ++;
                    result = String.format("Got it. I've added this task: \n\t%s \nNow you have %d tasks in the list.",
                            temp.toString(),TaskNo);
                } else if (input.startsWith("deadline")) {
                    int byPos = input.indexOf("/by");
                    String description = input.substring(9,byPos);
                    String by = input.substring(byPos+4);
                    Deadline ddl = new Deadline(description, by);
                    TaskList[TaskNo] = ddl;
                    TaskNo ++;
                    result = String.format("Got it. I've added this task: \n\t%s \nNow you have %d tasks in the list.",
                            ddl.toString(),TaskNo);
                } else if (input.startsWith("event")) {
                    int atPos = input.indexOf("/at");
                    String description = input.substring(6,atPos);
                    String at = input.substring(atPos+4);
                    Event event = new Event(description, at);
                    TaskList[TaskNo] = event;
                    TaskNo ++;
                    result = String.format("Got it. I've added this task: \n\t%s \nNow you have %d tasks in the list.",
                            event.toString(),TaskNo);
                } else {
                    result = input;
                }
            }
        }

        // print result
        horizontalLine();
        System.out.println(result);
        System.out.println();
        horizontalLine();
    }

    public static void list(){
        horizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < TaskNo ; i++) {
            if (TaskList[i] != null) {
                System.out.format("%s. %s\n", i + 1, TaskList[i].toString());
            }
        }
        System.out.println();
        horizontalLine();
    }

    public static void mark(int currTask){
        TaskList[currTask].markAsDone();
        horizontalLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(TaskList[currTask].toString());
        System.out.println();
        horizontalLine();
    }

    public static void unmark(int currTask){
        TaskList[currTask].markAsNotDone();
        horizontalLine();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.println(TaskList[currTask].toString());
        System.out.println();
        horizontalLine();
    }


    public static void main(String[] args) {
        greet();
        while (!isEnd) {
            echo();
        }
    }
}
