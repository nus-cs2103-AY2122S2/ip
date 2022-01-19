import java.util.*;

public class Van {
    public static void main(String[] args) {
        int counter = 0;
        String command = null;
        String[] tasks = new String[100];
        Scanner input = new Scanner(System.in);
        String divide = "-------------------------------------------";
        System.out.println("Hello I am Van");
        System.out.println("How may i assist you");
        System.out.println(divide);
        command = input.nextLine();
        System.out.println(divide);
        while (!command.equalsIgnoreCase("bye")) {
            switch(command.toLowerCase()) {
                case "list":
                    for (int i = 0; i < counter; i++) {
                        System.out.println(i+1 + ". " + tasks[i]);
                    }
                    System.out.println(divide);
                    break;
                default:
                    tasks[counter] = command;
                    counter++;
                    System.out.println("added: " + command);
                    System.out.println(divide);
            }
            command = input.nextLine();
            System.out.println(divide);
        }
        System.out.println("Bye");
    }
}
