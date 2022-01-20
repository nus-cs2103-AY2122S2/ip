import java.util.ArrayList;
import java.util.Scanner;

public class Bobby {
    static ArrayList<Task> tasks = new ArrayList<Task>();
    enum Commands {
        todo, bye, list, mark, unmark, deadline, event, delete, illegal
    }

    public static void AddTask(Task t){
        tasks.add(t);
    }
    public static void DeleteTask(String input, String[] inputs) throws DeleteException {
        if (input.substring(6).isBlank()) {                             // no argument
            throw new DeleteException("empty");
        } else if (Character.isLetter(input.charAt(7))) {               // contains letter instead of number
            throw new DeleteException("letter");
        } else if (Integer.parseInt(inputs[1]) > tasks.size()) {        // out of bounds
            throw new DeleteException("OOB");
        } else if (Integer.parseInt(inputs[1]) < 1) {
            throw new DeleteException("negative");
        }
        Task t = Bobby.tasks.get(Integer.parseInt(inputs[1])-1);
        System.out.println("    Alright I'm deleting this task:\n    " + t);
        tasks.remove(t);
        System.out.println("    " + DisplayNumTasks());
    }
    public static String DisplayNumTasks() {
        return "Now you have " + tasks.size() + " in the list.";
    }
    public static void ListTask() {
        Task currTask;
        for (int i = 0; i < tasks.size(); i++) {
            currTask = Bobby.tasks.get(i);
            int index = i+1;
            System.out.println("    " + index + "." + currTask);
        }
    }
    public static void markAsDone(String input, String[] inputs) throws MarkException {
        if (input.substring(4).isBlank()) {                             // no argument
            throw new MarkException("empty");
        } else if (Character.isLetter(input.charAt(5))) {               // contains letter instead of number
            throw new MarkException("letter");
        } else if (Integer.parseInt(inputs[1]) > tasks.size()) {        // out of bounds
            throw new MarkException("OOB");
        } else if (Integer.parseInt(inputs[1]) < 1) {
            throw new MarkException("negative");
        }
        Task t = Bobby.tasks.get(Integer.parseInt(inputs[1])-1);
        if (t.done) {
            throw new MarkException("alr_marked");
        }
        t.markDone();
        System.out.println("    Finally... I've marked this task as done:");
        System.out.println("      " + t);
    }
    public static void markAsNotDone(String input, String[] inputs) throws MarkException {
        if (input.substring(4).isBlank()) {                             // no argument
            throw new MarkException("empty");
        } else if (Character.isLetter(input.charAt(7))) {               // contains letter instead of number
            throw new MarkException("letter");
        } else if (Integer.parseInt(inputs[1]) > tasks.size()) {        // out of bounds
            throw new MarkException("OOB");
        } else if (Integer.parseInt(inputs[1]) < 1) {
            throw new MarkException("negative");
        }
        Task t = Bobby.tasks.get(Integer.parseInt(inputs[1])-1);
        if (!t.done) {
            throw new MarkException("alr_unmarked");
        }
        t.unmarkDone();
        System.out.println("    " + "Could you be any more lazy? I've marked this task as not done yet:");
        System.out.println("      " + t);
    }
    public static void AddDeadline(String s) throws DeadlineException {
        if (s.substring(8).isBlank()) {                             // nothing after command
            throw new DeadlineException("deadline1");
        } else if (!s.contains("/")){                               // no "/"
            throw new DeadlineException("deadline2");
        } else if (s.substring(s.indexOf("/")+1).isBlank()) {       // nothing after time
            throw new DeadlineException("deadline3");
        }
        Deadline newDeadline = new Deadline(s.substring(s.indexOf(" ")+1, s.indexOf("/")-1),
                s.substring(s.indexOf("/")));
        System.out.println("    Oh boy, another deadline? Added task:");
        System.out.println("    " + newDeadline);
        AddTask(newDeadline);
        System.out.println("    " + DisplayNumTasks());
    }
    public static void AddToDo(String s) throws ToDoException {
        if (s.substring(4).isBlank()) {                             // nothing after command
            throw new ToDoException("todo");
        }
        ToDo newToDo = new ToDo(s.substring(4));
        System.out.println("    OK you better do this today, or else! Added task:");
        System.out.println("    " + newToDo);
        AddTask(newToDo);
        System.out.println("    " + DisplayNumTasks());
    }
    public static void AddEvent(String s) throws EventException {
        if (s.substring(5).isBlank()) {                             // nothing after command
            throw new EventException("event1");
        } else if (!s.contains("/")){                               // no "/"
            throw new EventException("event2");
        } else if (s.substring(s.indexOf("/")+1).isBlank()) {       // nothing after time
            throw new EventException("event3");
        }
        Event newEvent = new Event(s.substring(s.indexOf(" ")+1, s.indexOf("/")-1), s.substring(s.indexOf("/")));
        System.out.println("    Let's see... A new event! Added task:");
        System.out.println("    " + newEvent);
        AddTask(newEvent);
        System.out.println("    " + DisplayNumTasks());
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____  _____ ____  ____ __  __\n"
                + "|  _ \\|  _  |  _ \\|  _ \\\\ \\/ /\n"
                + "| |_| | | | | |_| | |_| |\\  /\n"
                + "| |_| | |_| | |_| | |_| ||  |\n"
                + "|____/|_____|____/|____/ |__|\n";
        System.out.println("Hello from\n" + logo);
        String dash = "    ____________________________________\n";
        String dash2 = "    ____________________________________";
        System.out.println(dash + "    Howdy! I'm Bobby\n    What can I do for you?\n" + dash2);

         while (true) {
             String input = sc.nextLine();
             String inputs[] = input.split(" ");
             Commands cmd = null;
             try {
                 cmd = Commands.valueOf(inputs[0]);
             } catch (IllegalArgumentException e) {
                 cmd = Commands.illegal;
             }
             switch(cmd) {
                 case bye:
                     System.out.println(dash + "    Bye! Hope to see you again soon!\n" + dash2);
                     return;
                 case list:
                     System.out.println(dash);
                     ListTask();
                     System.out.println(dash2);
                     break;
                 case mark:
                     System.out.println(dash);
                     try {
                         markAsDone(input, inputs);
                     } catch (MarkException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case unmark:
                     System.out.println(dash);
                     try {
                         markAsNotDone(input, inputs);
                     } catch (MarkException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case todo:
                     System.out.println(dash);
                     try {
                         AddToDo(input);
                     } catch (ToDoException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case deadline:
                     System.out.println(dash);
                     try {
                         AddDeadline(input);
                     } catch (DeadlineException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case event:
                     System.out.println(dash);
                     try {
                         AddEvent(input);
                     } catch (EventException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case delete:
                     System.out.println(dash);
                     try {
                         DeleteTask(input, inputs);
                     } catch (DeleteException e) {
                         System.out.println(e);
                     }
                     System.out.println(dash2);
                     break;
                 case illegal:
                     System.out.println(dash + "    Invalid command\n" + dash2);
             }

        }
    }

}
