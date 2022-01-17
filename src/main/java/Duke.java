import java.util.Scanner;

public class Duke {
    private static String[] taskList = new String[100];
    private static int taskNum = 0;

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?\n";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greet);

        while(true) {
            Scanner sc = new Scanner(System.in);
            String curr_word = sc.nextLine();
            if (curr_word.equals("bye")) {
                System.out.println(goodbye);
                break;
            } else if (curr_word.equals("list")) {
                if (taskNum == 0) {
                    System.out.println("No task");
                } else {
                    for (int i = 1; i < taskNum + 1; i++) {
                        String currTask = taskList[i - 1];
                        System.out.println(i + ". " + currTask);
                    }
                }
            } else {
                taskList[taskNum] = curr_word;
                taskNum++;
                System.out.println("added: " + curr_word);
            }
        }
    }
}
