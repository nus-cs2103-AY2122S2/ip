import java.util.Scanner;

public class Duke {

    public static String LINE = "*~*~*~*~*~*~*~*~*~*~*~*~*~*~*";
    public static String[] tasks = new String[100];
    public static int tasksAdded_index = 0;


    private static void messageProcess() {
        Scanner user_input = new Scanner(System.in).useDelimiter("\n");
        String user_message = user_input.next();

        while (!user_message.equals("bye")) {
            if (user_message.equals("list")) {
                int i = 0;
                while(tasks[i] != null) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                    i++;
                }
            } else {
                System.out.println(LINE + "\n" + "New task added:" + user_message + "\n" + LINE);
                tasks[tasksAdded_index] = user_message;
                tasksAdded_index ++;
            }

            user_message = user_input.next();
        }
    }


    public static void main(String[] args) {
        String logo = "  *    ,---.    ,-----.  ,--.  ,--.   ,---.      *\n"
                + "      '   .-'  '  .-.  ' |  ,'.|  |  /  O  \\    *\n"
                + " *    `.  `-.  |  | |  | |  |' '  | |  .-.  |   \n"
                + "    * .-'    | '  '-'  ' |  | `   | |  | |  |      *\n"
                + "*     `-----'   `-----'  `--'  `--' `--' `--'   *";
        System.out.println("Hello from\n" + logo);
        System.out.println("\nHi! You can call me Sona ^^~\nHow can I help you?\n");

        messageProcess();


        System.out.println(LINE + "\nBye-bye :')  Hope to see you soon! \n" + LINE);
    }
}
