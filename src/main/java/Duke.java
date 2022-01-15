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
        String userIn = ".";
        while (!userIn.equals("bye")) {
            userIn = sc.nextLine();
            System.out.println(speech(userIn));
        }
    }

    private static String speech(String text) {
        return "____________________________\n" +
                text +
                "\n____________________________\n";
    }
}
