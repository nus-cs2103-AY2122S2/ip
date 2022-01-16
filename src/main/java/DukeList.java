import java.util.*;

public class DukeList {

    public List<Task> a;

    public DukeList(){
        this.a = new ArrayList<Task>();
    }

    public void add(Task t){
        a.add(t);
        System.out.println("\nDuke: Got it. I've added this task:\n      " + t.show() + "\n      Now you have " +
                this.a.size() + " tasks in the list.\n");
    }

    public void mark(int x){
        a.get(x-1).mark();
    }

    public void unmark(int x){
        a.get(x-1).unmark();
    }

    public String print(){
        String ans = "";
        int y = 1;
        for(Task x : a){
            ans += y + ". " + x.show() + "\n";
            y++;
        }
        return ans;
    }

}
