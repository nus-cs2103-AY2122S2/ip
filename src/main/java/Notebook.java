import java.util.ArrayList;

/**
 * Stores and handles the list of tasks
 */
public class Notebook {
    private final ArrayList<Task> tasks;

    /**
     * Constructor to initialise a new task list.
     */
    public Notebook() {
        tasks = new ArrayList<>();
    }

    /**
     * Uses input string e to determine appropriate action.
     * If input is "list", will execute listOut method. If not, adds
     * instruction to tasks list.
     * @param e The instruction to follow
     * @return The string result
     */
    public String instruction(String e) {
        try {
            ArthurException.checkException(e);
        } catch (InvalidInstructionException | EmptyDescriptionException f) {
            return f.getMessage();
        }


        String[] temp = e.split(" ", 2);    // Gets the first word
        String inst = temp[0];

        switch (inst) {
        case "list":
            e = this.listOut();
            break;
        case "mark":
        case "unmark":
            e = this.marker(e);
            break;
        case "todo":
            e = this.todo(temp[1]);
            break;
        case "deadline":
            e = this.deadline(temp[1]);
            break;
        case "event":
            e = this.event(temp[1]);
            break;
        case "delete":
            e = this.deleter(Integer.parseInt(temp[1]));
            break;
        }
        return e;
    }

    /**
     * Lists out the tasks stored in the tasks Arraylist.
     * @return A string version of the list formatted with numbering.
     */
    private String listOut() {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list: \n");
        int tempCounter = 1;

        for (Task a : tasks) {
            temp.append(tempCounter).append(". ").append(a).append("\n");
            tempCounter++;
        }
        return temp.toString();
    }

    /**
     * Marks or Unmark the indicated task
     * @param e The instruction to follow with the task number
     * @return The string result of the instruction
     */
    private String marker(String e) {
        String[] temp = e.split(" ");

        try {
            Task currTask = tasks.get(Integer.parseInt(temp[1]) - 1);
            if (temp[0].equals("mark")) {
                currTask.mark();
                return "Good job! Task Completed \n" + currTask;
            } else {
                currTask.unmark();
                return "Alright, I will unmark this \n" + currTask;
            }
        } catch (IndexOutOfBoundsException a) {
            return "Invalid task number. Please try again";
        }
    }

    /**
     * Initialises and adds new Todo task to task list.
     * @param e The description of the todo task to be added
     * @return String conformation of the input
     */
    private String todo(String e) {
        Todo temp = new Todo(e);
        tasks.add(temp);
        return "Added a new Todo task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with deadline to task list.
     * String after "/by " will be taken as the deadline info.
     * @param e The description of the task with deadline info to be added
     * @return String conformation of the input
     */
    private String deadline(String e) {
        Deadline temp;
        try {
            String[] tempArr = e.split("/by ");
            temp = new Deadline(tempArr[0], tempArr[1]);
        } catch (IndexOutOfBoundsException a) {
            return "Please add the deadline date";
        }
        tasks.add(temp);
        return "Added a new Deadline task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Initialises and adds new task with timing to task list.
     * String after "/at " will be taken as the date/time the task
     * would occur.
     * @param e The description of the task with timing info to be added
     * @return String conformation of the input
     */
    private String event(String e) {
        Event temp;
        try {
            String[] tempArr = e.split("/at ");
            temp = new Event(tempArr[0], tempArr[1]);
        } catch (IndexOutOfBoundsException a) {
            return "Please add the event Date and/or Time";
        }

        tasks.add(temp);
        return "Added a new Event task: \n" + temp
                + "\n" + this.outstanding();
    }

    /**
     * Generates a string about the number of tasks left in list
     * @return String info regarding num of tasks left
     */
    private String outstanding() {
        return "You have "
                + this.tasks.size()
                + " tasks in list at the moment.";
    }

    /**
     * Deletes the task from the list.
     * @param i The task number to delete
     * @return String conformation of the task deletion 
     */
    private String deleter(int i) {
        try {
            Task currTask = tasks.remove(i - 1);
            return "Successfully removed this task: \n" + currTask
                    + "\n" + outstanding();
        } catch (IndexOutOfBoundsException e) {
            return "Invalid task number. Please try again";
        }
    }
}
