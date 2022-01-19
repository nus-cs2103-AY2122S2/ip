import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Introduction
        String introduction = "____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________";
        System.out.println(introduction);

        // Scanner for new input
        Scanner sc = new Scanner(System.in);

        // ToDoList for the program
        Calendar calendar = new Calendar();

        while (true) {
            try {
                String command = sc.next();
                String input = sc.nextLine();
                command = command.trim(); //remove whitespace at back of input
                input = input.trim(); // remove whitespace at front of input

                switch (command) {
                    case "bye":
                        output("Bye! Hope to see you again soon.");
                        return;
                    case "list":
                        output(calendar.toString());
                        continue;
                    case "todo":
                        if (input.length() == 0) {
                            throw new EmptyInputException("Sorry!! The description of a todo cannot be empty.");
                        }
                        Todo newTodo = new Todo(input);
                        calendar.add(newTodo);
                        taskAdded(newTodo.toString(), calendar.size());
                        continue;
                    case "deadline":
                        if (input.length() == 0) {
                            throw new EmptyInputException("Sorry!! The description of a deadline cannot be empty.");
                        }
                        String[] processedDeadline = input.split("/", 2);
                        Deadline newDeadline = new Deadline(processedDeadline[0], processedDeadline[1]);
                        calendar.add(newDeadline);
                        taskAdded(newDeadline.toString(), calendar.size());
                        continue;
                    case "event":
                        if (input.length() == 0) {
                            throw new EmptyInputException("Sorry!! The description of an event cannot be empty");
                        }
                        String[] processedEvent = input.split("/", 2);
                        Event newEvent = new Event(processedEvent[0], processedEvent[1]);
                        calendar.add(newEvent);
                        taskAdded(newEvent.toString(), calendar.size());
                        continue;
                    case "mark":
                        if (input.length() == 0) {
                            throw new EmptyInputException("Sorry!! I need to know which task you want to mark as done!");
                        }
                        try {
                            calendar.get(Integer.valueOf(input)).complete();
                            output("Nice! I've marked this task as done for you.\n   " + calendar.get(Integer.valueOf(input)));
                        } catch (IndexOutOfBoundsException ioobe) {
                            throw new InvalidListIndex(String.format("Sorry! The list only has %s values but the input value was %s.", calendar.size(), input));
                        } catch (NumberFormatException nfe) {
                            throw new InvalidListIndex(String.format("Sorry! This command expected an integer value but obtained the value %s", input));
                        }
                        continue;
                    case "unmark":
                        if (input.length() == 0) {
                            throw new EmptyInputException("Sorry!! I need to know which task you want to mark as incomplete!");
                        }
                        try {
                            calendar.get(Integer.valueOf(input)).incomplete();
                            output("OK! I've marked this task as incomplete for you.\n   " + calendar.get(Integer.valueOf(input)));
                        } catch (IndexOutOfBoundsException ioobe) {
                            throw new InvalidListIndex(String.format("Sorry! The list only has %s values but the input value was %s.", calendar.size(), input));
                        } catch (NumberFormatException nfe) {
                            throw new InvalidListIndex(String.format("Sorry! This command expected an integer value but obtained the value %s", input));
                        }
                        continue;
                    default:
                        throw new InvalidCommandException();
                }
            } catch (DukeException e) {
                output(e.getMessage());
            }
        }
    }

    static void output(String message) {
        System.out.println(String.format("____________________________________________________________\n%s\n____________________________________________________________", message));
    }

    static void taskAdded(String task, int size) {
        output(String.format("Got it. I've added this task:\n   %s\nNow you have %s tasks in the list.", task, size));
    }
}
