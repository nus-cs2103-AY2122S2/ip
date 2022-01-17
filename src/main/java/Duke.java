import java.util.Scanner;

public class Duke {
    private int repeatListSize = 0;
    private String[] repeatList = new String[100];
    private Scanner scanner;

    public Duke(Scanner sc) {
        this.scanner = sc;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke Lumu = new Duke(sc);

        Lumu.botInitialize();
        Lumu.botResponse();
    }

    private String lineBreak() {
        return "\n－－－－－－－－－－（・Ａ・）－－－－－－－－－\n";
    }

    private void botInitialize() {
        String logo = " _       _   _   __  __   _   _ \n"
            + "| |     | | | | |  \\/  | | | | |\n"
            + "| |     | | | | | |\\/| | | | | |\n"
            + "| |___  | |_| | | |  | | | |_| |\n"
            + "|_____|  \\___/  |_|  |_|  \\___/ \n";

        System.out.println(lineBreak() + "Hello I'm\n" + logo);
        System.out.println("What can I do for you?" + lineBreak());
    }

    private void botResponse() {
        while (true) {
            String str = scanner.nextLine();

            if (str.compareToIgnoreCase("bye") == 0) {
                System.out.println("LUMU: Goodbye. Hope to see you again soon");
                System.out.println(lineBreak());
                scanner.close();
                return;
            } else if (str.compareToIgnoreCase("list") == 0) {
                displayList();
                System.out.println(lineBreak());
            } else {
                System.out.println("added: " + str);
                addToList(str);
                System.out.println(lineBreak());
            }
        }
    }

    private void addToList(String str) {
        repeatList[repeatListSize] = str;
        repeatListSize++;
    }

    private void displayList() {
        if (repeatListSize == 0) {
            System.out.println("LUMU: Your list is empty!");
        } else {
            for (int i = 0; i < repeatList.length && i < repeatListSize; i++) {
                System.out.println(String.valueOf(i + 1) + ". " + repeatList[i]);
            }
        }
    }
}
