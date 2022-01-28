package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Todo extends Task{

    public Todo(String taskName){
        this.taskName = taskName;
    }

    public char getType(){
        return 'T';
    }

    @Override
    public String getDateForSaving(){
        return String.format("%c\t%c\t%s\t%s\n",getType(),getDone(),getTaskName(),"None");
    }

    @Override
    public LocalDateTime getDateObj(){
        return null;
    }

    @Override
    public String getDate(){
        return "";
    }
}