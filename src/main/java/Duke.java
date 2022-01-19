import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> taskList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String userInput = sc.nextLine();
            if (userInput.equals(ValidCommand.BYE.label)) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals(ValidCommand.LIST.label)) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(i+1 + ". " + taskList.get(i));
                }
                if (taskList.size() == 0) {
                    System.out.println("task list is empty");
                }
            } else {
                taskList.add(userInput);
                System.out.println("added: " + userInput);
            }
        }
    }
}
