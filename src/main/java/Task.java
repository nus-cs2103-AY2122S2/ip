import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean done;
    private LocalDateTime dead;
    private String type;

    public Task(String type, String name) {
        this.type = type;
        this.name = name;
        this.done = false;
    }

    public Task(String type, String name, LocalDateTime dead) {
        this.type = type;
        this.name = name;
        this.dead = dead;
        this.done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public String getType() {
        return type;
    }

    public String getDone() {
        if (done) {
            return "1";
        } else {
            return "0";
        }
    }

    public String getName(){
        return name;
    }

    public String getDead() {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
        return dead.format(outputFormatter).toString();
    }

    @Override
    public String toString() {
        String str = "[";
        //Completion
        if (this.done) {
            str += "X";
        } else {
            str += " ";
        }
        str += "] " + this.name;
        return str;
    }
}
