import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String lineBreak = "____________________________________________________________\n";
        String catFace = " =^_^=\n";
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____      /\\_/\\           ___\n"
                + "| | | | | | | |/ / _      = o_o =_______    \\ \\  -Julie Rhodes-\\\n"
                + "| |_| | |_| |   <  __/     __^      __(  \\.__) )\n"
                + "|____/ \\__,_|_|\\_\\___| (@)<_____>__(_____)____/\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(lineBreak + "Meow! I'm Duke" + catFace + "What can I do for you?\n" + lineBreak);

        String input = sc.nextLine().strip();

        while (!input.equals("bye")) {
            System.out.println(lineBreak + input + catFace + lineBreak);
            input = sc.nextLine().strip();
        }

        System.out.println(lineBreak + "Bye. Hope to see you again soon!" + catFace + lineBreak);

        sc.close();
    }
}