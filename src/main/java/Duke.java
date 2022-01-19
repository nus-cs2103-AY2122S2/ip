import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        String line = "\t_______________________________________________\n";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String items[] = new String[100];
        int count = 0;

        System.out.println(line + logo + "\t Hello! I'm Duke\n" + "\t What can I do for you?\n" + line);

        while (true) {
            String input = reader.readLine();
            System.out.println(line);
            if (input.equals("bye")) {
                System.out.println("\t Bye. Hope to see you again soon!\n" + line);
                break;
            } else if (input.equals("list")) {
                for (int i=0; i<count; i++) {
                    System.out.println("\t " + i + ". " + items[i]);
                }
            } else {
                items[count] = input;
                count++;
                System.out.println("\t added: " + input);
            }
            System.out.println(line);
        }
    }
}
