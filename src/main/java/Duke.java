import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Duke {
    public static String listStatus(int totalTasks) {
        return "There are " + totalTasks + (totalTasks > 1 ? " tasks" : " task")
                + " in the list now.\n";
    }

    public static void main(String[] args) throws IOException {
        String logo = "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n";

        String intro = "    Hello! I'm Duke!\n"
                + "    What can I do for you?\n";

        String topLine = "    ――――――――――――――――――――――――――――――――――\n    ";
        String bottomLine = "    ――――――――――――――――――――――――――――――――――\n";
        String indent = "    ";

        System.out.print(logo + "\n" + intro + bottomLine);

        ArrayList<Task> taskArr = new ArrayList<>();
        int totalTasks = 0;
        List<String> taskTypes = Arrays.asList("todo", "deadline", "event");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        while (!input.equals("bye")) {
            String[] inputArr = input.split(" ");
            String command = inputArr[0];

            try {
                if (command.equals("list")) { // list out all added tasks
                    if (totalTasks == 0) {
                        System.out.println(topLine + "There are no tasks in your list.\n");
                    } else {
                        System.out.print(topLine + "Here are the tasks in your list:\n");
                        int index = 1;
                        for (int n = 0; n < totalTasks; n++) {
                            Task t = taskArr.get(n);
                            System.out.println(indent + index + "." + t.toString());
                            index++;
                        }
                        System.out.print(bottomLine);
                    }

                } else if (command.equals("mark") || command.equals("unmark")) { // change status of task
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine + "Sorry, I don't know which task you would like to mark :(\n"
                                + bottomLine);
                    } else {
                        int taskNum = Integer.parseInt(inputArr[1]);
                        Task t = taskArr.get(taskNum - 1);

                        if (command.equals("mark")) { // mark task as done
                            System.out.print(topLine + "Nice! You've completed this task:\n  ");
                            t.markAsDone();
                        } else { // unmark task
                            System.out.print(topLine + "Okay, I've marked this task as undone:\n  ");
                            t.markAsUndone();
                        }
                        System.out.print(indent + t + "\n" + bottomLine);
                    }

                } else if (command.equals("delete")) { // delete task from list
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine + "Sorry, I don't know which task you would like to delete :(\n"
                                + bottomLine);
                    } else {
                        int taskNum = Integer.parseInt(inputArr[1]);
                        Task t = taskArr.remove(taskNum - 1);
                        totalTasks--;
                        System.out.print(topLine + "Okay, I've deleted this task:\n  "
                                + indent + t + "\n" + indent + listStatus(totalTasks) + bottomLine);
                    }

                } else if (taskTypes.contains(command)) { // add task to list
                    if (inputArr.length == 1) {
                        throw new DukeException(topLine + "Oops, the task needs a description!\n" + bottomLine);
                    } else {
                        if (command.equals("todo")) {
                            String str = input.substring(5);
                            taskArr.add(new Todo(str));

                        } else if (command.equals("deadline")) {
                            String str = input.substring(9);
                            String[] strArr = str.split(" /by ");
                            if (strArr.length == 1) {
                                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                                        + bottomLine);
                            } else {
                                taskArr.add(new Deadline(strArr[0], strArr[1]));
                            }

                        } else if (command.equals("event")) {
                            String str = input.substring(6);
                            String[] strArr = str.split(" /at ");
                            if (strArr.length == 1) {
                                throw new DukeException(topLine + "Oops, please set a date/time for this task!\n"
                                        + bottomLine);
                            } else {
                                taskArr.add(new Event(strArr[0], strArr[1]));
                            }
                        }
                        totalTasks++;
                        System.out.print(topLine + "Got it! I've added this task:\n  "
                                + indent + taskArr.get(totalTasks - 1).toString() + "\n"
                                + indent + listStatus(totalTasks) + bottomLine);
                    }

                } else { // not a recognised command
                    throw new DukeException(topLine + "Oh no! I don't understand what that means...\n" + bottomLine);
                }
            } catch (DukeException e) {
                System.out.print(e.message);
            } catch (IndexOutOfBoundsException e) {
                System.out.print(topLine + "Invalid task number. " + listStatus(totalTasks) + bottomLine);
            } finally {
                input = br.readLine();
            }
        }
        System.out.print(topLine + "Bye! Hope to see you again soon!" + "\n" + bottomLine);
    }
}


