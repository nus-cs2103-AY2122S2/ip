import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    public void enterHalloumi() {
        Ui.lineOne();
        System.out.println("Hi! I'm Halloumi ^_^");
        System.out.println("What do you need help with today?");
        Ui.lineOne();
    }

    public void mark(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("Good Job! ^_^");
        System.out.println("Task number " + task + " has been marked as done!");
        int tNum = Integer.parseInt(task);
        list.get(tNum - 1).done();
        System.out.println(list.get(tNum - 1));
        Ui.lineOne();
    }

    public void unmark(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("I've unmarked task number " + task);
        System.out.println("Complete it soon! ^_^");
        int tNo = Parser.stringToInt(task);
        list.get(tNo - 1).undo();
        System.out.println(list.get(tNo - 1));
        Ui.lineOne();
    }

    public void delete(String task, ArrayList<Task> list) {
        Ui.lineOne();
        System.out.println("Noted. I've removed this task:");
        int t2No = Parser.stringToInt(task);
        System.out.println(list.get(t2No - 1));
        list.remove(t2No - 1);
        Ui.lineOne();
    }

    public void event(String desc, String at, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        Task t1 = new Event(desc, at);
        list.add(t1);
        System.out.println(t1);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }

    public void todo(String desc, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        Task t = new ToDo(desc);
        list.add(t);
        System.out.println(t);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }

    public void deadline(String desc, String by, ArrayList<Task> list) {
        Ui.lineTwo();
        System.out.println("New task added:");
        String[] date = Parser.splitSpace(by);
        Task t2 = new Deadline(desc, LocalDate.parse(date[1]));
        list.add(t2);
        System.out.println(t2);
        System.out.println("You have " + list.size() + " tasks left now! ^_^");
        Ui.lineTwo();
    }
}
