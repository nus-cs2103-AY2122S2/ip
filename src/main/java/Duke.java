import java.sql.SQLOutput;
import java.util.Scanner;

public class Duke {

    private static String WELCOME_MESSAGE = "Hello, this is Duke!\nWhat can I do for you today?";
    private static String END_MESSAGE = "Bye!";

    public static void main(String[] args) {

        System.out.println(WELCOME_MESSAGE);

        TaskHandler taskHandler = new TaskHandler();
        taskHandler.doTasks();


    }
}
