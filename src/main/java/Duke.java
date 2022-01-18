import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println("Hello from\n" + logo);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command = br.readLine();
        String keyWord;
        ArrayList<Task> list = new ArrayList<>();
        while (!command.equals("bye")) {
            keyWord = command.split(" ")[0];
            try {
                switch (keyWord) {
                    case "list": {
                        System.out.println("    ____________________________________________________________\n" +
                                "     Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            System.out.printf("     %d.%s%n", i + 1, list.get(i).toString());
                        }
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        continue;
                    }
                    case "mark": {
                        if (command.split(" ").length == 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Please choose a task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                        if (taskNo > list.size() || taskNo < 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Invalid task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        list.get(taskNo).markDone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done:");
                        System.out.printf("       %s%n", list.get(taskNo).toString());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        continue;
                    }
                    case "unmark": {
                        if (command.split(" ").length == 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Please choose a task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                        if (taskNo > list.size() || taskNo < 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Invalid task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        list.get(taskNo).markUndone();
                        System.out.println("    ____________________________________________________________\n" +
                                "     OK, I've marked this task as not done yet:");
                        System.out.printf("       %s%n", list.get(taskNo).toString());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        break;
                    }
                    case "todo": {
                        String task = command.replaceFirst("todo", "");
                        if (task.length() == 0 || task.trim().length() == 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                    "    ____________________________________________________________");
                        }
                        list.add(new ToDo(task));
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:");
                        System.out.printf("       %s%n", list.get(list.size() - 1).toString());
                        System.out.printf("     Now you have %d tasks in the list.%n", list.size());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        break;
                    }
                    case "deadline": {
                        String[] text = command.replaceFirst("deadline", "").split(" /by ");
                        String task = text[0];
                        if (task.trim().length() == 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                                    "    ____________________________________________________________");
                        }
                        if (text.length == 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The date/time of a deadline cannot be empty.\n" +
                                    "    ____________________________________________________________");
                        }
                        String by = text[1];
                        list.add(new Deadline(task, by));
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:");
                        System.out.printf("       %s%n", list.get(list.size() - 1).toString());
                        System.out.printf("     Now you have %d tasks in the list.%n", list.size());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        break;

                    }
                    case "event": {
                        String[] text = command.replaceFirst("event", "").split(" /at ");
                        String task = text[0];
                        if (task.trim().length() == 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                                    "    ____________________________________________________________");
                        }
                        if (text.length == 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! The time of an event cannot be empty.\n" +
                                    "    ____________________________________________________________");
                        }
                        String at = text[1];
                        list.add(new Event(task, at));
                        System.out.println("    ____________________________________________________________\n" +
                                "     Got it. I've added this task:");
                        System.out.printf("       %s%n", list.get(list.size() - 1).toString());
                        System.out.printf("     Now you have %d tasks in the list.%n", list.size());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        break;
                    }
                    case "delete": {
                        if (command.split(" ").length == 1) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Please choose a task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                        if (taskNo > list.size() || taskNo < 0) {
                            throw new DukeException("    ____________________________________________________________\n" +
                                    "     ☹ OOPS!!! Invalid task number.\n" +
                                    "    ____________________________________________________________");
                        }
                        Task deleted = list.remove(taskNo);
                        System.out.println("    ____________________________________________________________\n" +
                                "     Noted. I've removed this task:");
                        System.out.printf("       %s%n", deleted.toString());
                        System.out.printf("     Now you have %d tasks in the list.%n", list.size());
                        System.out.println("    ____________________________________________________________");
                        command = br.readLine();
                        break;
                    }
                    default: {
                        throw new DukeException("    ____________________________________________________________\n" +
                                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "    ____________________________________________________________");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                command = br.readLine();
                continue;
            }

        }

        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }
}
