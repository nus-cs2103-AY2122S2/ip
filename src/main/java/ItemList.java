import java.util.ArrayList;

class ItemList{
    protected ArrayList<String> itemList;

    ItemList() {
        this.itemList = new ArrayList<String>();
    }

    void addItem(String item) {
        itemList.add(item);
    } 

    void printList() {
        for (int index = 0; index < this.itemList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + itemList.get(index));
        }
    }
}