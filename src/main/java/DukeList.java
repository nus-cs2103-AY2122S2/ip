import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.List;

public class DukeList {

    public List<Task> a;
    private Storage storage;

    public static final String STORAGE_PATH = "../../data.txt";

    public DukeList(Storage s){
        this.a = s.load();
        this.storage = s;
    }

    /**
     * Used to add a task to the list, and print add message to the console.
     * @param t Task to be added
     */
    public void add(Task t){
        a.add(t);
        storage.store(a);
        System.out.println("\nDuke: Got it. I've added this task:\n      " + t.show() + "\n      Now you have " +
                this.a.size() + " tasks in the list.\n");
    }


    /**
     * Used to mark tasks as done.
     * @param x
     */
    public void mark(int x) {
        try {
            a.get(x - 1).mark();
        } catch(IndexOutOfBoundsException e){
            System.out.println("\nDuke: Wrong index to mark! Use \"list\" to see the current tasks.\n");
        }
        storage.store(a);
    }

    /**
     * Used to unmark tasks.
     * @param x The task number
     */
    public void unmark(int x){
        try {
            a.get(x - 1).unmark();
        } catch(IndexOutOfBoundsException e){
            System.out.println("\nDuke: Wrong index to unmark! Use \"list\" to see the current tasks.\n");
        }
        storage.store(a);
    }

    /**
     * Returns a String representation of the list, the indexes as well as the tasks, or NO TASK if empty.
     * @return String representation of list.
     */
    @Override
    public String toString(){
        if(a.isEmpty()){
            return "NO TASKS\n";
        }
        String ans = "";
        int y = 1;
        for(Task x : a){
            ans += y + ". " + x.show() + "\n";
            y++;
        }
        return ans;
    }



    /**
     * Used to delete tasks from the list
     * @param x String representation of the index to delete
     */
    public void delete(int x) {
        try{
            Task t = a.get(x-1);
            a.remove(x-1);
            System.out.println("\nDuke: Noted. I've removed this task:\n      " + t.show() + "\n      Now you have " +
                    this.a.size() + " tasks in the list.\n");
            storage.store(a);
        } catch( IndexOutOfBoundsException e) {
            System.out.println("\nDuke: Wrong index to delete! Use \"list\" to see the current tasks.\n");
        }
    }

    public void todayTask(){
        LocalDate cur = LocalDate.now();
        String day = "\nDuke: Here are the tasks due today\n";
        boolean b = true;
        for(Task t: a){
            if (t instanceof Events || t instanceof Deadlines ){
                if(t.date.isEqual(cur)) {
                    b = false;
                    day = day + "     " + t.show() + "\n";
                }
            }
        }
        if(b){
            day += "      NO TASK DUE TODAY";
        }
        System.out.println(day);
    }

}
