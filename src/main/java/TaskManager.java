import Exceptions.DateException;
import Exceptions.DukeException;
import Exceptions.TaskIndexException;
import java.util.ArrayList;

public class TaskManager {
    private final String line = "-------------------------------------------";
    private ArrayList<Task> tasks;
    private boolean status;


    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.status = true;
        String start = "Hello there! I'm \n" +
                " __          ___      .______      .______     ____    ____ \n" +
                "|  |        /   \\     |   _  \\     |   _  \\    \\   \\  /   / \n" +
                "|  |       /  ^  \\    |  |_)  |    |  |_)  |    \\   \\/   /  \n" +
                "|  |      /  /_\\  \\   |      /     |      /      \\_    _/   \n" +
                "|  `----./  _____  \\  |  |\\  \\----.|  |\\  \\----.   |  |     \n" +
                "|_______/__/     \\__\\ | _| `._____|| _| `._____|   |__|     \n" +
                "                                                            \n"+
                "What can I do for you?";
        System.out.println(line);
        System.out.println(start);
        System.out.println(line);
    }

    public void addTask(Task t) {
        tasks.add(t);
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
                tasks.add(newTodo);
                this.Line();
                System.out.println("Got it, I added: \n"+ newTodo);
                System.out.printf("Now you have %d item(s) in your list \n", tasks.size());
                this.Line();
                break;

            case "deadline":
                if (spliced.length == 1) {
                    throw new TaskIndexException("'deadline'");
                }
                try {
                    Task newDeadline = new Deadline(spliced[1]);
                    tasks.add(newDeadline);
                    this.Line();
                    System.out.println("Got it, I added: \n" + newDeadline);
                    System.out.printf("Now you have %d items in your list \n", tasks.size());
                    this.Line();
                } catch (DateException e) {
                    this.Line();
                    System.out.println("☹ OOPS!!! You didn't specify date!\n"+
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
                    tasks.add(newEvent);
                    this.Line();
                    System.out.println("Got it, I added: \n"+ newEvent);
                    System.out.printf("Now you have %d items in your list \n", tasks.size());
                    this.Line();
                }catch (DateException e) {
                    this.Line();
                    System.out.println("☹ OOPS!!! You didn't specify date!\n"+
                                    "use the format:\n"+
                            "'event your event here /at time'");
                    this.Line();
                }
                break;

            case "unmark":
                try {
                    Integer index = Integer.parseInt(spliced[1]);
                    tasks.get(index - 1).unmark();
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
                    if (tasks.get(index2 - 1) != null) {
                        tasks.get(index2 - 1).mark();
                    }
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
        System.out.println(line);
    }

    @Override
    public String toString() {
        String s = line + "\n";
        s += "Here's your list, Good Sir:\n";
        for (int i = 0; i < tasks.size(); i++) {
            s += i + 1 + ". "+ tasks.get(i).toString() + "\n";
        }
        s += line;
        return s;
    }


}
