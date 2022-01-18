import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Alfred {
    // class constants
    private static final String GREETING = "Hello! My name is Alfred.\n"
            + "How can I be of service?";
    private static final String BYE = "Bye! Hope I was of service.";
    private static final int BREAK_CHAR_LENGTH = 100;
    private final String BREAK_LINE = this.line();

    // functional attributes
    ArrayList<Task> task_list;

    Alfred() {
        this.task_list = new ArrayList<Task>();
    }

    private String line() {
        String out = "";
        for (int i = 0; i < Alfred.BREAK_CHAR_LENGTH; i++) {
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
        this.sandwich_and_print(Alfred.GREETING);
    }

    private void bye() {
        this.sandwich_and_print(Alfred.BYE);
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

    private void delete_task(int task_id) {
        // update data state
        Task task = this.task_list.remove(task_id);

        // return representation
        String text = "Noted sir. I've removed the following task:\n";
        text += task.toString();
        this.sandwich_and_print(text);
    }

    private void read_input(String input) throws InvalidCommandException, InvalidInputException, InvalidIndexException, MissingInputException {
        // read in arguments
        String[] arguments = input.split(" ");
        String command = arguments[0];

        // case by case, with checks
        // LIST
        if ((command.equals("list")) && (arguments.length == 1)) {
            this.list_tasks();

        // (UN)MARK and DELETE
        } else if (command.equals("mark") || command.equals("unmark") || command.equals("delete")) { // validity check for (un)mark
            if (arguments.length != 2) {
                throw new InvalidInputException();
            }
            int task_id;
            try {
                task_id = Integer.valueOf(arguments[1]) - 1;
            } catch (NumberFormatException nfe) {
                throw new InvalidInputException();
            }
            if (task_id >= this.task_list.size()) {
                throw new InvalidIndexException();
            }
            // checks complete
            if (command.equals("mark")) {
                this.mark_task(task_id);
            } else if (command.equals("unmark")) {
                this.unmark_task(task_id);
            } else {
                this.delete_task(task_id);
            }

        // DEADLINE
        } else if (command.equals("deadline")) {
            String s = input.substring(8); // select those after keyword
            String[] arg = s.split(" /by ");
            arg = Arrays.stream(arg).filter(in -> !in.isEmpty()).toArray(String[]::new); // filter away empty strings
            if (arg.length < 2) {
                throw new InvalidInputException();
            }
            this.add_deadline(arg[0], arg[1]);

        } else if (command.equals("todo")) {
            String descripton = input.substring(4);
            if ((descripton.length() < 1) || descripton.split(" ").length == 0) {
                throw new MissingInputException();
            }
            this.add_todo(descripton);

        } else if (command.equals("event")) {
            String s = input.substring(5);
            String[] arg = s.split(" /at ");
            arg = Arrays.stream(arg).filter(in -> !in.isEmpty()).toArray(String[]::new); // filter away empty strings
            if (arg.length < 2) {
                throw new InvalidInputException();
            }
            this.add_event(arg[0], arg[1]);
        } else {
            // invalid command
           throw new InvalidCommandException();
        }
    }

    private void handle_alfredException(AlfredException e) {
        this.sandwich_and_print(e.getMessage());
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
            try {
                bot.read_input(input);
            } catch(AlfredException e) {
                bot.handle_alfredException(e);
            }
            input = sc.nextLine();
        }

        // close
        bot.bye();

    }
}
