package duke;

import java.util.Scanner;

/**
 * Ui class that
 * is responsible to show actions that user undertakes
 */
public class Ui {

    public Ui() {
    }

    /**
     * reads text entered by user
     * @return String entered by user
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Shows loading error
     */
    public String showLoadingError() {
        return "Yo! My bad bud, but I think something's wrong with my loading mechanism!.";
    }

    public void showLine() {
        System.out.println("_______");
    }

    /**
     * Shows user deadline has been added
     * @param deadline deadline that user adds
     * @param tasks list of tasks
     */
    public String showDeadline(Deadline deadline, TaskList tasks) {
        StringBuilder sb = new StringBuilder("Alright bud, I've taken this " +
                "deadline down. Make sure you get it done!");
        sb.append(System.getProperty("line.separator"));
        sb.append(deadline);
        sb.append(System.getProperty("line.separator"));
        if (tasks.getSize() == 1) {
            sb.append("Bud, you only got one task left!");
        } else {
            sb.append("Buddy, you got a couple tasks left. ").append(tasks.getSize()).append(" to be exact.");
        }
        return sb.toString();
    }

    /**
     * Shows user event has been added
     * @param event event that user adds
     * @param tasks list of tasks
     */
    public String showEvent(Event event, TaskList tasks) {
        StringBuilder sb = new StringBuilder("Bud!! You got a date? Haha alright I've noted" +
                " this event down.");
        sb.append(System.getProperty("line.separator"));
        sb.append(event);
        sb.append(System.getProperty("line.separator"));
        if (tasks.getSize() == 1) {
            sb.append("Bud, you only got one task left!");
        } else {
            sb.append("Buddy, you got a couple tasks left. ").append(tasks.getSize()).append(" to be exact.");
        }
        return sb.toString();
    }

    /**
     * Shows user todo has been added
     * @param todo todo that user adds
     * @param tasks list of tasks
     */
    public String showTodo(Todo todo, TaskList tasks) {
        StringBuilder sb = new StringBuilder("Buddy!! The list of things to do" +
                " remains ever-growing, eh? I've added this for you bud.");
        sb.append(System.getProperty("line.separator"));
        sb.append(todo);
        sb.append(System.getProperty("line.separator"));
        if (tasks.getSize() == 1) {
            sb.append("Bud, you only got one task left!");
        } else {
            sb.append("Buddy, you got a couple tasks left. ").append(tasks.getSize()).append(" to be exact.");
        }
        return sb.toString();
    }

    /**
     * Shows user that task has been deleted
     * @param task task that is to be deleted
     * @param tasks list of tasks
     */
    public String showDelete(Task task, TaskList tasks) {
        StringBuilder sb = new StringBuilder("Dayum, bud! Getting rid of the clutter I see!");
        sb.append(System.getProperty("line.separator"));
        sb.append(task);
        sb.append(System.getProperty("line.separator"));
        if (tasks.getSize() == 2) {
            sb.append("Bud!! You only got one task left!");
        } else {
            sb.append("Buddy, you got a couple tasks left. ").append(tasks.getSize()).append(" to be exact.");
        }
        return sb.toString();
    }

    /**
     * Displays exit screen
     * @param storage  Storage
     * @param tasks list of tasks saved
     * @throws DukeException
     */
    public String showExit(Storage storage, TaskList tasks) throws DukeException {
        storage.save(tasks);
        return "Alright, bud! I guess I'll see you around alright?" +
                " Don't be a stranger!";
    }

    /**
     * shows tasks in list of tasks
     * @param tasks list of tasks
     */
    public String showTasks(TaskList tasks) {
        if(tasks.getSize() == 0) {
            return "Buddy!! You haven't added any tasks into your list yet you cheeky monkey!";
        } else {
            StringBuilder sb = new StringBuilder("Alrighty bud, Let me help you out " +
                    "here and list your tasks out for you:");
            sb.append(System.getProperty("line.separator"));
            for(int i = 0; i < tasks.getSize(); i++) {
                sb.append( Integer.toString(i + 1) + "." + tasks.get(i));
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        }
    }

    /**
     * shows error that occurs during program execution
     * @param errorMessage error message
     */
    public String showError(String errorMessage) {
        return (errorMessage);
    }

    /**
     * Shows user that task has been marked
     * @param task task that is to be marked
     * @return
     */
    public String showMark(Task task) {
        return "Way to go, bud! We can only go up from here! \n" + task;

    }

    /**
     * shows user that task has been unmarked
     * @param task task that is to be unmarked
     * @return
     */
    public String showUnmark(Task task) {
        return "Bud?? Were you trying to josh me when you marked it earlier? "
                + "\n" + task;
    }

    public String showFound(TaskList tasks) {
        StringBuilder sb = new StringBuilder("Bud, I searched high and low and this is what I found!");
        sb.append(System.getProperty("line.separator"));
        for(int i = 0; i < tasks.getSize(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i));
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
