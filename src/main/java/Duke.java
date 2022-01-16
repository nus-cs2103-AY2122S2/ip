import java.io.*;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        while (!command.equals("bye")) {
            switch(command) {
                case "list":
                    System.out.println("    ____________________________________________________________\n" +
                            "     list\n" +
                            "    ____________________________________________________________");
                    command = br.readLine();
                    break;
                case "blah":
                    System.out.println("    ____________________________________________________________\n" +
                            "     blah\n" +
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
