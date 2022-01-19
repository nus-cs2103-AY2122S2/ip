import java.util.ArrayList;

class ToDoList {
    ArrayList<String> lst;

    public ToDoList() {
        lst = new ArrayList<String>(100);
    }

    public void addItem(String s) {
        lst.add(s);
        System.out.println("added: " + s);
    }

    public void print() {
        lst.forEach((item) -> System.out.println((lst.indexOf(item)+1) + ". " + item));
    }
}