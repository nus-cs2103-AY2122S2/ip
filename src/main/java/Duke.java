import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bot = "Hello! I'm Duke\nWhat can I do for you?", response = "";
        StringBuilder ListResponse = new StringBuilder();

        System.out.println(appendLines(bot));

        while(response.compareTo("bye") != 0) {
            response = reader.readLine();
            if (response.compareTo("bye") == 0) {
                System.out.println(appendLines("Bye. Hope to see you again soon!"));
                break;
            } else if (response.compareTo("list") == 0) {
                System.out.println(appendLines(ListResponse.toString()));
            } else {
                System.out.println(appendLines("added: " + response));
                ListResponse.append(response + "\n");
            }
        }
    }

    public static String appendLines(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line;
    }
}
