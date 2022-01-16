import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        displayStartUpMessage();
        execute();
    }
    private static void displayStartUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo + "\n" + "What can I do for you?\n" + "____________________________________________________________");
    }
    private static void execute() {
        Scanner sc = new Scanner(System.in);
        String command;
        String line = "\t____________________________________________________________";
        while(true) {
            command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println(line + "\n" + "\tBye. Hope to see you again soon!\n" + line);
                break;
            }
            if(!command.equals(""))
                System.out.println(line + "\n\t" + command + "\n" + line);
        }
    }
}
