public class Ui {
    static final String DESC_RESPONSE = "Oops! \\(@.@)/ You have not keyed in a description for the task!\n" +
            "Let's try again ~(^.^)~\n" +
            "Type 'help' if you need to know how to use this command";
    static final String BYE_RESPONSE = "Bye~ Hope to see you again soon!\n✧･ﾟ: *✧･ﾟ:*(*❦ω❦)*:･ﾟ✧*:･ﾟ✧";
    static final String OOB_RESPONSE = "Sorry, I could not find the item \\(T.T)/\n" +
            "Please type 'list' to view your current entries.";

    public Ui() {
    }

    /**
     * Prints the 'bye' response by Ducky. Usually called when the user says "bye"
     */
    public void printBye(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task currentTask = tasks.getTask(i);
            String message = currentTask.getTask();
            output = output + (i + 1 + ". " + message) + "\n" ;
        }
        System.out.println(BYE_RESPONSE);
    }

    /**
     * Prints the 'help' response by Ducky. Usually called when the user says "help"
     */
    public void help() {
        String helpResponse = "> Type 'list' to see what you have in your task list\n" +
                "> Type 'todo <message>' to put a todo in your list\n" +
                "> Type 'deadline <message> /by <deadline>' to put a deadline in your list." +
                "\n\t - Deadline must be in 'DD/MM/YYYY' format" +
                "\n> Type 'event <message> /at <date>' to put an event in your list" +
                "\n\t - Date must be in 'DD/MM/YYYY' format" +
                "\n> Type 'mark <x>' to mark a task in your list" +
                "\n> Type 'unmark <x>' to unmark a task in your list";
        ;
        System.out.println(helpResponse);
    }

    public void printTasks(TaskList tasks) throws DukeException {
        if (tasks.getSize() == 0) {
            throw new DukeException("Please add some tasks first *(^.^)*");
        } else {
            System.out.println("Here are your tasks:");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task currentTask = tasks.getTask(i);
                String message = currentTask.getTask();
                System.out.println(i + 1 + ". " + message);
            }
        }
    }

    /**
     * Deletes the task specified by the user
     *
     * @param i ID of the task the user would like to delete.
     * @throws DukeException if there is an IndexOutOfBoundsException.
     */
    public void deleteTask(String i, TaskList tasks) throws DukeException {
        try {
//            Task currTask = tasks.getTask(Integer.parseInt(i) - 1);
            Task currTask = tasks.removeTask(Integer.parseInt(i) - 1);
            System.out.println("I have removed this from your tasks:\n" + currTask.getTask());
            System.out.println("You now have " + tasks.getSize() + " tasks");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }

    /**
     * Adds a Todo object to the list when specified by the user.
     *
     * @param description which is the task description from user.
     * @throws DukeException when there is no description provided by the user.
     */
    public TaskList addTodo(String description, TaskList tasks) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            Todo entry = new Todo(description);
            tasks.addTasks(entry);
            String message = entry.getTask();
            System.out.println("I have added the following todo:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
        return tasks;
    }

    /**
     * Adds a Deadline object to the list when specified by the user.
     *
     * @param description which is the task description from user.
     * @param time        which is the deadline provided by user.
     * @throws DukeException when there is no description provided or when there is no time specified by the user.
     */
    public void addDeadline(String description, String time, TaskList tasks) throws DukeException {
        DateHelper datetime = new DateHelper(time);
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        }
        if (time.length() == 0) {
            throw new DukeException("Oops! You have not keyed in a due date for the task! ┗(｀Дﾟ┗(｀ﾟДﾟ´)┛ﾟД´)┛\n" +
                    "Let's try again  (☆｀• ω •´)ｂ\n" +
                    "Type 'help' if you need to know how to use this command");
        } else {
            Deadline entry = new Deadline(description, datetime);
            tasks.addTasks(entry);
            String message = entry.getTask();
            System.out.println("I have added the following deadline:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
//        return tasks;
    }

    /**
     * Adds an Event object to the list when specified by the user.
     *
     * @param description which is the task description from user.
     * @param time        which is the event time provided by user.
     * @throws DukeException when there is no description provided or when there is no time specified by the user.
     */
    public TaskList addEvent(String description, String time, TaskList tasks) throws DukeException {
        DateHelper datetime = new DateHelper(time);
        if (description.length() == 0) {
            throw new DukeException(DESC_RESPONSE);
        } else {
            Event entry = new Event(description, datetime);
            tasks.addTasks(entry);
            String message = entry.getTask();
            System.out.println("I have added the following event:\n" + message);
            System.out.println("You now have " + tasks.getSize() + " tasks");
        }
        return tasks;
    }

    /**
     * Unmarks the item specified by the user.
     *
     * @param strID which is the ID number of the task in the list
     * @throws DukeException when the specified ID number is not in the list
     */
    public void unmarkItem(String strID, TaskList tasks) throws DukeException {
        try {
            Task currentTask = tasks.getTask(Integer.parseInt(strID) - 1);
            currentTask.setNotDone();
            String message = currentTask.getTask();
            System.out.println("Sure, I have unmarked the following task:\n" + message);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }

    /**
     * Marks the item specified by the user.
     *
     * @param strID which is the ID number of the task in the list.
     * @throws DukeException when the specified ID number is not in the list.
     */
    public void markItem(String strID, TaskList tasks) throws DukeException {
        try {
            Task currentTask = tasks.getTask(Integer.parseInt(strID) - 1);
            currentTask.setDone();
            String message = currentTask.getTask();
            System.out.println("Ok, I have marked the following task:\n" + message);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(OOB_RESPONSE);
        }
    }

}
