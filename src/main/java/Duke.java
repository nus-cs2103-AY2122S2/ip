import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bot = "Hello! I'm Duke\nWhat can I do for you?", response = "";
        ArrayList<Task> ListResponse = new ArrayList<Task>();

        System.out.println(appendLines(bot));

        int digit = 0;

        while(response.compareTo("bye") != 0) {
            response = reader.readLine();
            if (response.compareTo("bye") == 0) {
                System.out.println(appendLines("Bye. Hope to see you again soon!"));
                break;
            } else if (response.compareTo("list") == 0) {
                System.out.println("Here are the tasks in your list:\n");
                for(int i = 0; i < ListResponse.size(); i++) {
                    System.out.println(i + 1 + ". " + ListResponse.get(i).toString());
                }
            } else if (response.contains("mark")) {
                String[] split = response.split("\\s+");
                digit = Integer.parseInt(split[1]);
                if (digit > 0 && digit <= ListResponse.size()) {
                    switch (split[0]) {
                        case "mark":
                            System.out.println("Nice! I've marked this task as done:\n");
                            break;
                        case "unmark":
                            System.out.println("OK, I've marked this task as not done yet:\n");
                            break;
                    }
                    Task t = ListResponse.get(digit - 1);
                    t.unmark();
                    System.out.println(t.toString());
                } else {
                    System.out.println("Invalid range! Try again.\n");
                }
            }
            else {
                System.out.println(appendLines("added: " + response));
                ListResponse.add(new Task(response));
            }
        }
    }

    public static String appendLines(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line;
    }
}
