import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String lines = "____________________________________________________________";

        System.out.println(lines);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(lines);

        Scanner scanner = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

        Save saveFile = new Save("save.txt");
        saveFile.ParseFile(tasks);

        while (true) {
            try {
                String input = scanner.nextLine();
                String[] split = input.split(" ");
                if (input.equals("bye")) {
                    System.out.println(lines + "\nBye. Hope to see you again soon!\n" + lines);
                    saveFile.OverwriteFile(tasks);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(lines);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i).toString());
                    }
                    System.out.println(lines);

                } else if (split[0].equals("mark")) {
                    if (split.length == 1) {
                        throw new DukeException("What are you marking? Missing number!");
                    }
                    else {
                        int number;
                        try {
                            number = Integer.parseInt(split[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("That's not a number");
                        }
                        if (number < tasks.size()) {
                            tasks.get(number).markAsDone();
                            System.out.println(lines + "\nNice! I've marked this task as done:\n" +
                                    tasks.get(number).toString() + "\n" + lines);
                        } else {
                            throw new DukeException("No such task exists.");
                        }
                    }
                } else if (split[0].equals("unmark")) {
                    if (split.length == 1) {
                        throw new DukeException("What are you unmarking? Missing number!");
                    }
                    else {
                        int number;
                        try {
                            number = Integer.parseInt(split[1]) - 1;
                        } catch (NumberFormatException e) {
                            throw new DukeException("That's not a number");
                        }
                        if (number < tasks.size()) {
                            tasks.get(number).markAsNotDone();
                            System.out.println(lines + "\nOk, I've marked this task as not done yet:\n" +
                                    tasks.get(number).toString() + "\n" + lines);
                        } else {
                            throw new DukeException("No such task exists.");
                        }
                    }
                } else if (split[0].equals("delete")) {
                    if (split.length == 1) {
                        throw new DukeException("What are you deleting? Missing number!");
                    }
                    else {
                        int number = Integer.parseInt(split[1]) - 1;
                        if (number < tasks.size()) {
                            System.out.println(lines + "\nNoted. I've removed this task:\n" +
                                    tasks.get(number).toString() + "\n");
                            tasks.remove(number);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.\n" + lines);

                        } else {
                            throw new DukeException("No such task exists.");
                        }
                    }
                } else if (split[0].equals("todo")) {
                    if (split.length == 1) {
                        throw new DukeException("OH NO! The description of event cannot be empty.");
                    } else {
                        Todo newTodo = new Todo(input.replace("todo ", ""));
                        tasks.add(newTodo);
                        System.out.println(lines + "\nGot it. I've added this task:\n" + tasks.get(tasks.size()-1).toString()
                                + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n" + lines);
                    }

                } else if (split[0].equals("deadline")) {
                    if (split.length == 1) {
                        throw new DukeException("OH NO! The description of event cannot be empty.");
                    } else {
                        String[] newSplit = input.split(" /by ");
                        if (newSplit.length > 1) {
                            Deadline newDeadline = new Deadline(newSplit[0].replace("deadline ", ""), newSplit[1]);
                            tasks.add(newDeadline);
                            System.out.println(lines + "\nGot it. I've added this task:\n" + tasks.get(tasks.size()-1).toString()
                                    + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n" + lines);
                        } else {
                            throw new DukeException("You are missing the date!.");
                        }
                    }
                } else if (split[0].equals("event")) {
                    if (split.length == 1) {
                        throw new DukeException("OH NO! The description of event cannot be empty.");
                    } else {
                        String[] newSplit = input.split(" /at ");
                        if (newSplit.length > 1) {
                            Event newEvent = new Event(newSplit[0].replace("event ", ""), newSplit[1]);
                            tasks.add(newEvent);
                            saveFile.WriteToFile("E|0|" + newEvent.getDescription() + "|" + newEvent.getAt() + "\n");
                            System.out.println(lines + "\nGot it. I've added this task:\n" + tasks.get(tasks.size()-1).toString()
                                    + "\n" + "Now you have " + tasks.size() + " tasks in the list.\n" + lines);
                        } else {
                            throw new DukeException("You are missing the location!.");
                        }
                    }
                } else {
                    throw new DukeException("Sorry :( I don't know what this means.");
                }
                saveFile.OverwriteFile(tasks);
            } catch (DukeException e) {
                System.out.println(lines + "\n" + e.getErrorMsg() + "\n" + lines);
            }
        }
    }
}