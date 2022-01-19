import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "\t_______________________________________________\n";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(logo + line + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + line);

        while (true) {
            String input = reader.readLine();
            if (input.equals("bye")) {
                System.out.println(line + "\t Bye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.println(line + "\t " + input + "\n" + line);
            }
        }
    }
}
