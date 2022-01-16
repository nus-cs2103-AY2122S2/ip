import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        ArrayList<String> list = new ArrayList<>();
        while (!command.equals("bye")) {
            switch(command) {
                case "list":
                    System.out.println("    ____________________________________________________________");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.printf("     %d. %s%n",i + 1, list.get(i));
                    }
                    System.out.println("    ____________________________________________________________");
                    command = br.readLine();
                    break;
                case "blah":
                    System.out.println("    ____________________________________________________________\n" +
                            "     blah\n" +
                            "    ____________________________________________________________");
                    command = br.readLine();
                    break;
                default:
                    list.add(command);
                    System.out.println("    ____________________________________________________________\n" +
                            "     added: " + command + "\n" +
                            "    ____________________________________________________________");
                    command = br.readLine();
                    break;
            }
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");


    }
}
