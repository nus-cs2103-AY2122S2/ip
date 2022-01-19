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
                case "blah":
                    System.out.println("blah");
                    sc.nextLine();
                    break;
                case "mark": case "unmark":
                    mark(sc.nextInt());
                    sc.nextLine();
                    break;
                default:
                    // different cases add to different things
                    Task newTask;
                    if (command.equals("todo")) {
                        command = sc.nextLine();
                        newTask = new ToDo(command);
                    } else if (command.equals("deadline")) {
                        String[] s = sc.nextLine().split("/by");
                        newTask = new Deadline(s[0], s[1]);
                    } else {
                        String[] s = sc.nextLine().split("/at");
                        newTask = new Event(s[0], s[1]);
                    }
                    toDoList.add(newTask);
                    System.out.println("okie!! (✿◠‿◠)  i have added: \n" +
                            newTask.toString() + "\n" +
                            "now there are " + toDoList.size() + " tasks in the list! get to work (ง︡'-'︠)ง");
                    break;
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
}