
package task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import util.CommandList;
import util.DateTimeParser;

public class TaskList {
    private final List<Task> list = new ArrayList<>();
    private final DateTimeParser dtp = new DateTimeParser();
    private final CommandList cl = new CommandList();


    public List<Task> getTaskList() {
        return list;
    }

    private int size() {
        return list.size();
    }

    private Task get(int index) {
        return list.get(index);
    }

    private void remove(int index) {
        list.remove(index);
    }

    /**
     * executes the command and returns a response
     * @param command command to be executed
     * @param description description of command
     * @return response of command executed
     */

    public String execute(String command, String description) {
        switch (command) {
        case "todo":
            return processTodo(description);

        case "deadline":
            return processDeadline(description);

        case "event":
            return processEvent(description);

        case "list":
            return processList();

        case "mark":
            return processMark(description);

        case "unmark":
            return processUnmark(description);

        case "delete":
            return processDelete(description);

        case "find":
            return processFind(description);

        case "commandlist":
            return processCommandList();

        default:
            return "This command does not exists!\nUse 'commandlist' to list out the commands";

        }
    }

    private String processCommandList() {
        StringBuilder commands = new StringBuilder();
        for (String s: cl.getTaskCommands()) {
            commands.append(s).append("\n");
        }
        return "Here are the list of commands:\n" + commands;
    }

    private String processFind(String description) {
        if (description.equals("")) {
            return "Please specify the item that you would like to find.";
        }
        assert !description.equals("");
        return findResponse(description);
    }

    private String processTodo(String description) {
        if (!description.equals("")) {
            Task task = new Todo(description);
            list.add(task);
            return addTaskReponse(this.get(this.size() - 1), this.size());
        } else {
            return "Todo cannot be empty!!! :/";
        }

    }

    private String processDeadline(String description) {
        if (description.equals("")) {
            return "Deadline cannot be empty!!! :/";
        }
        try {
            dtp.parseDateTime(description, "deadline");
            Task task = new Deadline(dtp.getDescription(), dtp.getTime());
            list.add(task);
            return addTaskReponse(this.get(this.size() - 1), this.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please specify your deadline in the format 'task /by when'";
        } catch (DateTimeParseException e) {
            return "Please put your date in the format\nYYYY-MM-DD HH:mm";
        }

    }

    private String processEvent(String description) {
        if (description.equals("")) {
            return "Event cannot be empty!!! :/";
        }
        try {
            assert !description.equals("");
            dtp.parseDateTime(description, "event");
            Task task = new Event(dtp.getDescription(), dtp.getTime());
            list.add(task);
            return addTaskReponse(this.get(this.size() - 1), this.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Please specify your event in the format 'task /at when'";
        } catch (DateTimeParseException e) {
            return "Please put your date in the format\n'YYYY-MM-DD HH:mm'";
        }

    }

    private String processList() {
        StringBuilder lists = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (i != 0) {
                lists.append("\n");
            }
            lists.append(String.format("%d. %s", i + 1, get(i).toString()));

        }
        return "Here is the list of your tasks:\n" + lists;
    }

    private String processMark(String description) {
        if (description.equals("")) {
            return "Please specify the index of the item that you would like to mark.";
        }
        try {
            assert !description.equals("");
            int index = Integer.parseInt(description);
            get(index - 1).markAsDone();
            return "Good job for accomplishing something today! I've marked this task as done:\n"
                    + get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "You can't do that! It's not in the list!";
        } catch (NumberFormatException e) {
            return "Please input the index of task that you want to mark";
        }
    }

    private String processUnmark(String description) {
        if (description.equals("")) {
            return "Please specify the index of the item that you would like to unmark.";
        }
        try {
            assert !description.equals("");
            int index = Integer.parseInt(description);
            get(index - 1).markAsUndone();
            return "Stop procrastinating you lazy prick! I've marked this task as not done yet:\n"
                    + get(index - 1).toString();
        } catch (IndexOutOfBoundsException e) {
            return "You can't do that! It's not in the list!";
        } catch (NumberFormatException e) {
            return "Please input the index of task that you want to unmark";
        }
    }


    private String processDelete(String item) {
        try {
            int index = Integer.parseInt(item);
            Task t = this.get(index - 1);
            this.remove(index - 1);
            return removeTaskResponse(t, this.size());
        } catch (IndexOutOfBoundsException e) {
            return "You can't do that! It's not on the list!";
        } catch (NumberFormatException e) {
            return "Please input the index of task that you want to delete";
        }
    }

    private String removeTaskResponse(Task task, int total) {
        String tab = "    ";
        String firstLine = "Less work for you then less work for me then. I've removed this task:\n";
        String secondLine = task.toString() + "\n";
        String thirdLine;

        if (total == 1) {
            thirdLine = "Now you have " + total + " task in the list.";
        } else {
            thirdLine = "Now you have " + total + " tasks in the list.";
        }
        return firstLine + secondLine + thirdLine;

    }

    private String addTaskReponse(Task task, int total) {
        String firstLine = "Ah sure. I've added this task:\n";
        String secondLine = task.toString() + "\n";
        String thirdLine;
        if (total == 1) {
            thirdLine = "Now you have " + total + " task in the list.";
        } else {
            thirdLine = "Now you have " + total + " tasks in the list.";
        }

        return firstLine + secondLine + thirdLine;

    }

    private String findResponse(String item) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task t: this.list) {
            if (t.description.contains(item)) {
                foundTasks.add(t);

            }
        }

        String firstLine = "Here are the matching tasks in you list:\n";
        StringBuilder lists = new StringBuilder();
        for (int i = 0; i < foundTasks.size(); i++) {
            if (i != 0) {
                lists.append("\n");
            }
            lists.append(String.format("%d. %s", i + 1, foundTasks.get(i).toString()));

        }

        return firstLine + lists;
    }


}
