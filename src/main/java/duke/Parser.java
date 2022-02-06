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
    String listTasks() {
        String response = "Here are the tasks in your list:\n";
        for (int i = 1; i <= tasks.size(); i++) {
            response += i + ". " + tasks.get(i - 1) + "\n";
        }
        return response;
    }


    /**
     * finds and prints task based on whether it contains the given keyword
     *
     * @param keyword word to be matched with task description.
     */
    String findTask(String keyword) {
        String response = "Here are the matching tasks in your list:\n";
        int currCount = 1;
        for (int i = 1; i <= tasks.size(); i++) {
            if (tasks.get(i - 1).getDescription().contains(keyword)) {
                response += currCount++ + ". " + tasks.get(i - 1) +"\n";
            }

        }
        return response;
    }


    /**
     * marks the chosen task as done and prints a confirmation
     *
     * @param taskNumber represents which task to mark, 1 for the first task, 2 for the second, etc.
     */
    String mark(int taskNumber) {
        assert taskNumber > 0 : "Task number cannot be negative";
        tasks.get(taskNumber - 1).mark();
        String response = "Nice! I've marked this task as done:\n";
        response += tasks.get(taskNumber - 1);
        return response;
    }

    /**
     * unmarks the chosen task and prints a confirmation
     *
     * @param taskNumber represents which task to unmark, 1 for the first task, 2 for the second, etc.
     */
    String unmark(int taskNumber) {
        assert taskNumber > 0 : "Task number cannot be negative";
        tasks.get(taskNumber - 1).unmark();
        String response = "OK, I've marked this task as not done yet:";
        response += tasks.get(taskNumber - 1);
        return response;
    }

    /**
     * adds the given ToDo object and prints a confirmation
     *
     * @param toDo ToDo object to be added to TaskList.
     */
    String addToDo(ToDo toDo) {
        tasks.add(toDo);
        return "Got it. I've added this task:\n"
                + toDo + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size());
    }

    /**
     * adds the given Deadline object and prints a confirmation
     *
     * @param deadline Deadline object to be added to TaskList.
     */
    String addDeadline(Deadline deadline) {
        tasks.add(deadline);
        return "Got it. I've added this task:\n"
                + deadline + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size());
    }

    /**
     * adds the given Event object and prints a confirmation
     *
     * @param event Event object to be added to TaskList.
     */
    String addEvent(Event event) {
        tasks.add(event);
        return "Got it. I've added this task:\n"
                + event + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size());

    }

    /**
     * deletes task at a specified position and prints a confirmation
     *
     * @param number represents which task to delete, 1 for the first task, 2 for the second, etc.
     */
    String deleteTask(int number) {
        assert number < tasks.size() : "Number stated is beyond the size of the list";
        Task taskToDelete = tasks.get(number - 1);
        tasks.remove(number - 1);
       return "Noted. I've removed this task:\n"
                + taskToDelete + "\n"
                + String.format("Now you have %d tasks in the list."
                , tasks.size());
    }
}
