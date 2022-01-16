import java.util.Scanner;

public class Mike {
    public static void main(String[] args) {
        printGreeting();
        printStartInstruction();

        Scanner sc = new Scanner(System.in);
        String inputString = sc.next(); //limitation: can't accept sentences yet
        while(!inputString.equals("bye")) {
            String reply = inputString;
            printReply(reply);

            sc = new Scanner(System.in);
            printNextCommandInstruction();
            inputString = sc.next();
        }
        printGoodbyeMessage();
        }

    static void printGreeting() {
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
        System.out.println("Welcome!\n" + textBanner);
    }

    static void printStartInstruction() {
        String tip = "**Tip: type bye to end**";
        System.out.println(tip);
        System.out.println("Enter a command:\n");
    }

    static void printReply(String str){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + str);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    static void printGoodbyeMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + "Goodbye and see you again");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
