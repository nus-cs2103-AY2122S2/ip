import java.util.Scanner;

public class Duke {
    public static void echo() {
        Scanner getUserInput = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = getUserInput.nextLine();
        }
        System.out.println("Till we meet again");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How may I assist you?");
        echo();
    }
}
