import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void printTaskCount() {
        System.out.println(String.format("Now you have %d task(s) in your list.", this.getSize()));
    }

    public void printTaskAdded() {
        System.out.println("Got it. I've added this task:");
        System.out.println(this.get(this.getSize() - 1).toString());
        printTaskCount();
    }

    public String getTaskStatement(int i) {
        //[X] read book
        return this.get(i).toString();
    }

    public void addTask(String action, String input) {    //adds task to list
        switch (action) {
            case "todo":
                try {
                    String[] todoArr = input.split("\\s", 2);
                    if (todoArr.length <= 1) {
                        throw new InvalidArgumentException("todo.. todo what?");
                    }
                    this.add(new Todo(todoArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
            case "deadline":
                //deadline do hw /by no idea :-p
                try {
                    String[] deadlineArr = input.split("/by", 2);
                    String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
                    if (deadlineSplit.length <= 1) {    // no description
                        throw new InvalidArgumentException("Sorry but.. deadline of what??");
                    }
                    if (deadlineArr.length <= 1) { // don't have /by keyword
                        throw new InvalidArgumentException("By when?? ..");
                    }
                    String description = deadlineSplit[1].trim();
                    this.add(new Deadline(description, deadlineArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date-time format! Format: <dd/MM/yyyy HHMM>."
                            + System.lineSeparator() + "An example: 12/12/2222 0800");
                } finally {
                    break;
                }
            case "event":
                //event project meeting /at Mon 2-4pm
                try {
                    String[] eventArr = input.split("/at", 2);
                    String[] eventSplit = eventArr[0].split("\\s", 2);
                    if (eventSplit.length <= 1) {
                        throw new InvalidArgumentException("What event? No event stated.");
                    }
                    if (eventArr.length <= 1) {
                        throw new InvalidArgumentException("At where? Please specify again");
                    }
                    String description = eventSplit[1].trim();
                    this.add(new Event(description, eventArr[1].trim()));
                    printTaskAdded();
                } catch (InvalidArgumentException e){
                    System.out.println(e.getMessage());
                } finally {
                    break;
                }
        }
    }

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.getSize(); i++) {
            System.out.println(i + 1 + "." + this.getTaskStatement(i));
        }
    }

    public void mark(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) { //e.g mark 2, cannot be mark 2 2 or just mark
                throw new InvalidArgumentException("Please specify what to mark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > this.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            this.get(taskNumber - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(this.getTaskStatement(taskNumber - 1));
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unmark(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to unmark clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > this.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("The task %d does not exist!", taskNumber));
            }
            this.get(taskNumber - 1).markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(this.getTaskStatement(taskNumber - 1));
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    public void delete(String[] inputWords) {
        try {
            if (!(inputWords.length == 2)) {
                throw new InvalidArgumentException("Please specify what to delete clearly.");
            }
            int taskNumber = Integer.parseInt(inputWords[1]);
            if (taskNumber > this.getSize() || taskNumber <= 0) {
                throw new OutOfBoundsException(String.format("Cannot delete" +
                        " as task %d does not exist!", taskNumber));
            }
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.getTaskStatement(taskNumber - 1));
            this.remove(taskNumber - 1);
            printTaskCount();
        } catch (OutOfBoundsException | InvalidArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
