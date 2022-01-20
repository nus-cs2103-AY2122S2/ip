
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String line =
            "____________________________________________________________\n";
    private final Scanner s = new Scanner(System.in);
    private boolean finished = false;
    private final ArrayList<String> list = new ArrayList<>();

    public boolean isFinished() {
        return this.finished;
    }

    private String read() {
        String input = s.nextLine();
        if (input.equals("bye")) {
            this.finished = true;
            return "Bye. Hope to see you again soon!";
        }
        else if (input.equals("list")) return listing();
        else this.list.add(input);
        return "added: " + input;
    }

    private void respond(String respond) {
        respond = line + respond + "\n" + line;
        System.out.println(respond);
    }

    private String listing() {
        String s = "";
        for (int i = 1; i <= list.size(); i++) {
            s += i + ". ";
            s += list.get(i - 1) + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I am xpz\nWhat can I do for you?\n" + line);
        Duke xpz = new Duke();
        while (!xpz.isFinished()) xpz.respond(xpz.read());
    }
}
