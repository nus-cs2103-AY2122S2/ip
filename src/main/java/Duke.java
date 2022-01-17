import java.util.Scanner;

public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskNum = 0;
    private static String conformation = "Got it. I've added this task:";
    private static Scanner sc;

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(greet);
        sc = new Scanner(System.in);
        getUserInput();
        sc.close();
    }

    /**
     * To handle the user input.
     */
    private static void getUserInput(){
        String first_word = sc.next();
        String remaining_word = sc.nextLine();

        switch (first_word) {
        case "bye":
            exit();
            break;
        case "list":
            displayList();
            break;
        case "deadline":
            deadlineTask(remaining_word.trim());
            break;
        case "event":
            eventTask(remaining_word.trim());
            break;
        case "todo":
            todoTask(remaining_word.trim());
            break;
        case "mark":
            markTaskList(remaining_word.trim());
            break;
        case "unmark":
            unmarkTaskList(remaining_word.trim());
            break;
        default:
            String curr_word = first_word + remaining_word;
            Task curr_task = new Task(curr_word);
            taskList[taskNum] = curr_task;
            taskNum++;
            System.out.println("added: " + curr_task.getDescription());
            getUserInput();
        }
    }

    /**
     * To exit when user input "bye".
     */
    private static void exit() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    /**
     * To display the list of tasks.
     */
    private static void displayList() {
        if (taskNum == 0) {
            System.out.println("No task");
            getUserInput();
        } else {
            System.out.println("Here are the tasks in your list:");
            for(int i = 1; i < taskNum + 1; i++){
                Task currTask = taskList[i - 1];
                System.out.println(i + ". " + currTask);
            }
            getUserInput();
        }
    }

    /**
     * Handle the deadline task.
     *
     * @param task the task with deadline.
     */
    private static void deadlineTask(String task) {
        System.out.println(conformation);
        String[] actions = task.split("/by", 2);
        Deadline currTask = new Deadline(actions[0].trim(), actions[1].trim());
        taskList[taskNum] = currTask;
        taskNum++;
        System.out.println(currTask + "\n" + "Now you have " + taskNum
                + " tasks in the list.");
        getUserInput();
    }

    /**
     * Handle the event task.
     *
     * @param task the task that represent the event.
     */
    private static void eventTask(String task) {
        System.out.println(conformation);
        String[] actions = task.split("/at", 2);
        Event currTask = new Event(actions[0].trim(), actions[1].trim());
        taskList[taskNum] = currTask;
        taskNum++;
        System.out.println(currTask + "\n" + "Now you have " + taskNum
                + " tasks in the list.");
        getUserInput();
    }

    /**
     * Handle the todo task.
     *
     * @param task the task that represent the todo.
     */
    private static void todoTask(String task) {
        System.out.println(conformation);
        ToDo currTask = new ToDo(task);
        taskList[taskNum] = currTask;
        taskNum++;
        System.out.println(currTask + "\n" + "Now you have " + taskNum
                + " tasks in the list.");
        getUserInput();
    }

    /**
     * Marked the task as completed.
     *
     * @param task the task that needed to be mark.
     */
    private static void markTaskList(String task){
        int currTaskNum = Integer.parseInt(task);
        Task currTask = taskList[currTaskNum - 1];
        currTask.setChecked();
        System.out.println("Nice! I've marked this task as done:\n"
                + currTask);
        getUserInput();
    }

    /**
     * To unmark the task as not completed.
     *
     * @param task the task to be unmarked.
     */
    private static void unmarkTaskList(String task){
        int currTaskNum = Integer.parseInt(task);
        Task currTask = taskList[currTaskNum - 1];
        currTask.setUnchecked();
        System.out.println("OK, I've marked this task as not done yet:\n"
                + currTask);
        getUserInput();
    }
}