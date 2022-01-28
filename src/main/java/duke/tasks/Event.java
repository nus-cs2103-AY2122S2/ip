package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDateTime date = null;
    private String dateString = "";

    public Event(String taskName, LocalDateTime date){
        this.taskName = taskName;
        this.date = date;
    }

    public Event(String taskName, String dateString){
        this.taskName = taskName;
        this.dateString = dateString;
    }


    public char getType(){
        return 'E';
    }

    @Override
    public String getDateForSaving(){
        if (this.date == null){
            return String.format("%c\t%c\t%s\t%s\n",getType(),getDone(),getTaskName(),this.dateString);
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return String.format("%c\t%c\t%s\t%s\n",getType(),getDone(),getTaskName(),this.date.format(format));
        }
    }

    @Override
    public String toString(){
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

        return String.format("[%c][%c] %s (at: %s)",this.getType(),this.done,this.taskName,this.date.format(formatted));
    }

    @Override
    public String getDate(){
        if (this.date == null) {
            return this.dateString;
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return this.date.format(format);
        }
    }
}
