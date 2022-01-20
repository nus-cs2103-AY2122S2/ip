
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String line =
            "____________________________________________________________\n";
    private final Scanner s = new Scanner(System.in);
    private boolean finished = false;

    public boolean isFinished() {
        return this.finished;
    }

    private String read() {
        String input = s.nextLine();
        if (input.equals("bye")) this.finished = true;
        return input;
    }

    private void respond(String respond) {
        if (this.finished) respond = line + "Bye. Hope to see you again soon!\n" + line;
        else respond = line + respond + "\n" + line;
        System.out.println(respond);
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I am xpz\nWhat can I do for you?\n" + line);
        Duke xpz = new Duke();
        while (!xpz.isFinished()) xpz.respond(xpz.read());
    }
}
