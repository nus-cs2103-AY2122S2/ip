import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * Convert the plain output text to chat-box style with indentation for output
     *
     * @param text : String (output text)
     * @return chat-box style output text : String
     */
    private static String outputChatBox(String text) {
        return "    ____________________________________________________________\n"
                + text.replaceAll("(?m)^", "     ") + "\n"
                + "    ____________________________________________________________\n";
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

        public void setStatus(boolean new_status) {
            this.isDone = new_status;
        }

        @Override
        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
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
        protected String event_time;

        public Event(String description, String event_time) {
            super(description);
            this.event_time = event_time;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (at: " + event_time + ")";
        }
    }
    /**
     * The main body of the chat box. Will receive commands and do things accordingly
     **/
    private static class processIO {
        static void run() {
            Scanner scanner = new Scanner(System.in);
            String farewell_words = "Bye. Hope to see you again soon!";

            ArrayList<Task> todo_list = new ArrayList<Task>();

            while(true) {
                String command = scanner.nextLine();
                if (command.equals("bye") || command.equals("Bye")) {
                    System.out.print(outputChatBox(farewell_words));
                    break;
                } else if (command.equals("list")){
                    String output_list = "";
                    if (todo_list.isEmpty()) {
                        output_list = "Currently the list is empty. There is nothing to output.";
                    } else {
                        for (int i = 1; i <= todo_list.size(); i++) {
                            output_list = output_list.concat(i + "." + todo_list.get(i - 1));
                            if (i != todo_list.size()) output_list = output_list.concat("\n");
                        }
                    }

                    System.out.print(outputChatBox(output_list));
                } else {
                    String task_type = command.split(" ")[0];
                    if (task_type.equals("mark")) {
                        Task temp = todo_list.get(Integer.parseInt(command.split(" ")[1]) - 1);
                        temp.setStatus(true);
                        String output_list = "Nice! I've marked this task as done: \n  " + temp.toString();

                        System.out.print(outputChatBox(output_list));
                    } else if (task_type.equals("unmark")) {
                        Task temp = todo_list.get(Integer.parseInt(command.split(" ")[1]) - 1);
                        temp.setStatus(false);
                        String output_list = "OK, I've marked this task as not done yet: \n  " + temp.toString();

                        System.out.print(outputChatBox(output_list));
                    } else if (task_type.equals("todo")){
                        ToDo temp = new ToDo(command.substring(task_type.length()));
                        todo_list.add(temp);
                        String output_text = "Got it. I've added this task:\n    " + temp.toString()
                                + "\nNow you have " + todo_list.size() + " tasks in the list.";
                        System.out.print(outputChatBox(output_text));
                    } else if (task_type.equals("deadline")) {
                        String description = command.substring(task_type.length()).split("/")[0];
                        String task_by = command.substring(task_type.length()).split("/")[1].substring(3);
                        Deadline temp = new Deadline(description, task_by);
                        todo_list.add(temp);
                        String output_text = "Got it. I've added this task:\n    " + temp.toString()
                                + "\nNow you have " + todo_list.size() + " tasks in the list.";
                        System.out.print(outputChatBox(output_text));
                    } else if (task_type.equals("event")) {
                        String description = command.substring(task_type.length()).split("/")[0];
                        String task_time = command.substring(task_type.length()).split("/")[1].substring(3);
                        Event temp = new Event(description, task_time);
                        todo_list.add(temp);
                        String output_text = "Got it. I've added this task:\n    " + temp.toString()
                                + "\nNow you have " + todo_list.size() + " tasks in the list.";
                        System.out.print(outputChatBox(output_text));
                    }
                }
            }

            return ;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Pyke\n" + "What can I do for you?";
        System.out.print(outputChatBox(greeting));
        processIO.run();
    }
}
