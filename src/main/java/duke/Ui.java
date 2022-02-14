package duke;

import java.util.ArrayList;

class Ui {
    Ui() {
    }
    public String showGoodbyeMessage() {
        String endMessage = "Bye. Hope to see you again soon!";
        return endMessage;
    }

    public String showWelcomeMessage() {
        String startMessage = "Hello! I'm Duke\n"
                + "What can I do for you?";
        return startMessage;
    }

    public String showListMessage(TaskList tasklist) {
        if (tasklist.getTaskArray().isEmpty()) {
            String listMessage = "The list is empty";
            return listMessage;
        } else {
            String listMessage = "Here are the tasks in your list: \n";
            for (int i = 0; i < tasklist.getTaskArray().size(); i++) {
                String index = String.valueOf(i + 1);
                listMessage = listMessage + index + "."
                        + tasklist.getTaskArray().get(i) + "\n";
            }
            return listMessage;
        }
    }

    public String showFindMessage(ArrayList<Task> taskArray) {
        if (taskArray.isEmpty()) {
            String listMessage = "There are no matching tasks in your list.";
            return listMessage;
        } else {
            String listMessage = "Here are the matching tasks in your list: \n";
            for (int i = 0; i < taskArray.size(); i++) {
                String index = String.valueOf(i + 1);
                listMessage = listMessage + index + "." + taskArray.get(i) + "\n";
            }
            return listMessage;
        }
    }

    public String showAddedMessage(Task task, String no0fTask) {
        String messageTask = "Got it. I've added this task: \n";
        String taskString = task.toString();
        String output = messageTask + taskString + "\n"
                + "Now you have " + no0fTask + " tasks in the list.";
        return output;
    }

    public String showDeletedMessage(Task task, String noOfTask) {
        String messageTask = "Noted. I've removed this task: \n";
        String taskString = task.toString();
        String output = messageTask + taskString
                + "\n" + "Now you have " + noOfTask
                + " tasks in the list.";
        return output;
    }

    public String showDeleteError() {
        return "There are no tasks to be deleted!";
    }

    public String showInvalidInput() {
        return "The input is not valid :(";
    }

    public String showTodoError() {
        return "☹ OOPS!!! The description of a todo cannot be empty.";
    }

    public String showDeadlineError() {
        return "☹ OOPS!!! The description of a deadline cannot be empty.";
    }

    public String showEventError() {
        return "☹ OOPS!!! The description of a event cannot be empty.";
    }

    public String showDefaultMessage() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }


}
