import java.util.ArrayList;

class TaskList<String> {
    ArrayList<String> arrayList;

    TaskList() {
        this.arrayList = new ArrayList<String>();
    }

    public int length() {
        return this.arrayList.size();
    }

    public void add(String item) {
        this.arrayList.add(item);
    }

    public void list() {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println((i + 1) + ". " + arrayList.get(i));
        }
    }

    public boolean allTasksCompleted() {
        for (int i = 0; i < arrayList.size(); i++) {
            String task = arrayList.get(i);
        }
        return true;
    }

}