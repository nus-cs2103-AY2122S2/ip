import java.time.LocalDate;

public class Task {

    public String s;
    public boolean done;
    public LocalDate date;

    public Task(String s){
        this.s = s;
        this.done = false;
    }

    public void mark(){
        this.done = true;
        System.out.println("Duke: Nice! I've marked this task as done:\n      "+this.show());
    }

    public void unmark(){
        this.done = false;
        System.out.println("Duke: OK, I've marked this task as not done yet:\n      "+this.show());
    }

    public String show(){
        if(done){
            return "[X] " + this.s;
        }
        else{
            return "[ ] " + this.s;
        }
    }

    public String storeFormat(){
        return this.s;
    }

}
