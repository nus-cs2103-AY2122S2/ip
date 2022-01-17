public class Lister {
    String[] list;
    int count;

    public Lister() {
        count = 0;
        list = new String[100];
    }

    public void add(String task) {
        list[count] = task;
        count++;
        System.out.println("added: " + task);
    }

    public void list() {
        for (int i = 0; i < count; i++) {
            System.out.println(Integer.toString(i + 1) + ". " + list[i]);
        }
    }
}
