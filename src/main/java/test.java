public class test {
    public static void main(String[] args){
        Task a = new ToDos("hi");
        DukeList b = new DukeList();
        b.add(a);
        System.out.println(b.print());
        b.mark(1);
        System.out.println(b.print());
        b.unmark(1);
        System.out.println(b.print());
    }
}
