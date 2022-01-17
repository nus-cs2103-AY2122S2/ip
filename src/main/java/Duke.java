import java.util.Scanner;

public class Duke {
    private static ActivityList al = new ActivityList();
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("Tell me about your upcoming activities!");
        System.out.println("Input \"list\" to display your activities.");
        System.out.println("Input \"bye\" to end our session.");
        Scanner sc = new Scanner(System.in);
        String userIn = "";
        do {
            if (userIn.equals("list")) {
                speech(al.toString());
            } else if (userIn.length() > 0) {
                al.add(userIn);
                speech("added: " + userIn);
            }
            userIn = sc.nextLine();
        } while (!userIn.equals("bye"));
        speech("Good bye! Hope to see you again!");
    }

    /**
     * Prints Duke's formatted speech.
     *
     * @param Duke's line
     */
    private static void speech(String text) {
        System.out.println("____________________________");
        System.out.println(text);
        System.out.println("____________________________");
    }
}
