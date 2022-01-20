import java.util.ArrayList;

public class Control {
    private ArrayList<String> tasks;
    public Control() {
        String start =
                "________________________________\n"
                        + "Hello! I am Duke_two.\n"
                        + "Your Personal Assistant.\n"
                        + "What can I do for you?\n"
                        + "________________________________";
        this.tasks = new ArrayList<>();
        System.out.println(start);
    }

    public void add(String task) {
        this.tasks.add(task);
        System.out.println("________________________________");
        System.out.println("From Duke_two: \n\tAdded to your tasks: " + task);
    }

    public void bye() {
        String bye = "GoodBye! I hope to see you again!";
        System.out.println(bye);
    }

    public void list() {
        int leng = tasks.toArray().length;
        for (int i = 0; i < leng; i++) {
            String task = tasks.get(i);
            int num = i + 1;
            System.out.println(num + ": " + task);
        }
    }

}
