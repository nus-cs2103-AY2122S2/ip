import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Duke {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting); // print greeting message

        ArrayList<Task> list = new ArrayList<>(); // init arraylist
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
                int index = Integer.parseInt(secondWord);

                if (firstWord.equals("mark")) {
                    Task targetTask = list.get((index - 1));
                    targetTask.mark();
                } else {
                    Task targetTask = list.get((index - 1));
                    targetTask.unmark();
                }
            } else if (firstWord.equals("delete")) { // delete task
                String secondWord = inputs[1];
                int index = Integer.parseInt(secondWord);

                Task targetTask = list.get((index - 1));
                targetTask.delete();
                System.out.println("Now you have " + list.size() + " tasks in your list.");
            } else if (firstWord.equals("todo")) { // adds a todo task
                if (inputs.length == 1) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    break;
                }
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
                    if (inputs.length == 1) {
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                        break;
                    }
                    try {
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Date formattedDate = dateFormatter.parse(date);
                        String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

                        Deadline deadline = new Deadline(input.substring(9, start - 1), dateToString);
                        list.add(deadline);
                        System.out.println("Got it. I've added this task:\n"
                                + deadline + "\nNow you have " + list.size() + " tasks in your list.");
                    } catch (ParseException e) {
                        System.out.println("Please enter a valid date!");
                    }
                } else { // adds an event task
                    if (inputs.length == 1) {
                        System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                        break;
                    }
                    try {
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
                        Date formattedDate = dateFormatter.parse(date);
                        String dateToString = new SimpleDateFormat("MMM-dd-yyyy HHmm").format(formattedDate);

                        Event event = new Event(input.substring(6, start - 1), dateToString);
                        list.add(event);
                        System.out.println("Got it. I've added this task:\n"
                                + event + "\nNow you have " + list.size() + " tasks in your list.");
                    } catch (ParseException e) {
                        System.out.println("Please enter a valid date!");
                    }
                }
            } else { // print error message
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
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

        public void delete() {
            System.out.println("Noted. I've removed this task: \n"
                        + toString());
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