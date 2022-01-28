package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    private LocalDateTime date;
    private String dateString = "";

    public Deadline(String taskName, LocalDateTime date){
        this.taskName = taskName;
        this.date = date;
    }

    public Deadline(String taskName, String dateString){
        this.taskName = taskName;
        this.dateString = dateString;
    }

    public char getType(){
        return 'D';
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
        if (this.date == null){
            return String.format("[%c][%c] %s (by: %s)",
                    this.getType(),this.done,this.taskName,this.dateString);
        } else {
            DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

            return String.format("[%c][%c] %s (by: %s)",
                    this.getType(),this.done,this.taskName,this.date.format(formatted));
        }
    }

    @Override
    public LocalDateTime getDateObj(){
        return this.date;
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

    @Override
    public boolean equals(Object o){
        if (! (o instanceof Deadline)){
            return false;
        }

        @SuppressWarnings("Unchecked")
        Deadline deadline = (Deadline) o;

        if (deadline.taskName.equals(this.taskName)){
            if (deadline.getDate().equals(this.getDate())){
                return true;
            }
        }

        return false;
    }
}
