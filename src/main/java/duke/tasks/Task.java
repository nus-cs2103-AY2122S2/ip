package duke.tasks;

import java.time.LocalDateTime;

public class Task{
    protected String taskName;
    protected char done = ' ';
    protected LocalDateTime date;

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
    public LocalDateTime getDate(){
        return this.date;
    }

    public String toString(){
        String s = String.format("[%c][%c] %s",this.getType(),this.done,this.taskName);
        return s;
    }

    public char getType() {
        return ' ';
    }
}
