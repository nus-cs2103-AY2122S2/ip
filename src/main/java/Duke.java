import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String textBanner = "" +
            "  _     _   _                   \n" +
            " | |   (_) (_)                  \n" +
            " | |__  _   _    __ _ _ __ ___  \n" +
            " | '_ \\| | | |  / _` | '_ ` _ \\ \n" +
            " | | | | | | | | (_| | | | | | |\n" +
            " |_| |_|_| |_|_ \\__,_|_| |_| |_|\n" +
            "           (_) |                \n" +
            "  _ __ ___  _| | _____          \n" +
            " | '_ ` _ \\| | |/ / _ \\         \n" +
            " | | | | | | |   <  __/         \n" +
            " |_| |_| |_|_|_|\\_\\___|         \n" +
            "                                \n" +
            "                                ";
        System.out.println("Hello from\n" + textBanner);
        String tip = "Tip: type bye to end";
        System.out.println(tip);
        System.out.println("Enter a command:\n");

        Scanner sc = new Scanner(System.in);
        String inputString = sc.next(); //limitation: can't accept sentences yet
        while(!inputString.equals("bye")) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Mike: " + inputString);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            sc = new Scanner(System.in);
            System.out.println("Enter next command:");
            inputString = sc.next();
        }
        System.out.println("see you again");
    }
}
