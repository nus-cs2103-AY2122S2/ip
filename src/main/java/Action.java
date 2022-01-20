import java.lang.reflect.Array;
import java.util.ArrayList;

public class Action {

    Action() {}

    void greet() {
        String dash = "____________________________________________________________\n";
        String greet = "Hello! I'm JiaMing\nWhat can I do for you?\n";
        String output = String.format("%s%s%s", dash, greet, dash);
        System.out.println(output);
    }

    void echo(String phrase) {
        String dash = "____________________________________________________________\n";
        String output = String.format("%s%s\n%s", dash, phrase, dash);
        System.out.println(output);
    }

    void bye() {
        String dash = "____________________________________________________________\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String output = String.format("%s%s%s", dash, bye, dash);
        System.out.println(output);
    }

    void readBook(ArrayList<String> arrlst) {
        arrlst.add("read book");
        String dash = "____________________________________________________________\n";
        String readBook = "added: read book\n";
        String output = String.format("%s%s%s", dash, readBook, dash);
        System.out.println(output);
    }

    void returnBook(ArrayList<String> arrlst) {
        arrlst.add("return book");
        String dash = "____________________________________________________________\n";
        String returnBook = "added: return book\n";
        String output = String.format("%s%s%s", dash, returnBook, dash);
        System.out.println(output);
    }

    void list(ArrayList<String> arrlst) {
        String dash = "____________________________________________________________\n";
        System.out.println(dash);
        for (int i =  0; i < arrlst.size(); i++) {
            String output = String.format("%d. %s\n", i + 1, arrlst.get(i));
            System.out.println(output);
        }
        System.out.println(dash);
    }

    @Override
    public String toString() {
        return "this is an Action (Parent)";
    }

}
