import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final ArrayList<Task> TO_DO_LIST = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;

        System.out.println(WELCOME_MESSAGE);
        while (!isExit) {
            String userInput = sc.next();
            switch (userInput) {
                case "bye":
                    isExit = true;
                    System.out.println(GOODBYE_MESSAGE);
                    break;
                case "list":
                    for (int i = 0; i < TO_DO_LIST.size(); i++) {
                        System.out.printf("%d.%s%n", i + 1, TO_DO_LIST.get(i));
                    }
                    break;
                case "mark":
                    int markIndex = sc.nextInt() - 1;
                    Task markTask = TO_DO_LIST.get(markIndex);
                    markTask.setIsDone(true);
                    System.out.printf("Nice! I've marked this task as done: \n" +
                            "   %s\n", markTask);
                    break;
                case "unmark":
                    int unmarkIndex = sc.nextInt() - 1;
                    Task unmarkTask = TO_DO_LIST.get(unmarkIndex);
                    unmarkTask.setIsDone(false);
                    System.out.printf("Ok, I've marked this task as not done yet: \n" +
                            "   %s\n", unmarkTask);
                    break;
                default:
                    Task t = new Task(userInput);
                    TO_DO_LIST.add(t);
                    System.out.printf("added: %s%n", userInput);
                    break;
            }
        }
    }
}
