import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> toDoList = new ArrayList<Task>(100);

    public static void main(String[] args) {
        String logo = "__  __ ____________\n" +
                "\\ \\/ /|_  /_  /_  /\n" +
                " >  <  / / / / / / \n" +
                "/_/\\_\\/___/___/___|";
        System.out.println(logo);
        System.out.println("Hello uwu! I'm xzzz,");
        System.out.println("You can check your schedwle here (ɔ◔‿◔)ɔ ♥!");

        respond();
    }

    private static void respond() {
        Scanner sc = new Scanner(System.in);
        label:
        while (sc.hasNext()) {
            String command = sc.next();
            switch (command) {
            case "bye":
                System.out.println("Cya later~ ≧◉◡◉≦");
                break label;
            case "list":
                displayList();
                sc.nextLine();
                break;
            case "mark":
            case "unmark":
                mark(sc.nextInt());
                sc.nextLine();
                break;
            case "todo":
            case "deadline":
            case "event":
                Task newTask;
                if (command.equals("todo")) {
                    command = sc.nextLine();
                    try {
                        newTask = makeToDo(command);
                    } catch (ToDoIllegalArgumentException ex) {
                        System.out.println(ex);
                        break;
                    }
                } else if (command.equals("deadline")) {
                    String[] s = sc.nextLine().split("/by");
                    try {
                        newTask = makeDeadline(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println(new DeadlineIllegalArgumentException("Invalid Argument"));
                        break;
                    }
                } else {
                    String[] s = sc.nextLine().split("/at");
                    try {
                        newTask = makeEvent(s[0], s[1]);
                    } catch (IndexOutOfBoundsException ex) {
                        System.out.println(new EventIllegalArgumentException("Invalid Argument"));
                        break;
                    }
                }
                toDoList.add(newTask);
                System.out.println("okie!! (✿◠‿◠)  i have added: \n" +
                        newTask + "\n" +
                        "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
                break;
            case "delete":
                remove(sc.nextInt());
                break;
            default:
                System.out.println("sowwy i don't understand what that means ಠ_ಥ try something else pwease??");
            }
        }
    }

    private static void displayList() {
        int number = 1;
        System.out.println("here are your tasks ☜(ˆ▿ˆc)");
        for (Task item : toDoList) {
            System.out.println(number + ". " + item.toString());
            number++;
        }
    }

    private static void mark(int idx) {
        toDoList.get(idx - 1).mark();
        if (toDoList.get(idx - 1).getDone()) {
            System.out.println("yay!!! this task is now marked as done ٩(˘◡˘)۶");
        } else {
            System.out.println("this task is now marked as not done yet... do it soon! ᕙ(`▿´)ᕗ");
        }
        System.out.println(toDoList.get(idx - 1).toString());
    }

    private static Task makeToDo(String name) throws ToDoIllegalArgumentException {
        if (name.isEmpty()) {
            throw new ToDoIllegalArgumentException("Illegal Argument");
        }
        return new ToDo(name);
    }

    private static Task makeDeadline(String name, String by) {
        return new Deadline(name, by.trim());
    }

    private static Task makeEvent(String name, String at) {
        return new Event(name, at.trim());
    }

    private static void remove(int idx) {
        System.out.println("OKI!! i have removed this task: \n" +
                toDoList.remove(idx - 1) + "\n" +
                "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
    }
}