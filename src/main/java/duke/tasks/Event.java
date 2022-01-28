package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    public Event(String taskName, LocalDateTime date){
        this.taskName = taskName;
        this.date = date;
    }

    public char getType(){
        return 'E';
    }

    @Override
    public String toString(){
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

        return String.format("[%c][%c] %s (at: %s)",this.getType(),this.done,this.taskName,this.date.format(formatted));
    }
}
