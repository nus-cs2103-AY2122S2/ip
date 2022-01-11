import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void simpleTodo() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        input += scanner.nextLine();
        ArrayList<String> list = new ArrayList<>();

        while(true) {
                //bye, shut down Cortana
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
                //list all the tasks
            } else if (input.equalsIgnoreCase("list")) {
                if (list.size() == 0) {
                    System.out.println("You are done for the day, or are you?");
                } else {
                    for(int i = 0; i < list.size(); i ++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                }
                //valid command, add task
            } else {
                if(list.contains(input)) {
                    System.out.println("Task already exists!");
                } else {
                    list.add(input);
                    System.out.print("added: " + input + "\n");
                }
            }
            input = scanner.next();
            input += scanner.nextLine();
        }
        scanner.close();
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
        System.out.println("Hello from\n" + logo + "My name is Cortana, what can I do for you, master?");
        simpleTodo();
    }
}