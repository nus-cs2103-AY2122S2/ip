import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        String introduction = "____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________";
        System.out.println(introduction);

        // Scanner for new input
        Scanner sc = new Scanner(System.in);

        // ToDoList for the program
        Calendar calendar = new Calendar();

        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split("\\s");
            switch(splitInput[0]) {
                case "bye":
                    System.out.println(new OutputPackager("Bye! Hope to see you again soon."));
                    return;
                case "list":
                    System.out.println(new OutputPackager(calendar.toString()));
                    continue;
                case "mark":
                    try {
                        calendar.get(Integer.valueOf(splitInput[1])).complete();
                        System.out.println(new OutputPackager("Nice! I've marked this task as done for you.\n   " + calendar.get(Integer.valueOf(splitInput[1]))));
                    }
                    catch ( IndexOutOfBoundsException e ) {
                        System.out.println(new OutputPackager(String.format("Invalid task number. There are only %s tasks in the list.", calendar.size())));
                    } catch ( NumberFormatException n ) {
                        System.out.println(new OutputPackager(String.format("Invalid input. Expected integer value but obtained \"%s\".", splitInput[1])));
                    }
                    continue;
                case "unmark":
                    try {
                        calendar.get(Integer.valueOf(splitInput[1])).incomplete();
                        System.out.println(new OutputPackager("Ok. I've marked this task as not done yet.\n   " + calendar.get(Integer.valueOf(splitInput[1]))));
                    } catch ( IndexOutOfBoundsException e) {
                        System.out.println(new OutputPackager(String.format("Invalid task number. There are only %s tasks in the list.", calendar.size())));
                    } catch ( NumberFormatException n) {
                        System.out.println(new OutputPackager(String.format("Invalid input. Expected integer value but obtained \"%s\".", splitInput[1])));
                    }
                    continue;
                default:
                    calendar.add(new Task(input));
                    System.out.println(new OutputPackager(String.format("added: %s", input)));
            }
        }
    }
}
