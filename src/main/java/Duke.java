import Tasks.Event;
import Tasks.Task;
import Tasks.Deadline;
import Tasks.ToDo;
import exceptions.DukeException;
import validation.DateValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();

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
                        displayList(arr);
                        break;
                    case MARK: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            arr.get(currentTask).setComplete();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(arr.get(currentTask).toString());
                        } catch (NumberFormatException e){
                            throw new DukeException("Please enter a number of the item in the list you wish to mark!");
                        }
                        break;
                    }
                    case UNMARK: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            arr.get(currentTask).setIncomplete();
                            System.out.println("OK, I've marked this task as not done yet: ");
                            System.out.println(arr.get(currentTask).toString());
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please enter a number of the item in the list you wish to unmark!");
                        }
                        break;
                    }
                    case TODO: {
                        try {
                            arr.add(new ToDo(strArr[1]));
                            System.out.println("Got it. I've added this task:  ");
                            System.out.println(arr.get(arr.size() - 1).toString());
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        break;
                    }
                    case DEADLINE: {
                        String strArrDate[] = strArr[1].split("/by ", 2);
                        String eventName = strArrDate[0];
                        String eventDate = strArrDate[1];
                        DateValidator.isValid(eventDate);
                        System.out.println("Got it. I've added this task:  ");
                        arr.add(new Deadline(eventName, eventDate));
                        System.out.println(arr.get(arr.size() - 1).toString());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        break;
                    }
                    case EVENT: {
                        String strArrDate[] = strArr[1].split("/at ", 2);
                        String eventName = strArrDate[0];
                        String eventDate = strArrDate[1];
                        DateValidator.isValid(eventDate);
                        System.out.println("Got it. I've added this task:  ");
                        arr.add(new Event(eventName, eventDate));
                        System.out.println(arr.get(arr.size() - 1).toString());
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        break;
                    }
                    case REMOVE: {
                        try {
                            Integer currentTask = Integer.parseInt(strArr[1]) - 1;
                            Task toRemove = arr.get(currentTask);
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(toRemove.toString());
                            arr.remove(toRemove);
                            System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        } catch (NumberFormatException e){
                            throw new DukeException("Please enter the number of the item in the list you wish to remove!");
                        }
                        break;
                    }
                    case BYE:
                        break loop;
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
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
