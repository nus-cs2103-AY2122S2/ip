package li.zhongfu.cs2103.chatbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    private static final String HLINE = "____________________________________________________________";
    private static final String BOT_NAME = "Duke";

    private static void dialog(String[] lines) {
        System.out.println("    " + HLINE);
        for (String line: lines) {
            System.out.println("    " + line);
        }
        System.out.println("    " + HLINE + "\n");
    }

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        dialog(new String[]{
            String.format("Hello! I'm %s", BOT_NAME),
            "How can I help you?"
        });

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
           String input = br.readLine();
           switch (input) {
               case "bye":
                   dialog(new String[] {
                           "Bye. Hope to see you again soon!"
                   });
                   System.exit(0);
               default:
                   dialog(new String[] {
                           input
                   });
           }
        }
    }
}