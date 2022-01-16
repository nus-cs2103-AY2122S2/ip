import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static TaskList list = new TaskList();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String intro = "I am a Personal Assistant Chatbot that helps you to keep track of various things.\n"
                + "Let's get started.\n";

        String line = "-------------------------------------------------------------------------------------";

        String bye = "Bye. Hope to see you again soon!";

        System.out.println("Hello from\n" + logo);
        System.out.println(intro);
        System.out.println(line);

        Scanner s = new Scanner(System.in);

        String input;

        while (true) {

            input = s.nextLine();

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list.getTaskList());
            } else if (input.startsWith("unmark")) {
                try{
                    int index = Integer.parseInt(input.replaceAll("unmark", "").strip());
                    System.out.println(list.markTaskUndone(index));
                } catch (NumberFormatException n){
                    System.out.println("Invalid number entered! Please enter an integer");
                }

            } else if (input.startsWith("mark")) {
                try {
                    int index = Integer.parseInt(input.replaceAll("mark", "").strip());
                    System.out.println(list.markTaskDone(index));
                } catch (NumberFormatException n){
                    System.out.println("Invalid number entered! Please enter an integer");
                }
            }  else {
                //System.out.println("\n");
                System.out.println(line);
                String response = list.addTask(input);
                System.out.println(response);
                System.out.println(line);
            }
        }

        System.out.println(line);
        System.out.println(bye);
        System.out.println(line);
    }
}
