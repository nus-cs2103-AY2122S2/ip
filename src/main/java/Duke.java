import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bot = "Hello! I'm Duke\nWhat can I do for you?", response = "";

        System.out.println(appendLines(bot));

        while(response.compareTo("bye") != 0) {
            response = reader.readLine();
            if (response.compareTo("bye") == 0) {
                System.out.println(appendLines("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(appendLines(response));
        }
    }

    public static String appendLines(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line;
    }
}
