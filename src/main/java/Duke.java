import java.util.Scanner;

public class Duke {
    private static boolean isEnd = false;
    private static String[] TaskList = new String[100];
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

        // default return is added + input
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
            // add task
            TaskList[TaskNo] = input;
            TaskNo ++;
        }

        // print result
        horizontalLine();
        System.out.println(result);
        System.out.println();
        horizontalLine();
    }

    public static void list(){
        horizontalLine();
        for (int i = 0; i < TaskNo ; i++) {
            if (TaskList[i] != null) {
                System.out.format("%d. %s\n", i, TaskList[i]);
            }
        }
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
