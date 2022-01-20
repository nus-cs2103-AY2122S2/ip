import java.util.ArrayList;

class ItemList<T> {
    protected ArrayList<T> itemList;

    ItemList() {
        this.itemList = new ArrayList<T>();
    }

    T getItem(int index) {
        return this.itemList.get(index - 1);
    }

    void addItem(T item) {
        itemList.add(item);
    } 

    void printList() {
        for (int index = 0; index < this.itemList.size(); index++) {
            System.out.println(Integer.toString(index + 1) + ". " + itemList.get(index).toString());
        }
    }

    void printNoItems() {
        System.out.println("Now you have " + this.itemList.size() + " tasks in the list.");
    }

    void deleteFromIndex(int index) {
        this.itemList.remove(index - 1);
    }
}