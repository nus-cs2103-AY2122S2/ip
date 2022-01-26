import java.util.ArrayList;

public class TaskList {

    public static void printTheList(ArrayList<Task> storeList) {
        int sizeOfList = storeList.size();
        System.out.println("Everything in my blue brain now:");
        for (int i = 1; i <= sizeOfList; i++) {
            Task t = storeList.get(i - 1);
            System.out.println(i + "." + t.toString());
        }
    }
}
