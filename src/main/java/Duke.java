
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
        else if (input.length() == 6 && input.startsWith("mark "))
            return mark(input);
        else return add(input);
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
            return "Nice! I've marked this task as done:\n  " + t;
        }
        catch (Exception e){
            this.list.add(new Task(str, Type.TODO));
            return "added: " + str;
        }
    }

    private String add(String input) {
        try {
            Task t;
            if (input.startsWith("todo ")) t = new Task(input.split(" ", 2)[1], Type.TODO);
            else if (input.startsWith("event ")) {
                t = new Task(input.split("/")[0].split(" ", 2)[1], Type.EVENT);
                t.setTime(input.split("/at ", 2)[1]);
            } else {
                t = new Task(input.split("/")[0].split(" ", 2)[1], Type.DEADLINE);
                t.setTime(input.split("/by ", 2)[1]);
            }
            this.list.add((t));
            return "Got it. I've added this task:\n  " +
                    t + "\nNow you have " + this.list.size() + " tasks in the list.";
        }
        catch (Exception e) {   //array index out of bound
            //do something
            return "Bad input, please try again";
        }
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("Hello! I am xpz\nWhat can I do for you?\n" + line);
        Duke xpz = new Duke();
        while (!xpz.isFinished()) xpz.respond(xpz.read());
    }
}
