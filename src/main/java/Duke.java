import java.util.Scanner; // Imported Scanner class
import java.util.StringTokenizer; //Imported StringTokenizer

public class Duke {
    public static void main(String[] args) {
        String logo = "_______________________________________________________\n"
                + " ____        _         _    ____ _   _ \n"
                + "|  _ \\ _   _| | _____ | | /  ___| | | |\n"
                + "| | | | | | | |/ / _ \\| | | |   | |_| |\n"
                + "| |_| | |_| |   <  __/| |_| |___|  _  |\n"
                + "|____/ \\__,_|_|\\_\\___||___|\\____|_| |_|\n"
                + "Hello! I'm DukeLCH\n"
                + "How can I be of service?\n" //Simple Greet
                + "_______________________________________________________\n";
        System.out.println(logo);
        Commands cmd = new Commands();
        Scanner io = new Scanner(System.in); // Scanner object created

        label:
        while(true) {
            String input = io.nextLine();
            StringTokenizer temp = new StringTokenizer(input);
            String keyword = temp.nextToken();
            switch (keyword) {
                case "bye":
                    cmd.bye();
                    break label;
                case "list":
                    cmd.list();
                    break;
                case "mark": {
                    int index = Integer.parseInt(temp.nextToken()) - 1;
                    cmd.mark(index);
                    break;
                }
                case "unmark": {
                    int index = Integer.parseInt(temp.nextToken()) - 1;
                    cmd.unmark(index);
                    break;
                }
                default:
                    cmd.addTask(input);
                    break;
            }
        }
    }
}
