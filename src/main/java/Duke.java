import exceptions.DukeDeadlineException;
import exceptions.DukeEventException;
import exceptions.DukeException;
import exceptions.DukeTodoException;

import java.util.*;

public class Duke {
    public static void main(String[] args) throws DukeException, DukeDeadlineException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Oh hello dear, I'm Dukie, Zi Xin's favourite chattie box\n" +
                            "Nice to meet you dear:>\n" +
                            "What can I do for you?");

        Scanner myObj = new Scanner(System.in); //Create a Scanner object
        String input; //declare a string variable to store input
        List<Task> all = new ArrayList<Task>(); //ArrayList of Task
        while (myObj.hasNextLine()) {
            while (!(input = myObj.nextLine()).equals("bye")) { //check input not "bye"
                String[] words = input.split(" ", 2); //split input string to get first word (action)

                if (input.equals("list")) { //if list
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i <= all.size(); i++) {
                        System.out.println(i + ". " + all.get(i - 1).toString());
                    }

                } else if (words[0].equals("mark")) {
                    int n = Integer.parseInt(words[1]);
                    all.get(n - 1).markDone(); //call Task method, mark task as done

                } else if (words[0].equals("unmark")) {
                    int n = Integer.parseInt(words[1]);
                    all.get(n - 1).unMarkDone(); //call Task method, mark task as not done

                } else if (words[0].equals("delete")) {
                    int n = Integer.parseInt(words[1]);
                    all.remove(n - 1);
                    System.out.println("Now you have " + all.size() + " tasks in the list.");
                }

                else { //adding of Tasks
                    if (words[0].equals("todo")) {
                        try {
                            ToDo item = new ToDo(words[1]);
                            all.add(item);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(item.toString());
                            System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                        } catch (Exception e) {
                            DukeTodoException error = new DukeTodoException("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(error.getMessage());
                        }
                    }

                    else if (words[0].equals("deadline")) {
                        try {
                            Deadline item = Deadline.setDeadline(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                            }
                        } catch(Exception e) {
                            DukeDeadlineException d = new DukeDeadlineException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    }

                    else if (words[0].equals("event")) {
                        try {
                            Event item = Event.setEvent(words[1]);
                            if (item != null) {
                                all.add(item);
                                System.out.println("Now you have " + all.size() + " tasks in the list."); //print when new task added
                            }
                        } catch(Exception e) {
                            DukeEventException d = new DukeEventException("OOPS!!! Please re-enter.");
                            System.out.println(d.getMessage());
                        }
                    }

                    else {
                        DukeException d = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(d.getMessage());
                    }

                }

            }
            System.out.println("Bye. Hope to see you again soon!"); //ending sentence
        }
    }
}
