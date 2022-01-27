import exceptions.DukeException;
import storage.Storage;
import tasks.Event;
import tasks.Task;
import tasks.Deadline;
import tasks.ToDo;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static final String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage(FILE_PATH);
        ArrayList<Task> tasks = storage.loadData() == null
                ? new ArrayList<>()
                : storage.loadData();

        System.out.println("____________________________________________________________" + '\n'
                + "Hello! I'm Duke" + '\n'
                + "What can I do for you?" + '\n'
                + "____________________________________________________________");


        loop: while (sc.hasNext()) {
            try {
                String curr = sc.nextLine();
                String strArr[] = curr.split(" ", 2);
                String cmd = strArr[0];

                Commands command = validateInput(cmd);

                switch(command) {
                    case LIST:
                        displayList(tasks);
                        continue;
                    case MARK: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            tasks.get(currentTask).setComplete();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(tasks.get(currentTask).toString());
                            continue;
                        } catch (NumberFormatException e){
                            throw new DukeException("Please enter a number of the item in the list you wish to mark!");
                        }
                    }
                    case UNMARK: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            tasks.get(currentTask).setIncomplete();
                            System.out.println("OK, I've marked this task as not done yet: ");
                            System.out.println(tasks.get(currentTask).toString());
                            continue;
                        } catch (NumberFormatException e){
                            throw new DukeException("Please enter a number of the item in the list you wish to unmark!");
                        }
                    }
                    case TODO: {
                        try {
                            tasks.add(new ToDo(strArr[1]));
                            System.out.println("Got it. I've added this task:  ");
                            System.out.println(tasks.get(tasks.size() - 1).toString());
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            continue;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                    }
                    case DEADLINE: {
                        String strArrDate[] = strArr[1].split("/by ", 2);
                        String eventName = strArrDate[0];
                        String eventDate = strArrDate[1];
                        System.out.println("Got it. I've added this task:  ");
                        tasks.add(new Deadline(eventName, eventDate));
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        continue;
                    }
                    case EVENT: {
                        String strArrDate[] = strArr[1].split("/at ", 2);
                        String eventName = strArrDate[0];
                        String eventDate = strArrDate[1];
                        System.out.println("Got it. I've added this task:  ");
                        tasks.add(new Event(eventName, eventDate));
                        System.out.println(tasks.get(tasks.size() - 1).toString());
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        continue;
                    }
                    case REMOVE: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            Task toRemove = tasks.get(currentTask);
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(toRemove.toString());
                            tasks.remove(toRemove);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                            continue;
                        } catch (NumberFormatException e){
                            throw new DukeException("Please enter the number of the item in the list you wish to remove!");
                        }
                    }
                    case BYE:
                        break loop;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        storage.saveData(tasks);
        System.out.println("____________________________________________________________" + '\n'
                + "Bye. Hope to see you again soon!" + '\n'
                + "____________________________________________________________");
    }

    public static Commands validateInput(String cmd) throws DukeException {
        try {
            Commands command = Commands.valueOf(cmd.toUpperCase());
            return command;
        } catch (IllegalArgumentException e) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void displayList(ArrayList<Task> arr) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < arr.size(); i++) {
            Task currentTask = arr.get(i);
            System.out.println(i + 1 + "." + currentTask.toString());
        }
    }
}
