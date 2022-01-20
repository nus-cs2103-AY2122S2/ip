import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws InvalidDescriptorException,
            InvalidCommandException, MissingActionException, IndexOutOfBoundsException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\t____________________________________________________________");
        System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
        System.out.println("\t____________________________________________________________");

        // Initialise array of Tasks;
//        Task[] tasks = new Task[100];
        ArrayList<Task> tasks = new ArrayList<>(100);
        int count = 0;

        while (true) {
            String input = sc.nextLine();
            String[] tokens = input.split(" ");
            String command = tokens[0];
            int sizeOfInputArr = tokens.length;

            String name = "";
            for (int i = 1; i < sizeOfInputArr - 1; i++) {
                name = name.concat(tokens[i]);
                name = name.concat(" ");
            }
            name = name.concat(tokens[sizeOfInputArr - 1]);

            try {
                if (sizeOfInputArr <= 0) {
                    throw new MissingActionException("\t ☹ Woof Woof!!! No action taken!!!");
                } else if (sizeOfInputArr == 1) {
                    // if command = "bye"
                    if (input.equals("bye")) {
                        System.out.println("\t Woof woof! Hope to see you again soon!");
                        break;

                        // if command = "list"
                    } else if (input.equals("list")) {
                        System.out.println("\t____________________________________________________________");
                        System.out.println("\t Here are the tasks in your list:");
                        for (int taskCount = 0; taskCount < count; taskCount++) {
                            Task mainTask = tasks.get(taskCount);
                            System.out.println("\t " + (taskCount + 1) + "." + mainTask.track()
                                    + mainTask.status + " " + mainTask.toString());
                        }
                        System.out.println("\t____________________________________________________________");
                        continue;
                    } else {
                        throw new InvalidDescriptorException("\t ☹ Woof Woof!!! You haven't given me enough information for this action!!!");
                    }
                } else {
                    if (command.equals("todo") || command.equals("event") || command.equals("deadline")
                            || command.equals("mark") || command.equals("unmark") || command.equals("delete")) {
                        // if command = "mark x" where x is the task number
                        if (command.equals("mark")) {
                            String taskNumStr = tokens[1];
                            int taskNum = Integer.parseInt(taskNumStr) - 1;
                            try {
                                tasks.get(taskNum).mark();
                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t Nice! I've marked this task as done:");
                                System.out.println("\t\t" + tasks.get(taskNum).track() + tasks.get(taskNum).status
                                        + " " + tasks.get(taskNum));
                                System.out.println("\t____________________________________________________________");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
                                System.out.println("\t____________________________________________________________");
                            } finally {
                                continue;
                            }
                        }
                        // if command = "unmark x" where x is the task number
                        if (command.equals("unmark")) {
                            String taskNumStr = tokens[1];
                            int taskNum = Integer.parseInt(taskNumStr) - 1;
                            try {
                                tasks.get(taskNum).unmark();
                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t OK, I've marked this task as not done yet:");
                                System.out.println("\t\t" + tasks.get(taskNum).track() + tasks.get(taskNum).status
                                        + " " + tasks.get(taskNum));
                                System.out.println("\t____________________________________________________________");
                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
                                System.out.println("\t____________________________________________________________");
                            } finally {
                                continue;
                            }
                        }

                        // if command = "todo"
                        if (command.equals("todo")) {
                            Todo todo = new Todo(name);
                            tasks.add(todo);
                            System.out.println("\t____________________________________________________________");
                            System.out.println("\t Got it. I've added this task:");
                            System.out.println("\t\t " + todo.track() + todo.status + " " + todo.toString());
                            System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                            System.out.println("\t____________________________________________________________");
                        }

                        // if command = "event"
                        if (command.equals("event")) {
                            String[] tokensEvent = input.split("/");
                            String time = tokensEvent[1];

                            String[] tokensName = name.split("/");
                            String eventName = tokensName[0];
                            Event event = new Event(eventName, time);
                            tasks.add(event);

                            System.out.println("\t____________________________________________________________");
                            System.out.println("\t Got it. I've added this task:");
                            System.out.println("\t\t " + event.track() + event.status + " " + event.toString());
                            System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                            System.out.println("\t____________________________________________________________");


                        }

                        // if command = "deadline"
                        if (command.equals("deadline")) {
                            String[] tokensDeadline = input.split("/");
                            String date = tokensDeadline[1];

                            String[] tokensName = name.split("/");
                            String deadlineName = tokensName[0];
                            Deadline deadline = new Deadline(deadlineName, date);
                            tasks.add(deadline);

                            System.out.println("\t____________________________________________________________");
                            System.out.println("\t Got it. I've added this task:");
                            System.out.println("\t\t " + deadline.track() + deadline.status + " " + deadline.toString());
                            System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
                            System.out.println("\t____________________________________________________________");
                        }

                        // if command = "delete"
                        if (command.equals("delete")) {
                            int toBeDeleted = Integer.parseInt(tokens[1]) - 1;


                            if (tokens.length == 1) {
                                throw new InvalidCommandException(
                                        "\t☹ Woof Woof!!! You haven't provided me a task number to delete!!!");
                            }

                            try {
                                Task deleteTask = tasks.get(toBeDeleted);
                                tasks.remove(toBeDeleted);
                                count--;

                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t Noted. I've removed this task:");
                                System.out.println("\t\t " + deleteTask.track() + deleteTask.status + " " + deleteTask.toString());
                                System.out.println("\t Now you have " + count + " tasks in the list.");
                                System.out.println("\t____________________________________________________________");

                            } catch (IndexOutOfBoundsException e) {
                                System.out.println("\t____________________________________________________________");
                                System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
                                System.out.println("\t____________________________________________________________");
                            } finally {
                                continue;
                            }
                        }
                        count++;
                    } else {
                        throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
                    }
                }
            } catch (InvalidDescriptorException | MissingActionException | InvalidCommandException e) {
                System.out.println("\t____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("\t____________________________________________________________");
            }
        }
        tasks.clear();
        sc.close();
    }
}
