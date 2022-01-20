import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static void main(String[] args) {

        TaskManager duke = new TaskManager();
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        duke.mainLogic();
        System.out.println("Bye! Hope to see you again soon");
    }

}
