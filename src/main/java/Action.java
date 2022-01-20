import java.util.ArrayList;

public class Action {

    Action() {}

    void greet() {
        String greet = "Hello! I'm JiaMing\nWhat can I do for you?\n";
        System.out.println(greet);
    }

    void echo(String phrase) {
        System.out.println(phrase);
    }

    void bye() {
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
    }

    void showList(ArrayList<Task> arrlst) {
        System.out.println("Here are the tasks in your list:");
        for (int i =  0; i < arrlst.size(); i++) {
            String output = String.format("%d.[%s] %s\n", i + 1, arrlst.get(i).getStatusIcon(),
                                            arrlst.get(i).description);
            System.out.println(output);
        }
    }

    @Override
    public String toString() {
        return "this is an Action (Parent)";
    }

}
