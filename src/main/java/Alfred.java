import java.util.ArrayList;
import java.util.Scanner;

public class Alfred {
    // class constants
    private static String GREETING = "Hello! My name is Alfred.\n"
            + "How can I be of service?";
    private static String BYE = "Bye! Hope I was of service.";
    private static int BREAK_CHAR_LENGTH = 100;;
    private String BREAK_LINE = this.line();

    // functional attributes
    ArrayList<Task> task_list;

    Alfred() {
        this.task_list = new ArrayList<Task>();
    }

    private String line() {
        String out = "";
        for (int i = 0; i < this.BREAK_CHAR_LENGTH; i++) {
            out += "-";
        }
        out += "\n";
        return out;
    }

    private void sandwich_and_print(String text) {
        String out = "";
        out += this.BREAK_LINE;
        out += text + "\n";
        out += this.BREAK_LINE;
        System.out.println(out);
    }

    private void greeting() {
        this.sandwich_and_print(this.GREETING);
    }

    private void bye() {
        this.sandwich_and_print(this.BYE);
    }

    private void add_task(String description) {
        this.task_list.add(new Task(description));
        String text = "added: " + description;
        this.sandwich_and_print(text);
    }

    private void list_tasks() {
        String out = "";
        out += this.BREAK_LINE;
        out += "Sir, here are the things you need to do:\n";
        for (int i = 1; i < this.task_list.size() + 1; i++) {
            out += i + ". " + this.task_list.get(i - 1).toString() + "\n";
        }
        out += this.BREAK_LINE;
        System.out.println(out);
    }

    private void mark_task(int task_id) {
        // update data state
        this.task_list.get(task_id).mark_complete();

        // return representation
        String text = "Good job sir. I've marked this as complete.\n";
        text += this.task_list.get(task_id).toString();
        this.sandwich_and_print(text);
    }

    private void unmark_task(int task_id) {
        // update data state
        this.task_list.get(task_id).mark_incomplete();

        // return representation
        String text = "I see, no worries sir. I've marked this as to-be-done.\n";
        text += this.task_list.get(task_id).toString();
        this.sandwich_and_print(text);
    }

    private void read_input(String input) {
        if (input.equals("list")) {
            this.list_tasks();
        } else if (input.startsWith("mark")) {
            int task_id = Integer.valueOf(input.split(" ")[1]) - 1;
            this.mark_task(task_id);
        } else if (input.startsWith("unmark")) {
            int task_id = Integer.valueOf(input.split(" ")[1]) - 1;
            this.unmark_task(task_id);
        } else {
            this.add_task(input);
        }
    }


    public static void main(String[] args) {
        // initialize
        Scanner sc = new Scanner(System.in);
        Alfred bot = new Alfred();

        // greet
        bot.greeting();

        // input
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            bot.read_input(input);
            input = sc.nextLine();
        }

        // close
        bot.bye();

    }
}
