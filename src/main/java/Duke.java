import java.util.*;
import java.util.regex.PatternSyntaxException;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you? =)");
        TaskList tl = new TaskList();

        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) { break; }
            if (s.equals("list")) { tl.printItems(); }

            // mark, unmark, todo, deadline, event
            else {
                try {
                    String command = processCommand(s);

                    if (command.equals("mark")) {
                        int index = processNumericalDescription(s, "mark", tl.size());
                        System.out.println(tl.markItemDone(index));
                    } else if (command.equals("unmark")) {
                        int index = processNumericalDescription(s, "unmark", tl.size());
                        System.out.println(tl.markItemUndone(index));
                    } else {
                        if (command.equals("todo")) {
                            String description = processStringDescription(s, "todo");
                            tl.addTodo(description);
                        } else if (command.equals("deadline")) {
                            String[] params = processFormatDescription(s, "deadline", "/by");
                            tl.addDeadline(params[0], params[1]);
                        } else if (command.equals("event")) {
                            String[] params = processFormatDescription(s, "event", "/at");
                            tl.addEvent(params[0], params[1]);
                        }

                        System.out.print("added o.O:\n  ");
                        System.out.println(tl.getLast());
                        System.out.println("Now there are " + tl.size() + " tasks on the list x)");
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        // bye
        System.out.println("Bye t_t");
    }

    private static String processCommand(String s) throws WrongInputException {
        String[] inputs = s.split(" ");
        String[] acceptableInputs = new String[]{"mark","unmark","todo","deadline","event"};
        boolean isAcceptable = false;
        for (int i = 0; i < acceptableInputs.length; i++) {
            if (acceptableInputs[i].equals(inputs[0])) {
                isAcceptable = true;
                break;
            }
        }
        if (!isAcceptable) {
            throw new WrongInputException("D: D: D: I don't understand the input D: D: D:");
        }
        return inputs[0];
    }

    private static int processNumericalDescription(String s, String command, int length) throws WrongInputException {
        String[] inputs = s.split(command + " ");
        if (length == 0) {
            throw new WrongInputException("D: D: D: There are no items in the list D: D: D:");
        }
        if (inputs.length > 2) {
            throw new WrongInputException("D: D: D: The " + command +
                    " should be followed by a single number D: D: D:");
        }
        int tasknumber;
        try {
            tasknumber = Integer.parseInt(inputs[1]);
        } catch (NumberFormatException e) {
            throw new WrongInputException("D: D: D: The input doesn't seem to contain a number D: D: D:");
        }
        if (tasknumber > length || tasknumber < 1) {
            throw new WrongInputException("D: D: D: The task number isn't in the list D: D: D:");
        }
        return tasknumber - 1;
    }

    private static String[] processFormatDescription(String s, String command, String format)
            throws WrongInputException,IncompleteInputException {
        try {
            String[] inputs = s.split(" " + format + " ");
            if (inputs.length < 2) {
                throw new IncompleteInputException("D: D: D: The description of a " + command +
                        " is incorrect or empty D: D: D:");
            }
            String[] params = new String[2];
            if (inputs.length == 2) {
                String[] desc = inputs[0].split(command + " ");
                if (desc.length == 2) {
                    params[0] = desc[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                    params[0] = firstPart.toString();
                }
                params[1] = inputs[1];
            } else {
                String[] desc = inputs[0].split(command + " ");
                if (desc.length == 2) {
                    params[0] = desc[1];
                } else {
                    StringBuilder firstPart = new StringBuilder();
                    for (int i = 1; i < desc.length; i++) {
                        firstPart.append(desc[i]);
                        if (i < desc.length - 1) {
                            firstPart.append(command + " ");
                        }
                    }
                    params[0] = firstPart.toString();
                }
                StringBuilder secondPart = new StringBuilder();
                for (int i = 1; i < inputs.length; i++) {
                    secondPart.append(inputs[i]);
                    if (i < inputs.length - 1) {
                        secondPart.append(format + " ");
                    }
                }
                params[1] = secondPart.toString();
            }
            return params;
        } catch (PatternSyntaxException e) {
            throw new WrongInputException("D: D: D: Please specify the timing using " + format + " D: D: D:");
        }
    }

    private static String processStringDescription(String s, String command) throws IncompleteInputException {
        String[] inputs = s.split(command + " ");
        if (inputs.length == 1) {
            throw new IncompleteInputException("D: D: D: The description of a " + command +
                    " cannot be empty D: D: D:");
        }
        if (inputs.length == 2) {
            return inputs[1];
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i < inputs.length; i++) {
            output.append(inputs[i]);
            if (i < inputs.length - 1) {
                output.append(command + " ");
            }
        }
        return output.toString();
    }

    public static void echo(String s) {
        System.out.println("  " + s);
    }
}
