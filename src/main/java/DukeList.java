import java.util.*;
import java.util.regex.Pattern;

public class DukeList {

    public List<Task> a;

    public DukeList(){
        this.a = new ArrayList<Task>();
    }

    /**
     * Used to add a task to the list, and print add message to the console.
     * @param t Task to be added
     */
    public void add(Task t){
        a.add(t);
        System.out.println("\nDuke: Got it. I've added this task:\n      " + t.show() + "\n      Now you have " +
                this.a.size() + " tasks in the list.\n");
    }

    /**
     * Used to mark tasks.
     * @param x
     * @throws DukeTaskException is thrown when index out-of-bounds or not an int.
     */
    public void mark(String x) throws DukeTaskException{
        try {
            int b = Integer.parseInt(x.trim());
            a.get(b - 1).mark();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            throw new WrongMarkingFormat("\nDuke: Wrong index to mark! Use \"list\" to see the current tasks.\n");
        }
    }

    /**
     * Used to unmark tasks.
     * @param x
     * @throws DukeTaskException is thrown when index is out-of-bounds or not an int.
     */
    public void unmark(String x) throws DukeTaskException{
        try {
            int b = Integer.parseInt(x.trim());
            a.get(b - 1).unmark();
        } catch(NumberFormatException | IndexOutOfBoundsException e){
            throw new WrongMarkingFormat("\nDuke: Wrong index to unmark! Use \"list\" to see the current tasks.\n");
        }
    }

    /**
     * Returns a String representation of the list, the indexes as well as the tasks, or NO TASK if empty.
     * @return String representation of list.
     */
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

    /**
     * Used to handle splitting and creation of task into different types before they are added to the list.
     * @param t The type of task
     * @param m The description of the task
     * @throws DukeTaskException is thrown when description is empty, or the format is not followed correctly
     */
    public void addTask(String t, String m) throws DukeTaskException{
        if(t.equals("todo")){
            if(m.isEmpty()){
                throw new WrongTodoFormat("\nDuke: Wrong format! The description of a todo cannot be empty.\n");
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
                throw new WrongDeadlineFormat("\nDuke: Wrong format! To add a deadline, follow the format \"deadline description /by date\"\n");
            }
        }
        else{
            if(Pattern.matches(".+/at.+" , m)){
                String[] r = m.split("/at");
                Events e = new Events(r[0],r[1]);
                this.add(e);
            }
            else{
                throw new WrongEventFormat("\nDuke: Wrong format! To add an event, follow the format \"event description /at date\"\n");
            }
        }
    }

    /**
     * Used to delete tasks from the list
     * @param x String representation of the index to delete
     * @throws DukeTaskException is thrown when index is out-of-bounds or not an int
     */
    public void delete(String x) throws DukeTaskException{
        try{
            int n = Integer.parseInt(x.trim());
            Task t = a.get(n-1);
            a.remove(n-1);
            System.out.println("\nDuke: Noted. I've removed this task:\n      " + t.show() + "\n      Now you have " +
                    this.a.size() + " tasks in the list.\n");
        } catch(NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeTaskException("\nDuke: Wrong index to delete! Use \"list\" to see the current tasks.\n");
        }
    }

}
