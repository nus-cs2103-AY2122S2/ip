import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);

        ArrayList<Task> list = new ArrayList<Task>();
        String input = "";

        while (!input.equals("bye")) {

            input = br.readLine();
            String[] inputs = input.split(" ");
            String firstWord = inputs[0];

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i));
                }
            } else if (firstWord.equals("mark") || firstWord.equals("unmark")) {
                String secondWord = inputs[1];
                if (firstWord.equals("mark")) {
                    Task targetTask = list.get((Integer.parseInt(secondWord) - 1));
                    targetTask.mark();
                } else {
                    Task targetTask = list.get((Integer.parseInt(secondWord) - 1));
                    targetTask.unmark();
                }
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
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
}
