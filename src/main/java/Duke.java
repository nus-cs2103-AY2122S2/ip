import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = "--------------------------------------------------";
        String tabbedLine = "\t----------------------------------------------";
        System.out.println(line);
        System.out.println("Hello from Burp\n");
        System.out.println("What can I do for you?");
        System.out.println(line);
        while (true) {
            String cmd = br.readLine();
            if (cmd.equals("bye")) {
                System.out.println(tabbedLine + "\n\tBye. Hope to see you again soon!\n" + tabbedLine);
                break;
            } else {
                System.out.println(tabbedLine + "\n\t" + cmd + "\n" + tabbedLine);
            }
        }
    }
}
