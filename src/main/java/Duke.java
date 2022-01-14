import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> textList = new ArrayList<>();

    public static void main(String[] args) {
        printMsg("Hello! I am Spike ⊂( ・ ̫・)⊃\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                printMsg("See you soon! ﾍ(=￣∇￣)ﾉ");
                sc.close();
                return;
            } else if (command.equals("list")) {
                printList();
            } else {
                String str = "added: " + command;
                textList.add(command);
                printMsg(str);
            }
        }
    }

    /**
     * Format and print the response to the console.
     */
    public static void printMsg(String msg) {
        System.out.println("-------------------------------------------------\n"
                + msg + "\n"
                + "-------------------------------------------------");
    }

    /**
     * Print all items in the list
     */
    public static void printList() {
        int i = 1;
        String result = "";
        for (String str : textList) {
            if (i == textList.size()) {
                result = result + i + ". " + str;
            } else {
                result = result + i + ". " + str + "\n";
            }
            i++;
        }
        printMsg(result);
    }
}
