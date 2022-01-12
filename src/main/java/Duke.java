import java.util.Scanner;
import helperClasses.Task;

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

        // generate task
        Task temp = new Task(input);

        // default message is added + input
        String result = "added: " + input;

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
            }
            else {
                // add task
                TaskList[TaskNo] = temp;
                TaskNo ++;
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
                System.out.format("[%s] %d. %s\n",TaskList[i].getStatusIcon(), i+1, TaskList[i].getDescription());
            }
        }
        System.out.println();
        horizontalLine();
    }

    public static void mark(int currTask){
        TaskList[currTask].markAsDone();
        horizontalLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.format("[%s] %s\n",TaskList[currTask].getStatusIcon(), TaskList[currTask].getDescription());
        System.out.println();
        horizontalLine();
    }

    public static void unmark(int currTask){
        TaskList[currTask].markAsNotDone();
        horizontalLine();
        System.out.println("OK, I've marked this task as not done yet: ");
        System.out.format("[%s] %s\n",TaskList[currTask].getStatusIcon(), TaskList[currTask].getDescription());
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
