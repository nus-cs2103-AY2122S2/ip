import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Stores and handles the list of tasks
 */
public class Notebook {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructor to initialise a new task list.
     */
    public Notebook() {
        tasks = new ArrayList<>();
        storage = new Storage();
        taskFiller();
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
            try {
                storage.editTasks(tasks.get(Integer.parseInt(temp[1]) - 1), 1);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Please enter a valid number!");
            }
            break;
        case "todo":
            e = this.todo(temp[1]);
            storage.addTasks(tasks.get(tasks.size() - 1));
            break;
        case "deadline":
            e = this.deadline(temp[1]);
            storage.addTasks(tasks.get(tasks.size() - 1));
            break;
        case "event":
            e = this.event(temp[1]);
            storage.addTasks(tasks.get(tasks.size() - 1));
            break;
        case "delete":
            try {
                storage.editTasks(tasks.get(Integer.parseInt(temp[1]) - 1), 2);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Please enter a valid number!");
            }
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

    /**
     * Copies over tasks in data file to the task arraylist.
     */
    public void taskFiller() {
        try {
            Scanner sc = new Scanner(storage.getTasks());
            while (sc.hasNext()) {
                String temp = sc.nextLine();
                String taskInfo = temp.split(" >> ")[1];
                char taskType = temp.charAt(1);
                char marking = temp.charAt(4);
                switch (taskType) {
                case 'T':
                    todo(taskInfo);
                    break;
                case 'D':
                    taskInfo = taskInfo.replaceFirst("\\(By:", "/by");
                    taskInfo = taskInfo.replaceFirst("\\)", "");
                    deadline(taskInfo);
                    break;
                case 'E':
                    taskInfo = taskInfo.replaceFirst("\\(At:", "/at");
                    taskInfo = taskInfo.replaceFirst("\\)", "");
                    event(taskInfo);
                    break;
                }

                if (marking == 'X') {
                    tasks.get(tasks.size() - 1).mark();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
