import java.util.ArrayList;
import java.util.List;

public class ListStorage {
    public List<String> myStorage;
    String spacing = "    ";

    public ListStorage() {
        myStorage = new ArrayList<String>();
    }

    public String addToList(String item){
        myStorage.add(item);
        return "added: " + item;
        //System.out.println("added: " + item);
    }

    public String printList(){
        int i = 1;
        StringBuilder toPrint = new StringBuilder();
        for (String item : myStorage) {
            toPrint.append(spacing).append(i).append(". ").append(item).append("\n");
            //System.out.println(i + ". " + item);
            i++;
        }
        return toPrint.toString();
    }

}
