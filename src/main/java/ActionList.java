import java.util.ArrayList;

public class ActionList {
    ArrayList<String> actions = new ArrayList<String>();

    public boolean add(String action){
         return actions.add(action);
    }

    public void print(){
        for (int i = 0; i < actions.size(); i++) {
            System.out.print(i + 1);
            System.out.println(". " + actions.get(i));
        }
    }
}
