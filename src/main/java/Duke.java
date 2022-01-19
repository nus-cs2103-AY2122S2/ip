import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    public static String LINE = "____________________________________________________________";
    public static ArrayList<Task> LIST = new ArrayList<>();

    public static void greet() {
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
    }

    public static void addTodo(String s) {
        LIST.add(new Todo(s));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    public static void addDeadline(String s, String time) {
        LIST.add(new Deadline(s, time));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    public static void addEvent(String s, String time) {
        LIST.add(new Event(s, time));
        System.out.println(LINE + "\n Got it. I've added this task:");
        System.out.println("   " + LIST.get(LIST.size() - 1).toString());
        System.out.printf(" Now you have %d tasks in the list.\n", LIST.size());
        System.out.println(LINE);
    }

    public static void list() {
        System.out.println(LINE + "\n Here are the tasks in your list:");
        for (int i = 1; i <= LIST.size(); i++) {
            System.out.printf(" %d.%s\n", i, LIST.get(i - 1).toString());
        }
        System.out.println(LINE);
    }

    public static void mark(int i) {
        System.out.println(LINE + "\n Nice! I've marked this task as done:");
        LIST.get(i - 1).setDone();
        System.out.println("   " + LIST.get(i - 1).toString());
        System.out.println(LINE);
    }

    public static void unmark(int i) {
        System.out.println(LINE + "\n OK, I've marked this task as not done yet:");
        LIST.get(i - 1).setNotDone();
        System.out.println("   " + LIST.get(i - 1).toString());
        System.out.println(LINE);
    }

    public static void exit() {
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        whileLoop:
        while (sc.hasNext()) {
            String usrInput = sc.next();
            String task = "";
            switch (usrInput) {
                case "bye":
                    exit();
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
                case "todo": {
                    usrInput = sc.nextLine();
                    addTodo(usrInput.substring(1));
                    break;
                }
                case "deadline": {
                    task = sc.next();
                    while (sc.hasNext()) {
                        String currStr = sc.next();
                        if (currStr.equals("/by")) {
                            String time = sc.nextLine();
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
                            addEvent(task, time);
                            break;
                        } else {
                            task += " " + currStr;
                        }
                    }
                    break;
                }
                default:
                    usrInput += sc.nextLine();
                    addTodo(usrInput);
                    break;
            }
        }
    }
}
