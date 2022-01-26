import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> LIST = new ArrayList<>();

    /**
     * Prints out the greeting message
     */
    public static void greet() {
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
    }

    /**
     * Adds a Todo task to the tasklist
     *
     * @param task  the task to be added to the list
     */
    public static void addTodo(String task) {
        LIST.add(new Todo(task));
        System.out.println(LINE);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Adds a Deadline task to the tasklist
     *
     * @param task  the task to be added to the list
     * @param deadline  the deadline of the task
     */
    public static void addDeadline(String task, String deadline) {
        LIST.add(new Deadline(task, deadline));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Adds an Event task to the tasklist
     *
     * @param task  the task to be added to the list
     * @param deadline  the deadline of the task
     */
    public static void addEvent(String task, String deadline) {
        LIST.add(new Event(task, deadline));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Prints out a list of all tasks in the tasklist
     */
    public static void list() {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= LIST.size(); i++) {
            System.out.printf(" %d.%s\n", i, LIST.get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    /**
     * Marks the indicated task as done
     *
     * @param taskNum   the task number which is to be marked as done
     */
    public static void mark(int taskNum) {
        System.out.println(LINE + "\n Nice! I've marked this task as done:");
        LIST.get(taskNum - 1).setDone();
        System.out.println("   " + LIST.get(taskNum - 1).toString());
        System.out.println(LINE);
    }

    /**
     * Marks the indicated task as not done
     *
     * @param taskNum   the task number which is to be marked as not done
     */
    public static void unmark(int taskNum) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        LIST.get(taskNum - 1).setNotDone();
        System.out.println("   " + LIST.get(taskNum - 1).toString());
        System.out.println(LINE);
    }

    /**
     * Delete the indicated task from the tasklist
     *
     * @param taskNum   the task number which is to be deleted
     */
    public static void delete(int taskNum) {
        System.out.println(LINE + "\n Noted. I've removed this task:");
        Task removed = LIST.get(taskNum - 1);
        LIST.remove(taskNum - 1);
        System.out.println("   " + removed.toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    /**
     * Throw error depending on what kind of error it is
     *
     * @param type  The type of error to be thrown
     */
    public static void throwError(String type) {
        System.out.println(LINE);
        DukeException error;
        if (type.equals("")) {
            error = new DukeException();
        } else {
            error = new DukeException(type);
        }
        System.out.println(error);
        System.out.println(LINE);
    }

    /**
     * Print out a goodbye line
     */
    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        whileLoop:
        while (sc.hasNextLine()) {
            String usrInput = sc.next();
            String task;
            switch (usrInput) {
                case "bye":
                    exit();
                    sc.close();
                    break whileLoop;
                case "list":
                    list();
                    break;
                case "mark": {
                    int taskNum = Integer.parseInt(sc.next());
                    mark(taskNum);
                    break;
                }
                case "unmark": {
                    int taskNum = Integer.parseInt(sc.next());
                    unmark(taskNum);
                    break;
                }
                case "delete": {
                    int taskNum = Integer.parseInt(sc.next());
                    delete(taskNum);
                    break;
                }
                case "todo": {
                    usrInput = sc.nextLine();
                    if (usrInput.equals("")) {
                        throwError("todo");
                        break;
                    }
                    addTodo(usrInput.substring(1));
                    break;
                }
                case "deadline": {
                    task = sc.next();
                    while (sc.hasNext()) {
                        String currStr = sc.next();
                        if (currStr.equals("/by")) {
                            String time = sc.nextLine();
                            time = time.substring(1);
                            addDeadline(task, time);
                            break;
                        } else {
                            task += " " + currStr;
                        }
                    }
                    break;
                }
                case "event": {
                    task = sc.next();
                    while (sc.hasNext()) {
                        String currStr = sc.next();
                        if (currStr.equals("/at")) {
                            String time = sc.nextLine();
                            time = time.substring(1);
                            addEvent(task, time);
                            break;
                        } else {
                            task += " " + currStr;
                        }
                    }
                    break;
                }
                default:
                    throwError("");
                    break;
            }
        }
    }
}
