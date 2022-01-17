public class Duke {
    private static Task[] taskList = new Task[100];
    private static int taskNum = 0;
    private static String conformation = "Got it. I've added this task:";
    private static Scanner sc;

    public static void main(String[] args) {
<<<<<<< HEAD
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
        try {
            switch (first_word) {
            case "bye":
                exit();
                break;
            case "list":
                displayList();
                break;
            case "deadline":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a deadline cannot be empty. :<");
                }
                deadlineTask(remaining_word.trim());
                break;
            case "event":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a event cannot be empty. :<");
                }
                eventTask(remaining_word.trim());
                break;
            case "todo":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! The description of a todo cannot be empty. :<");
                }
                todoTask(remaining_word.trim());
                break;
            case "mark":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! Please input a number. :<");
                }
                markTaskList(remaining_word.trim());
                break;
            case "unmark":
                if (remaining_word.equals("")) {
                    errorMessage("OOPS!!! Please input a number. :<");
                }
                unmarkTaskList(remaining_word.trim());
                break;
            default:
                errorMessage("OOPS!!! I'm sorry, but I don't know what that means :<");
            }
        } catch (DukeException error) {
            System.out.println(error.getMessage());
            getUserInput();
        }
    }

    /**
     * To show the error message to user.
     *
     * @param message error message to be display to warn user.
     * @throws DukeException error exception shown if empty.
     */
    private static void errorMessage(String message) throws DukeException {
        throw new DukeException(message);
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
=======
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
>>>>>>> parent of 01f2bfb (duke chatbot)
