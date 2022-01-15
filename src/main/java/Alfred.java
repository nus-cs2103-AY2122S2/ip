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

    private void add_task(Task task) {
        this.task_list.add(task);
        String text = "Yes sir, I've added this task.\n";
        text += task.toString() + "\n";
        text += this.summarize_list();
        this.sandwich_and_print(text);
    }

    private void add_todo(String description) {
        Task task = new ToDo(description);
        this.add_task(task);
    }

    private void add_deadline(String description, String deadline) {
        Task task = new Deadline(description, deadline);
        this.add_task(task);
    }

    private void add_event(String description, String dateAndTime) {
        Task task = new Event(description, dateAndTime);
        this.add_task(task);
    }

    private String summarize_list() {
        return "Now you have " + this.task_list.size() + " task(s) in the your list.";
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
        } else if (input.startsWith("deadline")) {
            String s = input.substring(8);
            String[] arguments = s.split("/by ");
            this.add_deadline(arguments[0], arguments[1]);
        } else if (input.startsWith("todo")) {
            String descripton = input.substring(4);
            this.add_todo(descripton);
        } else if (input.startsWith("event")) {
            String s = input.substring(5);
            String[] arguments = s.split("/at ");
            this.add_event(arguments[0], arguments[1]);
        } else {
           // do nothing
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
