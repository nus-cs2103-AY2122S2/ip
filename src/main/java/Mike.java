import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mike {
    List<String> listItems;

    public static void main(String[] args) {
        Mike mike = new Mike();
        mike.printGreeting();
        mike.printStartingInstruction();

        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine(); //limitation: accepts blanks to the list
        while(!inputString.equals("bye")) {
            if (inputString.equals("list")) {
                mike.printList();
            } else {
                String reply = inputString;
                //mike.printReply(reply);
                mike.addToList(inputString);
            }
            sc = new Scanner(System.in);
            mike.printNextCommandInstruction();
            inputString = sc.nextLine();
        }
        mike.printGoodbyeMessage();
    }

    public Mike() {
        this.listItems = new ArrayList<String>();
    }

    void printGreeting() {
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

    void printStartingInstruction() {
        String tip = "**Tip: type bye to end conversation**";
        System.out.println(tip);
        System.out.println("Enter a command:\n");
    }

    void printReply(String str){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Mike: " + str);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void printNextCommandInstruction() {
        System.out.println("Enter next command:");
    }

    void printGoodbyeMessage() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String goodbyeMessage = "Mike: Goodbye and see you again";
        System.out.println(goodbyeMessage);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void addToList(String str) {
        this.listItems.add(str);
        printReply("Added \"" + str + "\" to the list!");
    }

    void printList() {
        int counter = 1;
        for (String str : listItems) {
            System.out.println(String.format("%d. %s", counter, str));
            counter++;
        }
    }
}
