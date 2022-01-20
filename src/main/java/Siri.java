import java.util.Scanner;

public class Siri {
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

        Scanner sc = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();

        while (true) {
            String inputText = sc.nextLine();

            if (inputText.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else {
                toDoList.handleCommand(inputText);
            }
        }
    }

}
