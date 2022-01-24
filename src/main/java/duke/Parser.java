package duke;

/**
 * Parser executes all actions passed from the Ui class
 */
public class Parser {

    TaskList tasks;

    Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * prints the list of currently stored tasks
     */
    void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
    }


    /**
     * finds and prints task based on whether it contains the given keyword
     *
     * @param keyword word to be matched with task description
     */
    void findTask(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        int currCount = 1;
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).getDescription().contains(keyword)) {
                System.out.println(currCount++ + ". " + tasks.get(i - 1));
            }

        }
    }


    /**
     * marks the chosen task as done and prints a confirmation
     *
     * @param taskNumber represents which task to mark, 1 for the first task, 2 for the second, etc
     */
    void mark(int taskNumber) {
        tasks.get(taskNumber - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    /**
     * unmarks the chosen task and prints a confirmation
     *
     * @param taskNumber represents which task to unmark, 1 for the first task, 2 for the second, etc
     */
    void unmark(int taskNumber) {
        tasks.get(taskNumber - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber - 1));
    }

    /**
     * adds the given ToDo object and prints a confirmation
     *
     * @param toDo ToDo object to be added to TaskList
     */
    void addToDo(ToDo toDo) {
        tasks.add(toDo);
        System.out.println("Got it. I've added this task:\n"
                + toDo + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    /**
     * adds the given Deadline object and prints a confirmation
     *
     * @param deadline Deadline object to be added to TaskList
     */
    void addDeadline(Deadline deadline) {
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n"
                + deadline + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    /**
     * adds the given Event object and prints a confirmation
     *
     * @param event Event object to be added to TaskList
     */
    void addEvent(Event event) {
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n"
                + event + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }

    /**
     * deletes task at a specified position and prints a confirmation
     *
     * @param number represents which task to delete, 1 for the first task, 2 for the second, etc
     */
    void deleteTask(int number) {
        Task taskToDelete = tasks.get(number - 1);
        tasks.remove(number - 1);
        System.out.println("Noted. I've removed this task:\n"
                + taskToDelete + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size()));
    }
}
