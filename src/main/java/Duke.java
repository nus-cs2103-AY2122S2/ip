import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARKED, ADDED, GIBBERISH, DELETE, NUMLEFT
    }
    private enum Styling {
        LINE
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Duke.speak(Dialogue.GREETING));
        Duke.runBot();
        System.out.println(Duke.speak(Dialogue.FAREWELL));
    }

    public static void runBot() {
        Scanner sc = new Scanner(System.in);
        List<Task> todo = new ArrayList<>();
        while (true) {
            try {
                String[] input = sc.nextLine().split(" ");
                System.out.println(style(Styling.LINE));
                if (input[0].equals("bye")) {
                    break;
                } else {
                    Duke.action(input, todo);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You don't have that many tasks.\n");
            } catch (DukeException e) {
                System.out.println(e);
            } finally {
                System.out.println(style(Styling.LINE));
            }
        }
        sc.close();
    }

    public static void action(String[] input, List<Task> todo) throws DukeException{
        StringBuilder obj = new StringBuilder("");
        switch (input[0]) {
            case "list":
                System.out.println(Duke.speak(Dialogue.LIST));
                Integer count = 1;
                for (int i = 0; i < todo.size(); i++) {
                    System.out.printf("%d. %s\n", count, todo.get(i));
                    count++;
                }
                break;
            case "mark":
            case "unmark":
                if (input.length != 2) {
                    throw new DukeException("Fill in proper integer for marking/unmarking.\n");
                }
                if (input[0].equals("mark")) {
                    todo.get(Integer.valueOf(input[1]) - 1).mark();
                } else {
                    todo.get(Integer.valueOf(input[1]) - 1).unmarked();
                }
                if (input[0].equals("mark")) {
                    System.out.println(Duke.speak(Dialogue.MARK));
                } else {
                    System.out.println(Duke.speak(Dialogue.UNMARKED));
                }
                System.out.println(todo.get(Integer.valueOf(input[1]) - 1));
                break;
            case "delete":
                if (input.length != 2) {
                    throw new DukeException("Fill in proper integer for deletion.\n");
                }
                todo.get(Integer.valueOf(input[1]) - 1);
                System.out.println(Duke.speak(Dialogue.DELETE));
                System.out.println(todo.get(Integer.valueOf(input[1]) - 1));
                todo.remove(Integer.valueOf(input[1]) - 1);
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                break;
            case "todo":
                if (input.length == 1) {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.\n");
                }
                for (int i = 1; i < input.length; i++) {
                    obj.append(input[i]);
                    obj.append(" ");
                }
                System.out.println(Duke.speak(Dialogue.ADDED));
                obj.setLength(obj.length()-1);
                todo.add(new ToDos(obj.toString()));
                System.out.println(todo.get(todo.size()-1));
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                break;
            case "deadline":
            case "event":
                if (input.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a " + input[0] + " cannot be empty.\n");
                }
                int i = 1;
                for (; i < input.length; i++) {
                    if (input[i].charAt(0) == '/') break;
                    obj.append(input[i]);
                    obj.append(" ");
                }
                if (i == input.length) {
                    throw new DukeException("☹ OOPS!!! There is no proper date for " + input[0] + ".\n");
                }
                StringBuilder date = new StringBuilder("");
                for (i = i+1; i < input.length; i++) {
                    date.append(input[i]);
                    date.append(" ");
                }
                obj.setLength(obj.length()-1);
                date.setLength(date.length()-1);
                if (input[0].equals("deadline")) {
                    todo.add(new DeadLine(obj.toString(), date.toString()));
                } else {
                    todo.add(new Events(obj.toString(), date.toString()));
                }
                System.out.println(Duke.speak(Dialogue.ADDED));
                System.out.println(todo.get(todo.size()-1));
                System.out.printf(Duke.speak(Dialogue.NUMLEFT, todo.size()));
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    public static String speak(Dialogue option, Integer num) {
        String reply;
        switch (option) {
            case NUMLEFT: reply =  "Now you have " + num.toString() + " task in the list\n";
                break;
            default: reply = "";
        }
        return reply;
    }

    public static String speak(Dialogue option) {
        String reply;
        switch (option) {
            case GREETING: reply =  "Hello! I'm Duke.\nWhat can I do for you?";
                break;
            case MARK: reply = "Nice! I've marked this task as done:\n";
                break;
            case UNMARKED: reply = "OK, I've marked this task as not done yet:\n";
                break;
            case DELETE: reply = "Noted. I've removed this task:\n";
                break;
            case ADDED: reply = "Got it. I've added this task:\n";
                break;
            case LIST: reply = "Here are the tasks in your list:\n";
                break;
            case FAREWELL: reply = "Bye. Hope to see you again soon!\n";
                break;
            default: reply = "Are you finally done?";
                break;
        }
        return reply;
    }

    public static String style(Styling option) {
        String style;
        switch (option) {
            case LINE: style =  "_________________________________________________________";
                break;
            default: style = "";
                break;
        }
        return style;
    }
}
