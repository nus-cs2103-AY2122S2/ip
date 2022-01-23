package duke;

import exceptions.DateException;
import exceptions.DukeException;
import exceptions.TaskIndexException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;
import java.util.ArrayList;

public class TaskManager {
    private final String LINE = "-------------------------------------------";
    private ArrayList<Task> TASKS;
    private boolean status;
    private String TASKLIST;


    public TaskManager() {
        this.TASKS = new ArrayList<>();
        this.status = true;
        System.out.println(LINE);
        System.out.println("Hello there, I'm Larry!");
        System.out.println(LINE);
    }

    public void addTask(Task t) {
        TASKS.add(t);
    }

    public void execute(String[] spliced) throws DukeException, TaskIndexException {
        switch (spliced[0]) {
            case "bye":
                this.status = false;
                this.Line();
                System.out.println("Bye. Hope to see you again soon!");
                this.Line();
                break;

            case "list":
                System.out.println(this);
                break;

            case "todo":
                if (spliced.length == 1) {
                    throw new TaskIndexException("'todo'");
                }
                Task newTodo = new ToDo(spliced[1]);
                TASKS.add(newTodo);
                this.Line();
                System.out.println("Got it, I added:\n"+ newTodo);
                System.out.printf("Now you have %d item(s) in your list\n", TASKS.size());
                this.Line();
                break;

            case "deadline":
                if (spliced.length == 1) {
                    throw new TaskIndexException("'deadline'");
                }
                try {
                    Task newDeadline = new Deadline(spliced[1]);
                    TASKS.add(newDeadline);
                    this.Line();
                    System.out.println("Got it, I added:\n" + newDeadline);
                    System.out.printf("Now you have %d items in your list\n", TASKS.size());
                    this.Line();
                } catch (DateException e) {
                    this.Line();
                    System.out.println("OOPS!!! You didn't specify date!\n"+
                            "use the format:\n"+
                            "'deadline your task here /by time'");
                    this.Line();
                }
                break;

            case "event":
                if (spliced.length == 1) {
                    throw new TaskIndexException("'event'");
                }
                try {
                    Task newEvent = new Event(spliced[1]);
                    TASKS.add(newEvent);
                    this.Line();
                    System.out.println("Got it, I added:\n"+ newEvent);
                    System.out.printf("Now you have %d items in your list\n", TASKS.size());
                    this.Line();
                }catch (DateException e) {
                    this.Line();
                    System.out.println("OOPS!!! You didn't specify date!\n"+
                                    "use the format:\n"+
                            "'event your event here /at time'");
                    this.Line();
                }
                break;

            case "unmark":
                try {
                    Integer index = Integer.parseInt(spliced[1]);
                    TASKS.get(index - 1).unmark();
                } catch (NumberFormatException e) {
                    this.Line();
                    System.out.println("Give me the task number in numbers please!");
                    this.Line();
                } catch (IndexOutOfBoundsException e) {
                    this.Line();
                    System.out.println("I don't think we have that task!\nUse 'list' to check");
                    this.Line();
                }
                break;

            case "mark":
                try {
                    Integer index2 = Integer.parseInt(spliced[1]);
                    TASKS.get(index2 - 1).mark();
                } catch (NumberFormatException e) {
                    this.Line();
                    System.out.println("Give me the task number in numbers please!");
                    this.Line();
                } catch (IndexOutOfBoundsException e) {
                    this.Line();
                    System.out.println("I don't think we have that task!\nUse 'list' to check");
                    this.Line();
                }
                break;
            case "delete":
                try {
                    Integer deleteItem = Integer.parseInt(spliced[1]);
                    System.out.println(this.LINE +"\n"+ "I removed this task for you:\n" + TASKS.get(deleteItem - 1));
                    TASKS.remove(deleteItem - 1);
                    System.out.printf("Now you have %d items in your list\n", TASKS.size());
                    this.Line();
                } catch (NumberFormatException e) {
                    this.Line();
                    System.out.println("Give me the task number in numbers please!");
                    this.Line();
                } catch (IndexOutOfBoundsException e) {
                    this.Line();
                    System.out.println("I don't think we have that task!\nUse 'list' to check");
                    this.Line();
                }
                break;
            default:
                throw new DukeException();
        }
    }

    public boolean open() {
        return this.status;
    }

    public void Line() {

        System.out.println(LINE);
    }

    @Override
    public String toString() {
        String s = LINE + "\n";
        s += "Here's your list, Good Sir:\n";
        for (int i = 0; i < TASKS.size(); i++) {
            s += i + 1 + ". "+ TASKS.get(i).toString() + "\n";
        }
        s += LINE;
        this.TASKLIST = s;
        return s;
    }


}
