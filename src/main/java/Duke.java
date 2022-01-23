import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int totalTasks = 0;

    private static String topLine = "    ――――――――――――――――――――――――――――――――――\n    ";
    private static String bottomLine = "    ――――――――――――――――――――――――――――――――――\n";
    private static String indent = "    ";

    private static String listStatus(int totalTasks) {
        if (totalTasks > 1) {
            return "There are " + totalTasks + " tasks in the list now.\n";
        } else {
            return "There is " + totalTasks + " task in the list now.\n";
        }
    }

    private static void printList() {
        if (totalTasks == 0) {
            System.out.print("There are no tasks in your list.\n");
        } else {
            System.out.print("Here are the tasks in your list:\n");
            int index = 1;
            for (int n = 0; n < totalTasks; n++) {
                Task t = tasks.get(n);
                System.out.println(indent + index + "." + t.toString());
                index++;
            }
        }
    }

    private static void deleteTask(String[] inputArr) {
        int taskNum = Integer.parseInt(inputArr[1]);
        Task t = tasks.remove(taskNum - 1);
        totalTasks--;
        System.out.print(topLine + "Okay, I've deleted this task:\n  "
                + indent + t + "\n" + indent + listStatus(totalTasks) + bottomLine);
    }

    private static void markTask(Command command, String[] inputArr) {
        int taskNum = Integer.parseInt(inputArr[1]);
        Task t = tasks.get(taskNum - 1);

        if (command.equals(Command.MARK)) { // mark task as done
            System.out.print(topLine + "Nice! You've completed this task:\n  ");
            t.markAsDone();
        } else { // unmark task
            System.out.print(topLine + "Okay, I've marked this task as undone:\n  ");
            t.markAsUndone();
        }
        System.out.print(indent + t + "\n" + bottomLine);
    }

    private static void addTask(Command command, String input) throws DukeException {
        if (command.equals(Command.TODO)) {
            String str = input.substring(5);
            tasks.add(new Todo(str));

        } else if (command.equals(Command.DEADLINE)) {
            String str = input.substring(9);
            String[] strArr = str.split(" /by ");
            if (strArr.length == 1) {
                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                        + bottomLine);
            } else {
                tasks.add(new Deadline(strArr[0], strArr[1]));
            }

        } else {
            String str = input.substring(6);
            String[] strArr = str.split(" /at ");
            if (strArr.length == 1) {
                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                        + bottomLine);
            } else {
                tasks.add(new Event(strArr[0], strArr[1]));
            }
        }
        totalTasks++;
        System.out.print(topLine + "Got it! I've added this task:\n  "
                + indent + tasks.get(totalTasks - 1).toString() + "\n"
                + indent + listStatus(totalTasks) + bottomLine);
    }

    public static void main(String[] args) throws IOException {
        String logo = "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String intro = "    Hello! I'm Duke!\n"
                + "    What can I do for you?\n";

        // Print introduction
        System.out.print(logo + "\n" + intro + bottomLine);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while (!input.equals("bye")) {

            try {
                String[] inputArr = input.split(" ");
                String c = inputArr[0].toUpperCase();
                Command command = Command.valueOf(c);

                switch (command) {
                case LIST:
                    System.out.print(topLine);
                    printList();
                    System.out.print(bottomLine);
                    break;
                case MARK:
                    // Fallthrough
                case UNMARK:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine
                                + "Sorry, I don't know which task you would like to mark :(\n"
                                + bottomLine);
                    } else {
                        markTask(command, inputArr);
                    }
                    break;
                case DELETE:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine
                                + "Sorry, I don't know which task you would like to delete :(\n"
                                + bottomLine);
                    } else {
                        deleteTask(inputArr);
                    }
                    break;
                case TODO:
                    // Fallthrough
                case DEADLINE:
                    // Fallthrough
                case EVENT:
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine + "Oops, the task needs a description!\n" + bottomLine);
                    } else {
                        addTask(command, input);
                    }
                    break;
                }
            } catch (DukeException e) {
                System.out.print(e.message);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(topLine + "Invalid task number. " + listStatus(totalTasks) + bottomLine);
            } catch (IllegalArgumentException e) {
                System.out.print(topLine + "Oh no! I don't understand what that means...\n" + bottomLine);
            } finally {
                input = br.readLine();
            }
        }

        // Print closing statement
        System.out.print(topLine + "Bye! Hope to see you again soon!" + "\n" + bottomLine);
    }
}


