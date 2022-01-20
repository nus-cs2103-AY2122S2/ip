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

        public String toString() {
            return "[" + this.getStatusIcon() + "] " + this.description;
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
                } else if (command.split(" ")[0].equals("mark")) {
                    Task temp = todo_list.get(Integer.parseInt(command.split(" ")[1]) - 1);
                    temp.setStatus(true);
                    String output_list = "Nice! I've marked this task as done: \n  " + temp.toString();

                    System.out.print(outputChatBox(output_list));
                } else if (command.split(" ")[0].equals("unmark")) {
                    Task temp = todo_list.get(Integer.parseInt(command.split(" ")[1]) - 1);
                    temp.setStatus(false);
                    String output_list = "OK, I've marked this task as not done yet: \n  " + temp.toString();

                    System.out.print(outputChatBox(output_list));
                } else {
                    todo_list.add(new Task(command));

                    System.out.print(outputChatBox("added: " + command));
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
