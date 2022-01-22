import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {
    private static Storage storage;

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        storage = new Storage();
        ArrayList<Task> tasks = storage.loadList();

        // load in the tasks required


        outerloop:
        while (true) {
            String str = sc.nextLine();
            String[] commandSections = str.split(" ", 2);
            Commands currentCommand = null;
            try {
                currentCommand = Commands.valueOf(commandSections[0]);
            } catch (IllegalArgumentException e) {
                System.out.println("Sorry I didnt catch that! Please make sure it is a valid command!");
                continue;
            }

            switch (currentCommand) {
                // quit
                case bye: {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break outerloop;
                }

                // list
                case list: {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task currentTask = tasks.get(i);
                        int index = i + 1;
                        System.out.println(index + "." + currentTask.toString());
                    }
                    break;
                }

                // mark
                case mark: {
                    try {
                        int indexMarked = Integer.parseInt(commandSections[1]) - 1;
                        Task currentTask = tasks.get(indexMarked);
                        currentTask.isDone = true;
                        storage.updateTasks(tasks);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(currentTask.toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    } catch (NumberFormatException e) {
                        System.out.println(new DukeException("provide an index instead :)"));
                    }
                    break;
                }


                // unmark
                case unmark: {
                    try {
                        int indexMarked = Integer.parseInt(commandSections[1]) - 1;
                        Task currentTask = tasks.get(indexMarked);
                        currentTask.isDone = false;
                        storage.updateTasks(tasks);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(currentTask.toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    } catch (NumberFormatException e) {
                        System.out.println(new DukeException("provide an index instead :)"));
                    }
                    break;
                }
                case delete: {
                    try {
                        int indexDelete = Integer.parseInt(commandSections[1]) - 1;
                        Task deletedTask = tasks.remove(indexDelete);
                        storage.updateTasks(tasks);
                        System.out.println("Noted. I've removed this task: ");
                        System.out.println(deletedTask.toString());
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(new DukeException("task does not exist!"));
                    } catch (NumberFormatException e) {
                        System.out.println(new DukeException("provide an index instead :)"));
                    }
                    break;
                }

                // generate tasks
                case todo: {
                    try {
                        if (commandSections[1].isBlank()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        Todo currentTodo = new Todo(commandSections[1]);
                        tasks.add(currentTodo);
                        storage.addTask(currentTodo);
                        System.out.println("added: " + currentTodo.toString());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The description of a todo cannot be empty"));
                    }
                    break;
                }
                case deadline: {
                    try {
                        String actualTask = commandSections[1];
                        String[] segments = actualTask.split(" /by ");
                        if (segments[0].isBlank() || segments[0].isBlank()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        Deadline currentDeadline = new Deadline(segments[0], segments[1]);
                        tasks.add(currentDeadline);
                        storage.addTask(currentDeadline);
                        System.out.println("added: " + currentDeadline.toString());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The description of a deadline cannot be empty"));
                    }
                    break;
                }

                case event: {
                    try {
                        String actualTask = commandSections[1];
                        String[] segments = actualTask.split(" /at ");
                        if (segments[0].isBlank() || segments[0].isBlank()) {
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        Event currentEvent = new Event(segments[0], segments[1]);
                        tasks.add(currentEvent);
                        storage.addTask(currentEvent);
                        System.out.println("added: " + currentEvent.toString());
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(new DukeException("The description of a event cannot be empty"));
                    }
                    break;
                }
                default: {
                    System.out.println("Sorry I didnt understand you!");
                }
            }
        }
    }
}
