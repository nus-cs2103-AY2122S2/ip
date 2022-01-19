import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "    ____________________________________________________________\n";
        String spacing = "    ";
        ListStorage myListStorage = new ListStorage();
        System.out.println("Hello from\n" + logo);
        Scanner myScanner = new Scanner(System.in);
        System.out.println(border + spacing +
                "Hello! I'm Duke\n" + spacing +
                "What can I do for you?\n" +
                border);
        String cmd = myScanner.nextLine();
        while (!cmd.equals("bye")) {
            if(cmd.equals("list")) {
                System.out.println(border + myListStorage.printList() + border);
            } else {
                String toPrint = myListStorage.addToList(cmd);
                System.out.println(border + spacing + toPrint + "\n" + border);
            }
            cmd = myScanner.nextLine();
        }
        System.out.println(border + spacing + "Bye. Hope to see you again soon!\n" + border);
    }
}
