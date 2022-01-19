import java.util.Scanner;

public class Siri {
    public static void main(String[] args) {
        String logo = "   -----      O    -----      O \n" +
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
            String[] inputSplit = inputText.split(" ");

            if (inputText.equals("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                break;
            } else if (inputText.equals("list")) {
                toDoList.print();
            } else if (inputSplit[0].equals("mark")) {
                // to input index as per keyed in String (index process to 0-based will be done by ToDoList method)
                toDoList.markItem(inputSplit[1]);
            } else if (inputSplit[0].equals("unmark")) {
                // to input index as per keyed in String (index process to 0-based will be done by ToDoList method)
                toDoList.unmarkItem(inputSplit[1]);
            } else {
                toDoList.addItem(inputText);
            }
        }
    }

}
