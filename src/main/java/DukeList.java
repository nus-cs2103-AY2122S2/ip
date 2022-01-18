import java.util.*;
import java.util.regex.Pattern;

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

    public void mark(String x) throws DukeTaskException{
        try {
            int b = Integer.parseInt(x.trim());
            a.get(b - 1).mark();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            throw new WrongMarkingFormat("\nDuke: Wrong index to mark! Use \"list\" to see the current tasks.");
        }
    }

    public void unmark(String x) throws DukeTaskException{
        try {
            int b = Integer.parseInt(x.trim());
            a.get(b - 1).unmark();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            throw new WrongMarkingFormat("\nDuke: Wrong index to unmark! Use \"list\" to see the current tasks.");
        }
    }

    public String print(){
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

    public void addTask(String t, String m) throws DukeTaskException{
        if(t.equals("todo")){
            if(m.isEmpty()){
                throw new WrongTodoFormat("\nDuke: Wrong format! The description of a todo cannot be empty.");
            }
            ToDos g = new ToDos(m);
            this.add(g);
        }
        else if(t.equals("deadline")){
            if(Pattern.matches(".+/by.+" , m)){
                String[] k = m.split("/by");
                Deadlines e = new Deadlines(k[0],k[1]);
                this.add(e);
            }
            else{
                throw new WrongDeadlineFormat("\nDuke: Wrong format! To add a deadline, follow the format \"deadline description /by date\"");
            }
        }
        else{
            if(Pattern.matches(".+/at.+" , m)){
                String[] r = m.split("/at");
                Events e = new Events(r[0],r[1]);
                this.add(e);
            }
            else{
                throw new WrongEventFormat("\nDuke: Wrong format! To add an event, follow the format \"event description /at date\"");
            }
        }
    }

}
