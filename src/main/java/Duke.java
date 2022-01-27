import java.io.IOException;
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


    /**
     * The main body of the chat box. Will receive commands and do things accordingly
     **/
    public static ArrayList<Task> todo_list = new ArrayList<Task>();
    static final String farewell_words = "Bye. Hope to see you again soon!";


    private static class processIO {
        static boolean run() throws EmptyDescriptionException,  EmptyTimeException, InvalidCommandException, InvalidNumberException {
            Scanner scanner = new Scanner(System.in);

            String command = scanner.nextLine();
            if (command.equals("bye") || command.equals("Bye")) {
                System.out.print(outputChatBox(farewell_words));
                return false;
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
                    int task_id = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (task_id < 0 || task_id >= todo_list.size()) {
                        throw new InvalidNumberException();
                    }
                    Task temp = todo_list.get(task_id);
                    temp.setStatus(true);
                    String output_list = "Nice! I've marked this task as done: \n  " + temp.toString();

                    System.out.print(outputChatBox(output_list));
                } else if (task_type.equals("unmark")) {
                    int task_id = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (task_id < 0 || task_id >= todo_list.size()) {
                        throw new InvalidNumberException();
                    }
                    Task temp = todo_list.get(task_id);
                    temp.setStatus(false);
                    String output_list = "OK, I've marked this task as not done yet: \n  " + temp.toString();

                    System.out.print(outputChatBox(output_list));
                } else if (task_type.equals("todo")){
                    String description = command.substring(task_type.length());
                    if (description.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    ToDo temp = new ToDo(description);
                    todo_list.add(temp);
                    String output_text = "Got it. I've added this task:\n    " + temp.toString()
                            + "\nNow you have " + todo_list.size() + " tasks in the list.";
                    System.out.print(outputChatBox(output_text));
                } else if (task_type.equals("deadline")) {
                    String description = command.substring(task_type.length()).split("/")[0];
                    if (description.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    String task_by = command.substring(task_type.length()).split("/")[1].substring(3);
                    if (task_by.length() == 0) {
                        throw new EmptyTimeException();
                    }
                    Deadline temp = new Deadline(description, task_by);
                    todo_list.add(temp);
                    String output_text = "Got it. I've added this task:\n    " + temp.toString()
                            + "\nNow you have " + todo_list.size() + " tasks in the list.";
                    System.out.print(outputChatBox(output_text));
                } else if (task_type.equals("event")) {
                    String description = command.substring(task_type.length()).split("/")[0];
                    if (description.length() == 0) {
                        throw new EmptyDescriptionException();
                    }
                    String task_time = command.substring(task_type.length()).split("/")[1].substring(3);
                    if (task_time.length() == 0) {
                        throw new EmptyTimeException();
                    }
                    Event temp = new Event(description, task_time);
                    todo_list.add(temp);
                    String output_text = "Got it. I've added this task:\n    " + temp.toString()
                            + "\nNow you have " + todo_list.size() + " tasks in the list.";
                    System.out.print(outputChatBox(output_text));
                } else if (task_type.equals("delete")) {
                    int task_id = Integer.parseInt(command.split(" ")[1]) - 1;
                    if (task_id < 0 || task_id >= todo_list.size()) {
                        throw new InvalidNumberException();
                    }
                    Task temp = todo_list.get(task_id);
                    todo_list.remove(task_id);
                    String output_list = " Noted. I've removed this task:\n    " + temp.toString()
                            + "\nNow you have " + todo_list.size() + " tasks in the list.";

                    System.out.print(outputChatBox(output_list));
                } else {
                    throw new InvalidCommandException();
                }
                try {
                    LocalFileIO.saveFile(todo_list);
                } catch (IOException e) {
                    System.out.println(outputChatBox("Oops, seems like there is an error when writing to local saved files"));
                }
            }
            return true;
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
        try {
            LocalFileIO.init(todo_list);
        } catch(IOException e) {
            System.out.println(outputChatBox("OOPS!!! Seems like there is error when creating the local files"));
        }
        while(true) {
            try {
                if(!processIO.run()) break;
            } catch (EmptyDescriptionException e) {
                System.out.println(outputChatBox("OOPS!!! The description cannot be empty. :-("));
            } catch (InvalidCommandException e) {
                System.out.println(outputChatBox("OOPS!!! I'm sorry, but I don't know what that means :-("));
            } catch (EmptyTimeException e) {
                System.out.println(outputChatBox("OOPS!!! The time cannot be empty. :-("));
            } catch (InvalidNumberException e) {
                System.out.println(outputChatBox("OOPS!!! Seems like this is a invalid number :-("));
            }
        }
    }
}

