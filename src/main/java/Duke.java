import java.util.Scanner; //import Scanner

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String dukeGreeting = "Hello! I'm Duke \nWhat can I do for you?";
        boolean endChat = false;
        String endMessage = "Bye. Hope to see you again soon!";
        Scanner sc = new Scanner(System.in);
        System.out.println(dukeGreeting);
        while (!endChat) {
            String userResponse = sc.nextLine();
            endChat = levelOneRespond(userResponse);
        }
        System.out.println(endMessage);


    }
    public static boolean levelOneRespond (String input) {
        String bye = "bye";
        String dukeMessage = "Duke: ";
        if (!input.equals(bye)) {
            System.out.println(dukeMessage + input);
            return false;
        } else {
            return true;
        }
    }
}
