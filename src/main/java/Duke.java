import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String lineBreak = "____________________________________________________________\n";
        String catFace = " =^_^=\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
                + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
                + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
                + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";

        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("Hello from\n" + logo);
        System.out.println(lineBreak + "Meow! I'm Duke" + catFace + "What can I do for you?\n" + lineBreak);

        String input = sc.nextLine().strip();

        while (!input.equals("bye")) {
            switch (input) {
            case "list":
                System.out.print(lineBreak);
                for (int i = 0; i < taskCount; i++) {
                    int index = i + 1;
                    System.out.println(index + ". " + tasks[i]);
                }
                System.out.print(lineBreak);
                break;
            default:
                tasks[taskCount] = input;
                taskCount++;
                System.out.println(lineBreak + "added: " + input + catFace + lineBreak);
                break;
            }

            input = sc.nextLine().strip();
        }

        System.out.println(lineBreak + "Bye. Meow!" + catFace + lineBreak);

        sc.close();
    }
}