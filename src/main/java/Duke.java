import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();
        System.out.println("YO WHASSUPPP I'm Productivitisation\n" + "What can I do for you today?");
        Scanner userInput = new Scanner(System.in);
        DukeManager dukeManager = new DukeManager();
        while (true) {
            try {
                String nextLine = userInput.nextLine();
                String testedString = dukeManager.test(nextLine);
                int counter = 1;
                //Exiting the code when bye is inputted
                if ((testedString.replaceAll("\\s+", "")).equals("bye")) {
                    System.out.println("  " + "That's all?? lame!!!");
                    break;
                    //display the list of tasks
                } else if ((testedString.replaceAll("\\s+", "")).equals("list")) {
                    System.out.println("Here are the tasks in your list:\n");
                    for (Task s : taskList) {
                        System.out.println("  " + counter + "." + s);
                        counter++;
                    }
                    //todocommand
                } else if (testedString.startsWith("delete")) {
                    delete(testedString, taskList);
                    counter--;
                } else if (testedString.startsWith("todo")) {
                    todo(testedString, taskList);
                    //deadline command
                } else if (testedString.startsWith("deadline")) {
                    deadline(testedString, taskList);
                    //event command
                } else if (testedString.startsWith("event")) {
                    event(testedString, taskList);
                    //unmark commnd
                } else if (testedString.startsWith("unmark")) {
                    int taskNumber = intSearch(testedString) - 1;
                    if (taskNumber < taskList.size() && taskNumber >= 0) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(false);
                        System.out.println("  " + "AIYO! I've marked this task as not done yet:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("EH HULLO!! Task does not exist! Check again hehe");
                    }
                    //mark command
                } else if (testedString.startsWith("mark")) {
                    int taskNumber = intSearch(testedString) - 1;
                    if (taskNumber < taskList.size() && taskNumber >= 0) {
                        Task intendedTask = taskList.get(taskNumber);
                        intendedTask.setDone(true);
                        System.out.println("  " + "SOLID! I've marked this task as done:\n"
                                + "    " + intendedTask);
                    } else {
                        System.out.println("EH HULLO!! Task does not exist! Check again hehe");
                    }
                }
            } catch (DukeException e) {
                System.err.print(e + "\n");
            }
        }
    }

    public static int intSearch(String text) {
        String[] splicedString = text.split(" ");
        return Integer.parseInt(splicedString[1]);
    }

    public static void delete(String text, ArrayList<Task> list) {
        int indexToDelete = intSearch(text) - 1;
        if (indexToDelete < 0 || indexToDelete >= list.size()) {
            System.out.println("EH HULLO!! Task does not exist! Check again hehe");
        } else {
            Task deleteTask = list.get(indexToDelete);
            list.remove(indexToDelete);
            System.out.println("   " + "ALRIGHTY. I've removed this task:\n"
                    + "    " + deleteTask + "\n" + "   Now you have " + list.size() + " tasks in the list.");
        }
    }

    public static void todo(String text, ArrayList<Task> list) {
        String splicedString = text.substring(5);
        ToDo freshTodo = new ToDo(splicedString);
        list.add(freshTodo);
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void deadline(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /by ");
        String splicedDescription = splicedString[0].substring(9);
        String dueDate = splicedString[1];
        Deadline freshDeadline = new Deadline(splicedDescription, dueDate);
        list.add(freshDeadline);
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshDeadline + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }

    public static void event(String description, ArrayList<Task> list) {
        String[] splicedString = description.split(" /at ");
        String splicedDescription = splicedString[0].substring(6);
        String dueDate = splicedString[1];
        Event freshEvent = new Event(splicedDescription, dueDate);
        list.add(freshEvent);
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshEvent + "\n" +  "   Now you have " + list.size() + " tasks in the list.");
    }
}
