import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        String introduction = "____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________";
        System.out.println(introduction);

        // Scanner for new input
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            String[] splitInput = input.split("\\s");
            switch(splitInput[0]) {
                case "bye":
                    System.out.println("____________________________________________________________\nBye! Hope to see you again\n____________________________________________________________");
                    return;
                default:
                    System.out.println("____________________________________________________________\n" + input + "\n____________________________________________________________");
            }
        }
    }
}
