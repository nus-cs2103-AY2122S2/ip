package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public abstract class Task{
    protected String taskName;
    protected char done = ' ';


    public Task(){}

    public void markDone(){
        this.done = 'X';
    }
    public void markUndone(){
        this.done = ' ';
    }

    public char getDone(){
        return this.done;
    }
    public String getTaskName(){
        return this.taskName;
    }
    public abstract String getDate();
    public abstract LocalDateTime getDateObj();
    public abstract String getDateForSaving();

    public String toString(){
        String s = String.format("[%c][%c] %s",this.getType(),this.done,this.taskName);
        return s;
    }

    public char getType() {
        return ' ';
    }
}

