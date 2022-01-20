import java.util.Scanner;

public class Duke {

    private static String getFirstWord(String text) {
        int index = text.indexOf(' ');
        String word;
        if (index > -1) {
            word = text.substring(0, index);
        } else {
            word = text;
        }
        return word;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList<String> taskList = new TaskList<>();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        while (sc.hasNextLine()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.exit(0);
                sc.close();
            } else if (command.equals("list")) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.list();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            } else {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                taskList.add(command);
                System.out.println("added: " + command);
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }
    }
}
