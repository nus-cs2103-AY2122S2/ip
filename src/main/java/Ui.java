import java.util.ArrayList;

public class Ui {
    public static void lineOne() {
        System.out.println("*************************************************************************");
    }

    public static void lineTwo() {
        System.out.println("|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|-|");
    }

    public static void printList(ArrayList<Task> list, int size) {
        lineOne();
        System.out.println("List:");
        if(list.isEmpty()) {
            System.out.println("No tasks to complete! ^_^");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        lineOne();
    }

    public static int exitHalloumi() {
        lineOne();
        System.out.println("See you soon! Have a good day ^_^");
        lineOne();
        return 1;
    }
}
