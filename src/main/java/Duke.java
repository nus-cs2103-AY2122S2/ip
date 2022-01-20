import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("How may I serve you?");
            String input = sc.nextLine();

            //level-1 feature: for exit
            if(input.equals("bye")) {
                System.out.println("Bye! See you again");
                break;
            }

            System.out.println(input);

        }

    }
}
