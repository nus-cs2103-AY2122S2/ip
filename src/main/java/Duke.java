import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("Hello! I'm Whey\n" + "What can I do for you today?");
        Scanner userInput = new Scanner(System.in);
        DukeManager dukeManager = new DukeManager();
        while (true) {
            try {
                String nextLine = userInput.nextLine();
                String testedString = dukeManager.test(nextLine);
                int counter = 1;
                //Exiting the code when bye is inputted
                if ((testedString.replaceAll("\\s+", "")).equals("bye")) {
                    System.out.println("  " + "Bye beautiful! hope to see you again hehe");
                    break;
                    //display the list of tasks
                } else if ((testedString.replaceAll("\\s+", "")).equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (Task s : taskList) {
                        System.out.println("  " + counter + "." + s);
                        counter++;
                    }
                    //unmarking a task
                } else if (testedString.startsWith("todo")) {
                    todo(testedString, taskList);
                } else if (testedString.startsWith("deadline")) {
                    deadline(testedString, taskList);
                } else if (testedString.startsWith("event")) {
                    event(testedString, taskList);
                } else if (testedString.startsWith("unmark")) {
                    int taskNumber = intSearch(testedString) - 1;
                    if (taskNumber <= taskList.size()) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(false);
                        System.out.println("  " + "Ok! I've marked this task as not done yet:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("Task does not exist! Check again hehe");
                    }
                    //marking a task
                } else if (testedString.startsWith("mark")) {
                    int taskNumber = intSearch(testedString) - 1;
                    if (taskNumber <= taskList.size()) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(true);
                        System.out.println("  " + "Nice! I've marked this task as done:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("Task does not exist! Check again hehe");
                    }
                }
            } catch (DukeException e) {
                System.err.print(e + "\n");
            }
        }
    }

    public static int intSearch(String text) {
        //replace non digit number with empty space
        text = text.replaceAll("[^\\d]", "");
        //replace every white space with empty space
        text = text.replaceAll(" +", "");
        if (text.equals("")) {
            return -1;
        }
        return Integer.parseInt(text);
    }

    public static void todo(String text, ArrayList<Task> list) {
        String splicedString = text.substring(5);
        ToDo freshTodo = new ToDo(splicedString);
        list.add(freshTodo);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void deadline(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /by ");
        String splicedDescription = splicedString[0].substring(9);
        String dueDate = splicedString[1];
        Deadline freshDeadline = new Deadline(splicedDescription, dueDate);
        list.add(freshDeadline);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshDeadline + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void event(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /at ");
        String splicedDescription = splicedString[0].substring(6);
        String dueDate = splicedString[1];
        Event freshEvent = new Event(splicedDescription, dueDate);
        list.add(freshEvent);
        System.out.println("   " + "Got it. I've added this task:\n"
                + "    " + freshEvent + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }
}
