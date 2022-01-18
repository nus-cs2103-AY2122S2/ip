import java.net.StandardSocketOptions;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        ArrayList<String> newArray = new ArrayList<>();
        while(true) {
            Scanner sc = new Scanner(System.in);
            String reply = sc.nextLine();
            if (reply.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                break;
            } else if (reply.equals("list")) {
                String totalString = "    ____________________________________________________________\n";
                for(int i = 0; i < newArray.size(); i++) {
                    totalString += "    " + (i + 1) + ": " + newArray.get(i) + "\n";
                }
                totalString += "    ____________________________________________________________\n";
                System.out.println(totalString);
            }

            else {
                newArray.add(reply);
                System.out.println("    ____________________________________________________________\n" +
                        "     " + "added: " + reply + "\n" +
                        "    ____________________________________________________________");
            }
        }
    }
}
