
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String line =
            "____________________________________________________________\n";
    private final Scanner s = new Scanner(System.in);
    private boolean finished = false;
    private final ArrayList<Task> list = new ArrayList<>();

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
        else if (input.length() == 6 && input.substring(0, 5).equals("mark "))
            return mark(input);
        else this.list.add(new Task(input));
        return "added: " + input;
    }

    private void respond(String respond) {
        respond = line + respond + "\n" + line;
        System.out.println(respond);
    }

    private String listing() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += (i + 1) + ".";
            s += list.get(i) + "\n";
        }
        return s;
    }

    private String mark(String str) {
        try{
            int i = Integer.parseInt(str.substring(5)) - 1;
            Task t = list.get(i);
            t.mark();
            return "Nice! I've marked this task as done:\n  " + t.toString();
        }
        catch (Exception e){
            this.list.add(new Task(str));
            return "added: " + str;
        }
    }


    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I am xpz\nWhat can I do for you?\n" + line);
        Duke xpz = new Duke();
        while (!xpz.isFinished()) xpz.respond(xpz.read());
    }
}
