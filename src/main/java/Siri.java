import java.util.Scanner;

public class Siri {
    private static Scanner sc;
    private static ToDoList toDoList;

    public static void main(String[] args) {
        String logo = "   -----      O    -----      O\n" +
                      " /   _   \\   __   |       \\   __\n" + 
                      " |  | |__|  |  |  |   O   |  |  |\n" +
                      " |   ----\\  |  |  |       /  |  |\n" +
                      "  \\ __   |  |  |  |   ---    |  |\n" +
                      " |---|   |  |  |  |       \\  |  |\n" +
                      "  \\______/  |__|  |___|\\___\\ |__|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        sc = new Scanner(System.in);
        toDoList = new ToDoList();

        runApp();
    }

    private static void runApp() {
        boolean toRun = true;

        while (toRun) {
            try {
                String inputText = sc.nextLine();

                if (inputText.equals("bye")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    toRun = false;
                } else {
                    toDoList.handleCommand(inputText);
                }
            } catch (SiriException se) {
                System.out.println(se.getMessage());
            }
        }
    }

}
