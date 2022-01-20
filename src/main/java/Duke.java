import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting); // print greeting message

        ArrayList<Task> list = new ArrayList<Task>(); // init arraylist
        String input = "";

        while (!input.equals("bye")) {

            input = br.readLine(); // read input
            String[] inputs = input.split(" ");
            String firstWord = inputs[0];

            if (input.equals("bye")) { // check for exit condition
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) { // list all tasks
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) { // mark/unmark task
                String secondWord = inputs[1];
                if (firstWord.equals("mark")) {
                    Task targetTask = list.get((Integer.parseInt(secondWord) - 1));
                    targetTask.mark();
                } else {
                    Task targetTask = list.get((Integer.parseInt(secondWord) - 1));
                    targetTask.unmark();
                }
            } else if (firstWord.equals("todo")) { // adds a todo task
                ToDo toDo = new ToDo(input.substring(5));
                list.add(toDo);
                System.out.println("Got it. I've added this task:\n"
                        + toDo + "\nNow you have " + list.size() + " tasks in your list.");
            } else if (firstWord.equals("deadline") || firstWord.equals("event")) {
                int start = input.indexOf("/");
                if (start == -1) { // if / char cannot be found
                    System.out.println("Please enter a valid date!");
                    continue;
                }
                String date = input.substring(start + 4);
                if (firstWord.equals("deadline")) { // adds a deadline task
                    Deadline deadline = new Deadline(input.substring(9, start - 1), date);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:\n"
                            + deadline + "\nNow you have " + list.size() + " tasks in your list.");
                } else { // adds an event task
                    Event event = new Event(input.substring(6, start - 1), date);
                    list.add(event);
                    System.out.println("Got it. I've added this task:\n"
                            + event + "\nNow you have " + list.size() + " tasks in your list.");
                }
            } else { // default task that is not a todo/event/deadline
                Task task = new Task(input);
                list.add(task);
                System.out.println("Got it. I've added this task:\n"
                        + task + "\nNow you have " + list.size() + " tasks in your list.");
            }
        }
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void mark() {
            if (!isDone) {
                isDone = true;
                System.out.println("Nice! I've marked this task as done: \n"
                        + toString());
            } else {
                System.out.println("This task is already done! \n"
                        + toString());
            }
        }

        public void unmark() {
            if (isDone) {
                isDone = false;
                System.out.println("OK, I've marked this task as not done yet: \n"
                        + toString());
            } else {
                System.out.println("This task has not been completed! \n"
                        + toString());
            }
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by + ")";
        }
    }

    public static class Event extends Task {

        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + at + ")";
        }
    }

}
