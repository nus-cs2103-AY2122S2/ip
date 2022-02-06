class Ui {
    private static final String DIVIDER = "=======================================";
    
    Ui() {
    }

    public void showGoodbyeMessage() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(endMessage);
        System.out.println(DIVIDER);
    }

    public void showWelcomeMessage() {
        String startMessage = "Hello! I'm Duke\n"
                            + "What can I do for you?";
        System.out.println(startMessage);
        System.out.println(DIVIDER);
    }

    public void showListMessage(TaskList tasklist) {
        if (tasklist.getTaskArray().isEmpty()) {
            String listMessage = "The list is empty";
            System.out.println(listMessage);
            System.out.println(DIVIDER);
        } else {
            String listMessage = "Here are the tasks in your list: \n";
            for (int i = 0; i < tasklist.getTaskArray().size(); i++) {
                String index = String.valueOf(i + 1);
                listMessage =  listMessage + index + "." + tasklist.getTaskArray().get(i) + "\n";
            }
            System.out.println(listMessage);
            System.out.println(DIVIDER);
        }
    }

    public void showAddedMessage(Task task, String no0fTask) {
        String messageTask = "Got it. I've added this task: \n";
        String taskString = task.toString();
        System.out.println(messageTask + taskString + "\n"
                        + "Now you have " + no0fTask + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void showDeletedMessage(Task task, String noOfTask) {
        String messageTask = "Noted. I've removed this task: \n"; 
        String taskString = task.toString();
        System.out.println(messageTask + taskString 
                            + "\n" +
                            "Now you have " + noOfTask + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public void showDeleteError() {
        System.out.println("There are no tasks to be deleted!");
        System.out.println(DIVIDER);
    }

    public void showInvalidInput() {
        System.out.println("The input is not valid :(");
        System.out.println(DIVIDER);
    }

    public void showTodoError() {
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        System.out.println(DIVIDER);
    }

    public void showDeadlineError() {
        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
        System.out.println(DIVIDER);
    }

    public void showEventError() {
        System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
        System.out.println(DIVIDER);
    }

    public void showDefaultMessage() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(DIVIDER);
    }


}
